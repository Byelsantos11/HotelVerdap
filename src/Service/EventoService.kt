package Service

import model.Cliente
import model.Evento

/**
 * Serviço responsável pelo gerenciamento de eventos do hotel.
 *
 * Esta classe controla o fluxo de participação dos clientes em eventos,
 * permitindo visualizar eventos disponíveis, realizar inscrição com
 * acompanhantes e consultar eventos em que o cliente está inscrito.
 */
class EventoService {

    /**
     * Lista de eventos disponíveis no hotel.
     */
    val listaEventos = mutableListOf<Evento>()

    /**
     * Mapa que guarda os participantes de cada evento.
     * Evento -> Lista de nomes (cliente + acompanhantes)
     */
    val participantesEvento = mutableMapOf<Evento, MutableList<String>>()

    /**
     * Exibe o menu principal de eventos.
     *
     * Permite ao cliente escolher um evento para participar,
     * visualizar eventos em que está inscrito ou voltar ao menu anterior.
     *
     * @param cliente Cliente que está utilizando o sistema.
     */
    fun cadastroEvento(cliente: Cliente) {

        // Cria eventos apenas uma vez
        if (listaEventos.isEmpty()) {

            listaEventos.add(
                Evento("Pascoa", "Sala eventos 1", "Sala temática", 100, 14)
            )

            listaEventos.add(
                Evento("Natal", "Salão Principal", "Ceia de natal", 150, 20)
            )

            listaEventos.add(
                Evento("Ano novo", "Área da Piscina", "Festa da virada", 200, 22)
            )
        }

        println("\n--- Eventos HotelVerdap ---")
        println("1 - Evento Páscoa")
        println("2 - Evento Natal")
        println("3 - Evento Ano Novo")
        println("4 - Meus eventos")
        println("5 - Voltar")

        val escolha = readln().toIntOrNull()

        when (escolha) {

            1 -> participarEvento(cliente, listaEventos[0])

            2 -> participarEvento(cliente, listaEventos[1])

            3 -> participarEvento(cliente, listaEventos[2])

            4 -> visualizarEventos(cliente)

            5 -> sair(cliente)

            else -> {
                println("Opção inválida")
                cadastroEvento(cliente)
            }
        }
    }

    /**
     * Realiza a inscrição do cliente em um evento específico.
     *
     * Permite adicionar acompanhantes e registra todos os participantes
     * na lista associada ao evento.
     *
     * @param cliente Cliente que está se inscrevendo.
     * @param evento Evento selecionado.
     */
    fun participarEvento(cliente: Cliente, evento: Evento) {

        println("\n--- Evento Selecionado ---")
        println("Evento: ${evento.nomeEvento}")
        println("Local: ${evento.localidade}")
        println("Horário: ${evento.horario}h")

        println("Quantos acompanhantes irão com você?")
        val quantidade = readln().toIntOrNull() ?: 0

        val nomesAcompanhantes = mutableListOf<String>()

        for (i in 1..quantidade) {

            println("Digite o nome do acompanhante $i:")
            val nome = readln()

            nomesAcompanhantes.add(nome)
        }

        // Recupera ou cria lista de participantes do evento
        val lista = participantesEvento.getOrPut(evento) { mutableListOf() }

        lista.add(cliente.nomeCliente)
        lista.addAll(nomesAcompanhantes)

        println("\nInscrição realizada com sucesso!")

        cadastroEvento(cliente)
    }

    /**
     * Exibe os eventos em que o cliente está inscrito.
     *
     * Lista o evento e os acompanhantes associados ao cliente.
     *
     * @param cliente Cliente que deseja visualizar seus eventos.
     */
    fun visualizarEventos(cliente: Cliente) {

        println("\n--- Meus Eventos ---")

        var encontrouEvento = false

        for ((evento, pessoas) in participantesEvento) {

            if (pessoas.contains(cliente.nomeCliente)) {

                encontrouEvento = true

                println("\nEvento: ${evento.nomeEvento}")
                println("Local: ${evento.localidade}")
                println("Horário: ${evento.horario}h")

                println("Acompanhantes:")

                for (nome in pessoas) {

                    if (nome != cliente.nomeCliente) {

                        println("- $nome")
                    }
                }
            }
        }

        if (!encontrouEvento) {

            println("Você ainda não está inscrito em nenhum evento.")
        }

        cadastroEvento(cliente)
    }

    /**
     * Retorna ao menu inicial do sistema.
     *
     * @param cliente Cliente que está utilizando o sistema.
     */
    fun sair(cliente: Cliente) {

        val inicio = InicioService()
        inicio.menu(cliente)
    }
}