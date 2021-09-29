package ventanas;

import clases.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import static java.lang.Thread.sleep;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author ignacio
 */
public class Utilidades {

    public static Color fondo = new Color(0, 102, 246);
    public static Color fondoDos = new Color(211, 228, 225);
    public static String nombre = "Bryan Cristhopher Ignacio Xoy 201602516";

    public static JPanel crearPanel(Color argColor, int cx, int cy, int ancho, int alto) {
        JPanel tempPanel = new JPanel();
        tempPanel.setBounds(cx, cy, ancho, alto);
        tempPanel.setBackground(argColor);
        tempPanel.setLayout(null);
        return tempPanel;
    }

    public static JLabel crearEtqImagen(String ruta, int cx, int cy, int ancho, int alto) {
        JLabel tempLabel = new JLabel();
        tempLabel.setBounds(cx, cy, ancho, alto);
        ImageIcon icon = new ImageIcon(ruta);
        tempLabel.setOpaque(false);
        tempLabel.setIcon(new ImageIcon(icon.getImage().getScaledInstance(tempLabel.getWidth(), tempLabel.getHeight(), Image.SCALE_SMOOTH)));
        return tempLabel;
    }

    public static JLabel crearEtiqueta(String texto, int cx, int cy, int ancho, int alto, int constant) {
        JLabel tempLabel = new JLabel(texto, constant);
        tempLabel.setBounds(cx, cy, ancho, alto);
        tempLabel.setOpaque(false);
        return tempLabel;
    }

    public static JButton crearBoton(String titulo, int cx, int cy, int ancho, int alto) {
        JButton tempButton = new JButton(titulo);
        tempButton.setBounds(cx, cy, ancho, alto);
        return tempButton;
    }

    public static JTextField crearField(int cx, int cy, int ancho, int alto) {
        JTextField tempField = new JTextField();
        tempField.setBounds(cx, cy, ancho, alto);
        return tempField;
    }

    public static JPasswordField crearPasswordField(int cx, int cy, int ancho, int alto) {
        JPasswordField tempJP = new JPasswordField();
        tempJP.setBounds(cx, cy, ancho, alto);
        return tempJP;
    }

    public static JComboBox crearComboBox(Object[] elementos, int cx, int cy, int ancho, int alto) {
        JComboBox tempCombo = new JComboBox(elementos);
        tempCombo.setBounds(cx, cy, ancho, alto);
        return tempCombo;
    }

    public static JFileChooser crearFileChooser() {
        JFileChooser tempChooser = new JFileChooser();
        return tempChooser;
    }

    public static void interaccionBoton(JButton btn, Color cbtn, Color cpbtn) {
        btn.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                btn.setBackground(cpbtn);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                btn.setBackground(cbtn);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
    }

    public static JTextArea crearTextArea(int cx, int cy, int ancho, int alto) {
        JTextArea tempArea = new JTextArea();
        tempArea.setBounds(cx, cy, ancho, alto);
        return tempArea;
    }

    public static JTable crearTable(String[] columnas, Object[][] datos, int cx, int cy, int ancho, int alto) {
        JTable tempTable = new JTable(datos, columnas);
        return tempTable;
    }

