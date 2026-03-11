package model

data class Reserva (
    val nomeCliente: String,
    val quarto: String,
    val reservistas: List<String>,
    val valorTotal: Int
)