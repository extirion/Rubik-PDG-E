package controlador;

import java.awt.Color;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JTextField;

import interfaz.*;
import mundo.*;

/**
 * Realiza la conexion entre la interfaz y el mundo del problema.
 *
 * @version 1.0.0.
 * @author Giovanni Fajardo Utria.
 */
public class Controlador {
    // Objetos controlables de la interfaz 	

    private PanelCaras pnlIzquierdo, pnlFrontal, pnlDerecho;
    private PanelCaras pnlPosterior, pnlSuperior, pnlInferior;
    private PanelBotones pnlHorizontal, pnlTransversal, pnlVertical;

    // Clase del mundo
    Rubik rubik;

    // Constructor	
    /**
     * Constructor por defecto. Crea la instancia del mundo.
     */
    public Controlador() { // Instanciar la clase principal del modelo
        this.rubik = new Rubik();
    }

    /**
     * Recibe las referencias de los objetos a controlar de la interfaz.
     *
     * @param pnlIzquierdo cara izquierda
     * @param pnlFrontal	cara frontal
     * @param pnlDerecho	cara derecha
     * @param pnlPosterior	cara posterior
     * @param pnlSuperior	cara superior
     * @param pnlInferior	cara inferior
     *
     * @param pnlHorizontal	grupo de botones para realizar los movimientos
     * horizontales
     * @param pnlTransversal	grupo de botones para realizar los movimientos
     * transversales
     * @param pnlVertical	grupo de botones para realizar los movimientos
     * verticales
     */
    public void conectar(PanelCaras pnlIzquierdo, PanelCaras pnlFrontal, PanelCaras pnlDerecho, PanelCaras pnlPosterior, PanelCaras pnlSuperior, PanelCaras pnlInferior, PanelBotones pnlHorizontal, PanelBotones pnlTransversal, PanelBotones pnlVertical) {
        // Paneles de caras  
        this.pnlIzquierdo = pnlIzquierdo;
        this.pnlFrontal = pnlFrontal;
        this.pnlDerecho = pnlDerecho;
        this.pnlPosterior = pnlPosterior;
        this.pnlSuperior = pnlSuperior;
        this.pnlInferior = pnlInferior;

        // Visualiza las caras	 
        this.visualizar(this.rubik);

        // Paneles de botones
        this.pnlHorizontal = pnlHorizontal;
        this.pnlTransversal = pnlTransversal;
        this.pnlVertical = pnlVertical;

    }

    // --------------------------------------------------------------	
    // Implementacion de los metodos de la clase controladora	
    // --------------------------------------------------------------
    /**
     * Realiza la conversion visual del mundo tridimensional,a formato
     * bidimensional.
     */
    public void visualizar(Rubik rubik) {
        ArrayList cara;
        int l;

        this.rubik = rubik;
        cara = pnlIzquierdo.getCara();
        l = 0;
        for (int i = 0; i < 2; i++) {
            for (int k = 1; k >= 0; k--) {
                ((JTextField) cara.get(l++)).setBackground(rubik.cubo(i, 0, k).getIzquierda());
            }
        }

        cara = pnlFrontal.getCara();
        l = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                ((JTextField) cara.get(l++)).setBackground(rubik.cubo(i, j, 0).getFrontal());
            }
        }

        cara = pnlDerecho.getCara();
        l = 0;
        for (int i = 0; i < 2; i++) {
            for (int k = 0; k < 2; k++) {
                ((JTextField) cara.get(l++)).setBackground(rubik.cubo(i, 1, k).getDerecha());
            }
        }

        cara = pnlPosterior.getCara();
        l = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 1; j >= 0; j--) {
                ((JTextField) cara.get(l++)).setBackground(rubik.cubo(i, j, 1).getPosterior());
            }
        }

        cara = pnlSuperior.getCara();
        l = 0;
        for (int k = 1; k >= 0; k--) {
            for (int j = 0; j < 2; j++) {
                ((JTextField) cara.get(l++)).setBackground(rubik.cubo(0, j, k).getSuperior());
            }
        }

        cara = pnlInferior.getCara();
        l = 0;
        for (int k = 0; k < 2; k++) {
            for (int j = 0; j < 2; j++) {
                ((JTextField) cara.get(l++)).setBackground(rubik.cubo(1, j, k).getInferior());
            }
        }
    }

    // --------------------------------------------------------------	
    // Implementaci�n de los requerimientos de usuario	
    // --------------------------------------------------------------
    /**
     * Invoca los metodos del mundo que realizan los requerimientos.
     *
     * @param quePanel identifica el tipo de movimiento horizonal, transversal o
     * vertical.
     * @param disco	disco sobre el cual se debe ejecutar el movimiento.
     */
    public void movimiento(PanelBotones quePanel, int disco, boolean tipo) {
        if (tipo == false) {
            if (pnlHorizontal == quePanel) {
                rubik.horizontal(disco);
            } else if (pnlVertical == quePanel) {
                rubik.vertical(disco);
            } else {
                if (disco == 0) {
                    rubik.transversal(1);
                } else {
                    rubik.transversal(0);
                }
            }
        }else{
            if (pnlHorizontal == quePanel) {
                rubik.horizontal_negativo(disco);
            } else if (pnlVertical == quePanel) {
                rubik.vertical_negativo(disco);
            } else {
                if (disco == 0) {
                    rubik.transversal_negativo(1);
                } else {
                    rubik.transversal_negativo(0);
                }
            }
        }
        this.visualizar(this.rubik);
    }

    /*
    Esta es la funcion que utilizaremos para darle solucion al cubo rubik el unico parametro que resibe desde 
    la interfaz es el estado del chekbox con tal de saber si se va a resolver por medio del desplazamiento 
    normal de los discos(en sentido de las manesillas del relos) o de manera contraria, de resot los paramentros 
    que requeriela funcion movimiento que esta arriba los podemos asignar dentro de esta funcion.
    */
    public void solucionador(boolean tipo) {
        if(tipo == true){
            System.out.println("negativo seleccioando");
        }
        System.out.println("Ejecutar solucion");
    }

}
