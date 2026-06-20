import Entities.*;
import Enums.*;
import Exceptions.*;
import Interfaces.*;
import Services.*;
import Menu.MenuFoodStore;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {

    public static void main (String[] args) throws MailDuplicadoException, StockInvalidoException, EntidadNoEncontradaException{

        Scanner scanner = new Scanner(System.in);

        int opcionSistema;

        System.out.println("======== BIENVENIDO AL SISTEMA DE FOOD STORE ========");

        do{

            System.out.println("=== SISTEMA DE PEDIDOS (FOOD STORE) ===");

            System.out.println("\n---- Seleccione ----");

            System.out.println("1. Categorías\n2. Productos\n3. Usuarios\n4.Pedidos\n0.Salir");

            System.out.println("\nIngrese el número correspondiente a la acción que desee ejecutar: ");

            opcionSistema = scanner.nextInt();

            switch (opcionSistema){

                case 1:

                    MenuFoodStore.menuCategoria();

                    break;

                case 2:



                case 3:



                case 4:



                case 0:

                    System.out.println("\nGracias por usar el sistema. ¡Vuelva pronto!");

                    break;

                default:

                    System.out.println("\nPor favor, ingrese una instrucción válida.");

            }

        } while (opcionSistema != 0);

    }

}
