package clases;

import java.util.logging.Level;
import java.util.logging.Logger;
import ventanas.Proceso;

/**
 *
 * @author ignacio
 */
public class Cronometro extends Thread {

    private Proceso ventanaProceso;
    private int minutos, segundos;
    private long pausa;
    //probando
    private int milis, horas;

    public Cronometro(Proceso ventanProceso, int milis, int minutos, int segundos, long pausa) {
        this.ventanaProceso = ventanProceso;
        this.milis = milis;
        this.minutos = minutos;
        this.segundos = segundos;
        this.pausa = pausa;
    }

    @Override
    public void run() {
        String tiempo;
        while (true) {
            tiempo = "";
            if (minutos < 10) {
                tiempo += "0" + minutos;
            } else {
                tiempo += minutos;
            }

            tiempo += ":";

            if (segundos < 10) {
                tiempo += "0" + segundos;
            } else {
                tiempo += segundos;
            }

            tiempo += ":";

            if (milis < 10) {
                tiempo += "0" + milis;
            } else {
                tiempo += milis;
            }

            milis++;

            if (milis == 800) {
                segundos++;
                milis = 0;
                if (segundos == 60) {
                    segundos = 0;
                    minutos++;
                    if (minutos == 60) {
                        minutos = 0;
                    }
                }
            }

            this.ventanaProceso.actualizarEtiquetaTiempo(tiempo);

            try {
                sleep(pausa);
            } catch (InterruptedException ex) {
                Logger.getLogger(Cronometro.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
}