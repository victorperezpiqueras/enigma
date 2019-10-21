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
public class Stecker {

    //private ArrayList<Character> in = new ArrayList<Character>();
    //private ArrayList<Character> out = new ArrayList<Character>();
    private char in;
    private char out;

    public Stecker(char in, char out) {
        this.in = in;
        this.out = out;
    }

    public Stecker(String s) {
        this.in = s.charAt(0);
        this.out = s.charAt(1);
    }

    //GETTERS AND SETTERS
    public char getIn() {
        return in;
    }

    public void setIn(char in) {
        this.in = in;
    }

    public char getOut() {
        return out;
    }

    public void setOut(char out) {
        this.out = out;
    }

    @Override
    public String toString() {
        return "{"+this.in+this.out+"}";
    }

}
