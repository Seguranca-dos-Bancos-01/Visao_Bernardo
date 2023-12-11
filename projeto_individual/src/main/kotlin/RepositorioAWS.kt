import org.apache.commons.dbcp2.BasicDataSource
import org.springframework.jdbc.core.JdbcTemplate

class RepositorioAWS {


    var jdbctemplate: JdbcTemplate? = null
        get() {
            if (field == null) {

                val dataSource = BasicDataSource()
                dataSource.driverClassName = "com.microsoft.sqlserver.jdbc.SQLServerDriver"
                val serverName = "34.206.192.7"
                val dataBase = "SecurityBank"
                dataSource.url =
                    "jdbc:sqlserver://$serverName;database=$dataBase;encrypt=true;trustServerCertificate=true"
                dataSource.username = "sa"
                dataSource.password = "UrubuDoGit123"
                val jdbcTemplate = JdbcTemplate(dataSource)
                field = jdbcTemplate
            }
            return field
        }


    fun valServ(email: String, senha: String): Int {
        var idBanco = jdbctemplate!!.queryForObject(
            """
           SELECT fkBanco FROM funcionarios WHERE email = '$email' and senha = '$senha' 
        """, Int::class.java
        )
        return idBanco
    }

    fun descServ(fkBanco: Int): Int {
        val servidor = jdbctemplate!!.queryForObject(
            """
 SELECT TOP 1 idServidor
FROM servidor
WHERE fkBanco = $fkBanco
ORDER BY idServidor ASC;
          
        """, Int::class.java
        )

        return servidor
    }

    fun verExist(servidorId: Int): Int {
        val qtd = jdbctemplate!!.queryForObject(
            """
            SELECT COUNT(*) AS count FROM particao WHERE fkServidor = '$servidorId'
        """, Int::class.java
        )
        return qtd
    }

    fun verNomBanco(idBanco: Int): String {
        val nomeBanco = jdbctemplate!!.queryForObject(
            """
            select nomeFantasia from banco where idBanco = $idBanco;
        """, String::class.java
        )
        return nomeBanco
    }

    fun verNomServ(servidorId: Int): String {
        val nomeServer = jdbctemplate!!.queryForObject(
            """
            select apelido from servidor where idServidor = $servidorId;
        """, String::class.java
        )
        return nomeServer
    }


}