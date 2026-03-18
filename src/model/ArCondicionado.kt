package model

data class ArCondicionado (
    val nomeEmpresa: String,
    val valorAparelho: Double,
    val quantidadeAparelho: Int,
    val desconto: Double,
    val cliente: Cliente
)
