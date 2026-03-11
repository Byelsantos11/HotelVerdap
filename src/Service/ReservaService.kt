package Service
import model.Cliente

class ReservaService {

    fun reservarQuarto(cliente: Cliente) {

        println("Menu Reserva")
        println("1 - Reservar")                                 //Menu Reservas
        println("2 - Ver minhas reservas")
        println("3 - Voltar")

        val escolha = readln().toIntOrNull()

        when (escolha) {

            1 -> {
                reservar()          //Chama a função reservar
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
                println("Opção inválida")
                reservarQuarto(cliente)
            }
        }
    }

    fun reservar() {

        println("Quartos para reserva:")
        println("1 - Quarto Flores")
        println("2 - Quarto B")                                 //Menu de escolha de quarto
        println("3 - Quarto C")
        println("4 - Quarto D")

        val escolha = readln().toIntOrNull()

        when (escolha) {

            1 -> {
                quartoFlores()      //Chama a função quartoFlores
            }

            2 -> {
                println("Quarto B ainda não implementado.")
            }

            3 -> {
                println("Quarto C ainda não implementado.")
            }

            4 -> {
                println("Quarto D ainda não implementado.")
            }

            else -> {
                println("Opção inválida.")
                reservar()
            }
        }
    }
    fun quartoFlores() {        //Função Quarto Flores

        val listaReservistas = mutableListOf<String>()      //Lista de reservistas
        val diaria = 100                                    //Valor diaria
        var valorTotal = 0

        println("--- Quarto Flores ---")
        println("--Diária por pessoa: R$100--")
        println("Menores de 6 anos não pagam")              //Menu inicial informativo
        println("Maiores de 62 anos pagam metade")

        println("Digite a quantidade de reservas:")                        //Quantidade de reservas
        val quantidadeReserva = readln().toIntOrNull() ?: 0

        for (i in 1..quantidadeReserva) {

            println("Escreva o nome do reservista:")                    //Laço de repetição para recuperação de nome e idade
            val nomeReservista = readln()

            println("Digite a idade do reservista:")
            val idade = readln().toIntOrNull() ?: 0

            listaReservistas.add(nomeReservista)

            if (idade < 6) {
                println("$nomeReservista não paga.")
                                                                        //Verificação de idade
            } else if (idade > 62) {
                println("$nomeReservista paga meia.")
                valorTotal += diaria / 2

            } else {
                println("$nomeReservista paga diária normal.")
                valorTotal += diaria
            }
        }

        println("\nReservistas cadastrados:")

        for (nome in listaReservistas) {
            println(nome)                           //Lista de reservistas
        }

        println("\nValor total da reserva: R$$valorTotal")  //Mostrar valor das reservas
    }


}