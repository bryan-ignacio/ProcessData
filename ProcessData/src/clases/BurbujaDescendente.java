package clases;

import java.util.logging.*;
import ventanas.*;

/**
 *
 * @author ignacio
 */
public class BurbujaDescendente extends Thread {
    /**
     * Atributos que contienen los datos.
     */
    private Proceso ventanaProceso;
    private ArregloDatos arregloDatos;
    private long pausa;
    private int pasos;
    
    /**
     * inicializa los atributos pasandole los datos que se moveran dinamicamente.(ventana, arregloDatos)
     * @param proceso
     * @param arregloDatos
     * @param espera 
     */
    public BurbujaDescendente(Proceso proceso, ArregloDatos arregloDatos, long pausa) {
        this.ventanaProceso = proceso;
        this.arregloDatos = arregloDatos;
        this.pausa = pausa;
        this.pasos = 0;
    }
    /**
     * Contiene el cuerpo de nuestra tarea actual y es llamado por el metodo start.
     */
    @Override
    public void run() {
        long tiempoinicio = System.currentTimeMillis();
        Utilidades.metodoBurbujaDescendente(arregloDatos, ventanaProceso, pausa, pasos);
        long tiempoFinal = System.currentTimeMillis();
        long total = tiempoFinal - tiempoinicio;
        System.out.println("total = " + total);
        this.ventanaProceso.agregarReporteFinal();
        //se actualiza el tiempo en la ventana proceso.
        //this.ventanaProceso.actualizarTiempo(total);
    }
}
