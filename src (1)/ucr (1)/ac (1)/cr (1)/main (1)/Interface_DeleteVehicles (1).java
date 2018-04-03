package ucr.ac.cr.main;

import java.io.File;
import java.io.IOException;
import ucr.ac.cr.file.Vehicles_File;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class Interface_DeleteVehicles {

 
    
    // interface para borrar vehiculos
    public VBox dlt_Vehicles() {

        //labels
        Label lb_delete = new Label("Insert the series to search the vehicle that you want delete");
        Label lb_delete1 = new Label("the vehicle has been Deleted");
        Label lb_delete2 = new Label("th vheicle doesn´t exist");

        //texfields
        TextField tfd_delete = new TextField();
        tfd_delete.setVisible(true);
        //botones
        Button btn_search = new Button("Search");
        Button btn_delete = new Button("Delete");
        Button btn_back = new Button("Back");
        btn_back.setVisible(false);
        btn_delete.setVisible(false);

        //VBox para la interfaz
        VBox vbx_Delete = new VBox();
        vbx_Delete.getChildren().addAll(lb_delete, tfd_delete, btn_search, btn_back);
        vbx_Delete.setPadding(new Insets(10, 10, 10, 10));
        vbx_Delete.setAlignment(Pos.CENTER);
        vbx_Delete.setSpacing(20);

        //acciones
        btn_search.setOnAction(e -> {

            btn_search.setVisible(false);
            btn_delete.setVisible(true);
            btn_back.setVisible(true);
            tfd_delete.setVisible(false);

            try {
                boolean temp = false;
                //Crea un archivo nuevo
                File fl = new File("archive_vehicle.dat");

                //Instancia de la clase de archivos de vehiculo
                Vehicles_File fle_vehicle = new Vehicles_File(fl);
                

                if (Integer.parseInt(tfd_delete.getText()) > 0) {

                    temp = fle_vehicle.deleteRecord(Integer.parseInt(tfd_delete.getText()));

                    if (temp == true) {

                        lb_delete1.setText("The vehicle has been deleted");
                        tfd_delete.setText("");
                        vbx_Delete.getChildren().clear();
                        vbx_Delete.getChildren().addAll(lb_delete1, btn_back);

                    }

                    if (temp == false) {

                        lb_delete2.setText("The vehicle does´t exist");
                        tfd_delete.setText("");
                        vbx_Delete.getChildren().clear();
                        vbx_Delete.getChildren().addAll(lb_delete2, btn_back);
                    }
                    
                }

            } catch (IOException ex) {

                System.out.println(" error-10005 ");
            }

        });

        //Boton que retorna a la interfaz principal
        btn_back.setOnAction(e -> {

            btn_search.setVisible(true);
            btn_delete.setVisible(false);
            btn_back.setVisible(false);
            tfd_delete.setVisible(true);

            vbx_Delete.getChildren().clear();
            vbx_Delete.getChildren().addAll(lb_delete, tfd_delete, btn_search, btn_back);

        });

        return vbx_Delete;
    }

}