    public static String leerArchivo(String ruta) throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(ruta));
        String linea = "";
        String contenido = "";
        while ((linea = br.readLine()) != null) {
            contenido += linea + "\n";
        }
        br.close();
        return contenido;
    }

    public static int contarFilasPrimerArchivo(String contenido) throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(contenido));
        String linea = "";
        int filas = 0;
        while ((linea = br.readLine()) != null) {
            filas++;
        }
        return filas;
    }

    /**
     * Para la carga masiva voy a utilizar un for normal que comenzara en uno
     * sin contar el 0 ya que el cero lo voy a guardar en un string. Solo voy a
     * cargar desde el uno.
     *
     * @param contenido
     * @param arregloDatos
     */
    public static void cargaMasivaDatos(String contenido, ArregloDatos arregloDatos) throws ArrayIndexOutOfBoundsException {

        String[] datos = contenido.split("\n");

        //voy a recorrer el arreglo datos quitandole el primer elemento.
        for (int i = 1; i < datos.length; i++) {

            String[] atributos = datos[i].split(",");

            String nombre = atributos[0];
            //int cantidad = Integer.parseInt(atributos[1]);
                Double cantidad = Double.parseDouble(atributos[1]);
                Dato temp = new Dato(nombre, cantidad);
                arregloDatos.insertar(temp);
        }
    }

    public static String leerArchivoTextField(String ruta) throws FileNotFoundException {
        String cadena = "";
        Scanner sc = new Scanner(new File(ruta));
        while (sc.hasNextLine()) {
            cadena += sc.nextLine() + "\n";
        }
        sc.close();
        return cadena;
    }

    public static int contarFilasTextField(String ruta) throws FileNotFoundException {
        String cadena = "";
        int fila = 0;
        Scanner sc = new Scanner(new File(ruta));
        while (sc.hasNextLine()) {
            cadena += sc.nextLine() + "\n";
            fila++;
        }
        sc.close();
        return fila;
    }

    public static String getPrimeraFilaArchivo(String ruta) throws FileNotFoundException, IOException {
        BufferedReader br = new BufferedReader(new FileReader(ruta));
        String linea = "";
        linea = br.readLine();
        br.close();
        return linea;
    }

    public static void metodoBurbuja(ArregloDatos arr, Proceso ventana, long pausa, int pasos) {
        int i, j;
        //j inicia en el ultimo indice del arreglo, 
        //se mueve una posicion hacia la izquierda cada vez que culmina una iteracion
        for (j = arr.numElements - 1; j > 0; j--) {
            for (i = 0; i < j; i++) {
                try {
                    sleep(pausa);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Utilidades.class.getName()).log(Level.SEVERE, null, ex);
                }
                ventana.actualizarDatos(arr, pasos);
                pasos++;
                if (arr.valorElemento(i).getCantidad() > arr.valorElemento(i + 1).getCantidad()) {
                    //guarda en la var temp el elemento en la posicion 0
                    //int temp = arregloDatos.valorElemento(i).getCantidad();
                    Double temp = arr.valorElemento(i).getCantidad();
                    //intercambiamos elementos se le asigna el elemento en la posicion i a la posicion i + 1
                    arr.valorElemento(i).setCantidad(arr.valorElemento(i + 1).getCantidad());
                    //se le asigna a la posicion i + 1 el elemento de la variable temporal. 
                    arr.valorElemento(i + 1).setCantidad(temp);
                    //CAMBIAR LOS NOMBRES.
                    String nombreTemp = arr.valorElemento(i).getNombre();
                    arr.valorElemento(i).setNombre(arr.valorElemento(i + 1).getNombre());
                    arr.valorElemento(i + 1).setNombre(nombreTemp);
                }
            }
        }
    }

    public static void metodoBurbujaDescendente(ArregloDatos arr, Proceso ventana, long pausa, int pasos) {
        int i, j;
        //j inicia en el ultimo indice del arreglo, 
        //se mueve una posicion hacia la izquierda cada vez que culmina una iteracion
        for (j = arr.numElements - 1; j > 0; j--) {

            for (i = 0; i < j; i++) {

                try {
                    sleep(pausa);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Utilidades.class.getName()).log(Level.SEVERE, null, ex);
                }
                ventana.actualizarDatos(arr, pasos);
                pasos++;

                if (arr.valorElemento(i).getCantidad() < arr.valorElemento(i + 1).getCantidad()) {
                    //guarda en la var temp el elemento en la posicion 0
                    //int temp = arregloDatos.valorElemento(i).getCantidad();
                    Double temp = arr.valorElemento(i).getCantidad();
                    //intercambiamos elementos se le asigna el elemento en la posicion i a la posicion i + 1
                    arr.valorElemento(i).setCantidad(arr.valorElemento(i + 1).getCantidad());
                    //se le asigna a la posicion i + 1 el elemento de la variable temporal. 
                    arr.valorElemento(i + 1).setCantidad(temp);

                    //-----------------------------------------------------------------------------------------------
                    //CAMBIAR LOS NOMBRES.
                    String nombreTemp = arr.valorElemento(i).getNombre();

                    arr.valorElemento(i).setNombre(arr.valorElemento(i + 1).getNombre());

                    arr.valorElemento(i + 1).setNombre(nombreTemp);
                }
            }
        }
    }

    public static void metodoQuickSort(ArregloDatos arr, int inicio, int fin, Proceso ventana, long pausa, int pasos) {
        int i = inicio;
        int f = fin;
        int mitad = (i + f) / 2;
        Double pivote = arr.valorElemento(mitad).getCantidad();
        Double aux;
        do {
            pasos++;
            //mientras que arreglo en la posicion inicial sea menor que el pivote hacer esto..                
            while (arr.valorElemento(i).getCantidad() < pivote) {
                i++; //aumentamos inicio en uno. pasa al otro elemento del arreglo.
            }
            while (arr.valorElemento(f).getCantidad() > pivote) {
                f--; //decrementamos final en uno, pasa al elemento anterior del arreglo.
            }

            if (i <= f) {
                aux = arr.valorElemento(i).getCantidad();
                Double temp = arr.valorElemento(f).getCantidad();
                arr.valorElemento(i).setCantidad(temp);
                arr.valorElemento(f).setCantidad(aux);

                String auxName = arr.valorElemento(i).getNombre();
                String tempName = arr.valorElemento(f).getNombre();
                arr.valorElemento(i).setNombre(tempName);
                arr.valorElemento(f).setNombre(auxName);
                i++;
                f--;
            }
            try {
                sleep(pausa);
            } catch (InterruptedException ex) {
                Logger.getLogger(Utilidades.class.getName()).log(Level.SEVERE, null, ex);
            }
            ventana.actualizarDatos(arr, pasos);
            //pasos++;
        } while (i <= f);
        if (inicio < f) {
            metodoQuickSort(arr, inicio, f, ventana, pausa, pasos);
        }
        if (i < fin) {
            metodoQuickSort(arr, i, fin, ventana, pausa, pasos);
        }
    }

    public static void metodoQuickSortDescendente(ArregloDatos arr, int inicio, int fin, Proceso ventana, long pausa, int pasos) {
        int i = inicio;
        int f = fin;
        int mitad = (i + f) / 2;
        Double pivote = arr.valorElemento(mitad).getCantidad();
        Double aux;
        do {
            pasos++;
            //mientras que arreglo en la posicion inicial sea menor que el pivote hacer esto..                
            while (arr.valorElemento(i).getCantidad() > pivote) {
                i++; //aumentamos inicio en uno. pasa al otro elemento del arreglo.
            }
            while (arr.valorElemento(f).getCantidad() < pivote) {
                f--; //decrementamos final en uno, pasa al elemento anterior del arreglo.
            }

            if (i <= f) {
                aux = arr.valorElemento(i).getCantidad();
                Double temp = arr.valorElemento(f).getCantidad();
                arr.valorElemento(i).setCantidad(temp);
                arr.valorElemento(f).setCantidad(aux);

                String auxName = arr.valorElemento(i).getNombre();
                String tempName = arr.valorElemento(f).getNombre();
                arr.valorElemento(i).setNombre(tempName);
                arr.valorElemento(f).setNombre(auxName);
                i++;
                f--;
            }
            try {
                sleep(pausa);
            } catch (InterruptedException ex) {
                Logger.getLogger(Utilidades.class.getName()).log(Level.SEVERE, null, ex);
            }
            ventana.actualizarDatos(arr, pasos);
            //pasos++;

        } while (i <= f);
        if (inicio < f) {
            metodoQuickSortDescendente(arr, inicio, f, ventana, pausa, pasos);
        }
        if (i < fin) {
            metodoQuickSortDescendente(arr, i, fin, ventana, pausa, pasos);
        }

        System.out.println("");
        arr.mostrarElementos();
    }

    public static void metodoShell(ArregloDatos arreglo, Proceso ventana, long pausa, int pasos) {
        int i;
        int j;
        int k;
        int salto = arreglo.numElements / 2;
        while (salto > 0) {
            for (i = salto; i < arreglo.numElements; i++) {
                j = i - salto;
                while (j >= 0) {
                    k = j + salto;
                    if (arreglo.valorElemento(j).getCantidad() <= arreglo.valorElemento(k).getCantidad()) {
                        break;
                    } else {
                        Double aux = arreglo.valorElemento(j).getCantidad();
                        Double tempk = arreglo.valorElemento(k).getCantidad();
                        arreglo.valorElemento(j).setCantidad(tempk);
                        arreglo.valorElemento(k).setCantidad(aux);

                        String auxString = arreglo.valorElemento(j).getNombre();
                        String tempString = arreglo.valorElemento(k).getNombre();
                        arreglo.valorElemento(j).setNombre(tempString);
                        arreglo.valorElemento(k).setNombre(auxString);

                        j -= salto;
                    }
                    try {
                        sleep(pausa);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Utilidades.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    ventana.actualizarDatos(arreglo, pasos);
                    pasos++;
                }
            }
            salto = salto / 2;
        }
    }

    public static void metodoShellDescendente(ArregloDatos arreglo, Proceso ventana, long pausa, int pasos) {
        int i;
        int j;
        int k;
        int salto = arreglo.numElements / 2;
        while (salto > 0) {
            for (i = salto; i < arreglo.numElements; i++) {
                j = i - salto;
                while (j >= 0) {
                    k = j + salto;
                    if (arreglo.valorElemento(j).getCantidad() >= arreglo.valorElemento(k).getCantidad()) {
                        break;
                    } else {
                        Double aux = arreglo.valorElemento(j).getCantidad();
                        Double tempk = arreglo.valorElemento(k).getCantidad();
                        arreglo.valorElemento(j).setCantidad(tempk);
                        arreglo.valorElemento(k).setCantidad(aux);

                        String auxString = arreglo.valorElemento(j).getNombre();
                        String tempString = arreglo.valorElemento(k).getNombre();
                        arreglo.valorElemento(j).setNombre(tempString);
                        arreglo.valorElemento(k).setNombre(auxString);

                        j -= salto;
                    }
                    try {
                        sleep(pausa);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Utilidades.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    ventana.actualizarDatos(arreglo, pasos);
                    pasos++;
                }
            }
            salto = salto / 2;
        }
    }
}
