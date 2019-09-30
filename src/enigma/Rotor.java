/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enigma;

import java.util.ArrayList;

/**
 *
 * @author vikti
 */
public class Rotor {

    private String type;
    private ArrayList<Character> code = new ArrayList(26);
    private int offset = 0;
    private Rotor leftRotor;
    private int notch;

    public Rotor(String type, ArrayList<Character> code, Rotor rotor, int notch) {
        this.code = code;
        this.type = type;
        this.offset = 0;
        this.leftRotor = rotor;
        this.notch = notch;
    }

    public char transformLetter(char letter) {
        ArrayList<Character> input = new ArrayList<Character>();
        for (char c : "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray()) {
            input.add(c);
        }

        //if not reflector->
        if (!this.type.contains("reflector")) {
            this.incrementOffset();
        }

        //get index of the ABCD and add the offset
        int index = (input.indexOf(letter) + this.offset) % 26;

        //get the letter of the index in the Rotor code
        char newLetter = this.code.get(index);

        return newLetter;
    }

    public void notifyRotor() {
        this.leftRotor.incrementOffset();
    }

    public void incrementOffset() {
        //if max offset and has left rotor->notify left rotor to change
        if (this.offset == this.notch && this.leftRotor != null) {
            this.notifyRotor();
        }
        //increment offset
        this.offset = (this.offset + 1) % 26;
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

}
