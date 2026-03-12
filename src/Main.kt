import Service.UsuarioService

fun main() {

    val usuarioService = UsuarioService()

    println("=== Sistema HotelVerdao ===")

    usuarioService.cadastroUsuario()

    usuarioService.login()

}