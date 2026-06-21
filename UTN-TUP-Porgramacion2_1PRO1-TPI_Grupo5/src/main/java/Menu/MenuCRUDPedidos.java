package Menu;
import Entities.*;
import Enums.*;
import Services.*;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuCRUDPedidos {

    public static void menu(Usuario usuario, PedidoService pservice, Scanner scanner){

        System.out.println("\n====== MENÚ PEDIDOS ======");

        int opcion;

        do {

            System.out.println("\n1. Listar\n2. Crear\n3. Editar\n4. Eliminar\nSeleccione: ");

            opcion = scanner.nextInt();

            switch (opcion){

                case 1:

                    pservice.listarPedidos();

                    return;

                case 2:

                    System.out.println("Ingrese el id del nuevo pedido: ");

                    Long id_newPedido = scanner.nextLong();

                    System.out.println("Ingrese el estado del pedido (1. PENDIENTE\n2. CONFIRMADO\n3. TERMINADO\n4. CANCELADO): ");

                    int est = scanner.nextInt();

                    Estado estado = Estado.fromCodigo(est);

                    double total = 0;

                    System.out.println("Ingrese la forma de pago del pedido (1. TARJETA\n2. TRANSFERENCIA\n3. EFECTIVO): ");

                    int fp = scanner.nextInt();

                    FormaPago formaPago = FormaPago.fromCodigo(fp);

                    int seguirAgregandoDetalles;

                    ArrayList<DetallePedido> detallesPedido = new ArrayList<>();

                    do {

                        System.out.println("¿Desea ingresar un nuevo detalle? (Ingrese 0. Para NO, o 1. Para SI): ");

                        seguirAgregandoDetalles = scanner.nextInt();

                        if (seguirAgregandoDetalles == 1){

                            System.out.println("Ingrese el id del producto: ");

                            Long idProducto = scanner.nextLong();

                            ProductoService prodservice = new ProductoService();

                            Producto producto = prodservice.buscarPorId(idProducto);

                            System.out.println("Ingrese la cantidad que desee del producto (Recuerde que no puede superar su stock: " + producto.getStock() + "): ");

                            int cantidadDetalle = scanner.nextInt();

                            double subtotalDetalle = producto.getPrecio() * cantidadDetalle;

                            System.out.println("Ingrese el id del pedido: ");

                            Long idDetalle = scanner.nextLong();

                            DetallePedido detallePedido = new DetallePedido(idDetalle, false, LocalDateTime.now(), cantidadDetalle, subtotalDetalle, producto);

                            total =+ subtotalDetalle;

                            detallesPedido.add(detallePedido);

                        }

                    } while (seguirAgregandoDetalles != 0);

                    Pedido pedido = new Pedido(id_newPedido, false, LocalDateTime.now(), LocalDate.now(), estado, total, formaPago, usuario, detallesPedido);

                    pservice.crearPedido(pedido);

                    return;

                case 3:

                    System.out.println("Ingrese el id del nuevo pedido: ");

                    Long id_edPedido = scanner.nextLong();

                    System.out.println("Ingrese el estado del pedido (1. PENDIENTE\n2. CONFIRMADO\n3. TERMINADO\n4. CANCELADO): ");

                    est = scanner.nextInt();

                    estado = Estado.fromCodigo(est);

                    System.out.println("Ingrese el nuevo total del pedido: ");

                    double totalEditado = scanner.nextDouble();

                    System.out.println("Ingrese la forma de pago del pedido (1. TARJETA\n2. TRANSFERENCIA\n3. EFECTIVO): ");

                    fp = scanner.nextInt();

                    formaPago = FormaPago.fromCodigo(fp);

                    ArrayList<DetallePedido> detallesEditados = new ArrayList<>();

                    Pedido pedidoEditado = new Pedido(id_edPedido, false, LocalDateTime.now(), LocalDate.now(), estado, totalEditado, formaPago, usuario, detallesEditados);

                    pservice.eliminarPedido(id_edPedido);

                    pservice.crearPedido(pedidoEditado);

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
