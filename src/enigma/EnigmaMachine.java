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
public class EnigmaMachine {
    //private ArrayList<Rotor> rotors = new ArrayList();

    private Rotor reflector;

    private Rotor leftRotor;
    private Rotor centerRotor;
    private Rotor rightRotor;

    public EnigmaMachine() {

    }

    public Rotor generateRotor(String type, String encoding, Rotor leftRotor) {
        ArrayList<Character> chars = new ArrayList<Character>();
        for (char c : encoding.toCharArray()) {
            chars.add(c);
        }
        Rotor rotor = new Rotor(type, chars, leftRotor);
        return rotor;
    }

    public void configurateKey(Key key) {

        //DEFAULT CONFIG:
        this.setReflector(key.getReflector());
        this.setLeftRotor(key.getRotors().get(0));
        this.setCenterRotor(key.getRotors().get(1));
        this.setRightRotor(key.getRotors().get(2));

        //FIND OFFSETS:
        char letter = key.getStartingPos()[0];
        //int offset = this.leftRotor.getCode().indexOf(letter);
        int offset = this.generateOffset(letter);
        this.leftRotor.setOffset(offset);

        letter = key.getStartingPos()[1];
        //offset = this.centerRotor.getCode().indexOf(letter);
        offset = this.generateOffset(letter);
        this.centerRotor.setOffset(offset);

        letter = key.getStartingPos()[2];
        //offset = this.rightRotor.getCode().indexOf(letter);
        offset = this.generateOffset(letter);
        this.rightRotor.setOffset(offset);

    }

    //gets the index of the letter to generate its offset-->A=0;C=2
    public int generateOffset(char letter) {
        ArrayList<Character> chars = new ArrayList<Character>();
        int offset = 0;
        for (char c : "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray()) {
            if (c == letter) {
                break;
            }
            offset++;
        }
        return offset;
    }

    public char transformChar(char c) {
        char result = c;
        result = this.rightRotor.transformLetter(result);
        result = this.centerRotor.transformLetter(result);
        result = this.leftRotor.transformLetter(result);
        result = this.reflector.transformLetter(result);

        result = this.leftRotor.transformLetter(result);
        result = this.centerRotor.transformLetter(result);
        result = this.rightRotor.transformLetter(result);

        return result;
    }

    public String enigma(Key key, String msg) {
        //configure the key rotors and options
        this.configurateKey(key);

        //for each character-->transform it
        String result = "";
        for (int i = 0; i < msg.length(); i++) {
            char c = msg.charAt(i);
            c = this.transformChar(c);
            result += c;
        }

        //return result
        return result;
    }

    //GETTERS AND SETTERS
    public Rotor getLeftRotor() {
        return leftRotor;
    }

    public void setLeftRotor(Rotor leftRotor) {
        this.leftRotor = leftRotor;
    }

    public Rotor getCenterRotor() {
        return centerRotor;
    }

    public void setCenterRotor(Rotor centerRotor) {
        this.centerRotor = centerRotor;
    }

    public Rotor getRightRotor() {
        return rightRotor;
    }

    public void setRightRotor(Rotor rightRotor) {
        this.rightRotor = rightRotor;
    }

    public Rotor getReflector() {
        return reflector;
    }

    public void setReflector(Rotor reflector) {
        this.reflector = reflector;
    }

}
