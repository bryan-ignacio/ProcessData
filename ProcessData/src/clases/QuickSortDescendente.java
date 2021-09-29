package clases;

import ventanas.*;

/**
 *
 * @author ignacio
 */
public class QuickSortDescendente extends Thread {

    private Proceso ventanaProceso;
    private ArregloDatos arregloDatos;
    private long pausa;
    private int pasos;
    
    /**
     * inicializa los atributos con los datos pasados por parametro.
     * @param proceso
     * @param arregloDatos
     * @param pausa 
     */
    public QuickSortDescendente(Proceso proceso, ArregloDatos arregloDatos, long pausa) {
        this.ventanaProceso = proceso;
        this.arregloDatos = arregloDatos;
        this.pausa = pausa;
        this.pasos = 0;
    }
    
    /**
     * Actualiza la ventana con las datos modificados por el ordenamiento.
     */
    @Override
    public void run() {
        long tiempoinicio = System.currentTimeMillis();
        Utilidades.metodoQuickSortDescendente(arregloDatos, 0, arregloDatos.numElements - 1, ventanaProceso, pausa, pasos);
        long tiempoFinal = System.currentTimeMillis();
        long total = tiempoFinal - tiempoinicio;
        System.out.println("total = " + total);
        this.ventanaProceso.agregarReporteFinal();
        //this.ventanaProceso.actualizarTiempo(total);
    }
}
