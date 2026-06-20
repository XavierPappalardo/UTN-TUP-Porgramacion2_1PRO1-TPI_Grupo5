package Menu;

import java.util.Scanner;

public class MenuFoodStore {

    public static void menuCategoria(){

        System.out.println("\n====== MENÚ CATEGORÍAS ======");

        int opcion;

        do {

            System.out.println("\n1. Listar\n2. Crear\n3. Editar\n4. Eliminar\nSeleccione: ");

            Scanner scanner = new Scanner(System.in);

            opcion = scanner.nextInt();

            switch (opcion){

                case 1:

                    System.out.println("a");

                    return;

                case 2:

                    System.out.println("a");

                    break;

                case 3:

                    System.out.println("a");

                    return;

                case 4:

                    System.out.println("a");

                    return;

                default:

                    System.out.println("\nPor favor, ingrese una instrucción válida.");

            }

        } while (opcion < 0 || opcion > 4);

    }

}
