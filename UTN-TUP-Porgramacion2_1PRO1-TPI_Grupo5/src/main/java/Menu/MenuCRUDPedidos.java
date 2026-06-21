package Menu;
import Entities.Categoria;
import Entities.DetallePedido;
import Entities.Pedido;
import Entities.Producto;
import Enums.*;
import Services.*;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.Scanner;

public class MenuCRUDPedidos {

    public static void menu(){

        System.out.println("\n====== MENÚ PEDIDOS ======");

        int opcion;

        do {

            System.out.println("\n1. Listar\n2. Crear\n3. Editar\n4. Eliminar\nSeleccione: ");

            Scanner scanner = new Scanner(System.in);

            PedidoService pservice = new PedidoService();

            opcion = scanner.nextInt();

            switch (opcion){

                case 1:

                    pservice.listarPedidos();

                    return;

                case 2:

                    System.out.println("Ingrese el id del nuevo pedido: ");

                    Long id_newPedido = scanner.nextLong();

                    System.out.println("Ingrese el nombre del nuevo pedido: ");

                    String nombre_Pedido = scanner.nextLine();

                    System.out.println("Ingrese la descripción del nuevo pedido: ");

                    String descripcion_Pedido = scanner.nextLine();

                    System.out.println("Ingrese el estado del pedido (1. PENDIENTE\n2. CONFIRMADO\n3. TERMINADO\n4. CANCELADO): ");

                    int est = scanner.nextInt();

                    Estado estado = Estado.fromCodigo(est);

                    System.out.println("Ingrese el total del pedido: ");

                    double total = scanner.nextDouble();

                    System.out.println("Ingrese la forma de pago del pedido (1. TARJETA\n2. TRANSFERENCIA\n3. EFECTIVO): ");

                    int fp = scanner.nextInt();

                    FormaPago formaPago = FormaPago.fromCodigo(fp);

                    int seguirAgregandoDetalles;

                    do {

                        System.out.println("¿Desea ingresar un nuevo detalle? (Ingrese 0. Para NO, o 1. Para SI): ");

                        seguirAgregandoDetalles = scanner.nextInt();

                        if (seguirAgregandoDetalles == 1){

                            System.out.println("Ingrese el nombre del producto: ");

                            String nombreProducto = scanner.nextLine();

                            System.out.println("Ingrese el precio del producto: ");

                            double precioProducto = scanner.nextDouble();

                            System.out.println("Ingrese la descripción del producto: ");

                            String descripcionProducto = scanner.nextLine();

                            System.out.println("Ingrese el stock del producto: ");

                            int stockProducto = scanner.nextInt();

                            System.out.println("Ingrese el id de la categoría del producto: ");

                            Long idCategoria = scanner.nextLong();

                            CategoriaService cservice = new CategoriaService();

                            Categoria categoria = cservice.buscarPorId(idCategoria);

                            Producto producto = new Producto(this.getStock(), categoria);

                            DetallePedido detallePedido = new DetallePedido();

                        }

                    } while (seguirAgregandoDetalles != 0);

                    Pedido pedido = new Pedido(id_newPedido, false, LocalDateTime.now(), LocalDate.now(), estado, total, formaPago, nombre_Pedido, descripcion_Pedido);

                    pservice.crearPedido(pedido);

                    break;

                case 3:

                    System.out.println("Ingrese el id de la categoría que desee editar: ");

                    Long id_edCategoria = scanner.nextLong();

                    System.out.println("Ingrese el nuevo nombre de la categoría: ");

                    String nombre_edCategoria = scanner.nextLine();

                    System.out.println("Ingrese la nueva descripción de la categoría: ");

                    String descripcion_edCategoria = scanner.nextLine();

                    cservice.editarCategoria(id_edCategoria, nombre_edCategoria, descripcion_edCategoria);

                    return;

                case 4:

                    System.out.println("Ingrese el id del pedido que desee eliminar: ");

                    Long id_delPedido = scanner.nextLong();

                    pservice.eliminarPedido(id_delPedido);

                    return;

                default:

                    System.out.println("\nPor favor, ingrese una instrucción válida.");

            }

        } while (opcion < 0 || opcion > 4);

    }

}
