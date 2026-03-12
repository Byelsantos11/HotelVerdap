package Service
import model.Cliente
import model.Evento

//Classe de eventos
class EventoService {

    val listaEventos = mutableListOf<Evento>()

    fun cadastroEvento(cliente: Cliente) {

        println("---Eventos Atuais---")
        println("1 - Evento Verde 20/08/2026")
        println("2 - Evento Luxo 10/09/2026")                   //Menu de eventos
        println("3 - Evento Praia 11/10/2026")

        val escolhaEvento = readln().toIntOrNull()

        when (escolhaEvento) {

            1 -> cadastrarNoEvento(
                nomeEvento = "Evento Verde",
                cliente = cliente,                          //Evento Verde
                capacidadeEvento = 100,
                auditorio = "Auditório Verde"
            )

            2 -> cadastrarNoEvento(
                nomeEvento = "Evento Luxo",
                cliente = cliente,                          //Evento Luxo
                capacidadeEvento = 200,
                auditorio = "Auditório Verdap"
            )

            3 -> cadastrarNoEvento(
                nomeEvento = "Evento Praia",
                cliente = cliente,                            //Evento Praia
                capacidadeEvento = 90,
                auditorio = "Auditório Praiano"
            )

            else -> println("Evento inválido")
        }
    }


    fun cadastrarNoEvento(
        nomeEvento: String,
        cliente: Cliente,                               //Parametros
        capacidadeEvento: Int,
        auditorio: String
    ) {

        println("Quantos acompanhantes deseja?")
        val acompanhantes = readln().toIntOrNull() ?: 0         //Perguntas de aocmpanhantes

        val totalPessoas = acompanhantes + 1

        if (totalPessoas > capacidadeEvento) {
            println("Limite de pessoas no evento excedido. \n Vagas restantes: ${capacidadeEvento}")        //Verificação de capacidade
            return
        }

        val novoEvento = Evento(
            nomeEvento = nomeEvento,
            capacidadeRestante = capacidadeEvento - totalPessoas,
            auditorio = auditorio,                                         //Criando o evento com os parametros
            participantes = mutableListOf(cliente)
        )

        listaEventos.add(novoEvento)                                    //Adicionanar na lista de eventos

        println("Cliente ${cliente.nomeCliente} adicionado ao $nomeEvento")
        println("Acompanhantes: $acompanhantes")
        println("Capacidade restante: ${capacidadeEvento - totalPessoas}")
    }
}