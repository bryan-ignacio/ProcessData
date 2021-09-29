package ventanas;

import clases.*;
import java.awt.event.*;
import java.io.*;
import java.util.logging.*;
import javax.swing.*;
import org.jfree.chart.*;
import org.jfree.chart.plot.*;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author ignacio
 */
public class Proceso extends JFrame {

    //ATRIBUTOS DEL JFRAME.
    private JPanel principal;
    private JLabel etqAlgo, etqVel, etqOrden, etqTiempo, etqPasos;
    private JLabel etqValorAlgo, etqValorVel, etqValorOrden, etqValorTiempo, etqValorPasos;
    //ATRIBUTOS QUE CONTIENEN LAS OTRAS VENTANAS PARA HACER LA GRAFICA.
    private String titulo;
    private String nombresEjes;
    private ArregloDatos arregloDatos;
    //ATRIBUTOS QUE CAMBIAN CON LOS RADIOBUTONS.
    private int pasos;
    private String velocidad;
    private String algoritmo;
    private String orden;
    private long tiempo;
    private String time;

    /**
     * inicializa los atributos de nuestra clase.
     *
     * @param titulo
     * @param nombresEjes
     * @param arregloDatos
     * @param velocidad
     * @param algoritmo
     * @param orden
     */
    public Proceso(String titulo, String nombresEjes, ArregloDatos arregloDatos, String velocidad, String algoritmo, String orden) {
        this.velocidad = velocidad;
        this.algoritmo = algoritmo;
        this.orden = orden;
        this.titulo = titulo;
        this.nombresEjes = nombresEjes;
        this.arregloDatos = arregloDatos;
        initComponents();
    }

    /**
     * inicializa los componentes del jframe.
     */
    private void initComponents() {
        setVentana();
        addPanel();
        addLabels();
        crearGrafica();
    }

    /**
     * modifica el jframe.
     */
    private void setVentana() {
        this.setTitle("Proceso de Ordenamiento");
        this.setSize(740, 700);
        this.setVisible(true);
        this.setLayout(null);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /**
     * agrega el jpanel al jframe.
     */
    private void addPanel() {
        this.principal = Utilidades.crearPanel(Utilidades.fondoDos, 0, 0, 740, 700);
        this.getContentPane().add(this.principal);
    }

    /**
     * agrega las etiquetas al jpanel.
     */
    private void addLabels() {
        this.etqAlgo = Utilidades.crearEtiqueta("Algoritmo:", 20, 20, 80, 20, SwingConstants.LEFT);
        this.principal.add(this.etqAlgo);
        this.etqVel = Utilidades.crearEtiqueta("Velocidad:", 20, 50, 80, 20, SwingConstants.LEFT);
        this.principal.add(this.etqVel);
        this.etqOrden = Utilidades.crearEtiqueta("Orden:", 20, 80, 80, 20, SwingConstants.LEFT);
        this.principal.add(this.etqOrden);
        this.etqTiempo = Utilidades.crearEtiqueta("Tiempo:", 300, 20, 80, 20, SwingConstants.LEFT);
        this.principal.add(this.etqTiempo);
        this.etqPasos = Utilidades.crearEtiqueta("Pasos: ", 300, 50, 80, 20, SwingConstants.LEFT);
        this.principal.add(this.etqPasos);
        this.etqValorAlgo = Utilidades.crearEtiqueta(this.algoritmo, 105, 20, 100, 20, SwingConstants.LEFT);
        this.principal.add(this.etqValorAlgo);
        this.etqValorVel = Utilidades.crearEtiqueta(this.velocidad, 105, 50, 80, 20, SwingConstants.LEFT);
        this.principal.add(this.etqValorVel);
        this.etqValorOrden = Utilidades.crearEtiqueta(this.orden, 105, 80, 100, 20, SwingConstants.LEFT);
        this.principal.add(this.etqValorOrden);
        //this.etqValorTiempo = Utilidades.crearEtiqueta(String.valueOf(this.tiempo) + " milisegundos", 385, 20, 120, 20, SwingConstants.LEFT);
        this.etqValorTiempo = Utilidades.crearEtiqueta(this.time, 385, 20, 120, 20, SwingConstants.LEFT);
        this.principal.add(this.etqValorTiempo);
        this.etqValorPasos = Utilidades.crearEtiqueta(String.valueOf(this.pasos), 385, 50, 80, 20, SwingConstants.LEFT);
        this.principal.add(this.etqValorPasos);
    }

    /**
     * crea la grafica y la agrega la jpanel.
     */
    private void crearGrafica() {
        DefaultCategoryDataset categoria = new DefaultCategoryDataset();
        for (int i = 0; i < this.arregloDatos.numElements; i++) {
            categoria.setValue(this.arregloDatos.valorElemento(i).getCantidad(), this.arregloDatos.valorElemento(i).getNombre(), this.arregloDatos.valorElemento(i).getNombre());
        }
        String[] ejes = this.nombresEjes.split(",");
        String nombre = ejes[0];
        String cantidad = ejes[1];

        JFreeChart freeChart = ChartFactory.createBarChart3D(titulo, nombre, cantidad, categoria, PlotOrientation.VERTICAL, true, true, false);
        ChartPanel cp = new ChartPanel(freeChart);

        try {
            //VAMOS A TOMAR LA GRAFICA DESORDENADA. METODO DE WHATSAPP-------------------------------------
            OutputStream outputStream = new FileOutputStream("desordenada.jpg") {
            };

            ChartUtilities.writeChartAsJPEG(outputStream, freeChart, 600, 460);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Proceso.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Proceso.class.getName()).log(Level.SEVERE, null, ex);
        }

        cp.setBounds(15, 120, 700, 460);
        this.principal.add(cp);
        this.principal.repaint();
    }

