package clases;

import java.util.logging.*;
import javax.swing.*;
import ventanas.*;

/**
 *
 * @author ignacio
 */
public class Burbuja extends Thread {
    
    /**
     * atributos de la clase.
     */
    private Proceso ventanaProceso;
    private ArregloDatos arregloDatos;
    private long pausa;
    private int pasos;
    
    /**
     * inicializa los atributos de la clase.
     * @param proceso
     * @param arregloDatos
     * @param pausa 
     */
    public Burbuja(Proceso proceso, ArregloDatos arregloDatos, long pausa) {
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
        Utilidades.metodoBurbuja(this.arregloDatos, this.ventanaProceso, this.pausa, this.pasos);
        long tiempoFinal = System.currentTimeMillis();
        long total = tiempoFinal - tiempoinicio;
        System.out.println("total = " + total);
        this.ventanaProceso.agregarReporteFinal();
        //Esto se hace con el timeMillis.
        //this.ventanaProceso.actualizarTiempo(total);
    }
}
