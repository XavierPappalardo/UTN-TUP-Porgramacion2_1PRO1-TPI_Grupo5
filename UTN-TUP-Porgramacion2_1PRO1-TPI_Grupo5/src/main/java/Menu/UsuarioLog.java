package Menu;

import Entities.Usuario;
import Enums.Rol;
import Exceptions.MailDuplicadoException;
import Services.UsuarioService;

import java.time.LocalDateTime;
import java.util.Scanner;

public class UsuarioLog {

    public static Usuario usuarioLog(UsuarioService ususervice, Scanner scanner) throws MailDuplicadoException {

        if (ususervice.getUsuarios().isEmpty()) {

            System.out.println("No se han encontrado usuarios registrados. ¿Desea crear uno?\n(Ingrese '1' si la respuesta es afirmativa, o '0' en caso contrario): ");

            int crearUsuario = scanner.nextInt();
            scanner.nextLine();

            if (crearUsuario == 1){

                System.out.println("Ingrese el id del nuevo usuario");
                Long idNuevoUsuario = scanner.nextLong();
                scanner.nextLine();

                System.out.println("Ingrese el nombre del nuevo usuario");
                String nombreNuevoUsuario = scanner.nextLine();

                System.out.println("Ingrese el apellido del nuevo usuario");
                String apellidoNuevoUsuario = scanner.nextLine();

                System.out.println("Ingrese el mail del nuevo usuario");
                String mailNuevoUsuario = scanner.nextLine();

                System.out.println("Ingrese el celular del nuevo usuario");
                String celularNuevoUsuario = scanner.nextLine();

                System.out.println("Ingrese la contraseña del nuevo usuario");
                String contraseniaNuevoUsuario = scanner.nextLine();

                System.out.println("Ingrese el rol del nuevo usuario (ADMIN: 1 / USUARIO: 2): ");
                int codigoRol = scanner.nextInt();
                scanner.nextLine();

                Rol rolNuevoUsuario = Rol.fromCodigo(codigoRol);

                Usuario nuevoUsuario = new Usuario(idNuevoUsuario, false, LocalDateTime.now(), nombreNuevoUsuario, apellidoNuevoUsuario, mailNuevoUsuario, celularNuevoUsuario, contraseniaNuevoUsuario, rolNuevoUsuario);

                ususervice.crearUsuario(nuevoUsuario);

                return nuevoUsuario;

            } else {

                System.out.println("Entendido.");

                return null;

            }

        } else {

            System.out.println("Ingrese su id de usuario: ");

            Long idUsuario = scanner.nextLong();
            scanner.nextLine();

            Usuario usuario = ususervice.buscarPorId(idUsuario);

            if (usuario != null) {
                return usuario;
            } else {
                System.out.println("No hay ningun usuario con ese id. Intente de nuevo:");
                return null;
            }

        }

    }

}
