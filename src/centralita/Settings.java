package centralita;

public class Settings {

	
	//Probabilidad de que alguien llame en un segundo dado.
	//Es decir, cada vez que pase un segundo, la probabilidad de
	//que alguien llame será PROB_LLAMADA.
	public static final double PROB_LLAMADA = 0.3;
	
	//Tamaño máximo de la cola de llamadas en espera
	public static final int TAM_COLA_ESPERA = 5;
	
	//Número de operadores que atienden llamadas
	public static final int NUM_OPERADORES = 3;
	
	
	// Tiempos mínimo y máximo para atender una llamada
	public static final int TMIN_LLAMADA = 2000;
	public static final int TMAX_LLAMADA = 4000;
	
	//Probabilidad de que un cliente compre 0.2
	public static final double PROB_COMPRA = 0.2;
	
	// Tiempos mínimo y máximo para procesar una compra
	public static final int TMIN_COMPRA = 2000;
	public static final int TMAX_COMPRA = 4000;
	
	//Número de productos disponibles para vender
	public static final int NUM_PRODUCTOS = 10;	
	
	//Tiempo en segundos que durará la simulación 45000
	public static final int TIEMPO_SIMULACION = 45000;

	
}
