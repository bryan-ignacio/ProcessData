package clases;

import ventanas.*;

/**
 *
 * @author ignacio
 */
public class ShellSort extends Thread {
    
    /**
     * atributos de la clase.
     */
    private Proceso ventanaProceso;
    private ArregloDatos arregloDatos;
    private long pausa;
    private int pasos;

    /**
     * inicializa los atributos con los valores actualizados.
     * @param proceso
     * @param arregloDatos
     * @param pausa 
     */
    public ShellSort(Proceso proceso, ArregloDatos arregloDatos, long pausa) {
        this.ventanaProceso = proceso;
        this.arregloDatos = arregloDatos;
        this.pausa = pausa;
        this.pasos = 0;
    }
    
    /**
     * modifica los valores pasados por parametro del ordenamiento a la ventana proceso.
     */
    @Override
    public void run() {
        long tiempoinicio = System.currentTimeMillis();
        Utilidades.metodoShell(this.arregloDatos, this.ventanaProceso, this.pausa, this.pasos);
        long tiempoFinal = System.currentTimeMillis();
        long total = tiempoFinal - tiempoinicio;
        System.out.println("total = " + total);
        this.ventanaProceso.agregarReporteFinal();
        //this.ventanaProceso.actualizarTiempo(total);
    }
}
