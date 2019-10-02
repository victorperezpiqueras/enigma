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
        Rotor r1 = em.generateRotor("1", "EKMFLGDQVZNTOWYHXUSPAIBRCJ", null, 17);
        Rotor r2 = em.generateRotor("2", "AJDKSIRUXBLHWTMCQGZNPYFVOE", r1, 5);
        Rotor r3 = em.generateRotor("3", "BDFHJLCPRTXVZNYEIWGAKMUSQO", r2, 22);
        Rotor reflector = em.generateRotor("reflector", "YRUHQSLDPXNGOKMIEBFZCWVJAT", null, 0);

        ArrayList<Rotor> rotorsConfig = new ArrayList();
        
        //default config:
        rotorsConfig.add(r1);
        rotorsConfig.add(r2);
        rotorsConfig.add(r3);
        
        //default starting positions:
        char[] startingPos = {'A', 'A', 'X'};
        
        //CREATE KEY:
        Key key = new Key(rotorsConfig, reflector, startingPos);
        em.configurateKey(key);
        
        //CREATE MSG:
        System.out.println("Introduce la clave:");
        Scanner sc = new Scanner(System.in);
        String msg = sc.nextLine();
        //upper case
        msg = msg.toUpperCase();
        
        //EXECUTE ENIGMA:
        String result = em.enigma(key, msg);

        //PRINT OUTPUT:
        System.out.println("Output:");
        System.out.println(result);
    }

}
