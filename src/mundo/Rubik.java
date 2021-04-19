/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Rubik.java,v 1.0 2007/04/14
 * Universidad Piloto de Colombia (Bogot� - Colombia)
 * Programa de Ingenieria de Sistemas
 * Licenciado bajo el esquema Academic Free License version 2.1
 *
 * Ejercicio: Erno Rubik
 * Autor:Giovanni Fajardo Utria - Abril 14, 2007
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package mundo;

import java.awt.Color;
import java.io.Serializable;
import controlador.Controlador;

/**
 * Class Rubik define la abstraccion del cubo de Erno Rubik.
 *
 * @author Giovanni Fajardo Utria.
 * @version 1.0.0
 */
public class Rubik implements Serializable, Cloneable {

    // Atributos
    /**
     * Contenedor de 8 cubos basicos.
     * Cadena de carcteres que almacena el ultimo movimiento que se realizo sobre el cubo
     */
    private Cube ernoRubik[][][];
    private String ultimo_movimiento;

    // Constructores
    /**
     * Constructor por defecto de un cubo de Erno Rubik. <br>
     * <b>post: </b> Crea un cubo de Erno Rubik.
     */
    public Rubik() {
        ultimo_movimiento = "";
        ernoRubik = new Cube[2][2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 2; k++) {
                    ernoRubik[i][j][k] = new Cube();
                }
            }
        }
    }

    //retorna el valor del ultimo movimiento realizado en el cubo rubik
    public String get_ultimo_movimiento(){
        return ultimo_movimiento;
    }
    
    //Almacena el ultimo movimiento realziado en el cubo rubik
    public void set_ultimo_movimiento(String ultimo_movimiento){
        this.ultimo_movimiento = ultimo_movimiento;
    }
    
    private Cube[][][] getErnoRubik() {
        return ernoRubik;
    }

    /**
     * Retorna el cubo referenciado.
     * <b>pre:  </b> i, j, k Valores: { 0, 1, 2 }
     *
     * @param i fila
     * @param j columna
     * @param k profundidad
     * @return Cube retorna un cubo.
     */
    public Cube cubo(int i, int j, int k) {
        return ernoRubik[i][j][k];
    }

    /**
     * Realiza un corrimiento de los colores de las caras, horizontalmente.
     * <b>pre: </b> El parametro disco debe tener uno de los siguientes valores:
     * { 0, 1, 2 }
     * <b>post: </b> Afecta los cubos del disco referenciado.
     *
     * @param disco disco sobre el cual se realizara el movimiento.
     */
    public void horizontal(int disco) {
        System.out.println("Horizontal " + disco);
        // Cubos basicos afectados girando horizontalmente  
        for (int j = 0; j < 2; j++) {
            for (int k = 0; k < 2; k++) {
                ernoRubik[disco][j][k].horizontal();
            }
        }

        // Cubos basicos afectados cambiando de posicion
        Cube aux = ernoRubik[disco][0][0];
        ernoRubik[disco][0][0] = ernoRubik[disco][0][1];
        ernoRubik[disco][0][1] = ernoRubik[disco][1][1];
        ernoRubik[disco][1][1] = ernoRubik[disco][1][0];
        ernoRubik[disco][1][0] = aux;
    }

    /**
     * Realiza un corrimiento de los colores de las caras, verticalmente.
     * <b>pre: </b> El parametro disco debe tener uno de los siguientes valores:
     * { 0, 1, 2 }
     * <b>post: </b> Afecta los cubos del disco referenciado.
     *
     * @param disco disco sobre el cual se realizara el movimiento.
     */
    public void vertical(int disco) {
        System.out.println("Vertical " + disco);
        // Cubos basicos afectados girando horizontalmente  
        for (int i = 0; i < 2; i++) {
            for (int k = 0; k < 2; k++) {
                ernoRubik[i][disco][k].vertical();
            }
        }
        
        // Cubos basicos afectados cambiando de posicion
        Cube aux = ernoRubik[0][disco][0];
        ernoRubik[0][disco][0] = ernoRubik[0][disco][1];
        ernoRubik[0][disco][1] = ernoRubik[1][disco][1];
        ernoRubik[1][disco][1] = ernoRubik[1][disco][0];
        ernoRubik[1][disco][0] = aux;
    }

    /**
     * Realiza un corrimiento de los colores de las caras, transversalmente.
     * <b>pre: </b> El parametro disco debe tener uno de los siguientes valores:
     * { 0, 1, 2 }
     * <b>post: </b> Afecta los cubos del disco referenciado.
     *
     * @param disco disco sobre el cual se realizara el movimiento.
     */
    public void transversal(int disco) {
        System.out.println("Transversal " + disco);
        // Cubos basicos afectados girando horizontalmente  
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                ernoRubik[i][j][disco].transversal();
            }
        }
        
        // Cubos basicos afectados cambiando de posicion
        Cube aux = ernoRubik[0][0][disco];
        ernoRubik[0][0][disco] = ernoRubik[1][0][disco];
        ernoRubik[1][0][disco] = ernoRubik[1][1][disco];
        ernoRubik[1][1][disco] = ernoRubik[0][1][disco];
        ernoRubik[0][1][disco] = aux;
    }
    
    
    /**
     * Realiza un corrimiento de los colores de las caras, horizontalmente.
     * <b>pre: </b> El parametro disco debe tener uno de los siguientes valores:
     * { 0, 1, 2 }
     * <b>post: </b> Afecta los cubos del disco referenciado.
     *
     * @param disco disco sobre el cual se realizara el movimiento.
     */
    public void horizontal_negativo(int disco) {
        System.out.println("Horizontal " + disco);
        // Cubos basicos afectados girando horizontalmente  
        for (int j = 1; j >= 0; j--) {
            for (int k = 1; k >= 0; k--) {
                ernoRubik[disco][j][k].horizontal_negativo();
            }
        }

        // Cubos basicos afectados cambiando de posicion
        Cube aux = ernoRubik[disco][0][0];
        ernoRubik[disco][0][0] = ernoRubik[disco][1][0];
        ernoRubik[disco][1][0] = ernoRubik[disco][1][1];
        ernoRubik[disco][1][1] = ernoRubik[disco][0][1];
        ernoRubik[disco][0][1] = aux;
        
    }

    /**
     * Realiza un corrimiento de los colores de las caras, verticalmente.
     * <b>pre: </b> El parametro disco debe tener uno de los siguientes valores:
     * { 0, 1, 2 }
     * <b>post: </b> Afecta los cubos del disco referenciado.
     *
     * @param disco disco sobre el cual se realizara el movimiento.
     */
    public void vertical_negativo(int disco) {
        System.out.println("Vertical " + disco);
        // Cubos basicos afectados girando horizontalmente  
        for (int i = 1; i >= 0; i--) {
            for (int k = 1; k >= 0; k--) {
                ernoRubik[i][disco][k].vertical_negativo();
            }
        }
        
        // Cubos basicos afectados cambiando de posicion
        Cube aux = ernoRubik[0][disco][0];
        ernoRubik[0][disco][0] = ernoRubik[1][disco][0];
        ernoRubik[1][disco][0] = ernoRubik[1][disco][1];
        ernoRubik[1][disco][1] = ernoRubik[0][disco][1];
        ernoRubik[0][disco][1] = aux;
    }

    /**
     * Realiza un corrimiento de los colores de las caras, transversalmente.
     * <b>pre: </b> El parametro disco debe tener uno de los siguientes valores:
     * { 0, 1, 2 }
     * <b>post: </b> Afecta los cubos del disco referenciado.
     *
     * @param disco disco sobre el cual se realizara el movimiento.
     */
    public void transversal_negativo(int disco) {
        System.out.println("Transversal " + disco);
        // Cubos basicos afectados girando horizontalmente  
        for (int i = 1; i >= 0; i--) {
            for (int j = 1; j >= 0; j--) {
                ernoRubik[i][j][disco].transversal_negativo();
            }
        }
        
        // Cubos basicos afectados cambiando de posicion
        Cube aux = ernoRubik[0][0][disco];
        ernoRubik[0][0][disco] = ernoRubik[0][1][disco];
        ernoRubik[0][1][disco] = ernoRubik[1][1][disco];
        ernoRubik[1][1][disco] = ernoRubik[1][0][disco];
        ernoRubik[1][0][disco] = aux; 
    }
}
