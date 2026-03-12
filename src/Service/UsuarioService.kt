package Service
import model.Cliente

class UsuarioService {

    val menu = InicioService()

    var cliente: Cliente? = null

    fun cadastroUsuario() {

        println("Digite seu nome: ")
        val nomeUsuario = readln()

        println("Digite seu cpf: ")
        val cpfUsuario = readln()

        println("Digite seu telefone: ")
        val telefoneUsuario = readln()

        println("Digite seu email: ")
        val emailUsuario = readln()

        println("Digite sua senha: ")
        val senhaUsuario = readln()

        if (
            nomeUsuario.isBlank() ||
            cpfUsuario.isBlank() ||
            telefoneUsuario.isBlank() ||
            emailUsuario.isBlank() ||
            senhaUsuario.isBlank()
        ) {
            println("Preencher todos os campos")
            return
        }

        cliente = Cliente(
            nomeUsuario,
            cpfUsuario,
            telefoneUsuario,
            emailUsuario,
            senhaUsuario
        )

        println("Cliente ${cliente!!.nomeCliente} cadastrado com sucesso!")

    }

    fun login() {

        while (true) {
            println("--Login HotelVerdap--")

            println("Digite seu email")
            val emailUsuario = readln()

            println("Digite sua senha")
            val senhaUsuario = readln()

            if (
                cliente != null &&
                emailUsuario == cliente!!.emailCliente &&
                senhaUsuario == cliente!!.senhaCliente
            ) {

                println("Login realizado com sucesso!")
                menu.menu(cliente!!)
                break

            } else {
                println("Email ou senha incorretos. Tente novamente.\n")
            }
        }
    }
}