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
    private ArrayList<Character> abcd = new ArrayList<Character>();

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

    public char transformLetter(char letter, String mode) {

        //if not reflector->
        if (!this.type.contains("reflector") && mode.contains("in")) {
            this.incrementOffset();
        }

        int index;
        char newLetter;
        //mode input
        if(mode.contains("in")){
          //get index of the ABCD and add the offset-->find letter in the table
          index = (this.abcd.indexOf(letter) + this.offset) % 26;
          //get the letter of the index in the Rotor code
          newLetter = this.code.get(index);
        }
        //mode output
        else {
          index = (this.code.indexOf(letter) + this.offset) % 26;
          //get the letter of the abcd in the Rotor code
          newLetter = this.abcd.get(index);
        }

        return newLetter;
    }
/*
    public void notifyRotor() {
        
    }
*/
    public void incrementOffset() {/////////////before or after notify the next rotor, which should turn first
        //increment offset
        if(this.type.contains("3")){
            this.offset = (this.offset + 1) % 26;
        }
        
        //if max offset and has left rotor->notify left rotor to change
        if (this.offset == this.notch && this.leftRotor != null) {
            this.leftRotor.offset = (this.leftRotor.offset + 1) % 26;
            //this.notifyRotor();
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
