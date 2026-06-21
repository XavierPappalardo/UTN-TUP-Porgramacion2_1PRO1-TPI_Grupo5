import Enums.Rol;
import Exceptions.*;
import Entities.*;
import Menu.MenuCRUDCategorias;
import Menu.MenuCRUDPedidos;
import Menu.UsuarioLog;
import Services.CategoriaService;
import Services.PedidoService;
import Services.ProductoService;
import Services.UsuarioService;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws MailDuplicadoException, StockInvalidoException, EntidadNoEncontradaException {

        Scanner scanner = new Scanner(System.in);
        PedidoService pservice = new PedidoService();
        ProductoService prodsevice = new ProductoService();
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

            Usuario usuarioActivo = new Usuario(null, false, null, null, null, null, null, null, null);

            switch (opcionSistema) {

                case 1:

                    Usuario usuarioCategorias = UsuarioLog.usuarioLog();

                    if (usuarioCategorias !=null) {
                        MenuCRUDCategorias.menu(usuarioCategorias);
                    } else {
                        System.out.println("\nLogin invalido, volviendo al menu principal...");
                    }

                    break;

                    case 2:

                        do {

                            usuarioActivo = UsuarioLog.usuarioLog();

                            break;

                        } while (UsuarioLog.usuarioLog() == null);

                        MenuCRUDPedidos.menu(usuarioActivo);

                        break;

                    case 3:


                    case 4:

                        Usuario usuarioPedidos = UsuarioLog.usuarioLog();

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