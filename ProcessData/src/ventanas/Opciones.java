package ventanas;

import clases.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author ignacio
 */
public class Opciones extends JFrame implements ActionListener {

    //ATRIBUTOS DE LA INTERFAZ.
    private JPanel principal;
    private JLabel etqUno, etqDos, etqTres;
    private JButton btnOrdenar;
    private JRadioButton rdAs, rdDes, rdBaja, rdMedia, rdAlta, rdBurbuja, rdQuick, rdShell;
    private ButtonGroup grupoOrden = new ButtonGroup();
    private ButtonGroup grupoVelocidad = new ButtonGroup();
    private ButtonGroup grupoAlgoritmo = new ButtonGroup();
    //PARA ELIMINAR LA VENTANA.
    private JFrame framePrincipal;
    //ESTOS ATRIBUTOS CONTIENE TODO EL CONTENIDO DE LAS ANTERIORES VENTANAS.
    private String titulo;
    private String nombresEjes;
    private ArregloDatos arregloDatos;
    //ATRIBUTO PARA EL ALGORITMO BURBUJA.
    private Burbuja HiloBurbuja;
    private BurbujaDescendente HiloBurbujaDescendente;
    //ATRIBUTOS PARA EL ALGORITMO QUICKSORT.
    private QuickSort HiloQuickSort;
    private QuickSortDescendente HiloQuickSortDescendente;
    //ATRIBUTOS PARA EL ALGORITMO SHELLSORT.
    private ShellSort hiloShellSort;
    private ShellSortDescendente hiloShellSortDescendente;

    private Cronometro hiloCronometro;

    /**
     * inicializa los atributos de nuestra clase.
     *
     * @param framePrincipal
     * @param titulo
     * @param nombresEjes
     * @param arregloDatos
     */
    public Opciones(JFrame framePrincipal, String titulo, String nombresEjes, ArregloDatos arregloDatos) {
        this.framePrincipal = framePrincipal;
        this.titulo = titulo;
        this.nombresEjes = nombresEjes;
        this.arregloDatos = arregloDatos;
        initComponents();
    }

    /**
     * inicializa los componentes de nuestro jframe.
     */
    private void initComponents() {
        setVentana();
        addPanel();
        addLabels();
        addRadioBotones();
        addBotones();
    }

