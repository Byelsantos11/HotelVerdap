package Service

import model.Cliente
import model.Reserva

/**
 * Serviço responsável pelo gerenciamento de reservas do hotel.
 *
 * Esta classe controla o fluxo de interação com o cliente para realização de reservas,
 * visualização de reservas existentes e cálculo de valores de hospedagem com base
 * na idade dos hóspedes.
 */
class ReservaService {

    /**
     * Lista que armazena todas as reservas realizadas no sistema.
     */
    val listaReservas = mutableListOf<Reserva>()

    /**
     * Exibe o menu principal de reservas e processa a escolha do cliente.
     *
     * Permite ao cliente iniciar uma nova reserva, visualizar suas reservas atuais
     * ou retornar ao menu anterior.
     *
     * @param cliente O cliente que está acessando o menu de reservas.
     */
    fun reservarQuarto(cliente: Cliente) {

        println("Menu Reserva")
        println("1 - Reservar")
        println("2 - Ver minhas reservas")
        println("3 - Remover reserva")

        val escolha = readln().toIntOrNull()

        when (escolha) {

            1 -> reservar(cliente)

            2 -> verReserva(cliente)

            3 -> removerReserva(cliente)

            4 -> sair(cliente)

            else -> {
                println("Opção inválida")
                reservarQuarto(cliente)
            }
        }
    }

    /**
     * Exibe as reservas associadas a um cliente específico.
     *
     * Filtra a lista global de reservas pelo nome do cliente. Se houver reservas,
     * exibe os detalhes de cada uma (quarto, reservistas e valor total).
     * Caso contrário, informa que não há reservas.
     * Ao final, retorna ao menu de reservas.
     *
     * @param cliente O cliente cujas reservas devem ser exibidas.
     */
    fun verReserva(cliente: Cliente) {

        val reservaCliente = listaReservas.filter { it.nomeCliente == cliente.nomeCliente }

        if (reservaCliente.isEmpty()) {

            println("Você não possui reservas.")

        } else {

            for (reserva in reservaCliente) {

                println("Quarto: ${reserva.quarto}")
                println("Reservistas: ${reserva.reservistas}")
                println("Valor de pagamento: ${reserva.valorTotal}")
                println("-----------------------")
            }
        }

        reservarQuarto(cliente)
    }

    /**
     * Apresenta as opções de quartos disponíveis para reserva.
     *
     * Exibe um menu com os tipos de quartos. Baseado na escolha, direciona
     * para o processo de reserva específico do quarto ou informa se a opção
     * ainda não foi implementada.
     *
     * @param cliente O cliente que está realizando a reserva.
     */
    fun reservar(cliente: Cliente) {

        println("Quartos para reserva:")
        println("1 - Quarto Flores")
        println("2 - Quarto Verdap")
        println("3 - Quarto C")
        println("4 - Quarto D")

        val escolha = readln().toIntOrNull()

        when (escolha) {

            1 -> reservarQuartos(cliente, "Flores", 100)

            2 -> reservarQuartos(cliente, "Verdap", 200)

            3 -> println("Quarto C ainda não implementado.")

            4 -> println("Quarto D ainda não implementado.")

            else -> {
                println("Opção inválida.")
                reservar(cliente)
            }
        }
    }


    fun removerReserva(cliente: Cliente) {

        val reservaCliente = listaReservas.filter { it.nomeCliente == cliente.nomeCliente }

        if (reservaCliente.isEmpty()) {
            println("Você não possui reservas.")
        } else {
            reservaCliente.forEachIndexed { indice, reserva ->
                println("${indice + 1} - Quarto: ${reserva.quarto}, Reservistas: ${reserva.reservistas}, Valor: R$${reserva.valorTotal}")
            }

            println("Digite o número da reserva que deseja remover:")
            val opcao = readln().toIntOrNull()

            if (opcao != null && opcao > 0 && opcao <= reservaCliente.size) {
                val reservaParaRemover = reservaCliente[opcao - 1]
                listaReservas.remove(reservaParaRemover)
                println("Reserva removida com sucesso!")
            } else {
                println("Opção inválida.")
            }
        }

        reservarQuarto(cliente)
    }

    /**
     * Realiza o processo de reserva de um quarto específico.
     *
     * Solicita a quantidade de hóspedes e seus dados (nome e idade).
     * Calcula o valor total da reserva aplicando regras de negócio:
     * - Menores de 6 anos: isentos.
     * - Maiores de 62 anos: pagam 50% da diária.
     * - Demais idades: pagam valor integral.
     *
     * Cria o objeto [Reserva], adiciona à lista de reservas e exibe o resumo.
     * Retorna ao menu de reservas ao final.
     *
     * @param cliente O cliente titular da reserva.
     * @param nomeQuarto O nome do quarto selecionado.
     * @param diaria O valor base da diária por pessoa para o quarto selecionado.
     */
    fun reservarQuartos(cliente: Cliente, nomeQuarto: String, diaria: Int) {

        val listaReservistas = mutableListOf<String>()
        var valorTotal = 0

        println("--- Quarto $nomeQuarto ---")
        println("--Diária por pessoa: R$$diaria--")
        println("Menores de 6 anos não pagam")
        println("Maiores de 62 anos pagam metade")

        println("Digite a quantidade de reservas:")
        val quantidadeReserva = readln().toIntOrNull() ?: 0

        for (i in 1..quantidadeReserva) {

            println("Escreva o nome do reservista:")
            val nomeReservista = readln()

            println("Digite a idade do reservista:")
            val idade = readln().toIntOrNull() ?: 0

            listaReservistas.add(nomeReservista)

            if (idade < 6) {

                println("$nomeReservista não paga.")

            } else if (idade > 62) {

                println("$nomeReservista paga meia.")
                valorTotal += diaria / 2

            } else {

                println("$nomeReservista paga diária normal.")
                valorTotal += diaria
            }
        }

        val reserva = Reserva(
            nomeCliente = cliente.nomeCliente,
            quarto = nomeQuarto,
            reservistas = listaReservistas,
            valorTotal = valorTotal
        )

        listaReservas.add(reserva)

        println("\nReserva cadastrada com sucesso!")
        println("Valor total: R$$valorTotal")

        reservarQuarto(cliente)
    }

    /**
     * Redireciona o cliente de volta para o menu inicial do sistema.
     *
     * Instancia o [InicioService] e invoca o menu principal.
     *
     * @param cliente O cliente que está navegando no sistema.
     */
    fun sair(cliente: Cliente) {
        val menu = InicioService()
        menu.menu(cliente)
    }
}