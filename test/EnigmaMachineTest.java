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
        assertEquals(result,"B");
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

    @Test
    public void transformCharA2UandU2A() {
        //default starting positions:
        char[] startingPos = {'A', 'A', 'Z'};
        //CREATE KEY:
        Key key = new Key(this.rotorsConfig, reflector, startingPos);

        this.em.configurateKey(key);
        
        char result = 'A';
        r3.setOffset(25);

        result = this.r3.transformLetter(result, "in");
        //System.out.println("["+'B'+"] found: "+result);
        assertEquals(result, 'B');
        
        result = this.r2.transformLetter(result, "in");
        //System.out.println("["+'J'+"] found: "+result); 
        assertEquals(result, 'J');
        
        result = this.r1.transformLetter(result, "in");
        //System.out.println("["+'Z'+"] found: "+result);
        assertEquals(result, 'Z');
        
        //
        result = this.reflector.transformLetter(result, "");
        //System.out.println("["+'T'+"] found: "+result);
        assertEquals(result, 'T');
        //
        
        result = this.r1.transformLetter(result, "out");
        //System.out.println("["+'L'+"] found: "+result);
        assertEquals(result, 'L');
        
        result = this.r2.transformLetter(result, "out");
        //System.out.println("["+'K'+"] found: "+result);
        assertEquals(result, 'K');
        
        result = this.r3.transformLetter(result, "out");
        //System.out.println("["+'U'+"] found: "+result);
        assertEquals(result, 'U');
        //////////////////////////////opposite
        result = 'U';
        r3.setOffset(25);

        result = this.r3.transformLetter(result, "in");
        //System.out.println("["+'B'+"] found: "+result);
        assertEquals(result, 'K');
        
        result = this.r2.transformLetter(result, "in");
        //System.out.println("["+'J'+"] found: "+result); 
        assertEquals(result, 'L');
        
        result = this.r1.transformLetter(result, "in");
        //System.out.println("["+'Z'+"] found: "+result);
        assertEquals(result, 'T');
        
        //
        result = this.reflector.transformLetter(result, "");
        //System.out.println("["+'T'+"] found: "+result);
        assertEquals(result, 'Z');
        //
        
        result = this.r1.transformLetter(result, "out");
        //System.out.println("["+'L'+"] found: "+result);
        assertEquals(result, 'J');
        
        result = this.r2.transformLetter(result, "out");
        //System.out.println("["+'K'+"] found: "+result);
        assertEquals(result, 'B');
        
        result = this.r3.transformLetter(result, "out");
        //System.out.println("["+'U'+"] found: "+result);
        assertEquals(result, 'A');
        /*
        System.out.println("IT2========");
        
        result = 'A';
        
        result = this.r3.transformLetter(result, "in");
        System.out.println("["+'D'+"] found: "+result);
        assertEquals(result, 'D');
        
        result = this.r2.transformLetter(result, "in");
        System.out.println("["+'D'+"] found: "+result); //parece un fallo de haber saltado un indice de mas
        assertEquals(result, 'D');
        
        result = this.r1.transformLetter(result, "in");
        System.out.println("["+'F'+"] found: "+result);
        assertEquals(result, 'F');
        
        //
        result = this.reflector.transformLetter(result, "");
        System.out.println("["+'S'+"] found: "+result);
        assertEquals(result, 'S');
        //
        
        result = this.r1.transformLetter(result, "out");
        System.out.println("["+'S'+"] found: "+result);
        assertEquals(result, 'S');
        
        result = this.r2.transformLetter(result, "out");
        System.out.println("["+'E'+"] found: "+result);
        assertEquals(result, 'E');
        
        result = this.r3.transformLetter(result, "out");
        System.out.println("["+'C'+"] found: "+result);
        assertEquals(result, 'C');
        */
        //AQUI FALTA TRANSFORMAR LA C A LA POSICION DEL OFFSET:(1) QUE SERÍA B->A 'B' C-0 '1' 2 Y DEVOLVER LA B

    }
    
}