package business;

import java.io.*;
import java.util.ArrayList;

public class TheaterModel {
    private ArrayList<Theaterveranstaltung> theaterveranstaltungen = new ArrayList<>();

    public ArrayList<Theaterveranstaltung> getTheaterveranstaltungen() {
        return this.theaterveranstaltungen;
    }

    public void importFromCsv() throws Exception {
        theaterveranstaltungen = leseTheaterveranstaltungenAusCsvDatei();
    }

    public void importFromTxt() throws Exception {
        theaterveranstaltungen = leseTheaterveranstaltungenAusTxtDatei();
    }

    private ArrayList<Theaterveranstaltung> leseTheaterveranstaltungenAusCsvDatei() throws Exception {
        BufferedReader ein = new BufferedReader(new FileReader("Theaterveranstaltungen.csv"));
        String zeile = null;            
        String[] zeileAsArray = null;
        ArrayList<Theaterveranstaltung> ergebnis = new ArrayList<>();
        do {
            zeile = ein.readLine();
            if(zeile != null) {
                zeileAsArray = zeile.split(";");
                ergebnis.add(new Theaterveranstaltung(
                    zeileAsArray[0], zeileAsArray[1], zeileAsArray[2], zeileAsArray[3],
                    zeileAsArray[4], zeileAsArray[5], Integer.parseInt(zeileAsArray[6])));
            }    
        } while (zeile != null);
        ein.close();
        return ergebnis;
    }
        
    private ArrayList<Theaterveranstaltung> leseTheaterveranstaltungenAusTxtDatei() throws Exception {
        BufferedReader ein = new BufferedReader(new FileReader("Theaterveranstaltungen.txt"));
        String zeile = null;
        ArrayList<Theaterveranstaltung> ergebnis = new ArrayList<>();
        do {
            zeile = ein.readLine();
            if(zeile != null) {
                ergebnis.add(new Theaterveranstaltung(
                    zeile, ein.readLine(), ein.readLine(), ein.readLine(),
                    ein.readLine(), ein.readLine(), Integer.parseInt(ein.readLine())));    
            }
        } while (zeile != null);
        ein.close();
        return ergebnis;
    }    
    
    public Theaterveranstaltung getTheaterveranstaltung(String name) {
        for (Theaterveranstaltung tv : theaterveranstaltungen) {
            if (name.equals(tv.getName())) {
                return tv;
            }
        }
        return null;
    }
}