    private void actualizarGrafica() {
        this.principal.removeAll();
        this.principal.revalidate();
        addLabels();//////----------------------------
        DefaultCategoryDataset categoria = new DefaultCategoryDataset();

        for (int i = 0; i < this.arregloDatos.numElements; i++) {
            categoria.setValue(this.arregloDatos.valorElemento(i).getCantidad(), this.arregloDatos.valorElemento(i).getNombre(), this.arregloDatos.valorElemento(i).getNombre());
        }

        String[] ejes = this.nombresEjes.split(",");

        String nombre = ejes[0];
        String cantidad = ejes[1];

        JFreeChart freeChart = ChartFactory.createBarChart3D(titulo, nombre, cantidad, categoria, PlotOrientation.VERTICAL, true, true, false);
        ChartPanel cp = new ChartPanel(freeChart);

        try {
            //VAMOS A TOMAR LA GRAFICA ORDENADA. METODO DE WHATSAPP-------------------------------------
            OutputStream outputStream = new FileOutputStream("ordenada.jpg") {
            };

            ChartUtilities.writeChartAsJPEG(outputStream, freeChart, 600, 460);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(Proceso.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Proceso.class.getName()).log(Level.SEVERE, null, ex);
        }

        cp.setBounds(15, 120, 700, 460);
        this.principal.add(cp);
        this.principal.repaint();

    }

    /**
     * El hilo utiliza este metodo para actualizar el arreglo y el atributo
     * pasos.
     *
     * @param arregloNuevo
     * @param pasos
     */
    public void actualizarDatos(ArregloDatos arregloNuevo, int pasos) {
        this.arregloDatos = arregloNuevo;
        this.pasos = pasos;
        actualizarGrafica();
    }

    /**
     * Se utiliza con el timeMilis en Burbuja.
     * Actualiza el tiempo en la etiqueta al final.
     *
     * @param tiempo
     */
    public void actualizarTiempo(long tiempo) {
        String temp = String.valueOf(tiempo);
        this.etqValorTiempo.setText(temp + " milisegundos");
        JOptionPane.showMessageDialog(this, "Termino el ordenamiento :D");

//---REPORTE-------------------------------------------------------------------------------------------
        agregarGraficaOrdenada();
        agregarDatosActualizadosReporte("Datos Ordenados", arregloDatos, nombresEjes);
        datosMenorMayor(arregloDatos);
        datosDescendente(arregloDatos);
        datosOrdenamiento();
        try {
            generarReporte();
        } catch (IOException ex) {
            Logger.getLogger(Proceso.class.getName()).log(Level.SEVERE, null, ex);
        }

        JOptionPane.showMessageDialog(this, "Se genero el Reporte.");
    }
    
    public void actualizarEtiquetaTiempo(String tiempo) {
        time = tiempo;
    }
    
    public void agregarReporteFinal() {
         JOptionPane.showMessageDialog(this, "Termino el ordenamiento :D");

//---REPORTE-------------------------------------------------------------------------------------------
        agregarGraficaOrdenada();
        agregarDatosActualizadosReporte("Datos Ordenados", arregloDatos, nombresEjes);
        datosMenorMayor(arregloDatos);
        datosDescendente(arregloDatos);
        datosOrdenamiento();
        try {
            generarReporte();
        } catch (IOException ex) {
            Logger.getLogger(Proceso.class.getName()).log(Level.SEVERE, null, ex);
        }

        JOptionPane.showMessageDialog(this, "Se genero el Reporte.");
    }

    /**
     * agrega la grafica ordenada al reporte.
     */
    private void agregarGraficaOrdenada() {
        String imagen = "<div align = \"center\" class=\"seccion\">\n";
        imagen += "<h1>Grafica Ordenada</h1>\n";
        imagen += "<img src=\"ordenada.jpg\" height=\"500 px\" width=\"500 px\" alt=\"\">\n";
        imagen += "</div>\n";
        Principal.reporte += imagen;
    }

