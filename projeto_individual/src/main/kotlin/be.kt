import com.github.britooo.looca.api.core.Looca
import java.util.Scanner


    fun main() {

            val repositorio = Repositorio()
            val repositorioAWS = RepositorioAWS()

            var looca = Looca()


            val scanner = Scanner(System.`in`)
            println("Insira seu email")
            val email = scanner.nextLine()
            println("Insira sua senha")
            var senha = scanner.nextLine()
            var idBanco = repositorio.valServ(email, senha)
            var idBancoAWS = repositorioAWS.valServ(email, senha)
            var nomeBanco = repositorio.verNomBanco(idBanco)
            var nomeBancoAWS = repositorioAWS.verNomBanco(idBanco)
        println(nomeBanco)
        var servidorId = repositorio.descServ(idBanco)
        var servidorIdAWS = repositorioAWS.descServ(idBanco)

            println(servidorId)
            var nomeServer = repositorio.verNomServ(servidorId)
            var nomeServerAWS = repositorioAWS.verNomServ(servidorId)
        println(nomeServer)

        while(true) {

            var infoParticao = Particoes()
            val grupoDeDiscos = looca.grupoDeDiscos
            var discos = grupoDeDiscos.discos
            var volumes = grupoDeDiscos.volumes
            var nome = discos[0].nome
            println( discos[0].nome)


            infoParticao.nome = nome


            println()
            var idPro = Looca().processador.id

            var holder = repositorio.verExist(servidorId)
            var holderAWS = repositorioAWS.verExist(servidorId)

            if (holder == 0) {



                Thread.sleep(500000)
            }



            else{
                println("A maquina de id ${idPro}, do banco ${nomeBanco}, que pertence ao sevidor${nomeServer} já está cadastrada, agora é só inciar o python para utilizar a solução")

                println("A maquina de id ${idPro}, do banco ${nomeBancoAWS}, que pertence ao sevidor${nomeServerAWS} já está cadastrada, agora é só inciar o python para utilizar a solução")
            }
            Thread.sleep(1)


        }
    }

