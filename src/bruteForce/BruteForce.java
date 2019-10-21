/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bruteForce;

import enigma.*;
import java.util.ArrayList;

/**
 *
 * @author vikti
 */
public class BruteForce {

    //static elements:
    private EnigmaMachine em = new EnigmaMachine();
    private String msg = "KHIVQBTCYRFAFWPLVSCAMMRFVDMSIIRRTRZTLAOMWHFQDTOFARWZYVPWPZBNKWAV";
    private Key key;

    private ArrayList<String> dict = new ArrayList();
    //variable elements
    private char[] startingPos;
    private ArrayList<Stecker> steckers = new ArrayList<Stecker>();

    int numOfAttempts = 0;

    public BruteForce() {

    }

    public void configurateDefaultEnigma() {
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

        //configurate key:
        this.key = new Key(rotorsConfig, reflector, null, null);
    }

    public void fillDictionary() {
        this.dict.add("ambiguo".toUpperCase());
        this.dict.add("obvio".toUpperCase());
        this.dict.add("trivial".toUpperCase());
        this.dict.add("estupendo".toUpperCase());
        this.dict.add("esther".toUpperCase());
        this.dict.add("bugzilla".toUpperCase());
        this.dict.add("lugar".toUpperCase());
        this.dict.add("pacifico".toUpperCase());
        this.dict.add("diarrea".toUpperCase());
        this.dict.add("hola".toUpperCase());
        this.dict.add("mundo".toUpperCase());
        this.dict.add("garabata".toUpperCase());
        this.dict.add("calabaza".toUpperCase());
        this.dict.add("herpes".toUpperCase());
        this.dict.add("calabaza".toUpperCase());
        this.dict.add("celula".toUpperCase());
        this.dict.add("porro".toUpperCase());
        this.dict.add("suaves".toUpperCase());
        this.dict.add("albacete".toUpperCase());
        this.dict.add("fiesta".toUpperCase());
        this.dict.add("patata".toUpperCase());
    }

    public boolean findInDictionary(String result) {
        return this.dict.contains(result);
    }

    public void executeBruteForce(String msg) {
        //initialize variables to use:
        this.msg = msg;
        ArrayList<Character> abcd = new ArrayList<Character>();

        for (char c : "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray()) {
            abcd.add(c);
        }
        String result = "";

        //variables to save the matches:
        ArrayList<String> results = new ArrayList();
        ArrayList<Key> keys = new ArrayList();

        //loop:
        outerloop:
        for (char c1 : abcd) {
            System.out.println(this.numOfAttempts);
            for (char c2 : abcd) {
                for (char c3 : abcd) {
                    System.out.println("Key: "+c1+c2+c3);
                    /* for (char stecker1 : abcd) {
                        for (char stecker2 : abcd) {*/
                    for (int i = 0; i < abcd.size(); i++) {
                        for (int j = i + 1; j < abcd.size(); j++) {
                            //if (abcd.get(i) != abcd.get(j)) {
                                //apply configuration:
                                this.startingPos = new char[]{c1, c2, c3};
                                this.key.setStartingPos(this.startingPos);
                                this.steckers.add(new Stecker(abcd.get(i), abcd.get(j)));
                                this.key.setSteckers(this.steckers);

                                //execute enigma:
                                result = this.em.enigma(this.key, this.msg);
                                this.numOfAttempts++;
                                //System.out.println(result);

                                //add found config:
                                if (this.findInDictionary(result)) {
                                    System.out.println("FOUND: " + result);
                                    results.add(result);
                                    keys.add(key);
                                    //break outerloop;
                                }
                            //}
                        }
                        
                    }
                    
                }
                //System.gc();
            }

        }

        System.out.println("===========================");
        System.out.println("Finished");

        if (results.size() > 0) {
            System.out.println("Found matches:");
            for (int i = 0; i < results.size(); i++) {
                System.out.println(results.get(i));
                System.out.println(keys.get(i));
            }
            //System.out.println(this.key.toString());

        } else {
            System.out.println("No match found");
        }

    }

}
