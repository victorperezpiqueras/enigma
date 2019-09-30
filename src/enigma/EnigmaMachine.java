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

        
    public EnigmaMachine(){
        
    }
    
    public String enigma(Key key, String msg){
        
        //DEFAULT CONFIG:
        this.setLeftRotor(key.getRotors().get(0));
        this.setCenterRotor(key.getRotors().get(1));
        this.setRightRotor(key.getRotors().get(2));
        
        //FIND OFFSETS:
        char letter = key.getStartingPos()[0];
        int offset = this.leftRotor.getCode().indexOf(letter);
        this.leftRotor.setOffset(offset);
        
        letter = key.getStartingPos()[1];
        offset = this.centerRotor.getCode().indexOf(letter);
        this.centerRotor.setOffset(offset);
        
        letter = key.getStartingPos()[2];
        offset = this.rightRotor.getCode().indexOf(letter);
        this.rightRotor.setOffset(offset);
        
        
        return null;
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
