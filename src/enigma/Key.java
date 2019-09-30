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
    private char[] startingPos = new char[3];
    
    public Key(ArrayList<Rotor> rotors, char[] startingPos){
        this.rotors = rotors;
        this.startingPos=startingPos;
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
