package Service

import model.Cliente
import model.TipoGasolina

class AbastecimentoService {

    fun menuAbastecimento(cliente: Cliente){

        println("---Menu de Abastecimento do HotelVerdap--- \n")
        println("--Selecionar o tipo de gasolina-- \n")
        println("1 - Gasolina: 6,00 reais por litro")
        println("2 - Etanol:   5,00 reais por litro")
        println("3 - Alcool:   4,00 reais por litro")
        println("4 - Voltar")
        val escolha = readln().toIntOrNull()


        when(escolha){
            1->{
                AbastecerCarro(cliente, TipoGasolina.GASOLINA, 6.0)
            } 2->{
                AbastecerCarro(cliente, TipoGasolina.ETANOL, 5.0)
        }     3->{
                AbastecerCarro(cliente, TipoGasolina.ALCOOL, 4.0)
        }     4->{
                 sair(cliente)
        }
        }
    }

    fun AbastecerCarro(cliente: Cliente, tipoGasolina: TipoGasolina, valorLitro: Double){
        println("Gasolinha quantidade de litros:")
        val quantidadeLitros = readln().toDoubleOrNull() ?: 0.0
        val valorTotal = quantidadeLitros * valorLitro
        println("Valor para ser pago: ${valorTotal}")
    }


    fun sair(cliente: Cliente){
        val inicio = InicioService()
        inicio.menu(cliente)
    }


}