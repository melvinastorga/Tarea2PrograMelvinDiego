package ucr.ac.cr.main;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javax.swing.JOptionPane;
import ucr.ac.cr.domain.Vehicle;
import ucr.ac.cr.file.Vehicles_File;

public class Interface_Update {

    //Main_Interface m_inter = new Main_Interface();
    public VBox sc_Update() {

        //elementos del la interface de actualizar
        //vbox
        VBox vbx_Update = new VBox();
        //labels
        Label lb_seekVehicle = new Label("type the vehicle series");
        Label lb_seekAttribute = new Label("choose the attribute to update");
        Label lb_newValue = new Label("Enter the new value");
        Label lb_noVehicle = new Label("Vehicle not found, try again");

        //texfields
        TextField tf_seekSerie = new TextField();
        TextField tf_newValue = new TextField();

        //buttons
        Button btn_Search = new Button("Search Series");
        Button btn_Update = new Button("Update");
        Button btn_back = new Button("Back");
        Button btn_continue = new Button("Continue");

        //ChoiceBox
        ChoiceBox cb_Attribute = new ChoiceBox(FXCollections.observableArrayList(
                "Name", "Year", "Mileage", "American"));
        cb_Attribute.setValue("Name");
        ChoiceBox cb_American = new ChoiceBox(FXCollections.observableArrayList(
                "Yes", "No"));
        //cb_American.setValue("No");

        //setVisible(False) a los atributos que no usaremos al inicio
        lb_newValue.setVisible(false);
        lb_noVehicle.setVisible(false);
        lb_seekAttribute.setVisible(false);

        tf_newValue.setVisible(false);
        btn_Update.setVisible(false);
        btn_back.setVisible(false);
        btn_continue.setVisible(false);
        cb_Attribute.setVisible(false);
        cb_American.setVisible(false);

        //actions
        btn_Search.setOnAction(e -> {

            try {
                File fl = new File("archive_vehicle.dat");

                Vehicles_File fle_vehicle = new Vehicles_File(fl);

                int serie = Integer.parseInt(tf_seekSerie.getText());
                Vehicle vehicleTemp = fle_vehicle.getVehicleForSerie(serie);

                if (vehicleTemp != null && vehicleTemp.getSeries() == serie) {

                    lb_seekAttribute.setVisible(true);
                    cb_Attribute.setVisible(true);
                    btn_continue.setVisible(true);
                    btn_back.setVisible(true);

                    vbx_Update.getChildren().clear();
                    vbx_Update.getChildren().addAll(lb_seekAttribute, cb_Attribute, btn_continue, btn_back);

                } else {
                    lb_noVehicle.setVisible(true);
                    vbx_Update.getChildren().clear();
                    vbx_Update.getChildren().addAll(lb_seekVehicle, tf_seekSerie, btn_Search, lb_noVehicle);
                }

            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(null, "enter a whole number for series");

            } catch (IOException ex) {
                Logger.getLogger(Interface_Update.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        btn_continue.setOnAction(e -> {

            if (cb_Attribute.getValue().equals("Name")) {
                lb_newValue.setVisible(true);
                tf_newValue.setVisible(true);
                btn_Update.setVisible(true);

                vbx_Update.getChildren().clear();
                vbx_Update.getChildren().addAll(cb_Attribute, lb_newValue, tf_newValue, btn_continue, btn_Update, btn_back);

            } else if (cb_Attribute.getValue().equals("Year")) {
                lb_newValue.setVisible(true);
                tf_newValue.setVisible(true);
                btn_Update.setVisible(true);

                vbx_Update.getChildren().clear();
                vbx_Update.getChildren().addAll(cb_Attribute, lb_newValue, tf_newValue, btn_continue, btn_Update, btn_back);

            } else if (cb_Attribute.getValue().equals("Mileage")) {

                lb_newValue.setVisible(true);
                tf_newValue.setVisible(true);
                btn_Update.setVisible(true);

                vbx_Update.getChildren().clear();
                vbx_Update.getChildren().addAll(cb_Attribute, lb_newValue, tf_newValue, btn_continue, btn_Update, btn_back);

            } else if (cb_Attribute.getValue().equals("American")) {

                lb_newValue.setVisible(true);
                cb_American.setVisible(true);
                btn_Update.setVisible(true);

                vbx_Update.getChildren().clear();
                vbx_Update.getChildren().addAll(cb_Attribute, lb_newValue, cb_American, btn_continue, btn_Update, btn_back);

            }

            btn_continue.setVisible(false);
        });

        btn_back.setOnAction(e -> {
            vbx_Update.getChildren().clear();
            vbx_Update.getChildren().addAll(lb_seekVehicle, tf_seekSerie, btn_Search);
        });

        btn_Update.setOnAction(e -> {

            boolean success;
            String attribute = "";
            String newData = "";
            try {

                File fl = new File("archive_vehicle.dat");

                Vehicles_File fle_vehicle = new Vehicles_File(fl);

                //Seleccion que quiere cambiar
                if (cb_Attribute.getValue().equals("Name")) {
                    attribute = "Name";
                } else if (cb_Attribute.getValue().equals("Year")) {
                    attribute = "Year";
                } else if (cb_Attribute.getValue().equals("Mileage")) {
                    attribute = "Mileage";
                } else if (cb_Attribute.getValue().equals("American")) {
                    attribute = "American";
                }

                if (cb_Attribute.getValue().equals("American") && (cb_American.getValue().equals("Yes"))) {
                    newData = "Yes";

                }
                if (cb_Attribute.getValue().equals("American") && (cb_American.getValue().equals("No"))) {
                    newData = "No";

                }
                if (!cb_Attribute.getValue().equals("American")) {
                    newData = tf_newValue.getText();

                }

                //Seleccion que quiere cambiar
                if (cb_Attribute.getValue().equals("Name")) {
                    attribute = "Name";
                } else if (cb_Attribute.getValue().equals("Year")) {
                    attribute = "Year";
                } else if (cb_Attribute.getValue().equals("Mileage")) {
                    attribute = "Mileage";
                } else if (cb_Attribute.getValue().equals("American")) {
                    attribute = "American";
                }

                int serie = Integer.parseInt(tf_seekSerie.getText());
                Vehicle vehicleTemp = fle_vehicle.getVehicleForSerie(serie);

                if (vehicleTemp != null && vehicleTemp.getSeries() == serie) {

                    success = fle_vehicle.updateVehicle(serie, attribute, newData);
                    JOptionPane.showMessageDialog(null, vehicleTemp.getName());
                }

            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(null, "enter a whole number for series");

            } catch (IOException ex) {
                Logger.getLogger(Interface_Update.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        vbx_Update.getChildren().addAll(lb_seekVehicle, tf_seekSerie, lb_noVehicle, btn_Search, lb_seekAttribute, cb_Attribute, lb_newValue, tf_newValue, cb_American, btn_Update, btn_back);
        vbx_Update.setPadding(new Insets(10, 10, 10, 10));
        vbx_Update.setAlignment(Pos.CENTER);
        vbx_Update.setSpacing(10);

        return vbx_Update;

    }

}
