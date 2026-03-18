package Service
import model.ArCondicionado
import model.Cliente

//Classe Menu incial
class InicioService {

    // Funcionalidade de menu
    fun menu(cliente: Cliente) {

        val sairService = SairService()                 //Chamada das classes
        val reservaService = ReservaService()           //Chamada das classes
        val eventoService = EventoService()
        val AbastecimentoService = AbastecimentoService()
        val arCondicionadoService = ArCondicionadoService()


        println("Bem vindo ao HotelVerdao ${cliente.nomeCliente}!")
        println("Escolha sua operação desejada")
        println("1 - Sair")                                 //Menu visual para usuário
        println("2 - Reservar Quarto")
        println("3 - Eventos")
        println("4 - Abastecimento ")
        println("5 - Solicitar Ar Condicionado ")


        val escolha = readln().toIntOrNull()

        when (escolha) {
            1 -> sairService.sair(cliente)      //Chamada da função sair
            2 -> reservaService.reservarQuarto(cliente) //Chamada do método reservarQuarto da classe ReservaService
            3-> eventoService.cadastroEvento(cliente)   //Chamada do método visualizarEventos da classe EventoService
            4-> AbastecimentoService.menuAbastecimento(cliente) //Chamada do método menuAbastecimento da classe AbastecimentoService
            5-> arCondicionadoService.menuArCondicionado(cliente) //Chamada do método menuArCondicionado da classe ArCondicionadoService
            else -> {
                println("Opção inválida")       //Opção de erro, caso não seja válida a escolha do usuário
                menu(cliente)
            }
        }
    }
}