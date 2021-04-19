package interfaz;

import java.awt.*;
import javax.swing.*;
import controlador.*;
import java.awt.event.ActionEvent;
import util.Util;

/**
 * Interfaz principal de la aplicacion.
 *
 * @author Giovanni Fajardo Utria.
 * @version 1.0.0
 */
public class InterfazApp extends JFrame {
    // Atributos de la clase.

    private Controlador ctrlControlador;
    private PanelCaras pnlIzquierdo, pnlFrontal, pnlDerecho;
    private PanelCaras pnlPosterior, pnlSuperior, pnlInferior;
    private PanelBotones pnlHorizontal, pnlTransversal, pnlVertical;
    private JButton jbSolucion;
    private JLabel jlSubtitulo, jlSolucion;
    private JMenuBar mbrAcerca;
    private JMenu mnuAcerca;
    private JMenuItem mitAcerca;
    public JCheckBox negativo;

    // Constructor.
    /**
     * Instancia los paneles de control de la aplicacion.
     *
     * @param ctrlControlador, referencia del controlador.
     */
    InterfazApp(Controlador ctrlControlador) {
        setTitle("Cubo de Erno Rubik 2*2");
        getContentPane().setLayout(null);

        // Integra el Controlador. 
        this.ctrlControlador = ctrlControlador;

        // Instancia controles de menu
        mbrAcerca = new JMenuBar();
        mnuAcerca = new JMenu();
        mitAcerca = new JMenuItem();

        mnuAcerca.setMnemonic('A');
        mnuAcerca.setText("Acerca de");

        mitAcerca.setMnemonic('G');
        mitAcerca.setText("Preparado por: Giovanni Fajardo Utria.");

        mnuAcerca.add(mitAcerca);
        mbrAcerca.add(mnuAcerca);
        setJMenuBar(mbrAcerca);

        // Instancia los paneles-movimientos
        pnlHorizontal = new PanelBotones(this.ctrlControlador, 2, 1);
        pnlHorizontal.setBounds(39, 180, 30, 100);
        pnlHorizontal.setToolTipText(" Horizontal ");

        pnlTransversal = new PanelBotones(this.ctrlControlador, 2, 1);
        pnlTransversal.setBounds(140, 79, 30, 100);
        pnlTransversal.setToolTipText(" Transversal ");

        pnlVertical = new PanelBotones(this.ctrlControlador, 1, 2);
        pnlVertical.setBounds(171, 48, 100, 30);
        pnlVertical.setToolTipText(" Vertical ");

        // Instancia los paneles-caras.    
        pnlSuperior = new PanelCaras();
        pnlSuperior.setBounds(171, 79, 100, 100);

        pnlIzquierdo = new PanelCaras();
        pnlIzquierdo.setBounds(70, 180, 100, 100);

        pnlFrontal = new PanelCaras();
        pnlFrontal.setBounds(171, 180, 100, 100);

        pnlDerecho = new PanelCaras();
        pnlDerecho.setBounds(272, 180, 100, 100);

        pnlPosterior = new PanelCaras();
        pnlPosterior.setBounds(373, 180, 100, 100);

        pnlInferior = new PanelCaras();
        pnlInferior.setBounds(171, 281, 100, 100);

        jbSolucion = new JButton(" Solucionar ");
        jbSolucion.setBounds(10, 400, 140, 25);
        jbSolucion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ctrlControlador.solucionador(negativo.isSelected());
                jlSolucion.setText("Solucion ejecutada");
            }
        });
        jbSolucion.setToolTipText(" Solucionar ");

        jlSubtitulo = new JLabel("SOLUCION: ");
        jlSubtitulo.setBounds(10, 440, 140, 25);

        jlSolucion = new JLabel("H0T1H0H1V1V0H0");
        jlSolucion.setBounds(125, 470, 400, 25);
        jlSolucion.setFont(new Font("Tahoma", Font.PLAIN, 25));

        negativo = new JCheckBox(" Negativo ");
        negativo.setBounds(350, 400, 140, 25);

        // Organizar el panel principal.       
        getContentPane().add(pnlSuperior);
        getContentPane().add(pnlIzquierdo);
        getContentPane().add(pnlFrontal);
        getContentPane().add(pnlDerecho);
        getContentPane().add(pnlPosterior);
        getContentPane().add(pnlInferior);
        getContentPane().add(pnlHorizontal);
        getContentPane().add(pnlTransversal);
        getContentPane().add(pnlVertical);
        getContentPane().add(jbSolucion);
        getContentPane().add(jlSubtitulo);
        getContentPane().add(jlSolucion);
        getContentPane().add(negativo);

        // Conecta los objetos a controlar.   
        this.ctrlControlador.conectar(pnlIzquierdo, pnlFrontal, pnlDerecho, pnlPosterior, pnlSuperior, pnlInferior, pnlHorizontal, pnlTransversal, pnlVertical);

        // Establece atributos del frame.   
        setSize(500, 570);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Util.centrarVentana(this);
    }

    /**
     * Inicia la ejecuci�n de la aplicaci�n. Instancia el controlador y
     * posteriormente la interfaz.
     */
    public static void main(String args[]) {
        InterfazApp frmMain = new InterfazApp(new Controlador());
        frmMain.setVisible(true);
    }

}
