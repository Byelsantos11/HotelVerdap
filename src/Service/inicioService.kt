package Service
import model.Cliente

//Classe Menu incial
class InicioService {

    // Funcionalidade de menu
    fun menu(cliente: Cliente) {

        val sairService = SairService()                 //Chamada das classes
        val reservaService = ReservaService()           //Chamada das classes

        println("Bem vindo ao HotelVerdao ${cliente.nomeCliente}!")
        println("Escolha sua operação desejada")
        println("1 - Sair")                                 //Menu visual para usuário
        println("2 - Reservar Quarto")

        val escolha = readln().toIntOrNull()

        when (escolha) {
            1 -> sairService.sair(cliente)      //Chamada da função sair
            2 -> reservaService.reservarQuarto(cliente) //Chamada do método reservarQuarto da classe ReservaService
            else -> {
                println("Opção inválida")       //Opção de erro, caso não seja válida a escolha do usuário
                menu(cliente)
            }
        }
    }
}