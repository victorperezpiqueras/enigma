/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bruteForce;


/**
 *
 * @author victorperezpiqueras
 */
public class main {

    public static void main(String[] args) {
        BruteForce bF = new BruteForce();
        bF.configurateDefaultEnigma();
        bF.fillDictionary();
        String message = "KHIVQBTCYRFAFWPLVSCAMMRFVDMSIIRRTRZTLAOMWHFQDTOFARWZYVPWPZBNKWAV";
        //String message = "VUNCRIHV";
        bF.executeBruteForce(message);


    }

}
