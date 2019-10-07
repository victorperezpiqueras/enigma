/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enigma;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author victorperezpiqueras
 */
public class Enigma {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
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
        char[] startingPos = new char[3];
        System.out.println("Introduce la clave:");

        Scanner sc = new Scanner(System.in);
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String clave = input.readLine();
        clave = clave.toUpperCase();

        int i = 0;
        for (char c : clave.toCharArray()) {
            startingPos[i] = c;
            i++;
        }

        //CREATE KEY:
        Key key = new Key(rotorsConfig, reflector, startingPos);
        em.configurateKey(key);

        //CREATE STECKERS:
        ArrayList<Stecker> steckers = new ArrayList();
        System.out.println("Introduce el número de steckers:");
        int numSteckers = Integer.parseInt(input.readLine());
        
        System.out.println("Introduce los steckers con el formato: XX");
        for (i = 0; i < numSteckers; i++) {
            System.out.println("Stecker[" + i + "]:");
            String steckerText = input.readLine();
            steckerText = steckerText.toUpperCase();
            Stecker st = new Stecker(steckerText);
            steckers.add(st);
        }
        
        int err = em.configurateSteckers(steckers);
        if (err == -1) {
            System.exit(-1);
        }
        
        //CREATE MSG:
        System.out.println("Introduce el mensaje:");
        String msg = input.readLine();
        //upper case
        msg = msg.toUpperCase();

        //EXECUTE ENIGMA:
        String result = em.enigma(key, msg);

        //PRINT OUTPUT:
        System.out.println("Output:");
        System.out.println(result);
    }

}
