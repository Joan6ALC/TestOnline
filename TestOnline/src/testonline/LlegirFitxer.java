/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testonline;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author joan_
 */
public class LlegirFitxer {
    private FileReader fic = null;
    private BufferedReader lect = null;
    private int linea;

    public void open(String nom) {
        try {
            fic = new FileReader(nom);
            lect = new BufferedReader(fic);
            linea = 0;
        } catch (IOException e) {
//            InfoError.notificar(e.getMessage());
        }
    }
    
    public void close() {
        try {
            if (lect != null) {
                lect.close();
            }
            if (fic != null) {
                fic.close();
            }
        } catch (IOException e) {
//            InfoError.notificar(e.getMessage());
        }
    }
    
    public int read() {
        int carac = 0;
        try {
            carac = lect.read();
        } catch (Exception e) {
//            InfoError.notificar(e.getMessage());
        }
        return carac;
    }
    
    public char[] readLine() {
        char [] line = null;
        try {
            String aux = lect.readLine();
            if (aux != null) {
            line = aux.toCharArray();
            } else {
                line = null;
            }
        } catch (Exception e) {
            //InfoError.notificar(e.getMessage());
        }
        linea++;
        return line;
    }
    
    public boolean isTime(){
        return linea == 3;
    }
    
    public boolean isNumPreguntes(){
        return linea == 4;
    }
    
    public boolean isNumOpcions(){
        return linea == 5;
    }
    
    public boolean isQuestion(){
        return linea >= 6;
    }
    
    public boolean isRespostes(String t, int l){
        return ("respostes.txt".equals(t))&&(linea == l);
    }
    
    
}
