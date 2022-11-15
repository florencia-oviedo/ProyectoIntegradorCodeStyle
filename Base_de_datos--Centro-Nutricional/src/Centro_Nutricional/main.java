package Centro_Nutricional;

import java.util.Scanner;

public class main {

    static Scanner entrada = new Scanner(System.in);

    public static void main(String[] args) {
        

        int opcion;           
        boolean bandera = false;
        String datos_pac[][] = new String[100][100];
        int fila = 0;
        int id = 10000;
        datos_pac[0][0] = "Nombre y Apellido";
        datos_pac[0][1] = "Altura";
        datos_pac[0][2] = "Peso";
        datos_pac[0][3] = "IMC";
        datos_pac[0][4] = "ID";
        datos_pac[0][5] = "s";

        System.out.println("******************************************** BIENVENIDOS A NUTRICION DIGITAL ********************************************");
        System.out.println();

        do {
            System.out.println("1: Ingresar nuevo paciente");
            System.out.println("2: Editar paciente");
            System.out.println("3: Eliminar paciente");
            System.out.println("4: Buscar paciente");
            System.out.println("5: Listado de pacientes");
            System.out.println("10: Salir");
            System.out.println("Ingrese su opción: ");
            opcion = Integer.parseInt(entrada.nextLine());

            switch (opcion) {
                case 1: //verificamos si hubo algún paciente eliminado de la base de datos para 
                    //asignar o no un campo nuevo en la memoria a traves de una bandera que nos indica su estado 
                    //y utilizar  para ingresar datos del nuevo paciente
                    if (bandera == false) {
                        id++;
                        fila++;
                        IngresarDatos(datos_pac, fila, id);
                        break;
                    } else {
                        id++;
                       
                        IngresarDatos(datos_pac, fila, id);
                        bandera = false;
                        break;
                    }

                case 2: //editarPaciente(Datos_pac,fila);
                    break;

                case 3: //cambiamos valor de bandera a "verdadero"para indicar al Algoritmo que hubo usuario eliminado en la base de datos
                    bandera = true;
                    //eliminarPaciente(Datos_pac, fila); 
                    break;

                case 4: //buscarPaciente(Datos_pac, fila);
                    buscarPaciente(datos_pac,fila);
                    break;

                case 5:
                    mostrarPacientes(datos_pac, fila);
                    break;

                case 10:
                    System.out.println();
                    System.out.println("********** Team Developers CodeStyle **********");
                    System.out.println();
                    System.out.println("1: Florencia Micaela Oviedo ");
                    System.out.println("2: Dana Angellotti");
                    System.out.println("3: Maria Gabriela Silva");
                    System.out.println("4: Adriana Soledad Da Silva");
                    System.out.println("5: Juan Pablo Ayoroa Portugal");
                    System.out.println("6: Ivana Germir");
                    System.out.println("7: Martin Verstraeten");
                    System.out.println("8: Fernando Rojas");
                    System.out.println();
                    break;
                default:
                    System.out.println("La opción es incorrecta. Digite nuevamente");

            }
        } while (opcion != 10);

    }

    public static void IngresarDatos(String pacientes[][], int fila, int id) {
        System.out.println("***************************************************   Nuevo Paciente  ***************************************************");
        System.out.println();
        System.out.println("Ingresar Nombre y Apellido del paciente:  ");
        String nombre = entrada.nextLine();
        System.out.println("Ingrese la Altura en Metros: ");
        double altura = Double.parseDouble(entrada.nextLine());
        System.out.println("Ingrese el Peso en Kilogramos");
        double peso = Double.parseDouble(entrada.nextLine());
        double imc = Math.round( imc(altura,peso));
        String resultadoImc = Double.toString(imc);
        String altura1 = Double.toString(altura);
        String peso1 = Double.toString(peso);
        String id1 = Integer.toString(id);

        pacientes[fila][0] = nombre;
        pacientes[fila][1] = altura1;
        pacientes[fila][2] = peso1;
        pacientes[fila][3] = resultadoImc;
        pacientes[fila][4] = id1;
        pacientes[fila][5] = "s";
        mostrarPacientes(pacientes, fila);

    }
    
    // Calcular IMC
    public static double imc(double altura, double peso) {
		double calculo = peso/(altura*altura);
		return calculo;
	}

    //Listar pacientes
    public static void mostrarPacientes(String pacientes[][], int fila) {
        int i, j;
        System.out.println("***************************************************  PACIENTES  ***************************************************");
        for (i = 0; i <= fila; i++) {
            mostrarPaciente(pacientes, i);
            System.out.println("");
        }
        System.out.println("");

    }

    //Listar pacientes 
    public static void mostrarPaciente(String pacientes[][], int i) {
        int espacios, j, k;

        {
            espacios = 0;// Se inicializa la variable para luego hacer el calculo de espacios a completar
            for (j = 0; j <= 4; j++) {
                if ((pacientes[i][5].equals("s"))) {// Si el paciente esta activo ("s") entonces hacer...
                    if ((pacientes[i][j].length() % 2 == 0)) {// Calcula si la longitud del campo es par
                        espacios = 30 - pacientes[i][j].length();// calcula un espacio total de 30 menos la longitud del campo
                        for (k = 0; k <= Math.floor((espacios - 1) / 2); k++) {// Utilizamos la palabra reservada trunc que devuelve la parte entera de ese valor k
                            System.out.print(" ");
                        }
                        // 1 - imprimir el campo de pacientes
                        System.out.print(pacientes[i][j]);
                        for (k = 0; k <= Math.floor((espacios - 1) / 2); k++) {
                            System.out.print(" ");
                        }
                        System.out.print(" |");
                        // si es impar la longitud del campo...
                    } else {
                        // calcula un espacio total de 30 menos la longitud del campo
                        espacios = 30 - pacientes[i][j].length();
                        for (k = 0; k <= Math.floor((espacios - 1) / 2); k++) {
                            System.out.print(" ");
                        }
                        // imprimir el campo de pacientes
                        System.out.print(pacientes[i][j]);
                        for (k = 0; k <= Math.floor((espacios - 1) / 2); k++) {
                            System.out.print(" ");
                        }
                        System.out.print("|");
                    }
                }
            }
        }
    }
    
    //buscar pacientes
    public static void buscarPaciente(String pacientes[][], int fila){
            int i ;
            String id;
            boolean bandera=false;
            System.out.println("******************************* BUSCAR PACIENTES**********************************");
            System.out.println("Ingresar id del paciente:  ");
            id = entrada.nextLine();
            //id = Integer.parseInt( entrada.nextLine());
            for ( i = 0; i <= fila; i++) {
                if (pacientes[i][4].equals(id)) {
                    mostrarPaciente(pacientes,i);
                    bandera= true;
                }
            
            }
            if(!bandera){
                System.out.println("no se encontro paciente");
            }
            System.out.println("");
            
    }
            
            
            
            
}
