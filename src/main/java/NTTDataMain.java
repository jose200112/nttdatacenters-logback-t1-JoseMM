
import java.util.InputMismatchException;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author jose
 * 
 */
public class NTTDataMain {

	/** LOGGER */
	private static final Logger LOG = LoggerFactory.getLogger(NTTDataMain.class);

	/**
	 * Metodo principal.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		boolean gana = false;
		int limite = 0;
		int intentos = 7;

		System.out.println("-------- ¡Bienvenido! --------");
		
		
		//Pedira al usuario un numero que sera el limite
		do {
			try {
				System.out.println("Dime que numero hara de limite");
				limite = sc.nextInt();
				
				if(limite == 0) {
					System.out.println("No puede ser 0");
					LOG.warn("Debe ser mayor que 0");
				}
				
			} catch (InputMismatchException e) {
				System.out.println("Formato equivocado");
				LOG.error("Formato equivocado");
				sc.nextLine();
				LOG.debug("Reintentando...");
			}
		} while (limite == 0);

		// Se genera un numero secreto aleatorio
		System.out.println("¡A ver si aciertas el numero secreto!");
		int numero = 0;

		int numAleatorio = (int) (Math.random() * (limite-1)+1);

		
		// Pedira al usuario un numero, en cuestion de cercania al numero secreto respondera de una manera
		do {
			
			
			try {
				numero = sc.nextInt();
			} catch (InputMismatchException e) {
				LOG.error("Formato equivocado");
				sc.nextLine();
				numero = 0;
				LOG.debug("Reintentando...");
			}

			int resultado = numero - numAleatorio;

			if (numero <= 0 || numero > limite) {
				LOG.warn("Numero fuera de limite");
				System.out.println("Fuera del limite");
			} else {

				if (resultado == 0) {
					System.out.println("¡Has acertado!");
					gana = true;
					break;
				} else if (resultado >= 500 || resultado <= -500) {
					System.out.println("Te congelas");
					intentos--;
					LOG.info("Te quedan {} intentos", intentos);
				} else if (resultado > 100 && resultado < 500 || resultado < -100 && resultado > -500) {
					System.out.println("Frio");
					intentos--;
					LOG.info("Te quedan {} intentos", intentos);
				} else if (resultado <= 100 && resultado > 50 || resultado >= -100 && resultado < -50) {
					System.out.println("Templado");
					intentos--;
					LOG.info("Te quedan {} intentos", intentos);
				} else if (resultado <= 50 && resultado > 25 || resultado >= -50 && resultado < -25) {
					System.out.println("Calor");
					intentos--;
					LOG.info("Te quedan {} intentos", intentos);
				} else if (resultado <= 25 && resultado > 10 || resultado >= -25 && resultado < -10) {
					System.out.println("Muy caliente");
					intentos--;
					LOG.info("Te quedan {} intentos", intentos);
				} else if (resultado <= 10 && resultado > 1 || resultado >= -10 && resultado < -1) {
					System.out.println("¡Te quemas!");
					intentos--;
					LOG.info("Te quedan {} intentos", intentos);
				} else {
					System.out.println("¡LAVA!");
					intentos--;
					LOG.info("Te quedan {} intentos", intentos);

				}
			}

		} while (intentos > 0);

		
		//Se calculan los fallos, si el usuario acierta el numero, devolvera un mensaje
		int errores = 7 - intentos;
		
		if (gana == true) {
			System.out.println("Has ganado");
			LOG.info("Enhorabuena, has ganado =)");
			LOG.info("Victoria con {} intentos y con {} errores",intentos, errores);
		} else {
			System.out.println("Has perdido");
			LOG.info("Has perdido =(");
		}
		
		sc.close();
	}

}
