/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enigma;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author vikti
 */
public class Enigma {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //ROTORS:
        //char[] list1 = "EKMFLGDQVZNTOWYHXUSPAIBRCJ".toCharArray();
        ArrayList<Character> chars1 = new ArrayList<Character>();
        for (char c : "EKMFLGDQVZNTOWYHXUSPAIBRCJ".toCharArray()) {
            chars1.add(c);
          }
        Rotor r1 = new Rotor("1", chars1);

        //char[] list2 = "AJDKSIRUXBLHWTMCQGZNPYFVOE".toCharArray();
        ArrayList<Character> chars2 = new ArrayList<Character>();
        for (char c : "AJDKSIRUXBLHWTMCQGZNPYFVOE".toCharArray()) {
            chars2.add(c);
          }
        Rotor r2 = new Rotor("2", chars2);

        //char[] list3 = "BDFHJLCPRTXVZNYEIWGAKMUSQO".toCharArray();
        ArrayList<Character> chars3 = new ArrayList<Character>();
        for (char c : "BDFHJLCPRTXVZNYEIWGAKMUSQO".toCharArray()) {
            chars3.add(c);
          }
        Rotor r3 = new Rotor("3", chars3);

        //REFLECTOR:
       // char[] list4 = "YRUHQSLDPXNGOKMIEBFZCWVJAT".toCharArray();
        ArrayList<Character> chars4 = new ArrayList<Character>();
        for (char c : "YRUHQSLDPXNGOKMIEBFZCWVJAT".toCharArray()) {
            chars4.add(c);
          }
        Rotor reflector = new Rotor("reflector", chars4);

        //CREATE ENIGMA MACHINE:
        EnigmaMachine enigma = new EnigmaMachine();
    /* 
        enigma.setLeftRotor(r1);
        enigma.setCenterRotor(r2);
        enigma.setRightRotor(r3);
        enigma.setReflector(reflector);
    */
        
        //CREATE KEY:
        ArrayList<Rotor> rotorsConfig = new ArrayList();
        //default config:
        rotorsConfig.add(r1);
        rotorsConfig.add(r2);
        rotorsConfig.add(r3);
        
        //default starting positions:
        char[] startingPos = {'A', 'A', 'A'};
        Key key = new Key(rotorsConfig, startingPos);
        
        //CREATE MSG:
        System.out.println("Introduce la clave:");
        Scanner sc = new Scanner(System.in);
        String msg = sc.nextLine();
        
        //EXECUTE ENIGMA:
        String result = enigma.enigma(key, msg);
        
        //PRINT OUTPUT:
        System.out.println("Output:");
        System.out.println(result);
    }

}
