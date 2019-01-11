/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testonline;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author joan_
 */
public class EscriureFitxer {
    private FileWriter fic = null;
    private BufferedWriter buf = null;
    
    public void open(String nom) {
        open(nom, false);
    }
    
    public void open(String nom, boolean concatenar) {
        try {
            fic = new FileWriter(nom, concatenar);
            buf = new BufferedWriter(fic);
        } catch (IOException e) {
//            InfoError.notificar(e.getMessage());
        }
    }
    
    public void close() {
        try {
            if (buf != null) {
                buf.close();
            }
            if (fic != null) {
                fic.close();
            }
        } catch (IOException e) {
//            InfoError.notificar(e.getMessage());
        }
    }
    
    public void write(char[] cars) {
        try {
            buf.write(cars);
        } catch (Exception e) {
//            InfoError.notificar(e.getMessage());
        }
    }
    
    public void write(char car) {
        try {
            buf.write("" + car);
        } catch (Exception e) {
//            InfoError.notificar(e.getMessage());
        }
    }
}
