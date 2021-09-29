package clases;

/**
 *
 * @author ignacio
 */
public class Dato {

    private String nombre;
    private Double cantidad;
    /**
     * Inicializa los atributos de nuestra clase.
     * @param nombre
     * @param cantidad 
     */
    public Dato(String nombre, Double cantidad) {
        this.nombre = nombre;
        this.cantidad = cantidad;
    }
    
    /**
     * retorna el valor del atributo.
     * @return 
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * modifica el valor del atributo.
     * @param nombre 
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    /**
     * retorna el valor del atributo.
     * @return 
     */
    public Double getCantidad() {
        return cantidad;
    }
    
    /**
     * modifica el valor del atributo.
     * @param cantidad 
     */
    public void setCantidad(Double cantidad) {
        this.cantidad = cantidad;
    }
    
    /**
     * retorna una cadena con los valores de cada atributo.
     * @return 
     */
    @Override
    public String toString() {
        return " nombre = " + this.nombre + " cantidad = " + this.cantidad;
    }
}
