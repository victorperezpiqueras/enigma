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
        //CREATE ENIGMA MACHINE:
        EnigmaMachine em = new EnigmaMachine();
        
        //ROTORS:
//        ArrayList<Character> chars1 = new ArrayList<Character>();
//        for (char c : "EKMFLGDQVZNTOWYHXUSPAIBRCJ".toCharArray()) {
//            chars1.add(c);
//        }
//        Rotor r1 = new Rotor("1", chars1, null);
        Rotor r1 = em.generateRotor("1", "EKMFLGDQVZNTOWYHXUSPAIBRCJ", null);

//        ArrayList<Character> chars2 = new ArrayList<Character>();
//        for (char c : "AJDKSIRUXBLHWTMCQGZNPYFVOE".toCharArray()) {
//            chars2.add(c);
//        }
//        Rotor r2 = new Rotor("2", chars2, r1);
        Rotor r2 = em.generateRotor("2", "AJDKSIRUXBLHWTMCQGZNPYFVOE", r1);

//        ArrayList<Character> chars3 = new ArrayList<Character>();
//        for (char c : "BDFHJLCPRTXVZNYEIWGAKMUSQO".toCharArray()) {
//            chars3.add(c);
//        }
//        Rotor r3 = new Rotor("3", chars3, r2);
        Rotor r3 = em.generateRotor("3", "BDFHJLCPRTXVZNYEIWGAKMUSQO", r2);
        //REFLECTOR:
//        ArrayList<Character> chars4 = new ArrayList<Character>();
//        for (char c : "YRUHQSLDPXNGOKMIEBFZCWVJAT".toCharArray()) {
//            chars4.add(c);
//        }
//        Rotor reflector = new Rotor("reflector", chars4, null);
        Rotor reflector = em.generateRotor("reflector", "YRUHQSLDPXNGOKMIEBFZCWVJAT", null);

        ArrayList<Rotor> rotorsConfig = new ArrayList();
        //default config:
        rotorsConfig.add(r1);
        rotorsConfig.add(r2);
        rotorsConfig.add(r3);
        //default starting positions:
        char[] startingPos = {'A', 'A', 'Z'};
        //CREATE KEY:
        Key key = new Key(rotorsConfig, reflector, startingPos);

        //CREATE MSG:
        System.out.println("Introduce la clave:");
        Scanner sc = new Scanner(System.in);
        String msg = sc.nextLine();

        //EXECUTE ENIGMA:
        String result = em.enigma(key, msg);

        //PRINT OUTPUT:
        System.out.println("Output:");
        System.out.println(result);
    }

}
