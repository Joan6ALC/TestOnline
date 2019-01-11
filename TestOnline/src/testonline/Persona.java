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
 * @author Joan
 */
public class Persona {
    private String nom;
    private String data;
    private int test;
    private String resultat;

    public Persona() {
        nom = "";
        data = "";
        test = 0;
        resultat = "";
    }

    public Persona(String nom, String data, int test, String resultat) {
        this.nom = nom;
        this.data = data;
        this.test = test;
        this.resultat = resultat;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getTest() {
        return test;
    }

    public void setTest(int test) {
        this.test = test;
    }

    public String getResultat() {
        return resultat;
    }

    public void setResultat(String resultat) {
        this.resultat = resultat;
    }

    @Override
    public String toString() {
        return "Persona{" + "nom=" + nom + ", data=" + data + ", test=" + test + ", resultat=" + resultat + '}';
    }
    
    public void escriureFitxer() throws IOException{
        FileWriter fw = new FileWriter("resultats.txt", true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(toString());
        bw.newLine();
        bw.close();
    }
    
}
