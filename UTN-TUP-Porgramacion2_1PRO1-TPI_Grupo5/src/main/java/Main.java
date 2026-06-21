import Enums.*;
import Exceptions.*;
import Entities.*;
import Menu.*;
import Services.*;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws MailDuplicadoException, StockInvalidoException, EntidadNoEncontradaException {

        Scanner scanner = new Scanner(System.in);
        PedidoService pservice = new PedidoService();
        ProductoService prodservice = new ProductoService();
        CategoriaService catservice = new CategoriaService();
        UsuarioService ususervice = new UsuarioService();

        int opcionSistema;

        System.out.println("======== BIENVENIDO AL SISTEMA DE FOOD STORE ========");

        do {

            System.out.println("=== SISTEMA DE PEDIDOS (FOOD STORE) ===");

            System.out.println("\n---- Seleccione ----");

            System.out.println("1. Categorías\n2. Productos\n3. Usuarios\n4.Pedidos\n0.Salir");

            System.out.println("\nIngrese el número correspondiente a la acción que desee ejecutar: ");

            opcionSistema = scanner.nextInt();

            switch (opcionSistema) {

                case 1:

                    Usuario usuarioCategorias = UsuarioLog.usuarioLog(ususervice, scanner);

                    if (usuarioCategorias != null) {
                        MenuCRUDCategorias.menu(usuarioCategorias, catservice, scanner);
                    } else {
                        System.out.println("\nLogin inválido, volviendo al menú principal...");
                    }

                    break;

                    case 2:

                        Usuario usuarioProductos = UsuarioLog.usuarioLog(ususervice, scanner);

                        if (usuarioProductos != null) {
                            MenuCRUDProductos.menu(usuarioProductos, prodservice, catservice, scanner);
                        } else {
                            System.out.println("\nLogin inválido, volviendo al menú principal.");
                        }

                        break;

                    case 3:

                        Usuario usuarioUsuarios = UsuarioLog.usuarioLog(ususervice, scanner);

                        if (usuarioUsuarios != null) {
                            MenuCRUDUsuarios.menu(usuarioUsuarios, ususervice, scanner);
                        } else {
                            System.out.println("\nLogin invalido, volviendo al menu principal.");
                        }

                        break;

                    case 4:

                        Usuario usuarioPedidos = UsuarioLog.usuarioLog(ususervice, scanner);

                        if (usuarioPedidos != null) {
                            MenuCRUDPedidos.menu(usuarioPedidos, pservice, scanner);
                        } else {
                            System.out.println("\nLogin invalido, volviendo al menu principal");
                        }

                        break;

                    case 0:

                        System.out.println("\nGracias por usar el sistema. ¡Vuelva pronto!");

                        break;

                    default:

                        System.out.println("\nPor favor, ingrese una instrucción válida.");

                }

            } while (opcionSistema != 0);

        }

    }