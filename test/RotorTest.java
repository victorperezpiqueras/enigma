/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import enigma.EnigmaMachine;
import enigma.Rotor;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author victorperezpiqueras
 */
public class RotorTest {

    EnigmaMachine em;
    Rotor r1;
    Rotor r2;
    Rotor r3;
    private ArrayList<Character> abcd = new ArrayList<Character>();

    public RotorTest() {

    }

    @Before
    public void setUp() {
        em = new EnigmaMachine();
        r1 = em.generateRotor("1", "EKMFLGDQVZNTOWYHXUSPAIBRCJ", null, 17);
        r2 = em.generateRotor("2", "AJDKSIRUXBLHWTMCQGZNPYFVOE", r1, 5);
        r3 = em.generateRotor("3", "BDFHJLCPRTXVZNYEIWGAKMUSQO", r2, 22);
        for (char c : "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray()) {
            this.abcd.add(c);
        }
    }

    @Test
    public void transformLetter() {
        //System.out.println("-----------------");
        r3.setOffset(25);
        int oldOffset = r3.getOffset();
        int outputIndex = r3.transformLetter(0, "in");
        int output = this.abcd.get(outputIndex);

        //check offset changed
        assertEquals(r3.getOffset(), (oldOffset + 1) % 26);

        //check output is the expected:
        char expectedOutput = 'B';
        //System.out.println("Actual output is: " + output);
        //System.out.println("Expected is : " + expectedOutput);
        assertEquals(output, expectedOutput);
    }

    @Test
    public void incrementNextRotor() {
        //System.out.println("-----------------");
        r3.setNotch(1);
        r3.setOffset(0);
        r2.setOffset(0);
        int initialOffset = r2.getOffset();
        r3.incrementOffset();
        int finalOffset = r2.getOffset();
        //System.out.println("initialOffset: " + initialOffset);
        //System.out.println("finalOffset: " + finalOffset);
        assertEquals((initialOffset + 1) % 26, finalOffset);
    }

    @Test
    public void checkOffsetChanged() {
        //System.out.println("-----------------");
        int initialOffset = r2.getOffset();
        for (int i = 0; i < 27; i++) {
            r3.transformLetter(0, "in");
        }
        int finalOffset = r2.getOffset();
        //System.out.println("initialOffset: " + initialOffset);
        //System.out.println("finalOffset: " + finalOffset);
        assertEquals(initialOffset + 1, finalOffset);
    }

    @Test
    public void checkNotchWorks() {
        //System.out.println("-----------------");
        r3.setOffset(0);
        int initialOffset = r2.getOffset();
        r3.setNotch(1);
        r3.transformLetter(0, "in");
        int finalOffset = r2.getOffset();
        //System.out.println("initialOffset: " + initialOffset);
        //System.out.println("finalOffset: " + finalOffset);
        assertEquals(initialOffset + 1, finalOffset);

    }
}
