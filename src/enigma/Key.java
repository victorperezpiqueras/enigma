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
public class Key {

    private ArrayList<Rotor> rotors = new ArrayList();
    private Rotor reflector;
    private char[] startingPos = new char[3];
    private ArrayList<Stecker> steckers = new ArrayList<Stecker>();

    public Key(ArrayList<Rotor> rotors, Rotor reflector, char[] startingPos, ArrayList<Stecker> steckers) {
        this.rotors = rotors;
        this.reflector = reflector;
        this.startingPos = startingPos;
        this.steckers = steckers;
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

    public ArrayList<Stecker> getSteckers() {
        return steckers;
    }

    public void setSteckers(ArrayList<Stecker> steckers) {
        this.steckers = steckers;
    }

    @Override
    public String toString() {
        String print="[STECKERS]: ";
        for(Stecker s : this.steckers){
            print+=s.toString();
        }
        print+=" | ";
        print+="[KEY]: {"+this.startingPos[0]+this.startingPos[1]+this.startingPos[2]+"}";
        return print;
    }

}
