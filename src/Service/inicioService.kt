package Service
import model.Cliente

class InicioService {

    fun menu(cliente: Cliente) {

        val sairService = SairService()

        println("Bem vindo ao HotelVerdao ${cliente.nomeCliente}!")
        println("Escolha sua operação desejada")
        println("1 - Sair")

        val escolha = readln().toIntOrNull()

        when (escolha) {
            1 -> sairService.sair(cliente)
            else -> {
                println("Opção inválida")
                menu(cliente)
            }
        }
    }
}