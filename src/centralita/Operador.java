package centralita;

/**
 * Created by fjruiz on 1/03/17.
 */
public class Operador implements Runnable{
    private int numeroOperador;
    private int llamadasProcesadas;
    private int productosVendidos;
    private long jornadaLaboral;
    private Cola cola;

    /**
     *
     * @param numeroOperador
     * @param cola
     * @param jornadaLaboral fecha fin
     */
    public Operador(int numeroOperador,Cola cola,long jornadaLaboral) {
        this.numeroOperador = numeroOperador;
        this.cola=cola;
        this.jornadaLaboral=jornadaLaboral;
    }

    //region Propiedades

    public int getNumeroOperador() {
        return numeroOperador;
    }

    public void setNumeroOperador(int numeroOperador) {
        this.numeroOperador = numeroOperador;
    }

    public int getLlamadasProcesadas() {
        return llamadasProcesadas;
    }

    public void setLlamadasProcesadas(int llamadasProcesadas) {
        this.llamadasProcesadas = llamadasProcesadas;
    }

    public int getProductosVendidos() {
        return productosVendidos;
    }

    public void setProductosVendidos(int productosVendidos) {
        this.productosVendidos = productosVendidos;
    }

    public Cola getCola() {
        return cola;
    }

    public void setCola(Cola cola) {
        this.cola = cola;
    }

    public long getJornadaLaboral() {
        return jornadaLaboral;
    }

    public void setJornadaLaboral(long jornadaLaboral) {
        this.jornadaLaboral = jornadaLaboral;
    }
    //endregion

    public int addLlamada(){
        llamadasProcesadas++;
        return llamadasProcesadas;
    }

    public int addVenta(){
        productosVendidos++;
        return  productosVendidos;
    }

    @Override
    public void run() {
        //TODO cambiar
        while (System.currentTimeMillis()<jornadaLaboral){
            cola.atiendeLlamada(this);
        }
    }
}
