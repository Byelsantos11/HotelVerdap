package model

data class Evento(
    val nomeEvento: String,
    var capacidadeRestante: Int,
    val auditorio: String,
    val participantes: MutableList<Cliente>
)