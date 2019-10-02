/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import enigma.EnigmaMachine;
import enigma.Key;
import enigma.Rotor;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author 48259328
 */
public class EnigmaMachineTest {

    EnigmaMachine em;
    Rotor r1;
    Rotor r2;
    Rotor r3;
    Rotor reflector;
    ArrayList<Rotor> rotorsConfig = new ArrayList();

    public EnigmaMachineTest() {
    }

    @Before
    public void setUp() {
        em = new EnigmaMachine();
        r1 = em.generateRotor("1", "EKMFLGDQVZNTOWYHXUSPAIBRCJ", null, 17);
        r2 = em.generateRotor("2", "AJDKSIRUXBLHWTMCQGZNPYFVOE", r1, 5);
        r3 = em.generateRotor("3", "BDFHJLCPRTXVZNYEIWGAKMUSQO", r2, 22);
        reflector = em.generateRotor("reflector", "YRUHQSLDPXNGOKMIEBFZCWVJAT", null, 0);
        
        this.rotorsConfig.add(r1);
        this.rotorsConfig.add(r2);
        this.rotorsConfig.add(r3);
       
    }
    
    @Test
    public void test1(){
        //default starting positions:
        char[] startingPos = {'Z', 'A', 'A'};
        //CREATE KEY:
        Key key = new Key(this.rotorsConfig, reflector, startingPos);
        
        this.em.configurateKey(key);
        String result = em.enigma(key, "A");
        assertEquals(result,"Z");
    }
    
    @Test
    public void test2(){
        //default starting positions:
        char[] startingPos = {'A', 'A', 'Z'};
        //CREATE KEY:
        Key key = new Key(this.rotorsConfig, reflector, startingPos);
        
        this.em.configurateKey(key);
        String result = em.enigma(key, "A");
        assertEquals(result,"U");
        result = em.enigma(key, "U");
        assertEquals(result,"A");
    }
    
    @Test
    public void test3(){
        //default starting positions:
        char[] startingPos = {'Z', 'A', 'A'};
        //CREATE KEY:
        Key key = new Key(this.rotorsConfig, reflector, startingPos);
        
        this.em.configurateKey(key);
        String result = em.enigma(key, "ABCDEFFFF");
        assertEquals(result,"ZKFBIWHUH");
    }

    @Test
    public void configurationKeyWorks() {

        //default config:
        this.rotorsConfig.add(r1);
        this.rotorsConfig.add(r2);
        this.rotorsConfig.add(r3);
        //default starting positions:
        char[] startingPos = {'Z', 'A', 'Z'};
        //CREATE KEY:
        Key key = new Key(this.rotorsConfig, reflector, startingPos);

        this.em.configurateKey(key);
        //System.out.println("Expected offsets:[0,3,25]. Actual offsets:["+r1.getOffset()+"]"+"["+r2.getOffset()+"]"+"["+r3.getOffset()+"]");
        assertEquals(r1.getOffset(), 25);
        assertEquals(r2.getOffset(), 0);
        assertEquals(r3.getOffset(), 25);
    }
    
}