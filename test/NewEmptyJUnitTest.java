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
public class NewEmptyJUnitTest {

    EnigmaMachine em;
    Rotor r1;
    Rotor r2;

    public NewEmptyJUnitTest() {
    }

    @Before
    public void setUp() {
        em = new EnigmaMachine();
        r1 = em.generateRotor("1", "EKMFLGDQVZNTOWYHXUSPAIBRCJ", null);
        r2 = em.generateRotor("2", "AJDKSIRUXBLHWTMCQGZNPYFVOE", r1);
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void transformLetter() {
        //System.out.println("-----------------");
        char input = 'A';
        r1.setOffset(25);
        int oldOffset = r1.getOffset();
        char output = r1.transformLetter(input);

        //check offset changed
        assertEquals(r1.getOffset(), (oldOffset + 1) % 26);

        //check output is the expected:
        char expectedOutput = 'E';
        //System.out.println("Actual output is: " + output);
        //System.out.println("Expected is : " + input);
        assertEquals(output, expectedOutput);
        //System.out.println("2º:");
        //check second output is the second letter:
        output = r1.transformLetter(input);
        expectedOutput = 'K';
        //System.out.println("Actual output is: " + output);
        //System.out.println("Expected is : " + input);
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
        System.out.println("-----------------");
        int initialOffset = r1.getOffset();
        char input = 'A';
        for (int i = 0; i < 27; i++) {
            r2.transformLetter(input);
        }
        int finalOffset = r1.getOffset();
        
        System.out.println("initialOffset: " + initialOffset);
        System.out.println("finalOffset: " + finalOffset);
        assertEquals(initialOffset + 1, finalOffset);

    }
}
