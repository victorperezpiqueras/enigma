/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import enigma.EnigmaMachine;
import enigma.Rotor;
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
public class RotorTest {

    EnigmaMachine em;
    Rotor r1;
    Rotor r2;
    Rotor r3;

    public RotorTest() {
    }

    @Before
    public void setUp() {
        em = new EnigmaMachine();
        r1 = em.generateRotor("1", "EKMFLGDQVZNTOWYHXUSPAIBRCJ", null);
        r2 = em.generateRotor("2", "AJDKSIRUXBLHWTMCQGZNPYFVOE", r1);
        r3 = em.generateRotor("3", "BDFHJLCPRTXVZNYEIWGAKMUSQO", r2);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void transformLetter() {
        System.out.println("-----------------");
        char input = 'A';
        r3.setOffset(25);
        int oldOffset = r3.getOffset();
        char output = r3.transformLetter(input);

        //check offset changed
        assertEquals(r3.getOffset(), (oldOffset + 1) % 26);

        //check output is the expected:
        char expectedOutput = 'B';
        System.out.println("Actual output is: " + output);
        System.out.println("Expected is : " + expectedOutput);
        assertEquals(output, expectedOutput);
        
        System.out.println("2º:");
        
        //check second output is the second letter:
        output = r3.transformLetter(input);
        expectedOutput = 'D';
        System.out.println("Actual output is: " + output);
        System.out.println("Expected is : " + expectedOutput);
        assertEquals(output, expectedOutput);
    }
    
     @Test
    public void checkNotify() {
        //System.out.println("-----------------");
        int initialOffset = r1.getOffset();
        r2.notifyRotor();
        int finalOffset = r1.getOffset();
        //System.out.println("initialOffset: " + initialOffset);
        //System.out.println("finalOffset: " + finalOffset);
        assertEquals( (initialOffset + 1)%26, finalOffset);
    }
    

    @Test
    public void checkOffsetChanged() {
        //System.out.println("-----------------");
        int initialOffset = r1.getOffset();
        char input = 'A';
        for (int i = 0; i < 27; i++) {
            r2.transformLetter(input);
        }
        int finalOffset = r1.getOffset();
        
        //System.out.println("initialOffset: " + initialOffset);
        //System.out.println("finalOffset: " + finalOffset);
        assertEquals(initialOffset + 1, finalOffset);

    }
}
