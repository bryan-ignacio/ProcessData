package ventanas;

import clases.ArregloDatos;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author ignacio
 */
public class Acceso extends JFrame implements ActionListener {
    
    private JPanel principal;
    private JLabel etiqueta;
    private JButton btnNo, btnSi;
    //atributos que pasaran como parametro.
    private String titulo;
    private String nombreEjes;
    private ArregloDatos arregloDatos;
    private JFrame framePrincipal;
    
    /**
     * inicializamos nuestros atributos con los modificados en la ventana anterior.
     * @param titulo
     * @param nombreEjes
     * @param arregloDatos 
     */
    public Acceso(JFrame framePrincipal, String titulo, String nombreEjes, ArregloDatos arregloDatos) {
        this.framePrincipal = framePrincipal;
        this.titulo = titulo;
        this.nombreEjes = nombreEjes;
        this.arregloDatos = arregloDatos;
        initComponents();
    }
    
    /**
     * inicializa todos los componentes de nuestra interfaz.
     */
    private void initComponents(){
        setVentana();
        addPanel();
        addLabel();
        addBotones();
    }
    
    
    /**
     * modifica nuestra jframe.
     */
    private void setVentana() {
        this.setSize(300, 150);
        this.setVisible(true);
        this.setLayout(null);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    /**
     * agrega el panel al jframe.
     */
    private void addPanel() {
        this.principal = Utilidades.crearPanel(Utilidades.fondoDos, 0, 0, 300, 150);
        this.getContentPane().add(this.principal);
    }
    /**
     * agrega las etiquetas al panel.
     */
    private void addLabel() {
        this.etiqueta = Utilidades.crearEtiqueta("¿Ordenar Gráfica?", 80, 10, 140, 40, SwingConstants.CENTER);
        this.principal.add(this.etiqueta);
    }
    
    /**
     * agregar los botones al panel.
     */
    private void addBotones() {
        this.btnNo = Utilidades.crearBoton("No", 40, 70, 100, 40);
        this.principal.add(this.btnNo);
        this.btnNo.addActionListener(this);
        this.btnSi = Utilidades.crearBoton("Si", 160, 70, 100, 40);
        this.principal.add(this.btnSi);
        this.btnSi.addActionListener(this);
    }   
    
    /**
     * interaccion de los eventos con la interfaz.
     * @param e 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btnNo) {
            Principal p = new Principal();
            p.setVisible(true);
        }
        if (e.getSource() == this.btnSi) {
            new Opciones(this.framePrincipal, this.titulo, this.nombreEjes, this.arregloDatos);
            this.arregloDatos.mostrarElementos();
            System.out.println("nombre ejes: " + this.nombreEjes);
            System.out.println("titulo en acceso: " + this.titulo);
            this.setVisible(false);
            this.dispose();
        }
    }
}