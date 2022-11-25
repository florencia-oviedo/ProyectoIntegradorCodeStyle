package Centro_Nutricional;

import java.util.Scanner;


public class main {
    //definiendo variables
    // Datos_pac = nommbre de la matriz a contener datos de los pacientes
    //i: iterador que se usará a lo largo del programa
    //id = identificador único por paciente
    //bandera = operador logico para indicar si hubo  pacientes eliminados de la base de datos
    //la variable "fila" es la que lleva el dato actualizado de la ultima posicion de la matriz
    static Scanner entrada = new Scanner(System.in);
    private static String id;
    private static int opcion;
    private static String datos_pac;

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
        datos_pac[0][5] = "s";// S indica que el paciente esta  activo caso contrario se denota con una "n"

        System.out.println("**************************************************************** BIENVENIDOS A NUTRICION DIGITAL ****************************************************************");
        System.out.println();

        do {
	    System.out.println("******MENÚ PRINCIPAL******");
            System.out.println("1: Ingresar nuevo paciente");
            System.out.println("2: Editar paciente");
            System.out.println("3: Eliminar paciente");
            System.out.println("4: Buscar paciente");
            System.out.println("5: Listado de pacientes");
            System.out.println("10: Salir");
            System.out.print("Ingrese su opción: ");
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

                case 2:
                    editarPaciente(datos_pac, fila);
                    break;

                case 3: //cambiamos valor de bandera a "verdadero"para indicar al Algoritmo que hubo usuario eliminado en la base de datos
                    bandera = true;
                    eliminarPaciente(datos_pac, fila);
                    break;

                case 4: //buscarPaciente(Datos_pac, fila);
                    buscarPaciente(datos_pac, fila);
                    break;

                case 5:
                    mostrarPacientes(datos_pac, fila);
                    break;

                case 10:
                    System.out.println();
                    System.out.println("******************************************************************* Team Developers CodeStyle *******************************************************************");
                    String [] CodeStyle = {"Florencia Oviedo","Dana Angellotti","Maria Gabriela Silva","Adriana Soledad Da Silva",
                    "Juan Pablo Ayoroa Portugal", "Ivana Germir", "Martin Verstraeten","Fernando Rojas"};
                    for(int c=0; c<CodeStyle.length; c++) {
			System.out.println((c+1) +": "+ CodeStyle[c]);
                    }
                    break;
                default:
                    System.out.println("La opción es incorrecta. Digite nuevamente");
                    System.out.println("");
            }
        } while (opcion != 10);

    }

    //Ingreso de nuevo paciente.
    public static void IngresarDatos(String pacientes[][], int fila, int id) {
        System.out.println("************************************************************************ NUEVO PACIENTE *************************************************************************");
        System.out.println();
        System.out.print("Ingresar Nombre y Apellido del paciente:  ");
        String nombre = entrada.nextLine();
        System.out.print("Ingrese la Altura en Metros: ");
        double altura = Double.parseDouble(entrada.nextLine());
        System.out.print("Ingrese el Peso en Kilogramos: ");
        double peso = Double.parseDouble(entrada.nextLine());
        double imc = Math.round(imc(altura, peso));
        String resultadoImc = Double.toString(imc);
        String altura1 = Double.toString(altura);
        String peso1 = Double.toString(peso);
        String id1 = Integer.toString(id);

        pacientes[fila][0] = nombre;
        pacientes[fila][1] = altura1;
        pacientes[fila][2] = peso1;
        pacientes[fila][3] = resultadoImc;
        pacientes[fila][4] = id1;
        pacientes[fila][5] = "s";// inicializa como paciente activo para listarlo en las busquedas
        mostrarPacientes(pacientes, fila);// Muestra los pacientes luego de ingresarlo
        System.out.println("");
    }

    // Calcular IMC
    //Cálculo de IMC: se calcula en base al peso por altura al cuadrado, datos obtenidos en "altura" y "peso"
    public static double imc(double altura, double peso) { //Parámetros
        double calculo = peso / (altura * altura);
        return calculo;
    }

    //Listar pacientes
    public static void mostrarPacientes(String pacientes[][], int fila) {
        int i, j;
        System.out.println("*************************************************************************** PACIENTES ***************************************************************************");
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
    public static void buscarPaciente(String pacientes[][], int fila) {
        int i;
        String id;
        boolean bandera = false;
        System.out.println("*********************************************************************** BUSCAR PACIENTES ************************************************************************");
        System.out.print("Ingresar id del paciente:  ");
        id = entrada.nextLine();
        for (i = 0; i <= fila; i++) {
            if (pacientes[i][4].equals(id)) {
                mostrarPaciente(pacientes, i);
                System.out.println("");
                bandera = true;
            }

        }
        if (!bandera)// Evalua si bandera es falso
        {
            System.out.println("No se encontró paciente");// Muestra mensaje que no se encontro el paciente
        }
        System.out.println("");

    }

    // Editar pacientes
    public static void editarPaciente(String datos_pac[][], int fila) {
        int i;
        String id;
        boolean existe = false;
        
        System.out.println("*********************************************************************** EDITAR PACIENTES ************************************************************************");
        Scanner entrada = new Scanner(System.in);
        System.out.print("Digite el ID del paciente a editar: ");
        id = entrada.nextLine();
        System.out.println("");

        for (i = 1; i <= fila; i++) {
            if (datos_pac[i][4].equals(id)) {
                existe = true;
                do {
                    System.out.println("");
                    System.out.println("**********MENÚ EDITAR**********");
                    System.out.println("1. Editar Nombre y Apellido: ");
                    System.out.println("2. Editar Altura en metros:  ");
                    System.out.println("3. Editar peso en kilogramos: ");
                    System.out.println("4. Salir ");
                    System.out.print("Seleccione una opción: ");
                    opcion = Integer.parseInt(entrada.nextLine());

                    switch (opcion) {
                        case 1:
                            System.out.print("Ingrese el nuevo nombre: ");
                            datos_pac[i][0] = entrada.nextLine();
                            break;
                        case 2:
                            System.out.print("Ingrese la nueva altura: ");
                            datos_pac[i][1] = (entrada.nextLine());
                            Double resultado_imc = imc(Double.parseDouble(datos_pac[i][1]), Double.parseDouble(datos_pac[i][2]));
                            datos_pac[i][3] = Double.toString(Math.round(resultado_imc));
                            break;
                        case 3:
                            System.out.print("Ingrese el nuevo peso: ");
                            datos_pac[i][2] = (entrada.nextLine());
                            Double resultado_imc2 = imc(Double.parseDouble(datos_pac[i][1]), Double.parseDouble(datos_pac[i][2]));
                            datos_pac[i][3] = Double.toString(Math.round(resultado_imc2));
                            break;
                        case 4:
                            System.out.println("");
                            break;

                        default:
                            System.out.println("Opción no encontrada, por favor digite nuevamente o presione opción 4 para salir: ");

                    }
                } while (opcion != 4);

            } 

        }
        if(existe == false){
            System.out.println("El ID ingresado es incorrecto");
            System.out.println("");
        }
    }

    // Eliminar pacientes
    public static void eliminarPaciente(String pacientes[][], int fila) {
        int i, j, k;
        boolean existe = false;
        String id;
        k = 0;
        System.out.println("********************************************************************** ELIMINAR PACIENTES ***********************************************************************");
        System.out.print("Ingresar id del paciente:  ");
        id = entrada.nextLine();
        //verificamos si el ID a eliminar existe de ser asi le asignamos "n"
        //en una celda de la matriz que nos indica el estado del paciente
        //n= eliminar paciente s= paciente activo
        for (i = 0; i <= fila; i++) {
            if (pacientes[i][4].equals(id)) {
                pacientes[i][5] = "n";
                existe = true;
                break;
                
            }   
        }
        
        //"n" indica paciente a eliminar, al encontrar este dato hacemos que los datos del siguiente paciente
        //se sobrepongan a los datos del paciente a eliminar para que no queden huecos ni repeticiones en la base de datos
        //a partir de la fila representada con "k" se reemplazara esa fila con los datos de la siguiente fila hasta el final de la matriz

        for (i = 0; i <= fila; i++) {
            if (pacientes[i][4].equals(id)) {
                for (k = i; k <= fila - 1; k++) {
                    for (j = 0; j <= 5; j++) {
                        pacientes[k][j] = pacientes[k + 1][j];
                        
                    }
                }
            }
            
        }
        
        if(existe){
            System.out.println("Paciente eliminado con éxito");
            System.out.println("");
        }
        else{
            System.out.println("El id ingresado no existe, intentalo nuevamente");
            System.out.println("");
        }
        
        pacientes[k][5] = "n";
        // contiene datos repetidos por lo tanto se niega para cuando se ingrese un nuevo paciente ocupe este lugar en la memoria

    }
    
}
