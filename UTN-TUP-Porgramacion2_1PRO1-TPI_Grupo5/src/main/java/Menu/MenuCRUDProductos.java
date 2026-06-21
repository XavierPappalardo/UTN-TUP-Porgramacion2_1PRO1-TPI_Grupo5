package Menu;
import Entities.Categoria;
import Entities.Producto;
import Entities.Usuario;
import Exceptions.StockInvalidoException;
import Services.*;

import java.time.LocalDateTime;
import java.util.Scanner;

public class MenuCRUDProductos {

    public static void menu(Usuario usuarioActivo, ProductoService prodservice, CategoriaService catservice, Scanner scanner) {
        System.out.println("\n====== MENU PRODUCTOS ======");

        int opcion;

        do {
            System.out.println("\n1. Listar\n2. Crear\n3. Editar\n4. Eliminar\n5. Listar por categoría\n0. Volver al menú\nSeleccione: ");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch(opcion) {
                case 1:
                    prodservice.listarProductos();
                    return;
                case 2:

                    System.out.println("Ingrese el id del nuevo producto: ");
                    Long id_newProducto = scanner.nextLong();
                    scanner.nextLine();

                    System.out.println("Ingrese el nombre: ");
                    String nombre_new = scanner.nextLine();

                    System.out.println("Ingrese el precio: ");
                    Double precio_new = scanner.nextDouble();
                    scanner.nextLine();

                    System.out.println("Ingrese la descripción: ");
                    String descripcion_new = scanner.nextLine();

                    System.out.println("Ingrese el stock: ");
                    int stock_new = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Ingrese la imagen (URL o nombre de archivo): ");
                    String imagen_new = scanner.nextLine();

                    System.out.println("¿Está disponible? (1. Sí, 0. No): ");
                    int dispCodigo_new = scanner.nextInt();
                    scanner.nextLine();
                    Boolean disponible_new = (dispCodigo_new == 1);

                    System.out.println("Ingrese el id de la categoría: ");
                    Long idCategoria_new = scanner.nextLong();
                    scanner.nextLine();

                    Categoria categoria_new = catservice.buscarPorId(idCategoria_new);

                    if(categoria_new == null) {
                        System.out.println("No existe ninguna categoria con ese id. Opcion cancelada.");
                        return;
                    }

                    Producto nuevoProducto = new Producto(id_newProducto, false, LocalDateTime.now(), nombre_new, precio_new, descripcion_new, stock_new, imagen_new, disponible_new, categoria_new);

                    try {
                        prodservice.crearProducto(nuevoProducto);
                        System.out.println("Producto creado correctamente.");
                    } catch (StockInvalidoException e) {
                        System.out.println("Error: " + e.getMessage());
                    }

                    return;
                case 3:

                    System.out.println("Ingrese el id del producto a editar: ");
                    Long id_edProducto = scanner.nextLong();
                    scanner.nextLine();

                    System.out.println("Ingrese el nuevo nombre: ");
                    String nombre_ed = scanner.nextLine();

                    System.out.println("Ingrese el nuevo precio: ");
                    Double precio_ed = scanner.nextDouble();
                    scanner.nextLine();

                    System.out.println("Ingrese el nuevo stock: ");
                    int stock_ed = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Ingrese el id de la nueva categoría: ");
                    Long idCategoria_ed = scanner.nextLong();
                    scanner.nextLine();

                    Categoria categoria_ed = catservice.buscarPorId(idCategoria_ed);

                    if (categoria_ed == null) {
                        System.out.println("No existe ninguna categoría con ese id. Operación cancelada.");
                        return;
                    }

                    try {
                        boolean editado = prodservice.editarProducto(id_edProducto, nombre_ed, precio_ed, stock_ed, categoria_ed);

                        if (editado) {
                            System.out.println("Producto editado correctamente.");
                        } else {
                            System.out.println("No se encontró ningún producto con ese id.");
                        }
                    } catch (StockInvalidoException e) {
                        System.out.println("Error: " + e.getMessage());
                    }

                    return;

                case 4:

                    System.out.println("Ingrese el id del producto a eliminar: ");
                    Long id_delProducto = scanner.nextLong();
                    scanner.nextLine();

                    boolean eliminado = prodservice.eliminarProducto(id_delProducto);

                    if (eliminado) {
                        System.out.println("Producto eliminado correctamente.");
                    } else {
                        System.out.println("No se encontró ningún producto con ese id.");
                    }

                    return;

                case 5:

                    System.out.println("Ingrese el id de la categoría: ");
                    Long idCategoria_list = scanner.nextLong();
                    scanner.nextLine();

                    Categoria categoria_list = catservice.buscarPorId(idCategoria_list);

                    if (categoria_list == null) {
                        System.out.println("No existe ninguna categoría con ese id.");
                    } else {
                        prodservice.listarPorCategoria(categoria_list);
                    }

                    return;

                case 0:

                    System.out.println("--------Volviendo al menú principal-------\n");

                    return;

                default:

                    System.out.println("\nPor favor, ingrese una instruccion valida.");
            }
        } while (opcion < -1 || opcion > 6);
    }
}