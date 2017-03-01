package centralita;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Cola {

    private ArrayList<Llamada> colaLlamadas;
    private int tamanioActual;
    private int tamMaxCola;
    private int llamadasPerdidas;
    private int llamadasAtendidas;
    private Stock stockActual;
    private Semaphore semaforo;
    private Semaphore semaforoCompra;

    public Cola(Stock stockActual){
        this.colaLlamadas=new ArrayList<>();
        this.stockActual=stockActual;
        semaforo=new Semaphore(0,true);
        semaforoCompra=new Semaphore(1,true);
        tamanioActual=0;
        tamMaxCola =Settings.TAM_COLA_ESPERA;
        llamadasPerdidas=0;
        llamadasAtendidas=0;
    }


    //region Propiedades

    public ArrayList<Llamada> getColaLlamadas() {
        return colaLlamadas;
    }

    public void setColaLlamadas(ArrayList<Llamada> colaLlamadas) {
        this.colaLlamadas = colaLlamadas;
    }

    public int getTamanioActual() {
        return tamanioActual;
    }

    public void setTamanioActual(int tamanioActual) {
        this.tamanioActual = tamanioActual;
    }

    public int getTamMaxCola() {
        return tamMaxCola;
    }

    public void setTamMaxCola(int tamMaxCola) {
        this.tamMaxCola = tamMaxCola;
    }

    public int getLlamadasPerdidas() {
        return llamadasPerdidas;
    }

    public void setLlamadasPerdidas(int llamadasPerdidas) {
        this.llamadasPerdidas = llamadasPerdidas;
    }

    public int getLlamadasAtendidas() {
        return llamadasAtendidas;
    }

    public void setLlamadasAtendidas(int llamadasAtendidas) {
        this.llamadasAtendidas = llamadasAtendidas;
    }

    //endregion

    public synchronized void add(Llamada l){
        if(tamanioActual< tamMaxCola) {
            if (tamanioActual == 0) {
                this.notify();
            }
            colaLlamadas.add(l);
            semaforo.release();
            tamanioActual++;
            String cadenaLlamada="Llamada "+l.getNumeroLlamada();
            cadenaLlamada+=" pasa a cola de espera. Hay "+tamanioActual+" llamadas esperando";
            System.out.println(cadenaLlamada);
        }else{
            llamadasPerdidas++;
            System.out.println("Llamada "+l.getNumeroLlamada()+" se ha PERDIDO.Cola llena");
        }
    }

    public synchronized void deleteLlamada(){
        if(!colaLlamadas.isEmpty()){
            colaLlamadas.remove(0);
            tamanioActual--;
        }
    }

    public void atiendeLlamada(Operador operador){

        //Para evitar que una misma llamada sea cogida por varios empleados
        //añadimos un semaforo en el que cada llamada sea un permiso
        if(semaforo.tryAcquire()){
            llamadasAtendidas++;
            Llamada llamadaAtendiendo = colaLlamadas.get(0);
            operador.addLlamada();

            deleteLlamada();

            String cadenaAtendiendo="Operador "+operador.getNumeroOperador();
            cadenaAtendiendo+=" atendiendo llamada "+llamadaAtendiendo.getNumeroLlamada();
            cadenaAtendiendo+=" durante "+llamadaAtendiendo.gettLlamada()+" segundos";

            System.out.println(cadenaAtendiendo);
            //Esperamos el tiempo de la llamada
            try {
                Thread.sleep(llamadaAtendiendo.gettLlamada());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //En caso de que el cliente vaya a comprar
            if(llamadaAtendiendo.isComprador()){
                String cadenaVenta="VENTA: Operador "+operador.getNumeroOperador();
                cadenaVenta+=" a llamada "+llamadaAtendiendo.getNumeroLlamada();
                System.out.println(cadenaVenta);

                /*
                Puesto que solo se puede atender una compra a la vez, creamos un semáforo.
                Por defecto, al crear la cola hay un permiso, que retiramos uan vez que empezamos a gestionar la compra
                Así, si se está gestionando una compra, no podemos gestioanr otra
                 */

                //TODO cambiar por Lock
                while (!semaforoCompra.tryAcquire()) {
                    System.out.println("Operador "+operador.getNumeroOperador()+
                            " ESPERANDO para procesar compra");
                    synchronized (semaforoCompra) {
                        try {
                            semaforoCompra.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

                String cadenaComprando="Operador "+operador.getNumeroOperador();
                cadenaComprando+=" PROCESANDO compra durante "+llamadaAtendiendo.gettCompra()+" segundos";
                System.out.println(cadenaComprando);
                try {
                    Thread.sleep(llamadaAtendiendo.gettCompra());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                semaforoCompra.release();
                synchronized (semaforoCompra){
                    semaforoCompra.notify();
                }
                operador.addVenta();
                stockActual.vendeProducto();
            }else{
                System.out.println("Llamada "+llamadaAtendiendo.getNumeroLlamada()+" CUELGA sin comprar");
            }
        }
    }
}