    /**
     * modifica el jframe.
     */
    private void setVentana() {
        this.setTitle("Opciones de Ordenamiento");
        this.setSize(400, 400);
        this.setVisible(true);
        this.setLayout(null);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /**
     * agrega el jpanel al frame.
     */
    private void addPanel() {
        this.principal = Utilidades.crearPanel(Utilidades.fondoDos, 0, 0, 400, 400);
        this.getContentPane().add(this.principal);
    }

    /**
     * agrega las etiquetas al jpanel.
     */
    private void addLabels() {
        this.etqUno = Utilidades.crearEtiqueta("TIPO DE ORDENAMIENTO:", 95, 20, 210, 40, SwingConstants.CENTER);
        this.principal.add(this.etqUno);
        this.etqDos = Utilidades.crearEtiqueta("ALGORITMO DE ORDENAMIENTO:", 95, 120, 210, 40, SwingConstants.CENTER);
        this.principal.add(this.etqDos);
        this.etqTres = Utilidades.crearEtiqueta("VELOCIDAD DE ORDENAMIENTO:", 95, 220, 210, 40, SwingConstants.CENTER);
        this.principal.add(this.etqTres);
    }

    /**
     * agrega los radio botones al jpanel.
     */
    private void addRadioBotones() {
        this.rdAs = new JRadioButton("Ascendente");
        this.rdAs.setBounds(40, 70, 110, 40);
        this.principal.add(this.rdAs);
        this.rdDes = new JRadioButton("Descendente");
        this.rdDes.setBounds(160, 70, 120, 40);
        this.principal.add(this.rdDes);
        grupoOrden.add(rdAs);
        grupoOrden.add(rdDes);
        this.rdBurbuja = new JRadioButton("BubbleSort");
        this.rdBurbuja.setBounds(40, 165, 100, 40);
        this.principal.add(this.rdBurbuja);
        this.rdQuick = new JRadioButton("QuickSort");
        this.rdQuick.setBounds(160, 165, 100, 40);
        this.principal.add(this.rdQuick);
        this.rdShell = new JRadioButton("ShellSort");
        this.rdShell.setBounds(270, 165, 100, 40);
        this.principal.add(this.rdShell);
        grupoAlgoritmo.add(this.rdBurbuja);
        grupoAlgoritmo.add(this.rdQuick);
        grupoAlgoritmo.add(this.rdShell);
        this.rdBaja = new JRadioButton("Baja");
        this.rdBaja.setBounds(40, 260, 100, 40);
        this.principal.add(this.rdBaja);
        this.rdMedia = new JRadioButton("Media");
        this.rdMedia.setBounds(160, 260, 100, 40);
        this.principal.add(this.rdMedia);
        this.rdAlta = new JRadioButton("Alta");
        this.rdAlta.setBounds(270, 260, 100, 40);
        this.principal.add(this.rdAlta);
        grupoVelocidad.add(this.rdBaja);
        grupoVelocidad.add(this.rdMedia);
        grupoVelocidad.add(this.rdAlta);
    }

    /**
     * agregar los botones a nuestro jpanel.
     */
    private void addBotones() {
        this.btnOrdenar = Utilidades.crearBoton("Ordenar", 280, 320, 100, 40);
        this.btnOrdenar.addActionListener(this);
        this.principal.add(this.btnOrdenar);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //SI SE SELECCIONO EL RADIO ASCENDENTE Y BURBUJA Y BAJA.
        if (this.rdAs.isSelected() && this.rdBurbuja.isSelected() && this.rdBaja.isSelected()) {
            if (e.getSource() == this.btnOrdenar) {
                this.framePrincipal.setVisible(false);
                this.setVisible(false);
                Proceso ventanaProceso = new Proceso(this.titulo, this.nombresEjes, this.arregloDatos, "Baja", "BubbleSort", "Ascendente");
                //vamos a crare el hilo
                this.HiloBurbuja = new Burbuja(ventanaProceso, this.arregloDatos, 800); //el 500 son los milisegundos del sleep.
                this.hiloCronometro = new Cronometro(ventanaProceso, 0, 0, 0, 1); // la pausa es de 1000 para usar el otro algoritmo en comentarios en cronometro.
                this.HiloBurbuja.start();
                this.hiloCronometro.start();
                this.dispose();
            }
        }

        //SI SE SELECCIONO EL RADIO ASCENDENTE Y BURBUJA Y MEDIA.
        if (this.rdAs.isSelected() && this.rdBurbuja.isSelected() && this.rdMedia.isSelected()) {
            if (e.getSource() == this.btnOrdenar) {
                this.framePrincipal.setVisible(false);
                this.setVisible(false);
                Proceso ventanaProceso = new Proceso(this.titulo, this.nombresEjes, this.arregloDatos, "Media", "BubbleSort", "Ascendente");
                //vamos a crare el hilo
                this.HiloBurbuja = new Burbuja(ventanaProceso, this.arregloDatos, 500); //el 500 son los milisegundos del sleep.
                this.hiloCronometro = new Cronometro(ventanaProceso, 0, 0, 0, 1);
                this.HiloBurbuja.start();
                this.hiloCronometro.start();
                this.dispose();
            }
        }

        //SI SE SELECCIONO EL RADIO ASCENDENTE Y BURBUJA Y ALTA.
        if (this.rdAs.isSelected() && this.rdBurbuja.isSelected() && this.rdAlta.isSelected()) {
            if (e.getSource() == this.btnOrdenar) {
                this.framePrincipal.setVisible(false);
                this.setVisible(false);
                Proceso ventanaProceso = new Proceso(this.titulo, this.nombresEjes, this.arregloDatos, "Alta", "BubbleSort", "Ascendente");
                //vamos a crare el hilo
                this.HiloBurbuja = new Burbuja(ventanaProceso, this.arregloDatos, 100); //el 500 son los milisegundos del sleep.
                this.hiloCronometro = new Cronometro(ventanaProceso, 0, 0, 0, 1);
                this.HiloBurbuja.start();
                this.hiloCronometro.start();
                this.dispose();
            }

        }

        //------------------------------------------TERMINE CON BURBUJA ASCENDENTE FALTA DESCENDENTE--------------------------------------------
        if (this.rdDes.isSelected() && this.rdBurbuja.isSelected() && this.rdBaja.isSelected()) {
            if (e.getSource() == this.btnOrdenar) {
                this.framePrincipal.setVisible(false);
                this.setVisible(false);
                Proceso ventanaProceso = new Proceso(this.titulo, this.nombresEjes, this.arregloDatos, "Baja", "BubbleSort", "Descendente");
                this.HiloBurbujaDescendente = new BurbujaDescendente(ventanaProceso, this.arregloDatos, 800);
                this.hiloCronometro = new Cronometro(ventanaProceso, 0, 0, 0, 1);
                this.HiloBurbujaDescendente.start();
                this.hiloCronometro.start();
                this.dispose();
            }
        }

        if (this.rdDes.isSelected() && this.rdBurbuja.isSelected() && this.rdMedia.isSelected()) {
            if (e.getSource() == this.btnOrdenar) {
                this.framePrincipal.setVisible(false);
                this.setVisible(false);
                Proceso ventanaProceso = new Proceso(this.titulo, this.nombresEjes, this.arregloDatos, "Media", "BubbleSort", "Descendente");
                this.HiloBurbujaDescendente = new BurbujaDescendente(ventanaProceso, this.arregloDatos, 500);
                this.hiloCronometro = new Cronometro(ventanaProceso, 0, 0, 0, 1);
                this.HiloBurbujaDescendente.start();
                this.hiloCronometro.start();
                this.dispose();
            }
        }

        if (this.rdDes.isSelected() && this.rdBurbuja.isSelected() && this.rdAlta.isSelected()) {
            if (e.getSource() == this.btnOrdenar) {
                this.framePrincipal.setVisible(false);
                this.setVisible(false);
                Proceso ventanaProceso = new Proceso(this.titulo, this.nombresEjes, this.arregloDatos, "Alta", "BubbleSort", "Descendente");
                this.HiloBurbujaDescendente = new BurbujaDescendente(ventanaProceso, this.arregloDatos, 100);
                this.hiloCronometro = new Cronometro(ventanaProceso, 0, 0, 0, 1);
                this.HiloBurbujaDescendente.start();
                this.hiloCronometro.start();
                this.dispose();
            }
        }

        //------------------------------------------METODO QUICKSORT ASCENDENTE--------------------------------------------
        if (this.rdAs.isSelected() && this.rdQuick.isSelected() && this.rdBaja.isSelected()) {
            if (e.getSource() == this.btnOrdenar) {
                this.framePrincipal.setVisible(false);
                this.setVisible(false);
                Proceso ventanaProceso = new Proceso(this.titulo, this.nombresEjes, this.arregloDatos, "Baja", "QuickSort", "Ascendente");
                this.HiloQuickSort = new QuickSort(ventanaProceso, this.arregloDatos, 800);
                this.hiloCronometro = new Cronometro(ventanaProceso, 0, 0, 0, 1);
                this.HiloQuickSort.start();
                this.hiloCronometro.start();
                this.dispose();
            }
        }

        if (this.rdAs.isSelected() && this.rdQuick.isSelected() && this.rdMedia.isSelected()) {
            if (e.getSource() == this.btnOrdenar) {
                this.framePrincipal.setVisible(false);
                this.setVisible(false);
                Proceso ventanaProceso = new Proceso(this.titulo, this.nombresEjes, this.arregloDatos, "Media", "QuickSort", "Ascendente");
                this.HiloQuickSort = new QuickSort(ventanaProceso, this.arregloDatos, 500);
                this.hiloCronometro = new Cronometro(ventanaProceso, 0, 0, 0, 1);
                this.HiloQuickSort.start();
                this.hiloCronometro.start();
                this.dispose();
            }
        }

        if (this.rdAs.isSelected() && this.rdQuick.isSelected() && this.rdAlta.isSelected()) {
            if (e.getSource() == this.btnOrdenar) {
                this.framePrincipal.setVisible(false);
                this.setVisible(false);
                Proceso ventanaProceso = new Proceso(this.titulo, this.nombresEjes, this.arregloDatos, "Alta", "QuickSort", "Ascendente");
                this.HiloQuickSort = new QuickSort(ventanaProceso, this.arregloDatos, 100);
                this.hiloCronometro = new Cronometro(ventanaProceso, 0, 0, 0, 1);
                this.HiloQuickSort.start();
                this.hiloCronometro.start();
                this.dispose();
            }
        }

        //------------------------------------------METODO QUICKSORT DESCENDENTE--------------------------------------------
        if (this.rdDes.isSelected() && this.rdQuick.isSelected() && this.rdBaja.isSelected()) {
            if (e.getSource() == this.btnOrdenar) {
                this.framePrincipal.setVisible(false);
                this.setVisible(false);
                Proceso ventanaProceso = new Proceso(this.titulo, this.nombresEjes, this.arregloDatos, "Baja", "QuickSort", "Descendente");
                this.HiloQuickSortDescendente = new QuickSortDescendente(ventanaProceso, this.arregloDatos, 800);
                this.hiloCronometro = new Cronometro(ventanaProceso, 0, 0, 0, 1);
                this.HiloQuickSortDescendente.start();
                this.hiloCronometro.start();
                this.dispose();
            }
        }

        if (this.rdDes.isSelected() && this.rdQuick.isSelected() && this.rdMedia.isSelected()) {
            if (e.getSource() == this.btnOrdenar) {
                this.framePrincipal.setVisible(false);
                this.setVisible(false);
                Proceso ventanaProceso = new Proceso(this.titulo, this.nombresEjes, this.arregloDatos, "Media", "QuickSort", "Descendente");
                this.HiloQuickSortDescendente = new QuickSortDescendente(ventanaProceso, this.arregloDatos, 500);
                this.hiloCronometro = new Cronometro(ventanaProceso, 0, 0, 0, 1);
                this.HiloQuickSortDescendente.start();
                this.hiloCronometro.start();
                this.dispose();
            }
        }

        if (this.rdDes.isSelected() && this.rdQuick.isSelected() && this.rdAlta.isSelected()) {
            if (e.getSource() == this.btnOrdenar) {
                this.framePrincipal.setVisible(false);
                this.setVisible(false);
                Proceso ventanaProceso = new Proceso(this.titulo, this.nombresEjes, this.arregloDatos, "Alta", "QuickSort", "Descendente");
                this.HiloQuickSortDescendente = new QuickSortDescendente(ventanaProceso, this.arregloDatos, 100);
                this.hiloCronometro = new Cronometro(ventanaProceso, 0, 0, 0, 1);
                this.HiloQuickSortDescendente.start();
                this.hiloCronometro.start();
                this.dispose();
            }
        }

        //------------------------------------------METODO SHELL SORT ASCENDENTE--------------------------------------------
        if (this.rdAs.isSelected() && this.rdShell.isSelected() && this.rdBaja.isSelected()) {
            if (e.getSource() == this.btnOrdenar) {
                this.framePrincipal.setVisible(false);
                this.setVisible(false);
                Proceso ventanaProceso = new Proceso(this.titulo, this.nombresEjes, this.arregloDatos, "Baja", "ShellSort", "Ascendente");
                this.hiloShellSort = new ShellSort(ventanaProceso, this.arregloDatos, 800);
                this.hiloCronometro = new Cronometro(ventanaProceso, 0, 0, 0, 1);
                this.hiloShellSort.start();
                this.hiloCronometro.start();
                this.dispose();
            }
        }

        if (this.rdAs.isSelected() && this.rdShell.isSelected() && this.rdMedia.isSelected()) {
            if (e.getSource() == this.btnOrdenar) {
                this.framePrincipal.setVisible(false);
                this.setVisible(false);
                Proceso ventanaProceso = new Proceso(this.titulo, this.nombresEjes, this.arregloDatos, "Media", "ShellSort", "Ascendente");
                this.hiloShellSort = new ShellSort(ventanaProceso, this.arregloDatos, 500);
                this.hiloCronometro = new Cronometro(ventanaProceso, 0, 0, 0, 1);
                this.hiloShellSort.start();
                this.hiloCronometro.start();
                this.dispose();
            }
        }

        if (this.rdAs.isSelected() && this.rdShell.isSelected() && this.rdAlta.isSelected()) {
            if (e.getSource() == this.btnOrdenar) {
                this.framePrincipal.setVisible(false);
                this.setVisible(false);
                Proceso ventanaProceso = new Proceso(this.titulo, this.nombresEjes, this.arregloDatos, "Alta", "ShellSort", "Ascendente");
                this.hiloShellSort = new ShellSort(ventanaProceso, this.arregloDatos, 100);
                this.hiloCronometro = new Cronometro(ventanaProceso, 0, 0, 0, 1);
                this.hiloShellSort.start();
                this.hiloCronometro.start();
                this.dispose();
            }
        }

        //------------------------------------------METODO SHELL SORT DESCENDENTE--------------------------------------------
        if (this.rdDes.isSelected() && this.rdShell.isSelected() && this.rdBaja.isSelected()) {
            if (e.getSource() == this.btnOrdenar) {
                this.framePrincipal.setVisible(false);
                this.setVisible(false);
                Proceso ventanaProceso = new Proceso(this.titulo, this.nombresEjes, this.arregloDatos, "Baja", "ShellSort", "Descendente");
                this.hiloShellSortDescendente = new ShellSortDescendente(ventanaProceso, this.arregloDatos, 800);
                this.hiloCronometro = new Cronometro(ventanaProceso, 0, 0, 0, 1);
                this.hiloShellSortDescendente.start();
                this.hiloCronometro.start();
                this.dispose();
            }
        }

        if (this.rdDes.isSelected() && this.rdShell.isSelected() && this.rdMedia.isSelected()) {
            if (e.getSource() == this.btnOrdenar) {
                this.framePrincipal.setVisible(false);
                this.setVisible(false);
                Proceso ventanaProceso = new Proceso(this.titulo, this.nombresEjes, this.arregloDatos, "Media", "ShellSort", "Descendente");
                this.hiloShellSortDescendente = new ShellSortDescendente(ventanaProceso, this.arregloDatos, 500);
                this.hiloCronometro = new Cronometro(ventanaProceso, 0, 0, 0, 1);
                this.hiloShellSortDescendente.start();
                this.hiloCronometro.start();
                this.dispose();
            }
        }

        if (this.rdDes.isSelected() && this.rdShell.isSelected() && this.rdAlta.isSelected()) {
            if (e.getSource() == this.btnOrdenar) {
                this.framePrincipal.setVisible(false);
                this.setVisible(false);
                Proceso ventanaProceso = new Proceso(this.titulo, this.nombresEjes, this.arregloDatos, "Alta", "ShellSort", "Descendente");
                this.hiloShellSortDescendente = new ShellSortDescendente(ventanaProceso, this.arregloDatos, 100);
                this.hiloCronometro = new Cronometro(ventanaProceso, 0, 0, 0, 1);
                this.hiloShellSortDescendente.start();
                this.hiloCronometro.start();
                this.dispose();
            }
        }
    }
}
