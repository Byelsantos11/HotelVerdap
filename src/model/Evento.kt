package model

data class Evento (
    val nomeEvento: String,
    val localEvento: String,
    val descricao: String,
    val capacidade: Int,
    val duracaoHoras: Int,
)