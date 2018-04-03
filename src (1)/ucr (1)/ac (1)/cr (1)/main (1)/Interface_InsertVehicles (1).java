package ucr.ac.cr.main;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javax.swing.JOptionPane;
import ucr.ac.cr.domain.Vehicle;
import ucr.ac.cr.file.Vehicles_File;

public class Interface_InsertVehicles {

    public VBox itf_InsertVehicles() {

        //Labels o etiquetas
        Label lb_Name = new Label("Name");
        Label lb_Year = new Label("Year");
        Label lb_Miliage = new Label("Miliage");
        Label lb_American = new Label("American");
        Label lb_series = new Label("Series");
        Label lb_resultado = new Label("");

        //Textfiels o campos de texto
        TextField tfd_getName = new TextField();
        TextField tfd_getYear = new TextField();
        TextField tfd_getMileage = new TextField();
        TextField tfd_getseries = new TextField();
        //choice box para ver si es americano o no lo es
        ChoiceBox cb_American = new ChoiceBox(FXCollections.observableArrayList(
                "Yes", "No")
        );
        cb_American.setValue("No");

        //acciones
        Button btn_Insert = new Button("Accept");

        //Vbox de la interfaz
        VBox vbx_InsertVehicles = new VBox();
        vbx_InsertVehicles.setPadding(new Insets(10, 10, 10, 10));
        vbx_InsertVehicles.setAlignment(Pos.CENTER);
        vbx_InsertVehicles.setSpacing(20);
        vbx_InsertVehicles.getChildren().addAll(lb_Name, tfd_getName, lb_Year, tfd_getYear, lb_Miliage, tfd_getMileage,
                lb_American, cb_American, lb_series, tfd_getseries, btn_Insert);

        //inserta el vehiculo
        btn_Insert.setOnAction(e -> {

            File fl = new File("archive_vehicle.dat");

            try {
                Vehicles_File fle_vehicle = new Vehicles_File(fl);
                boolean american;

                if (cb_American.getValue().equals("Yes")) {
                    american = true;
                } else {
                    american = false;
                }

                if (fle_vehicle.compareRecord(Integer.parseInt(tfd_getseries.getText())) == true) {

                    lb_resultado.setText("the vehicle already exist");

                } else {

                    if (tfd_getName.getText().equals("") || tfd_getMileage.getText().equals("") || tfd_getYear.getText().equals("") || tfd_getseries.getText().equals("")) {

                       lb_resultado.setText("Please complete the spaces in white to insert the vehicle");

                    } else {
                        Vehicle vehicleTemp = new Vehicle(tfd_getName.getText(), Integer.parseInt(tfd_getYear.getText()), Float.parseFloat(tfd_getMileage.getText()), american, Integer.parseInt(tfd_getseries.getText()));
                        Vehicles_File vb = new Vehicles_File(fl);
                        vb.addEndRecord(vehicleTemp);
                        lb_resultado.setText("the vehicle has been added");

                    }

                }

                //Limpia los campos de texto.
                tfd_getName.setText("");
                tfd_getYear.setText("");
                tfd_getMileage.setText("");
                tfd_getseries.setText("");

                vbx_InsertVehicles.getChildren().clear();
                vbx_InsertVehicles.getChildren().addAll(lb_Name, tfd_getName, lb_Year, tfd_getYear, lb_Miliage, tfd_getMileage,
                        lb_American, cb_American, lb_series, tfd_getseries, btn_Insert, lb_resultado);

            } catch (IOException ex) {
                Logger.getLogger(Interface_InsertVehicles.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            catch(NumberFormatException nfe){
                JOptionPane.showMessageDialog(null, "Please complete the spaces in white to insert the vehicle");
            }

        });

        return vbx_InsertVehicles;
    }

}
