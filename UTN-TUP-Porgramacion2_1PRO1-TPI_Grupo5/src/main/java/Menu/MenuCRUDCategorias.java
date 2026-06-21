package Menu;
import Entities.Categoria;
import Entities.Usuario;
import Services.*;

import java.time.LocalDateTime;
import java.util.Scanner;

public class MenuCRUDCategorias {

    public static void menu(Usuario usuario, CategoriaService cservice, Scanner scanner){

        System.out.println("\n====== MENÚ CATEGORÍAS ======");

        int opcion;

        do {

            System.out.println("\n1. Listar\n2. Crear\n3. Editar\n4. Eliminar\n0. Volver al menú\nSeleccione: ");

            opcion = scanner.nextInt();

            scanner.nextLine();

            switch (opcion){

                case 1:

                    cservice.listarCategorias();

                    return;

                case 2:

                    System.out.println("Ingrese el id de la nueva categoría: ");
                    Long id_newCategoria = scanner.nextLong();
                    scanner.nextLine();

                    System.out.println("Ingrese el nombre de la nueva categoría: ");
                    String nombre_Categoria = scanner.nextLine();

                    System.out.println("Ingrese la descripción de la nueva categoría: ");
                    String descripcion_Categoria = scanner.nextLine();

                    Categoria categoria = new Categoria(id_newCategoria, false, LocalDateTime.now(), nombre_Categoria, descripcion_Categoria);

                    try {
                        cservice.crearCategoria(categoria);
                        System.out.println("Categoría creada correctamente.");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }

                    return;

                case 3:

                    System.out.println("Ingrese el id de la categoría que desee editar: ");
                    Long id_edCategoria = scanner.nextLong();
                    scanner.nextLine();

                    System.out.println("Ingrese el nuevo nombre de la categoría: ");
                    String nombre_edCategoria = scanner.nextLine();

                    System.out.println("Ingrese la nueva descripción de la categoría: ");
                    String descripcion_edCategoria = scanner.nextLine();

                    try {
                        boolean editado = cservice.editarCategoria(id_edCategoria, nombre_edCategoria, descripcion_edCategoria);

                        if (editado) {
                            System.out.println("Categoría editada correctamente.");
                        } else {
                            System.out.println("No se encontró ninguna categoría con ese id.");
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }

                    return;

                case 4:

                    System.out.println("Ingrese el id de la categoría que desee eliminar: ");
                    Long id_delCategoria = scanner.nextLong();
                    scanner.nextLine();

                    boolean eliminado = cservice.eliminarCategoria(id_delCategoria);

                    if (eliminado) {
                        System.out.println("Categoría eliminada correctamente.");
                    } else {
                        System.out.println("No se encontró ninguna categoría con ese id.");
                    }

                    return;

                case 0:

                    System.out.println("--------Volviendo al menú principal-------\n");

                    return;

                default:

                    System.out.println("\nPor favor, ingrese una instrucción válida.");

            }

        } while (opcion < -1 || opcion > 5);

    }

}