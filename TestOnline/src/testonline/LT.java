/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testonline;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class LT {

    public char[] llegirLiniaC() {
        String res = "";
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(System.in, "ISO-8859-1"));
            res = br.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res.toCharArray();
    }

    public Integer llegirSencer() {
        Integer res = 0;
        try {
            char[] pal = llegirLiniaC();
            res = Integer.parseInt(new String(pal));
        } catch (Exception e) {
            res = null;
        }
        return res;
    }

    public Double llegirReal() {
        Double res = 0.0;
        try {
            char[] pal = llegirLiniaC();
            res = Double.parseDouble(new String(pal));
        } catch (Exception e) {
            res = null;
        }
        return res;
    }

    public String llegirLiniaS() {
        String res;
        char[] pal = llegirLiniaC();
        res = new String(pal);
        return res;
    }

    public Character llegirCaracter() {
        Character res;
        char[] pal = llegirLiniaC();
        String s = new String(pal);
        if (s.length() == 0) {
            res = null;
        } else {
            res = new Character(s.charAt(0));
        }
        return res;
    }
}
