package gui;

import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class TheaterView {
    private Pane pane = new Pane();
    private Label lblTheater = new Label("Theater");
    private Label lblVeranstaltungen = new Label("Veranstaltungen");
    private Label lblName = new Label("Theatername:");
    private Label lblStrasseHnr = new Label("Straﬂe und Hausnummer:");
    private Label lblPlzOrt = new Label("Plz und Ort:");
    private ComboBox<String> cmbBxName = new ComboBox<>();
    private TextField txtStrasseHnr = new TextField();
    private TextField txtPlz = new TextField();
    private TextField txtOrt = new TextField();
    private TextArea txtVeranstaltungen = new TextArea();
    private Button btnAnzeigeVeranstaltungen = new Button("Anzeige Veranstaltungen");
    private MenuBar mnbrMenuLeiste = new MenuBar();
    private Menu mnDatei = new Menu("Datei");
    private MenuItem mnItmCsvImport = new MenuItem("csv-Import");
    private MenuItem mnItmTxtImport = new MenuItem("txt-Import");

    public TheaterView(Stage primaryStage) {
        Scene scene = new Scene(this.pane, 600, 280);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Verwaltung von Theatern");
        primaryStage.show();
        initKomponenten();
    }

    private void initKomponenten() {
        // Labels
        lblTheater.setLayoutX(20); lblTheater.setLayoutY(40);
        Font font = new Font("Arial", 24);
        lblTheater.setFont(font); lblTheater.setStyle("-fx-font-weight: bold;");
        lblVeranstaltungen.setLayoutX(360); lblVeranstaltungen.setLayoutY(40);
        lblVeranstaltungen.setFont(font); lblVeranstaltungen.setStyle("-fx-font-weight: bold;");
        lblName.setLayoutX(20); lblName.setLayoutY(90);
        lblStrasseHnr.setLayoutX(20); lblStrasseHnr.setLayoutY(130);
        lblPlzOrt.setLayoutX(20); lblPlzOrt.setLayoutY(170);
        pane.getChildren().addAll(lblTheater, lblVeranstaltungen, lblName, lblStrasseHnr, lblPlzOrt);

        // Textfelder
        cmbBxName.setLayoutX(170); cmbBxName.setLayoutY(90); cmbBxName.setPrefWidth(170);
        txtStrasseHnr.setLayoutX(170); txtStrasseHnr.setLayoutY(130); txtStrasseHnr.setPrefWidth(170);
        txtPlz.setLayoutX(170); txtPlz.setLayoutY(170); txtPlz.setPrefWidth(50);
        txtOrt.setLayoutX(230); txtOrt.setLayoutY(170); txtOrt.setPrefWidth(110);
        pane.getChildren().addAll(cmbBxName, txtStrasseHnr, txtPlz, txtOrt);

        // Textbereich
        txtVeranstaltungen.setEditable(false);
        txtVeranstaltungen.setLayoutX(360); txtVeranstaltungen.setLayoutY(90);
        txtVeranstaltungen.setPrefWidth(220); txtVeranstaltungen.setPrefHeight(125);
        pane.getChildren().add(txtVeranstaltungen);

        // Button
        btnAnzeigeVeranstaltungen.setLayoutX(360); btnAnzeigeVeranstaltungen.setLayoutY(230);
        pane.getChildren().add(btnAnzeigeVeranstaltungen);

        // Menu
        mnbrMenuLeiste.getMenus().add(mnDatei);
        mnDatei.getItems().add(mnItmCsvImport);
        mnDatei.getItems().add(mnItmTxtImport);
        pane.getChildren().add(mnbrMenuLeiste);
    }

    public void initListener(TheaterControl control) {
        mnItmCsvImport.setOnAction(e -> control.bearbeiteImport("csv"));
        mnItmTxtImport.setOnAction(e -> control.bearbeiteImport("txt"));
        cmbBxName.setOnAction(e -> control.zeigeTheateradresseAn());
        btnAnzeigeVeranstaltungen.setOnAction(e -> control.zeigeVeranstaltungenAn());
    }

    public void setzeCmBxName(ArrayList<String> names) {
        this.cmbBxName.getItems().clear();
        this.cmbBxName.getItems().addAll(names);
    }

    public void updateAdresse(String strasseHnr, String plz, String ort) {
        this.txtStrasseHnr.setText(strasseHnr);
        this.txtPlz.setText(plz);
        this.txtOrt.setText(ort);
    }

    public void updateVeranstaltungen(String veranstaltungen) {
        this.txtVeranstaltungen.setText(veranstaltungen);
    }

    public ComboBox<String> getCmbBxName() {
        return cmbBxName;
    }

    public Button getBtnAnzeigeVeranstaltungen() {
        return btnAnzeigeVeranstaltungen;
    }

    public MenuItem getMnItmCsvImport() {
        return mnItmCsvImport;
    }

    public MenuItem getMnItmTxtImport() {
        return mnItmTxtImport;
    }
}