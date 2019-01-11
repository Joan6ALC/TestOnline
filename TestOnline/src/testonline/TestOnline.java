/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testonline;

//import java.io.BufferedReader;
//import java.io.BufferedWriter;
import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Joan
 */
public class TestOnline {

    private String nom;
    private String fitxer;
    private int numRespostes;
    private char[] respTotal;
    private char[] solucio;
    private int numeroTest = 0;
    private int tempsTotal;
    private int numPreguntes;
    private int lineaResp = 0;
    private int cont = 0;

//    private int tamResp = 0;    // tamany array respostes
//    private double temps;
    private double temps1;
    private double temps2;
    private double tempsaux;
    private String resultat;
    LT lt = new LT();
    Date date = new Date();
    Persona p = new Persona();

    public void inici() throws FileNotFoundException, IOException {

        char resp;
        char[] preguntes;
        int aux = 0;
        int cont;
        tempsTotal = 0;
        temps1 = 0;
        temps2 = 0;

        String st;
        nom();
        menu();
        llegirLinees();
        fitxer = "respostes.txt";
        lineaResp = 1 + numeroTest;
        llegirLinees();
        comparar();
    }

    public void nom() {
        System.out.println("Introdueix el teu nom: ");
        nom = lt.llegirLiniaS();
        p.setNom(nom);
    }

    public void menu() throws IOException {

        int opcio;

        System.out.println("Tria una opció: ");
        System.out.println("    1. Test 1.");
        System.out.println("    2. Test 2.");
        System.out.println("    3. Sortir.");
        opcio = lt.llegirSencer();
//        p.setData(date.toString());
//        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
//        p.setData(dateFormat.format(date));
        switch (opcio) {
            case 1:
                fitxer = "test1.txt";
                numeroTest = 1;
                tempsaux = System.nanoTime();
                break;
            case 2:
                fitxer = "test2.txt";
                numeroTest = 2;
                tempsaux = System.nanoTime();
                break;
            case 3:
                System.exit(0);
                break;
            default:
                fitxer = "test1.txt";
                numeroTest = 1;
                tempsaux = System.nanoTime();
        }
        p.setTest(numeroTest);
    }

    public void llegirLinees() {
        LlegirFitxer lect = new LlegirFitxer();
        int cont = 0;
        lect.open(fitxer);
        char[] linea = lect.readLine();

        while (linea != null) {
            if (lect.isTime()) {
                tempsTotal = toInt(linea);
            }
            if (lect.isNumOpcions()) {
                numRespostes = toInt(linea);
            }
            if (lect.isNumPreguntes()) {

                numPreguntes = toInt(linea);
                respTotal = new char[(numPreguntes * 2) - 1];

            }
            if (lect.isQuestion()) {
                while (cont < numPreguntes) {
                    tractamentPreguntes(linea);
                    respostes();
                    cont++;
                    linea = lect.readLine();
                }

            }
            if (lect.isRespostes(fitxer, lineaResp)) {
                solucio = linea;
                System.out.print("Respostes vàlides:    ");
                System.out.println(solucio);
                break;
            }
            linea = lect.readLine();
        }
    }

    public void tractamentPreguntes(char[] linea) {

        for (int i = 0; i < linea.length; i++) {
            if (linea[i] != '[') {
                System.out.print(linea[i]);
            } else {
                System.out.println("");
                System.out.print("      " + linea[i]);
            }
        }
    }

    public void respostes() {
        char resp;

        System.out.println("");
        System.out.println("Introdueix la resposta: ");
        resp = lt.llegirCaracter();
        while (resp != 'a' && resp != 'b' && resp != 'c' && resp != 'd') {
            System.out.println("Introdueix una resposta vàlida: ");
            resp = lt.llegirCaracter();
        }
        temps2 = System.nanoTime();
        System.out.print("Has gastat: ");
        temps();

        respTotal[cont] = resp;
        cont = cont + 2;
        for (int i = 1; i < respTotal.length; i = i + 2) {
            respTotal[i] = ' ';
        }
//        System.out.println("nombre de respostes: " + numRespostes);
        System.out.print("Respostes usuari:     ");
        System.out.println(respTotal);
        temps2 = System.nanoTime();
    }

    public int toInt(char[] let) {
        int res = 0;
        boolean bo = true;
        for (int i = 0; (i < let.length) && bo; i++) {
            if ((let[i] < '0') || (let[i] > '9')) {
                bo = false;
            } else {
                res = res * 10;
                res = res + (let[i] - '0');
            }
        }
        if (!bo) {
            res = -1;
        }
        return res;
    }

    public void comparar() throws IOException {
        int encerts = 0;
        for (int i = 0; i < respTotal.length; i = i + 2) {
            if (solucio[i] == respTotal[i]) {
                encerts++;
            }
        }
        nota(encerts);
        System.out.println(p.toString());
        p.escriureFitxer();
        dades();
        inici();
    }

    public void nota(int e) {
        int nota;
        nota = (e * 10) / (numPreguntes);
        System.out.println("La teva nota és: " + nota);

        if (temps1 / 60 > tempsTotal) {
            resultat = "INSUFICIENT PER TEMPS";
        } else if (nota == 10) {
            resultat = "FORA DE SÈRIE";
        } else if (9 <= nota && nota < 10) {
            resultat = "EXCEL·LENT";
        } else if (7 <= nota && nota < 9) {
            resultat = "NOTABLE";
        } else if (6 <= nota && nota < 7) {
            resultat = "NORMAL";
        } else if (5 <= nota && nota < 6) {
            resultat = "SUFICIENT";
        } else if (nota < 5) {
            resultat = "INSUFICIENT";
        }
        System.out.println(resultat);

        p.setResultat(resultat);
    }

    public void temps() {
        temps1 = temps2 - tempsaux;
        temps1 = temps1 * (1.666667 * Math.pow(10, -11));
        temps1 = temps1 * 60;
        int hores;
        int minuts;
        int segons;
        String h = "";
        String m = "";
        String s = "";
        hores = (int) temps1 / 3600;
        minuts = (int) (temps1 / 60);
        segons = (int) temps1 % 60;

        h = h + hores;
        if (h.length() == 1) {
            h = "0" + h;
        }
        m = m + minuts;
        if (m.length() == 1) {
            m = "0" + m;
        }
        s = s + segons;
        if (s.length() == 1) {
            s = "0" + s;
        }
        System.out.println(h + ":" + m + ":" + s);
    }

    public void dades() {

        p.setResultat(resultat);
        p.setTest(numeroTest);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
        p.setData(dateFormat.format(date));
        p.setNom(nom);
    }

    public static void main(String[] args) throws IOException {
        new TestOnline().inici();
    }

}
