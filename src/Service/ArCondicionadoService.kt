package Service

import model.Cliente
import model.ArCondicionado

/**
 * Serviço responsável pelo gerenciamento de solicitações de ar-condicionado.
 *
 * Esta classe permite ao cliente solicitar instalação de aparelhos,
 * visualizar suas solicitações e gerenciar o fluxo de navegação
 * dentro do sistema.
 */
class ArCondicionadoService {

    /**
     * Serviço inicial para navegação entre menus.
     */
    val inicioService = InicioService()

    /**
     * Lista que armazena todos os pedidos de ar-condicionado realizados.
     */
    val pedidos = mutableListOf<ArCondicionado>()

    /**
     * Exibe o menu principal de solicitações de ar-condicionado.
     *
     * Permite ao cliente escolher entre empresas parceiras,
     * visualizar pedidos já realizados ou retornar ao menu anterior.
     *
     * @param cliente O cliente que está utilizando o sistema.
     */
    fun menuArCondicionado(cliente: Cliente){

        println("---Menu solicitação de Ar Condicionado--- \n")
        println("Fazer solicitação para a empresas parceira:")
        println("1 - AresMovida: R$: 300,00 por produto.")
        println("2 - BemAr  R$: 430,00 por produto mais instalação.")
        println("3 - Minhas solicitações")
        println("4 - Voltar")

        val escolha = readln().toIntOrNull()

        when(escolha){

            1 -> {
                empresa("AresMovida", 300.0, 50.0, cliente)
            }

            2 -> {
                empresa("BemAr", 430.0, 80.0, cliente)
            }

            3 -> {
                verPedidos(cliente)
            }

            4 -> {
                sair(cliente)
            }

            else -> {
                println("Opção inválida")
                menuArCondicionado(cliente)
            }
        }
    }

    /**
     * Realiza a solicitação de ar-condicionado para uma empresa específica.
     *
     * Solicita a quantidade de aparelhos desejada, calcula o valor total
     * aplicando desconto caso a quantidade seja maior ou igual a 2 unidades.
     * Em seguida, cria o pedido e o armazena na lista de pedidos.
     *
     * @param nomeAparelho Nome da empresa fornecedora.
     * @param valorAparelho Valor unitário do aparelho.
     * @param desconto Valor de desconto aplicado em compras acima de 2 unidades.
     * @param cliente Cliente que está realizando a solicitação.
     */
    fun empresa(
        nomeAparelho: String,
        valorAparelho: Double,
        desconto: Double,
        cliente: Cliente
    ) {

        println("Digite a quantidade de aparelhos:")
        val quantidadeAparelho = readln().toIntOrNull()

        // Validação da entrada
        if (quantidadeAparelho == null || quantidadeAparelho <= 0) {
            println("Quantidade inválida!")
            menuArCondicionado(cliente)
            return
        }

        val valorTotal: Double

        if (quantidadeAparelho >= 2) {
            valorTotal = (quantidadeAparelho * valorAparelho) - desconto
            println("Desconto aplicado de R$ $desconto!")
        } else {
            valorTotal = quantidadeAparelho * valorAparelho
        }

        val produto = ArCondicionado(
            nomeAparelho,
            valorAparelho,
            quantidadeAparelho,
            desconto,
            cliente
        )

        pedidos.add(produto)

        println("\n---Resumo da compra---")
        println("Empresa: $nomeAparelho")
        println("Quantidade: $quantidadeAparelho")
        println("Valor total: R$ $valorTotal")
        println("\nPedido salvo com sucesso!")

        menuArCondicionado(cliente)
    }

    /**
     * Exibe todos os pedidos realizados pelo cliente.
     *
     * Caso não existam pedidos, informa ao usuário.
     * Caso existam, lista os detalhes de cada solicitação.
     *
     * @param cliente Cliente que deseja visualizar seus pedidos.
     */
    fun verPedidos(cliente: Cliente) {

        if (pedidos.isEmpty()) {

            println("Você não possui pedidos.")

        } else {

            println("\n---Seus pedidos---")

            for (pedido in pedidos) {

                println("Empresa: ${pedido.nomeEmpresa}")
                println("Quantidade: ${pedido.quantidadeAparelho}")
                println("Valor do aparelho: ${pedido.valorAparelho}")
                println("Desconto: ${pedido.desconto}")
                println("-----------------------")
            }
        }

        menuArCondicionado(cliente)
    }

    /**
     * Retorna o cliente ao menu principal do sistema.
     *
     * @param cliente Cliente que está navegando no sistema.
     */
    fun sair(cliente: Cliente){
        inicioService.menu(cliente)
    }
}