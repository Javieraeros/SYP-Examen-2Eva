package centralita;

/**
 * Clase creada para controlar el stock
 */
public class Stock {
    private int productosActuales;

    public Stock(int productosActuales) {
        this.productosActuales = productosActuales;
    }

    public int getProductosActuales() {
        return productosActuales;
    }

    public void setProductosActuales(int productosActuales) {
        this.productosActuales = productosActuales;
    }

    /**
     * Método que disminuye en uno el stock
     * @return true si se ha podido vender, false en caso contrario
     */
    public boolean vendeProducto(){
        boolean resultado=false;
        if (productosActuales>0) {
            productosActuales--;
            resultado=true;
        }
        return resultado;
    }
}
