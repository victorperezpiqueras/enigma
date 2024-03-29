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
 * @author victorperezpiqueras
 */
public class BruteForce {

    //static elements:
    private EnigmaMachine em = new EnigmaMachine();
    private ArrayList<Rotor> rotors = new ArrayList();
    private Rotor reflector;
    //private String msg = "KHIVQBTCYRFAFWPLVSCAMMRFVDMSIIRRTRZTLAOMWHFQDTOFARWZYVPWPZBNKWAV";
    private Key key;

    private ArrayList<String> dict = new ArrayList();
    //variable elements
    private char[] startingPos = new char[3];
    private ArrayList<Stecker> steckers = new ArrayList<Stecker>();

    int numOfAttempts = 0;

    public BruteForce() {

    }

    public void configurateDefaultEnigma() {
        //ROTORS:
        this.reflector = em.generateRotor("reflector", "YRUHQSLDPXNGOKMIEBFZCWVJAT", null, 0);
        Rotor r1 = em.generateRotor("1", "EKMFLGDQVZNTOWYHXUSPAIBRCJ", null, 17);
        Rotor r2 = em.generateRotor("2", "AJDKSIRUXBLHWTMCQGZNPYFVOE", r1, 5);
        Rotor r3 = em.generateRotor("3", "BDFHJLCPRTXVZNYEIWGAKMUSQO", r2, 22);

        ArrayList<Rotor> rotorsConfig = new ArrayList();

        //default config:
        rotorsConfig.add(r1);
        rotorsConfig.add(r2);
        rotorsConfig.add(r3);
        this.rotors.addAll(rotorsConfig);
        //configurate key:
        this.key = new Key();

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

    public boolean findInDictionary(String result) {//for each in dict if contains
        for (String d : this.dict) {
            if (result.contains(d)) {
                return true;
            }
        }
        return false;
    }

    public void executeBruteForce(String msg) {
        //initialize variables to use:
        ArrayList<Character> abcd = new ArrayList<Character>();

        abcd = this.generateAlphabet();

        String result = "";

        //variables to save the matches:
        ArrayList<String> results = new ArrayList();
        ArrayList keys = new ArrayList();

        boolean noSteckers = true;
        //loop:
        for (char c1 : abcd) {
            //System.out.println(this.numOfAttempts);
            for (char c2 : abcd) {
                //System.out.println(c1 + "" + c2);
                for (char c3 : abcd) {
                    System.out.println("Key: " + c1 + c2 + c3);
                    noSteckers = true;
                    for (char s1 : abcd) {
                        for (char s2 : abcd) {
                            //reset Key configuration:
                            this.key.setReflector(this.reflector);
                            this.key.setRotors(this.rotors);

                            this.startingPos = new char[]{c1, c2, c3};
                            this.key.setStartingPos(this.startingPos);

                            //reset stecker:
                            this.steckers.clear();
                            //if first stecker that doesnt affect--> AA or GG-> avoid
                            if (s1 == s2 && noSteckers) {
                                noSteckers = false;
                            }//then avoid repeated stecker pairs
                            else if (s1 != s2) {
                                this.steckers.add(new Stecker(s1, s2));
                            }
                            this.key.setSteckers(this.steckers);

                            //execute enigma:
                            this.em = new EnigmaMachine();
                            result = this.em.enigma(this.key, msg);
                            this.numOfAttempts++;
                            //System.out.println(result);

                            //add found config:
                            if (this.findInDictionary(result)) {
                                if (!results.contains(result)) {
                                    results.add(result);

                                    ArrayList<Stecker> as = new ArrayList();
                                    if (!this.key.getSteckers().isEmpty()) {
                                        Stecker s = new Stecker(this.key.getSteckers().get(0).getIn(), this.key.getSteckers().get(0).getOut());
                                        as.add(s);
                                    }
                                    Key k = new Key(this.key.getRotors(), this.key.getReflector(), this.key.getStartingPos(), as);

                                    keys.add(k);
                                    System.out.println("FOUND: " + result);
                                    System.out.println(k);
                                }
                            }
                        }
                    }
                }
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

        } else {
            System.out.println("No match found");
        }

    }

    public ArrayList<Character> generateAlphabet() {
        ArrayList<Character> abcd = new ArrayList();
        for (char c : "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray()) {
            abcd.add(c);
        }
        return abcd;
    }

}
