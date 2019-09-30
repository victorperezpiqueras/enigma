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

    public EnigmaMachineTest() {
    }

    @Before
    public void setUp() {
        em = new EnigmaMachine();
        r1 = em.generateRotor("1", "EKMFLGDQVZNTOWYHXUSPAIBRCJ", null, 17);
        r2 = em.generateRotor("2", "AJDKSIRUXBLHWTMCQGZNPYFVOE", r1, 5);
        r3 = em.generateRotor("3", "BDFHJLCPRTXVZNYEIWGAKMUSQO", r2, 22);
        reflector = em.generateRotor("reflector", "YRUHQSLDPXNGOKMIEBFZCWVJAT", null, 0);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void configurateKey() {
        //System.out.println("-----------------");
        ArrayList<Rotor> rotorsConfig = new ArrayList();
        //default config:
        rotorsConfig.add(r1);
        rotorsConfig.add(r2);
        rotorsConfig.add(r3);
        //default starting positions:
        char[] startingPos = {'A', 'D', 'Z'};
        //CREATE KEY:
        Key key = new Key(rotorsConfig, reflector, startingPos);

        this.em.configurateKey(key);
        //System.out.println("Expected offsets:[0,3,25]. Actual offsets:["+r1.getOffset()+"]"+"["+r2.getOffset()+"]"+"["+r3.getOffset()+"]");
        assertEquals(r1.getOffset(), 0);
        assertEquals(r2.getOffset(), 3);
        assertEquals(r3.getOffset(), 25);
    }

    @Test
    public void transformChar() {
        char result = 'A';
        r3.setOffset(25);

        result = this.r3.transformLetter(result);
        System.out.println("Expected is : " + 'B');
        System.out.println("Actual output is: " + result);
        assertEquals(result, 'B');
        
        result = this.r2.transformLetter(result);
        System.out.println("Expected is : " + 'J');
        System.out.println("Actual output is: " + result);
        assertEquals(result, 'J');
        
        result = this.r1.transformLetter(result);
        System.out.println("Expected is : " + 'Z');
        System.out.println("Actual output is: " + result);
        assertEquals(result, 'Z');
        
        //
        result = this.reflector.transformLetter(result);
        System.out.println("Expected is : " + 'Z');
        System.out.println("Actual output is: " + result);
        assertEquals(result, 'T');
        //
        
        result = this.r1.transformLetter(result);
        System.out.println("Expected is : " + 'L');
        System.out.println("Actual output is: " + result);
        assertEquals(result, 'L');
        
        result = this.r2.transformLetter(result);
        System.out.println("Expected is : " + 'K');
        System.out.println("Actual output is: " + result);
        assertEquals(result, 'K');
        
        result = this.r3.transformLetter(result);
        System.out.println("Expected is : " + 'U');
        System.out.println("Actual output is: " + result);
        assertEquals(result, 'U');
    }

}
