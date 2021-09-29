package ventanas;

import clases.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.logging.*;
import javax.swing.*;
import org.jfree.chart.*;
import org.jfree.chart.plot.*;
import org.jfree.data.category.*;

/**
 *
 * @author ignacio
 */
public class Principal extends JFrame implements ActionListener {

    //ATRIBUTOS PARA LA VENTANA.
    private JPanel primero, segundo;
    private JLabel etqRuta, etqTitulo, etqImage;
    private JTextField txtRuta, txtTitulo;
    private JButton btnBuscar, btnAceptar;
    private JFileChooser chooser;
    //ATRIBUTOS PARA EL ARCHIVO.
    private String ruta;
    private String contenido;
    private int numeroFilas;
    private ArregloDatos arregloDatos;
    //ATRIBUTOS PARA LA GRAFICA.
    private String titulo;
    private String nombresEjes;
    //ATRIBUTOS PARA EL REPORTE.
    public static String reporte = "";

    /**
     * inicializa los atributo de nuetro jframe.
     */
    public Principal() {
        initComponents();
    }

    /**
     * inicia los componentes del jframe.
     */
    private void initComponents() {
        setVentana();
        addPanel();
        addLabels();
        addFields();
        addBotones();
    }

    /**
     * modifica el jframe.
     */
    private void setVentana() {
        this.setTitle("USAC Processing Data");
        this.setSize(1100, 550);
        this.setLayout(null);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /**
     * agrega el panel al jframe.
     */
    private void addPanel() {
        this.primero = Utilidades.crearPanel(Utilidades.fondo, 0, 0, 360, 550);
        this.getContentPane().add(this.primero);
        this.segundo = Utilidades.crearPanel(Utilidades.fondoDos, 360, 0, 740, 550);
        this.getContentPane().add(this.segundo);
    }

    /**
     * agrega las etiquetas al jpanel.
     */
    private void addLabels() {
        this.etqRuta = Utilidades.crearEtiqueta("Ruta del archivo:", 20, 230, 150, 20, SwingConstants.LEFT);
        this.primero.add(this.etqRuta);
        this.etqTitulo = Utilidades.crearEtiqueta("Titulo para la grafica: ", 20, 310, 150, 20, SwingConstants.LEFT);
        this.primero.add(this.etqTitulo);
        this.etqImage = Utilidades.crearEtqImagen("imagenes/logo.png", 105, 50, 150, 150);
        this.primero.add(this.etqImage);
    }

    /**
     * agrega los jtextFields al jpanel.
     */
    private void addFields() {
        this.txtRuta = Utilidades.crearField(20, 260, 200, 40);
        this.txtRuta.addActionListener(this);
        this.primero.add(this.txtRuta);
        this.txtTitulo = Utilidades.crearField(20, 340, 320, 40);
        this.primero.add(this.txtTitulo);
    }

    /**
     * agregar los botones al jpanel.
     */
    private void addBotones() {
        this.btnBuscar = Utilidades.crearBoton("Buscar", 240, 260, 100, 40);
        this.btnBuscar.addActionListener(this);
        this.primero.add(this.btnBuscar);
        this.btnAceptar = Utilidades.crearBoton("Aceptar", 80, 400, 200, 40);
        this.btnAceptar.addActionListener(this);
        this.primero.add(this.btnAceptar);
    }

    /**
     * crea la grafica y la agrega al jpanel.
     */
    private void crearGraficaBarras() {
        DefaultCategoryDataset categoria = new DefaultCategoryDataset();
        for (int i = 0; i < this.arregloDatos.numElements; i++) {
            categoria.setValue(this.arregloDatos.valorElemento(i).getCantidad(), this.arregloDatos.valorElemento(i).getNombre(), this.arregloDatos.valorElemento(i).getNombre());
        }
        String[] ejes = this.nombresEjes.split(",");
        String nombre = ejes[0];
        String cantidad = ejes[1];
        JFreeChart freeChart = ChartFactory.createBarChart3D(titulo, nombre, cantidad, categoria, PlotOrientation.VERTICAL, true, true, false);
        ChartPanel cp = new ChartPanel(freeChart);
        cp.setBounds(15, 30, 700, 460);
        this.segundo.add(cp);
        this.segundo.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //CUANDO PRESIONAMOS EL BOTON BUSCAR.
        //si el txt esta vacio y click en buscar.
        if (e.getSource() == this.btnBuscar && this.txtRuta.getText().isEmpty()) {

            System.out.println("FUNCION CON BOTON.");

            try {
                this.chooser = new JFileChooser();

                this.chooser.showOpenDialog(this);
                //encontrar la ruta seleccionada.
                this.ruta = this.chooser.getSelectedFile().getPath();
                //se la agrego al textField.
                //this.txtRuta.setText(this.ruta);
                //leo el archivo.
                String contenido = Utilidades.leerArchivo(this.ruta);

                this.nombresEjes = Utilidades.getPrimeraFilaArchivo(this.ruta);

                System.out.println(contenido);
                //  guardo el contenido del archivo en un arreglo.
                //1. necesito contar el numero de lineas en el archivo.
                this.numeroFilas = Utilidades.contarFilasPrimerArchivo(this.ruta);
                System.out.println("numero filas: " + this.numeroFilas);
                //2. nuestro arreglo encapsulado tiene el largo del numero de filas del archivo.
                this.arregloDatos = new ArregloDatos(this.numeroFilas);

                //3. cargamos los datos del contendio al arreglo encapsulado.
                //quiere decir que se crea un dato y se inserta al arreglo.
                Utilidades.cargaMasivaDatos(contenido, this.arregloDatos);

                System.out.println("nombres Ejes: " + nombresEjes);
                //4. mostramos los elementos que contiene el arreglo de datos.
                arregloDatos.mostrarElementos();
                //5. imprimo el numero de elementos que contiene el arreglo.
                int numero = arregloDatos.numElements;

                System.out.println("numero elementos: " + numero);

                JOptionPane.showMessageDialog(null, "Archivo leido correctamente.", "Desde Boton", JOptionPane.INFORMATION_MESSAGE);
            } catch (ArrayIndexOutOfBoundsException ex) {
                    JOptionPane.showMessageDialog(this, "archivo con estructura incorrecta");
            } catch (IOException ex) {
                System.out.println("excepcion de entrada/salida." + ex.getMessage());
                JOptionPane.showMessageDialog(this, "Error de entrada/salida");
            } catch (NullPointerException ex) {
                JOptionPane.showMessageDialog(this, "Seleccione un archivo.", "", JOptionPane.ERROR_MESSAGE);
            }
        }

        //HACER ESTO SI EL FIELDS NO ESTA VACIO.
        if (!(this.txtRuta.getText().isEmpty())) {

            System.out.println("FUNCION CON TXTFIELD.");
            //1. ingresamos la ruta.
            this.ruta = txtRuta.getText();

            try {
                //2. leemos el archivo y lo guardamos en un string.
                String contenido = Utilidades.leerArchivoTextField(this.ruta);

                this.nombresEjes = Utilidades.getPrimeraFilaArchivo(this.ruta);
                //3. le damos el valor del numero de filas.
                this.numeroFilas = Utilidades.contarFilasTextField(this.ruta);
                //4. imprimimos el contenido leido del archivo.
                System.out.println(contenido);
                //5. inicializamos e instanciamos el arreglo con el numero de filas leido.
                this.arregloDatos = new ArregloDatos(this.numeroFilas);
                //6. cargamos los datos del archivo al arreglo encapsulado.
                Utilidades.cargaMasivaDatos(contenido, this.arregloDatos);
                System.out.println("nombres ejes: " + this.nombresEjes);
                //7. mostramos los elementos que contiene el arreglo.
                arregloDatos.mostrarElementos();
                //8. imprimo el numero de elementos que contiene el arreglo.
                int numero = arregloDatos.numElements;

                System.out.println("numero elementos: " + numero);

                JOptionPane.showMessageDialog(null, "Archivo leido correctamente.", "Desde TextField", JOptionPane.INFORMATION_MESSAGE);
            }catch(NullPointerException ex){
                JOptionPane.showMessageDialog(this, "Escribe una ruta correcta");
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(this, "No se encontro el archivo.", "", JOptionPane.ERROR_MESSAGE);
            } catch (IOException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        //YA ELEGIDO UNO DE LOS DOS IF DE ARRIBA VAMOS A DARLE ACEPTAR.
        //CUANDO PRESIONAMOS EL BOTON ACEPTAR.
        //ya tenemos la ruta el archivo cargado y el titulo del archivo todo esto devemos pasarselo al constructor. 
        //de la ventana Siguiente que es (ACCESO). Acceso(titulo,los datos)
        if (e.getSource() == this.btnAceptar) {
            //Si esta vacio indicarle al usuario que ingrese un titulo.
            if (this.txtTitulo.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese un titulo.", "", JOptionPane.ERROR_MESSAGE);
            } else { //Si no esta vacio entonces darle el string a el tituto.
                this.titulo = this.txtTitulo.getText();
                System.out.println("titulo: " + this.titulo);
            }
            
            
            crearGraficaBarras();
          
            //--REPORTE-----------------------------------------------------------------------------------------------------                
            agregarDatosReporte("Datos Desordenados", arregloDatos, nombresEjes);
            agregarGraficaDesordenada();
            //vamos a crear una nueva ventana Acceso.
            new Acceso(this, titulo, nombresEjes, arregloDatos);

        }
    }

    /**
     * agrega los datos obtenidos al reporte.
     *
     * @param titulo
     * @param arreglo
     * @param nombresEjes
     */
    private static void agregarDatosReporte(String titulo, ArregloDatos arreglo, String nombresEjes) {

        String[] nombres = nombresEjes.split(",");
        String tabla = "<div class=\"seccion\">\n";
        tabla += "<h1> " + titulo + "</h1>\n";
        tabla += "<table>\n";
        tabla += "<tr>\n";
        tabla += "<td>" + nombres[0] + "</td>\n";
        for (int i = 0; i < arreglo.numElements; i++) {
            tabla += "<td>" + arreglo.valorElemento(i).getNombre() + "</td>\n";
        }
        tabla += "</tr>\n";
        tabla += "<tr>\n";
        tabla += "<td>" + nombres[1] + "</td>\n";
        for (int i = 0; i < arreglo.numElements; i++) {
            tabla += "<td>" + arreglo.valorElemento(i).getCantidad() + "</td>\n";
        }
        tabla += "</tr>\n";
        tabla += "</table>\n";
        tabla += "</div>\n";
        Principal.reporte += tabla;
    }

    /**
     * agrega la grafica desordenada al reporte.
     */
    private static void agregarGraficaDesordenada() {
        String imagen = "<div align = \"center\" class=\"seccion\">\n";
        imagen += "<h1>Grafica Desordenada</h1>\n";
        imagen += "<img src=\"desordenada.jpg\" height=\"500 px\" width=\"500 px\" alt=\"\">\n";
        imagen += "</div>\n";
        Principal.reporte += imagen;
    }
}
