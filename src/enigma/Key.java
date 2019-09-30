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
public class Key {

    private ArrayList<Rotor> rotors = new ArrayList();
    private Rotor reflector;
    private char[] startingPos = new char[3];

    public Key(ArrayList<Rotor> rotors, Rotor reflector, char[] startingPos) {
        this.rotors = rotors;
        this.reflector = reflector;
        this.startingPos = startingPos;
    }

    public Rotor getReflector() {
        return reflector;
    }

    public void setReflector(Rotor reflector) {
        this.reflector = reflector;
    }

    public ArrayList<Rotor> getRotors() {
        return rotors;
    }

    public void setRotors(ArrayList<Rotor> rotors) {
        this.rotors = rotors;
    }

    public char[] getStartingPos() {
        return startingPos;
    }

    public void setStartingPos(char[] startingPos) {
        this.startingPos = startingPos;
    }

}
