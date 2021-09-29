package clases;
import ventanas.Utilidades;

/**
 *
 * @author ignacio
 */
public class ArregloDatos {

    private Dato[] arreglo;
    public int numElements;

    /**
     * Constructor con un argumento.
     *
     * @param largo
     */
    public ArregloDatos(int largo) {
        this.arreglo = new Dato[largo]; //instancio el arreglo.
        this.numElements = 0; //inicializo la variable con 0 elementos.
    }

    /**
     * Algoritmo de busqueda el metodo retorna true si encontro el valor de lo
     * contrario false.
     *
     * @param dato
     * @return
     */
    public boolean buscar(Dato dato) {
        //variable de tipo int que guardara el ultimo numero.
        int i;
        for (i = 0; i < numElements; i++) {
            if (arreglo[i].getNombre().equalsIgnoreCase(dato.getNombre())) {
                break; //se encontro el dato.
            }
        }
        if (i == numElements) {
            return false; //si es igual al ultimo numero no se encontro el dato.
        } else {
            return true; //de lo contrario si se encontro el dato.
        }
    }

    /**
     * insertamos un dato al arreglo.
     *
     * @param dato
     */
    public void insertar(Dato dato) {
        this.arreglo[this.numElements] = dato; //insertamos dato en arreglo.
        //ahora se a aumentado el contenido del arreglo por lo que hay que aumentar.
        this.numElements++;
    }

    /**
     * Borramos un elemento del arreglo que sea igual al valor del argumento
     * dato.
     *
     * @param dato
     * @return
     */
    public boolean eliminar(long dato) {
        int i;//contiene el indice del elemento encontrado.
        for (i = 0; i < numElements; i++) {
            if (arreglo[i].equals(dato)) {
                break;
            } //le brinda el valor del indice donde se encuentra el dato igual.
        }
        if (i == numElements) { //no se encontro el dato buscado.
            return false;
        } else {
            //el elemento buscado a sido encontrado.

            //ahora vamos a borrar el elemento del arreglo.
            for (int k = i; k < numElements; k++) {
                //se reemplaza el valor de la celda siguiente.
                arreglo[k] = arreglo[k + 1];
                //ahora hay un elemento menos, por lo que tenemos que disminuir numElements.
            }
            numElements--;
            return true;
        }
    }

    /**
     * Imprimimos los elementos contenidos en el arreglo.
     */
    public void mostrarElementos() {

        for (int i = 0; i < numElements; i++) {
            System.out.print("[" + arreglo[i] + "]" + " ");
        }
        System.out.println("");
    }

    /**
     * Sirve para acceder a un elemento del arreglo dado por un indice.
     *
     * @param i
     * @return
     */
    public Dato valorElemento(int i) {
        return arreglo[i]; //retorna el valor del elemento en la posicion i del arreglo.
    }

    /**
     * retorna el indice del dato encontrado.
     *
     * @param dato
     * @return
     */
    public int getPosicion(Dato dato) {
        int i;
        for (i = 0; i < numElements; i++) {
            if (this.arreglo[i].getNombre().equalsIgnoreCase(dato.getNombre())) {
                break;
            } else if (this.arreglo[i] == null) {
                return -1;
            }
        }
        if (i == numElements) {
            return -1;
        }
        return i;
    }
}
