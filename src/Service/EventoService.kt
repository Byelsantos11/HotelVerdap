package Service
import model.Cliente
import model.Evento

/**
 * Classe de serviço para gerenciamento de eventos.
 */
class EventoService {

    val listaEventos = mutableListOf<Evento>()

    /**
     * Realiza o cadastro de um cliente em um evento disponível.
     * @param cliente O cliente a ser cadastrado.
     */
    fun cadastroEvento(cliente: Cliente) {

        println("---Eventos Atuais---")
        println("1 - Evento Verde 20/08/2026")
        println("2 - Evento Luxo 10/09/2026") // Menu de eventos
        println("3 - Evento Praia 11/10/2026")

        val escolhaEvento = readln().toIntOrNull()

        when (escolhaEvento) {

            // Evento Verde
            1 -> cadastrarNoEvento(
                nomeEvento = "Evento Verde",
                cliente = cliente,
                capacidadeEvento = 100,
                auditorio = "Auditório Verde"
            )

            // Evento Luxo
            2 -> cadastrarNoEvento(
                nomeEvento = "Evento Luxo",
                cliente = cliente,
                capacidadeEvento = 200,
                auditorio = "Auditório Verdap"
            )

            // Evento Praia
            3 -> cadastrarNoEvento(
                nomeEvento = "Evento Praia",
                cliente = cliente,
                capacidadeEvento = 90,
                auditorio = "Auditório Praiano"
            )

            else -> println("Evento inválido")
        }
    }

    /**
     * Cadastra o cliente e seus acompanhantes no evento selecionado.
     * @param nomeEvento Nome do evento.
     * @param cliente Cliente que está realizando o cadastro.
     * @param capacidadeEvento Capacidade total do evento.
     * @param auditorio Local do evento.
     */
    fun cadastrarNoEvento(
        nomeEvento: String,
        cliente: Cliente,
        capacidadeEvento: Int,
        auditorio: String
    ) {

        // Pergunta quantidade de acompanhantes
        println("Quantos acompanhantes deseja?")
        val acompanhantes = readln().toIntOrNull() ?: 0

        val totalPessoas = acompanhantes + 1

        // Verificação de capacidade
        if (totalPessoas > capacidadeEvento) {
            println("Limite de pessoas no evento excedido. \n Vagas restantes: ${capacidadeEvento}")
            return
        }

        // Criando o evento com os parametros
        val novoEvento = Evento(
            nomeEvento = nomeEvento,
            capacidadeRestante = capacidadeEvento - totalPessoas,
            auditorio = auditorio,
            participantes = mutableListOf(cliente)
        )

        // Adicionar na lista de eventos
        listaEventos.add(novoEvento)

        println("Cliente ${cliente.nomeCliente} adicionado ao $nomeEvento")
        println("Acompanhantes: $acompanhantes")
        println("Capacidade restante: ${capacidadeEvento - totalPessoas}")
    }
}