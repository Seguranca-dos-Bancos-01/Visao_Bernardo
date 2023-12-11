import org.springframework.jdbc.core.JdbcTemplate
import org.apache.commons.dbcp2.BasicDataSource

class Repositorio {
     var jdbctemplate: JdbcTemplate


    init {
        val dataSource = BasicDataSource()
        dataSource.driverClassName = "com.mysql.cj.jdbc.Driver"
        dataSource.url = "jdbc:mysql://localhost/SecurityBank"
        dataSource.username = "root"
        dataSource.password = "Brol2005"
        jdbctemplate = JdbcTemplate(dataSource)


    }

    fun valServ(email: String, senha: String): Int{
        var idBanco = jdbctemplate.queryForObject("""
           SELECT fkBanco FROM funcionarios WHERE email = '$email' and senha = '$senha' 
        """,Int::class.java)
        return idBanco
    }

    fun descServ(fkBanco: Int): Int{
        val servidor = jdbctemplate.queryForObject("""
       SELECT idServidor
FROM servidor
WHERE fkBanco = ${fkBanco}
ORDER BY idServidor ASC
LIMIT 1;

        """,Int::class.java)

        return servidor
    }

    fun verExist(servidorId: Int): Int{
        val qtd = jdbctemplate.queryForObject("""
            SELECT COUNT(*) AS count FROM particao WHERE fkServidor = '$servidorId'
        """,Int::class.java)
        return qtd
    }
    fun verNomBanco(idBanco: Int): String{
        val nomeBanco = jdbctemplate.queryForObject("""
            select nomeFantasia from banco where idBanco = $idBanco;
        """,String::class.java)
        return nomeBanco
    }
    fun verNomServ(servidorId: Int):String{
        val nomeServer = jdbctemplate.queryForObject("""
            select apelido from servidor where idServidor = $servidorId;
        """,String::class.java)
        return nomeServer
    }



}
