package Menu;
import Entities.Usuario;
import Exceptions.MailDuplicadoException;
import Services.*;
import Enums.*;

import java.time.LocalDateTime;
import java.util.Scanner;

public class MenuCRUDUsuarios {
    public static void menu(Usuario usuarioActivo, UsuarioService ususervice, Scanner scanner) throws MailDuplicadoException {
        System.out.println("\n====== MENU USUARIOS ======");

        int opcion;

        do {
            System.out.println("\n1. Listar\n2. Crear\n3. Editar\n4. Eliminar\nSeleccione: ");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:

                    ususervice.listarUsuarios();
                    return;

                case 2:

                    System.out.println("Ingrese el id del nuevo usuario: ");
                    Long id_newUsuario = scanner.nextLong();
                    scanner.nextLine();

                    System.out.println("Ingrese el nombre: ");
                    String nombre_new = scanner.nextLine();

                    System.out.println("Ingrese el apellido: ");
                    String apellido_new = scanner.nextLine();

                    System.out.println("Ingrese el mail: ");
                    String mail_new = scanner.nextLine();

                    System.out.println("Ingrese el celular: ");
                    String celular_new = scanner.nextLine();

                    System.out.println("Ingrese la contraseña: ");
                    String contrasenia_new = scanner.nextLine();

                    System.out.println("Ingrese el rol (1 - ADMIN - 2 USUARIO): ");
                    int codigoRol_new = scanner.nextInt();
                    scanner.nextLine();

                    Rol rol_new = Rol.fromCodigo(codigoRol_new);

                    Usuario nuevoUsuario = new Usuario(id_newUsuario, false, LocalDateTime.now(), nombre_new, apellido_new, mail_new, celular_new, contrasenia_new, rol_new);

                    ususervice.crearUsuario(nuevoUsuario);

                    return;

                case 3:

                    System.out.println("Ingrese el id del usuario a editar: ");
                    Long id_edUsuario = scanner.nextLong();
                    scanner.nextLine();

                    System.out.println("Ingrese el nuevo nombre: ");
                    String nombre_ed = scanner.nextLine();

                    System.out.println("Ingrese el nuevo apellido: ");
                    String apellido_ed = scanner.nextLine();

                    System.out.println("Ingrese el nuevo mail: ");
                    String mail_ed = scanner.nextLine();

                    System.out.println("Ingrese el nuevo celular: ");
                    String celular_ed = scanner.nextLine();

                    System.out.println("Ingrese la nueva contraseña: ");
                    String contrasenia_ed = scanner.nextLine();

                    boolean editado = ususervice.editarUsuario(id_edUsuario, nombre_ed, apellido_ed, mail_ed, celular_ed, contrasenia_ed);

                    if (editado) {
                        System.out.println("Usuario editado correctamente.");
                    } else {
                        System.out.println("No se encontró ningún usuario con ese id.");
                    }

                    return;

                case 4:

                    System.out.println("Ingrese el id del usuario a eliminar: ");
                    Long id_delUsuario = scanner.nextLong();
                    scanner.nextLine();

                    boolean eliminado = ususervice.eliminarUsuario(id_delUsuario);

                    if (eliminado) {
                        System.out.println("Usuario eliminado correctamente.");
                    } else {
                        System.out.println("No se encontró ningún usuario con ese id.");
                    }

                    return;

                default:

                    System.out.println("\nPor favor, ingrese una instrucción válida.");

            }
        } while (opcion < 0 || opcion > 4);
    }
}
