package ucr.ac.cr.main;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;

//Interfaz principal
public class Main_Interface {

    //Crea la tabla que nos muestra la informacion
    TableView_Vehicles tbv_Vehiles = new TableView_Vehicles();
    Interface_Update itf_Update = new Interface_Update();
    Interface_InsertVehicles itf_InsertVehicles = new Interface_InsertVehicles();
    Interface_DeleteVehicles itf_DeleteVehicles = new Interface_DeleteVehicles();

    //clase para la interfaz del menu principal
    public Scene Interface() throws IOException {

        //vbox
        VBox vbx_Inteface = new VBox();
        vbx_Inteface.setPadding(new Insets(10, 10, 10, 10));
        vbx_Inteface.setAlignment(Pos.CENTER);
        vbx_Inteface.setSpacing(10);
        //barra de menu
        MenuBar mb_index = new MenuBar();

        //instancia de la scena con su tamaño predefinido por el vbox
        Scene m_Interface = new Scene(new VBox(), 2000, 1000);

        //elementos de la escena
        //labels
        Label instructions = new Label("Choose one of the options");
        Label instructions2 = new Label("To insert a new vehicle please go to options");

        //botons
        Button btn_delete = new Button("Delete");
        Button btn_update = new Button("Update");

        //barra de menu
        Menu options = new Menu("Options");
        MenuItem insertVehicle = new Menu("Insert Vehicles");
        MenuItem back = new Menu("Back to Menu");

        //actions
        //boton para borrar
        btn_delete.setOnAction(e -> {

            vbx_Inteface.getChildren().clear();

            try {

                vbx_Inteface.getChildren().addAll(mb_index, tbv_Vehiles.tableViewVehicles(), itf_DeleteVehicles.dlt_Vehicles());
            } catch (IOException ex) {
                Logger.getLogger(Main_Interface.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        //boton para actualizar
        btn_update.setOnAction(e -> {

            vbx_Inteface.getChildren().clear();

            try {
                vbx_Inteface.getChildren().addAll(mb_index, tbv_Vehiles.tableViewVehicles(), itf_Update.sc_Update());
            } catch (IOException ex) {
                Logger.getLogger(Main_Interface.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        //boton para insertar vehiculo
        insertVehicle.setOnAction((e) -> {

            vbx_Inteface.getChildren().clear();
            vbx_Inteface.getChildren().addAll(mb_index, itf_InsertVehicles.itf_InsertVehicles());

        });

        back.setOnAction((e) -> {

            vbx_Inteface.getChildren().clear();
            try {
                vbx_Inteface.getChildren().addAll(mb_index, instructions2, tbv_Vehiles.tableViewVehicles(), instructions, btn_update, btn_delete);
            } catch (IOException ex) {
                Logger.getLogger(Main_Interface.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        options.getItems().addAll(insertVehicle, back);

        mb_index.getMenus().addAll(options);

        //add items to the vbox
        vbx_Inteface.getChildren().addAll(mb_index, instructions2, tbv_Vehiles.tableViewVehicles(), instructions, btn_update, btn_delete);

        ((VBox) m_Interface.getRoot()).getChildren().addAll(vbx_Inteface);
        return m_Interface;

    }

}