    /**
     * agrega los datos actualizados al reporte.
     *
     * @param titulo
     * @param arreglo
     * @param nombresEjes
     */
    private void agregarDatosActualizadosReporte(String titulo, ArregloDatos arreglo, String nombresEjes) {
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
     * genera el reporte en la carpeta del proyecto.
     *
     * @throws IOException
     */
    private void generarReporte() throws IOException {
        String link = "<link rel=\"stylesheet\" href=\"estilo.css\" href=\"https://fonts.googleapis.com/css?family=Handlee\"";
        String cabeza = "<head>" + link + "</head>\n";
        String cuerpo = "<body>" + "<div class=\"seccion\">\n" + "<h1>Bryan Cristhopher Ignacio Xoy 201602516</h1>\n" + "</div>\n" + Principal.reporte + "</body>\n";
        String html = "<html>\n" + cabeza + cuerpo + "</html>\n";
        BufferedWriter bw = new BufferedWriter(new FileWriter("Reporte.html", false));
        bw.write(html);
        bw.close();
        System.out.println("Reporte generado: Reporte.html");
    }

    /**
     * agrega los datos mayor y menor del ordenamiento ascendente al reporte.
     *
     * @param arreglo
     */
    private void datosMenorMayor(ArregloDatos arreglo) {
        String datos = "<div class=\"seccion\">\n";
        datos += "<h1>Dato Mayor y Menor Ascendente</h1>\n";
        datos += "<table>\n" + "<tr>\n";
        datos += "<td colspan=\"2\" bgcolor=\"#b2bec3\">Dato Menor</td>\n" + "</tr>\n";
        datos += " <tr>\n" + "<td>" + arreglo.valorElemento(0).getNombre() + "</td>\n";
        datos += "<td>" + arreglo.valorElemento(0).getCantidad() + "</td>\n";
        datos += "</tr>\n";
        datos += "</table>\n";
        datos += " <br>\n" + "<br>\n";
        datos += "<table>\n" + "<tr>\n";
        datos += "<td colspan=\"2\" bgcolor=\"#b2bec3\">Dato Mayor</td>\n" + "</tr>\n";
        datos += " <tr>\n" + "<td>" + arreglo.valorElemento(arreglo.numElements - 1).getNombre() + "</td>\n";
        datos += "<td>" + arreglo.valorElemento(arreglo.numElements - 1).getCantidad() + "</td>\n";
        datos += "</tr>\n";
        datos += "</table>\n" + " </div>";
        Principal.reporte += datos;
    }

    /**
     * agrega los datos mayor y menor del ordenamiento descendentes al reporte.
     *
     * @param arreglo
     */
    private void datosDescendente(ArregloDatos arreglo) {
        String datos = "<div class=\"seccion\">\n";
        datos += "<h1>Dato Mayor y Menor Descendente</h1>\n";
        datos += "<table>\n" + "<tr>\n";
        datos += "<td colspan=\"2\" bgcolor=\"#b2bec3\">Dato Mayor</td>\n" + "</tr>\n";
        datos += " <tr>\n" + "<td>" + arreglo.valorElemento(0).getNombre() + "</td>\n";
        datos += "<td>" + arreglo.valorElemento(0).getCantidad() + "</td>\n";
        datos += "</tr>\n";
        datos += "</table>\n";
        datos += " <br>\n" + "<br>\n";
        datos += "<table>\n" + "<tr>\n";
        datos += "<td colspan=\"2\" bgcolor=\"#b2bec3\">Dato Menor</td>\n" + "</tr>\n";
        datos += " <tr>\n" + "<td>" + arreglo.valorElemento(arreglo.numElements - 1).getNombre() + "</td>\n";
        datos += "<td>" + arreglo.valorElemento(arreglo.numElements - 1).getCantidad() + "</td>\n";
        datos += "</tr>\n";
        datos += "</table>\n" + " </div>";
        Principal.reporte += datos;
    }

    /**
     * agrega los datos obtenidos del ordenamiento al reporte.
     */
    private void datosOrdenamiento() {
        String datos = "<div align class=\"seccion\">\n" + "<h1>Datos del Ordenamiento</h1>\n";
        datos += "<h2>Algoritmo: </h2>\n" + "<p>" + this.algoritmo + "</p>\n";
        datos += "<h2>Velocidad: </h2>\n" + "<p>" + this.velocidad + "</p>\n";
        datos += "<h2>Orden: </h2>\n" + "<p>" + this.orden + "</p>\n";
        datos += "<h2>Tiempo: </h2\n>" + "<p>" + this.etqValorTiempo.getText() + "</p>\n";
        datos += "<h2>Pasos: </h2\n>" + "<p>" + this.pasos + "</p>\n";
        Principal.reporte += datos;
    }
}
