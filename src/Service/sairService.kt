package Service

import model.Cliente
import kotlin.system.exitProcess

class SairService {

    val inicioService = InicioService()     //Instanciando a Classe inicioService

    fun sair(cliente: Cliente) {        //  Função de sair

        println("Você deseja sair? S/N")            //Confirmação de saida
        val escolha = readln().uppercase()

        when (escolha) {

            "S" ->{
                println("Saindo...")
                exitProcess(0)
            }
            "N" -> inicioService.menu(cliente)              //Ações de voltação de saida ou permanencia do usuario

            else -> {
                println("Opção inválida.")
                sair(cliente)
            }

        }

    }

}