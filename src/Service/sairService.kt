package Service

import model.Cliente
import kotlin.system.exitProcess

class SairService {

    val inicioService = InicioService()

    fun sair(cliente: Cliente) {

        println("Você deseja sair? S/N")
        val escolha = readln().uppercase()

        when (escolha) {

            "S" -> exitProcess(0)
            "N" -> inicioService.menu(cliente)

            else -> {
                println("Opção inválida.")
                sair(cliente)
            }

        }

    }

}