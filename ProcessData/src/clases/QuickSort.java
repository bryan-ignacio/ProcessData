package clases;

import ventanas.*;

/**
 *
 * @author ignacio
 */
public class QuickSort extends Thread {

    private Proceso ventanaProceso;
    private ArregloDatos arregloDatos;
    private long pausa;
    private int pasos;
    private int cambios;
    
    /**
     * Inicializa los atributos de nuestra clase.
     * @param proceso
     * @param arregloDatos
     * @param pausa 
     */
    public QuickSort(Proceso proceso, ArregloDatos arregloDatos, long pausa) {
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
        Utilidades.metodoQuickSort(arregloDatos, 0, arregloDatos.numElements - 1, ventanaProceso, pausa, pasos);
        long tiempoFinal = System.currentTimeMillis();
        long total = tiempoFinal - tiempoinicio;
        System.out.println("total = " + total);
        this.ventanaProceso.agregarReporteFinal();
        //this.ventanaProceso.actualizarTiempo(total);
    }
}
