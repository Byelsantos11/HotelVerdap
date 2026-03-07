package Service
import model.Cliente

//Classe ReservaService
class ReservaService {

    fun reservarQuarto(cliente: Cliente){

        println("Menu Reserva")
        println("1 - Reservar")                 ////Menu visual para usuário
        println("2 - Ver minhas reservas")
        println("3 - Voltar")

        val escolha = readln().toIntOrNull()

        when(escolha){

            1 -> {
                println("Quarto reservado com sucesso!")       //Chamada da função reservar
                reservarQuarto(cliente)
            }

            2 -> {
                println("Aqui apareceriam suas reservas.")
                reservarQuarto(cliente)
            }

            3 -> {
                val menu = InicioService()
                menu.menu(cliente)
            }

            else -> {
                println("Opção inválida")        //Opção de erro, caso não seja válida a escolha do usuário
                reservarQuarto(cliente)
            }
        }
    }
}