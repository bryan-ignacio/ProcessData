package clases;

import ventanas.*;

/**
 *
 * @author ignacio
 */
public class ShellSortDescendente extends Thread {

    /**
     * atributos de clase.
     */
    private Proceso ventanaProceso;
    private ArregloDatos arregloDatos;
    private long pausa;
    private int pasos;

    /**
     * inicializa los atributos con los valores pasados por parametro.
     * @param proceso
     * @param arregloDatos
     * @param pausa
     */
    public ShellSortDescendente(Proceso proceso, ArregloDatos arregloDatos, long pausa) {
        this.ventanaProceso = proceso;
        this.arregloDatos = arregloDatos;
        this.pausa = pausa;
        this.pasos = 0;
    }
    
    /**
     * actualiza la ventana proceso con los datos pasados por parametro.
     */
    @Override
    public void run() {
        long tiempoinicio = System.currentTimeMillis();
        Utilidades.metodoShellDescendente(this.arregloDatos, this.ventanaProceso, this.pausa, this.pasos);
        long tiempoFinal = System.currentTimeMillis();
        long total = tiempoFinal - tiempoinicio;
        System.out.println("total = " + total);
        this.ventanaProceso.agregarReporteFinal();
        //this.ventanaProceso.actualizarTiempo(total);
    }
}
