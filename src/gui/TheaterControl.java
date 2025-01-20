package gui;

import business.TheaterModel;
import business.Theaterveranstaltung;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import ownUtil.MeldungsfensterAnzeiger;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.util.ArrayList;

public class TheaterControl {
    private TheaterView view;
    private TheaterModel model;

    public TheaterControl(Stage stage) {
        this.view = new TheaterView(stage);
        this.model = new TheaterModel();
        this.initListener();
    }

    private void initListener() {
        view.getMnItmCsvImport().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                bearbeiteImport("csv");
            }
        });
        view.getMnItmTxtImport().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                bearbeiteImport("txt");
            }
        });
        view.getCmbBxName().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                zeigeTheateradresseAn();
            }
        });
        view.getBtnAnzeigeVeranstaltungen().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                zeigeVeranstaltungenAn();
            }
        });
    }

    private void bearbeiteImport(String dateityp) {
        try {
            if("csv".equals(dateityp)){
                model.importFromCsv();
            } else if("txt".equals(dateityp)){
                model.importFromTxt();
            } else {
                throw new Exception();
            }
            ArrayList<String> theaterNamen = new ArrayList<>();
            for (Theaterveranstaltung tv : model.getTheaterveranstaltungen()) {
                theaterNamen.add(tv.getName());
            }
            view.setzeCmBxName(theaterNamen);
            zeigeInformationsfensterAn("Die Theaterveranstaltungen sind gelesen worden.");
        } catch(Exception Exc) {
            zeigeFehlermeldungsfensterAn("Fehler beim Lesen", 
                "Die " + dateityp + "-Datei konnte nicht gelesen werden.");
        }
    }

    private void zeigeTheateradresseAn() {
        String name = view.getCmbBxName().getValue();
        if (name != null) {
            Theaterveranstaltung theaterveranstaltung = model.getTheaterveranstaltung(name);
            if (theaterveranstaltung != null) {
                view.updateAdresse(theaterveranstaltung.getStrasseHnr(), theaterveranstaltung.getPlz(), theaterveranstaltung.getOrt());
            } else {
                zeigeInformationsfensterAn("Kein Theater gefunden für: " + name);
            }
        } else {
            zeigeInformationsfensterAn("Wählen Sie einen Theaternamen aus.");
        }
    }

    private void zeigeVeranstaltungenAn() {
        String name = view.getCmbBxName().getValue();
        if (name != null) {
            Theaterveranstaltung theaterveranstaltung = model.getTheaterveranstaltung(name);
            if (theaterveranstaltung != null) {
                view.updateVeranstaltungen(theaterveranstaltung.gibTheaterveranstaltungZurueck('\\'));
            } else {
                zeigeInformationsfensterAn("Kein Theater gefunden für: " + name);
            }
        } else {
            zeigeInformationsfensterAn("Wählen Sie einen Theaternamen aus.");
        }
    }

    private void zeigeInformationsfensterAn(String meldung){
        new MeldungsfensterAnzeiger(AlertType.INFORMATION, "Information", meldung).zeigeMeldungsfensterAn();
    }

    private void zeigeFehlermeldungsfensterAn(String titel, String meldung){
        new MeldungsfensterAnzeiger(AlertType.ERROR, titel, meldung).zeigeMeldungsfensterAn();
    }
}