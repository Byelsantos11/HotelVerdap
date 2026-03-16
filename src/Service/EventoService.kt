package Service
import model.Cliente
import model.Evento

/**
 * Classe de serviço para gerenciar operações relacionadas a eventos para um cliente.
 * Isso inclui inscrever-se em eventos, adicionar convidados e visualizar eventos inscritos.
 */
class EventoService{

    /**
     * Armazena uma lista de eventos nos quais o cliente se inscreveu.
     * Cada entrada é um Par contendo o objeto [Evento] e uma lista de nomes de convidados.
     */
    val meusEventos = mutableListOf<Pair<Evento, List<String>>>()

    /**
     * Exibe o menu principal para participação em eventos.
     * Permite ao cliente escolher um evento, visualizar seus eventos inscritos ou sair.
     *
     * @param cliente O cliente que participará dos eventos.
     */
    fun eventoParticipar(cliente: Cliente){

        println("---Eventos HotelVerdap---")
        println("Selecione o evento que gostaria de participar:")
        println("1 - Copa do mundo")
        println("2 - Pascoa")
        println("3 - Natal")
        println("4 - Ano novo")
        println("5 - Ver meus Eventos")
        println("6 - Sair")

        val escolha = readln().toIntOrNull()

        when(escolha){

            1 -> {
                criarEvento(cliente,"Copa do Mundo","Verdap1",
                    "Auditório verdap1. Capacidade de 100 pessoas.",100,48)
            }

            2 -> {
                criarEvento(cliente,"Pascoa","Verdap2",
                    "Salão de Festas. Capacidade de 200 pessoas.",200,5)
            }

            3 -> {
                criarEvento(cliente,"Natal","Restaurante",
                    "Ceia de Natal. Capacidade de 150 pessoas.",150,6)
            }

            4 -> {
                criarEvento(cliente,"Ano novo","Piscina",
                    "Festa da Virada. Capacidade de 300 pessoas.",300,8)
            }

            5 -> {
                visualizar()
                eventoParticipar(cliente)
            }

            6 -> {
                sair(cliente)
            }

            else -> {
                println("Opção inválida")
                eventoParticipar(cliente)
            }
        }
    }
    
    /**
     * Cria um novo evento com os detalhes especificados e solicita que o cliente participe.
     * Após exibir os detalhes do evento, chama [participarEvento] para lidar com o processo de inscrição.
     *
     * @param cliente O cliente que está se inscrevendo no evento.
     * @param nomeEvento O nome do evento.
     * @param localEvento O local do evento.
     * @param descricao Uma descrição do evento.
     * @param capacidade O número máximo de participantes para o evento.
     * @param duracaoHoras A duração do evento em horas.
     */
    fun criarEvento(
        cliente: Cliente,
        nomeEvento: String,
        localEvento: String,
        descricao: String,
        capacidade: Int,
        duracaoHoras: Int
    ){

        val evento = Evento(nomeEvento, localEvento, descricao, capacidade, duracaoHoras)

        println("\n--- Detalhes do Evento ---")
        println("Nome: ${evento.nomeEvento}")
        println("Local: ${evento.localEvento}")
        println("Descrição: ${evento.descricao}")
        println("Duração: ${evento.duracaoHoras}h")

        if (participarEvento(evento)) {
            println("Inscrição no evento realizada com sucesso!")
        }
        eventoParticipar(cliente)
    }

    /**
     * Lida com o processo de um cliente ingressar em um evento e registrar convidados.
     * Verifica os limites de capacidade e coleta os nomes dos convidados.
     *
     * @param evento O evento para participar.
     * @return `true` se a inscrição foi bem-sucedida, `false` se o usuário cancelou.
     */
    fun participarEvento(evento: Evento): Boolean {

        println("Quantas pessoas irão acompanhar você? (Máximo ${evento.capacidade - 1})")
        println("Ou digite -1 para cancelar.")

        val acompanhantes = readln().toIntOrNull() ?: 0

        if (acompanhantes == -1) {
            return false
        }

        if (acompanhantes + 1 > evento.capacidade) {

            println("Desculpe, a capacidade máxima foi excedida.")
            return participarEvento(evento)

        } else {

            val nomesAcompanhantes = mutableListOf<String>()

            for (i in 1..acompanhantes) {
                println("Digite o nome do acompanhante $i:")
                val nome = readln()
                nomesAcompanhantes.add(nome)
            }

            //salva evento + acompanhantes
            meusEventos.add(Pair(evento, nomesAcompanhantes))
            return true
        }
    }

    /**
     * Exibe todos os eventos nos quais o cliente está atualmente inscrito, incluindo listas de convidados.
     * Se o cliente não estiver inscrito em nenhum evento, exibe uma mensagem correspondente.
     */
    fun visualizar(){

        if(meusEventos.isEmpty()){
            println("\nVocê ainda não está inscrito em nenhum evento.")
            return
        }

        println("\n--- Meus Eventos ---")

        for((evento, acompanhantes) in meusEventos){

            println("\nEvento: ${evento.nomeEvento}")
            println("Local: ${evento.localEvento}")
            println("Descrição: ${evento.descricao}")
            println("Duração: ${evento.duracaoHoras}h")

            if(acompanhantes.isEmpty()){
                println("Sem acompanhantes")
            } else{
                println("Acompanhantes:")
                for(nome in acompanhantes){
                    println("- $nome")
                }
            }
        }
    }
    
    /**
     * Sai do menu de eventos e retorna o cliente ao menu principal do aplicativo.
     *
     * @param cliente O cliente atual.
     */
    fun sair(cliente: Cliente){
        val menu = InicioService()
        menu.menu(cliente)
    }
}