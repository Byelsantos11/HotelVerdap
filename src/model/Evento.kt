package model
data class Evento (
    val nomeEvento: String,
    val localidade: String,
    val descricao: String,
    val capacidade: Int,
    val horario: Int
)