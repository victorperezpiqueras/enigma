/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enigma;

import java.util.ArrayList;

/**
 *
 * @author victorperezpiqueras
 */
public class Rotor {

    private String type;
    private ArrayList<Character> code = new ArrayList(26);
    private int offset = 0;
    private Rotor leftRotor;
    private int notch;
    private ArrayList<Character> abcd = new ArrayList<Character>();

    //doble paso:
    private boolean doblePaso = false;
    private boolean incremented = false;

    public Rotor(String type, ArrayList<Character> code, Rotor rotor, int notch) {
        this.code = code;
        this.type = type;
        this.offset = 0;
        this.leftRotor = rotor;
        this.notch = notch;

        for (char c : "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray()) {
            this.abcd.add(c);
        }

    }

    public int transformLetter(int indexLetter, String mode) {

        //if not reflector->
        if (!this.type.contains("reflector") && mode.contains("in")) {
            this.incrementOffset();
        }

        int index;
        char newLetter;
        int newIndex;
        //MODE INPUT
        if (mode.contains("in")) {
            //get index of the ABCD and add the offset-->find letter in the table
            //index = (this.abcd.indexOf(letter) + this.offset) % 26;
            index = (indexLetter + this.getOffset()) % 26;
            //get the letter of the index in the Rotor code
            newLetter = this.code.get(index);
            newIndex = this.abcd.indexOf(newLetter) + (26 - this.getOffset());
        }//AAY,Z-->17(q) = 16 + ( 26-25(index) ) 
        //MODE OUTPUT
        else {
            //obtenemos la letra en la misma posicion
            index = (indexLetter + this.getOffset()) % 26;
            newLetter = this.abcd.get(index);
            //get the letter of the abcd in the Rotor code
            newIndex = this.code.indexOf(newLetter);
            newLetter = this.abcd.get(newIndex);
            newIndex = this.abcd.indexOf(newLetter) + (26 - this.getOffset());
        }
        char debugIndex = this.abcd.get(newIndex % 26);
        return newIndex % 26;
    }

    public void incrementOffset() {

        //Rotor 3 -> increment offset
        if (this.type.contains("3")) {
            this.addOffset();
        } else {
            //si no ha sido incrementado por el rotor drch y está en la pos. anterior a la muesca->marcar doble paso
            if (!this.incremented && ((this.offset + 1) % 26) == this.notch) {
                this.doblePaso = true;
            }
            //si toca doble paso->desactivarlo, girar rotor y rotor izq.
            if (doblePaso) {
                this.doblePaso = false;
                this.offset = (this.offset + 1) % 26;
                //mover rotor izquierdo:
                this.leftRotor.offset = (this.leftRotor.offset + 1) % 26;
            }

            //si ha sido incrementado pero en la siguiente está en pos. anterior a la muesca->marcar doble paso
            if (this.type.contains("2") && ((this.offset + 1) % 26) == this.notch) {
                this.doblePaso = true;
            }
            //resetear flag para proxima letra
            this.incremented = false;
        }

    }

    public void addOffset() {
        this.offset = (this.offset + 1) % 26;
        this.incremented = true;
        if (this.offset == this.notch && this.leftRotor != null) {
            this.leftRotor.addOffset();
        }
    }

    public Rotor getLeftRotor() {
        return leftRotor;
    }

    public void setLeftRotor(Rotor leftRotor) {
        this.leftRotor = leftRotor;
    }

    //GETTERS AND SETTERS:
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<Character> getCode() {
        return code;
    }

    public void setCode(ArrayList<Character> code) {
        this.code = code;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getNotch() {
        return notch;
    }

    public void setNotch(int notch) {
        this.notch = notch;
    }

}
