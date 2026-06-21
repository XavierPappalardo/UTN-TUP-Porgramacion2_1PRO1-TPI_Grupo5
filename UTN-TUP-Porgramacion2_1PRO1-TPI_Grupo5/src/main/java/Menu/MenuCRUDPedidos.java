package Menu;
import Entities.*;
import Enums.*;
import Services.*;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuCRUDPedidos {

    public static void menu(Usuario usuario, PedidoService pservice, ProductoService prodservice, Scanner scanner){

        System.out.println("\n====== MENÚ PEDIDOS ======");

        int opcion;

        do {

            System.out.println("\n1. Listar\n2. Crear\n3. Editar\n4. Eliminar\nSeleccione: ");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion){

                case 1:

                    pservice.listarPedidos();
                    return;

                case 2:

                    System.out.println("Ingrese el id del nuevo pedido: ");
                    Long id_newPedido = scanner.nextLong();
                    scanner.nextLine();

                    System.out.println("Ingrese el estado del pedido (1. PENDIENTE\n2. CONFIRMADO\n3. TERMINADO\n4. CANCELADO): ");
                    int est = scanner.nextInt();
                    scanner.nextLine();
                    Estado estado = Estado.fromCodigo(est);

                    System.out.println("Ingrese la forma de pago del pedido (1. TARJETA\n2. TRANSFERENCIA\n3. EFECTIVO): ");
                    int fp = scanner.nextInt();
                    scanner.nextLine();
                    FormaPago formaPago = FormaPago.fromCodigo(fp);

                    Pedido pedido = new Pedido(id_newPedido, false, LocalDateTime.now(), LocalDate.now(), estado, 0.0, formaPago, usuario, new ArrayList<>());

                    int seguirAgregandoDetalles;

                    do {

                        System.out.println("¿Desea ingresar un nuevo detalle? (Ingrese 0. Para NO, o 1. Para SI): ");
                        seguirAgregandoDetalles = scanner.nextInt();
                        scanner.nextLine();

                        if (seguirAgregandoDetalles == 1){

                            System.out.println("Ingrese el id del producto: ");
                            Long idProducto = scanner.nextLong();
                            scanner.nextLine();

                            Producto producto = prodservice.buscarPorId(idProducto);

                            if (producto == null) {
                                System.out.println("No existe ningún producto con ese id.");
                                continue;
                            }

                            System.out.println("Ingrese la cantidad que desee del producto (Recuerde que no puede superar su stock: " + producto.getStock() + "): ");
                            int cantidadDetalle = scanner.nextInt();
                            scanner.nextLine();

                            if (cantidadDetalle > producto.getStock()) {
                                System.out.println("La cantidad supera el stock disponible.");
                                continue;
                            }

                            pedido.addDetallePedido(cantidadDetalle, producto);

                        }

                    } while (seguirAgregandoDetalles != 0);

                    pservice.crearPedido(pedido);

                    System.out.println("Pedido creado correctamente. Total: " + pedido.getTotal());

                    return;

                case 3:

                    System.out.println("Ingrese el id del pedido a editar: ");
                    Long id_edPedido = scanner.nextLong();
                    scanner.nextLine();

                    System.out.println("Ingrese el estado del pedido (1. PENDIENTE\n2. CONFIRMADO\n3. TERMINADO\n4. CANCELADO): ");
                    est = scanner.nextInt();
                    scanner.nextLine();
                    estado = Estado.fromCodigo(est);

                    System.out.println("Ingrese la forma de pago del pedido (1. TARJETA\n2. TRANSFERENCIA\n3. EFECTIVO): ");
                    fp = scanner.nextInt();
                    scanner.nextLine();
                    formaPago = FormaPago.fromCodigo(fp);

                    Pedido pedidoEditado = new Pedido(id_edPedido, false, LocalDateTime.now(), LocalDate.now(), estado, 0.0, formaPago, usuario, new ArrayList<>());

                    pservice.eliminarPedido(id_edPedido);
                    pservice.crearPedido(pedidoEditado);

                    return;

                case 4:

                    System.out.println("Ingrese el id del pedido que desee eliminar: ");
                    Long id_delPedido = scanner.nextLong();
                    scanner.nextLine();

                    pservice.eliminarPedido(id_delPedido);

                    return;

                default:

                    System.out.println("\nPor favor, ingrese una instrucción válida.");

            }

        } while (opcion < 0 || opcion > 4);

    }

}