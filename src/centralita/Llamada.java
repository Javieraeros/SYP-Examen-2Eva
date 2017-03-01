package centralita;

public class Llamada {
    //Variable en la que guardaremos si el cliente va a comprador o no
    private boolean comprador;

    //Tiempo de la llamada
    private long tLlamada;

    //Tiempo de compra
    private long tCompra;

    private int numeroLlamada;

    public Llamada(int numeroLlamada) {
        this.numeroLlamada=numeroLlamada;
        tLlamada=(long) (Math.random()*(Settings.TMAX_LLAMADA-Settings.TMIN_LLAMADA)+Settings.TMIN_LLAMADA);
        double probabilidadCompra=Math.random();
        if(probabilidadCompra<=Settings.PROB_COMPRA){
            comprador =true;
            tCompra=(long) (Math.random()*(Settings.TMAX_COMPRA-Settings.TMIN_COMPRA)+Settings.TMIN_COMPRA);
        }
        tLlamada=(long) (Math.random()*(Settings.TMAX_LLAMADA-Settings.TMIN_LLAMADA)+Settings.TMIN_LLAMADA);
    }

    public boolean isComprador() {
        return comprador;
    }

    public void setComprador(boolean comprador) {
        this.comprador = comprador;
    }

    public long gettLlamada() {
        return tLlamada;
    }

    public void settLlamada(long tLlamada) {
        this.tLlamada = tLlamada;
    }

    public long gettCompra() {
        return tCompra;
    }

    public void settCompra(long tCompra) {
        this.tCompra = tCompra;
    }

    public int getNumeroLlamada() {
        return numeroLlamada;
    }

    public void setNumeroLlamada(int numeroLlamada) {
        this.numeroLlamada = numeroLlamada;
    }
}
