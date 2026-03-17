package model
import model.TipoGasolina

data class Abastecimento(
    val tipoGasolina: TipoGasolina,
    val valorLitro: Double
)

