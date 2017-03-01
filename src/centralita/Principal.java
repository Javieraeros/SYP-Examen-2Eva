package centralita;

import java.util.ArrayList;
import java.util.Set;

//TODO He modificado la clase settings.En la mía he cambiado los segundos pro milisegundos
public class Principal {
    public static void main(String[] args) {
        long tiempoInicio;
        long tiempoFin;
        int numeroLlamada=0;
        double probabilidadLlamada;
        Stock stock=new Stock(Settings.NUM_PRODUCTOS);
        Cola cola=new Cola(stock);
        ArrayList<Operador> operadores=new ArrayList<>();

        tiempoInicio=System.currentTimeMillis();
        tiempoFin=tiempoInicio+Settings.TIEMPO_SIMULACION;
        for (int i = 0; i < Settings.NUM_OPERADORES; i++) {
            Operador operador=new Operador(i,cola,tiempoFin);
            operadores.add(operador);
            Thread hiloOperador=new Thread(operador);
            hiloOperador.start();
        }
        while (System.currentTimeMillis()<tiempoFin && stock.getProductosActuales()>0){
            probabilidadLlamada=Math.random();
            if(probabilidadLlamada<=Settings.PROB_LLAMADA){
                Llamada llamada=new Llamada(numeroLlamada);
                numeroLlamada++;
                cola.add(llamada);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if(stock.getProductosActuales()<=0){
            System.out.println("STOCK AGOTADO");
        }else{
            System.out.println("TIEMPO AGOTADO");
        }

        System.out.println("Total productos Vendidos "+(Settings.NUM_PRODUCTOS-stock.getProductosActuales()));
        for (int i = 0; i < operadores.size(); i++) {
            Operador operador=operadores.get(i);
            System.out.println("Operador "+operador.getNumeroOperador()+" ha atendido "+operador.getLlamadasProcesadas());
            System.out.println("Operador "+operador.getNumeroOperador()+" ha vendido "+operador.getProductosVendidos());
        }

        System.out.println("Llamadas perdidas: "+cola.getLlamadasPerdidas());
        System.out.println("Llamadas atendidas: "+cola.getLlamadasAtendidas());
    }
}
