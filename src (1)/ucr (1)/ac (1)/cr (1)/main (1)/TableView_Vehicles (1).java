package ucr.ac.cr.main;

import java.io.File;
import java.io.IOException;
import ucr.ac.cr.domain.Vehicle;
import ucr.ac.cr.file.Vehicles_File;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class TableView_Vehicles {

    //Crea la tabla para mostrar los datos de los vehiculos
    public VBox tableViewVehicles() throws IOException {

        TableView<Vehicle> tbv_vehicles = new TableView<>();

        //Crea las columnas necesarias
        TableColumn columnaName = new TableColumn<>("Name");
        columnaName.setMinWidth(100);
        columnaName.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn columnYear = new TableColumn<>("Year");
        columnYear.setMinWidth(100);
        columnYear.setCellValueFactory(new PropertyValueFactory<>("year"));

        TableColumn columnaMileage = new TableColumn<>("Mileage");
        columnaMileage.setMinWidth(150);
        columnaMileage.setCellValueFactory(new PropertyValueFactory<>("mileage"));

        TableColumn columnaAmaerican = new TableColumn<>("American");
        columnaAmaerican.setMinWidth(150);
        columnaAmaerican.setCellValueFactory(new PropertyValueFactory<>("american"));

        TableColumn columnaSeries = new TableColumn<>("Series");
        columnaSeries.setMinWidth(150);
        columnaSeries.setCellValueFactory(new PropertyValueFactory<>("series"));

        ObservableList<Vehicle> datos = getVehicles();

        tbv_vehicles.setItems(datos);

        // agrega las columnas al tableview
        tbv_vehicles.getColumns().addAll(columnaName, columnYear, columnaMileage, columnaAmaerican, columnaSeries);

        VBox vbox2 = new VBox();
        vbox2.setSpacing(5);
        vbox2.setPadding(new Insets(10, 0, 0, 10));
        vbox2.getChildren().addAll(tbv_vehicles);

        return vbox2;
    }

    //Crea un arrayList y un observableList de vehiculos para pasarlos a la tabla
    public ObservableList<Vehicle> getVehicles() throws IOException {
        File fl = new File("archive_vehicle.dat");
        Vehicles_File vb_file = new Vehicles_File(fl);

        ArrayList array = new ArrayList();
        ArrayList<Vehicle> temp = vb_file.getVehicleList();

        for (Vehicle V : temp) {
            array.add(V);
        }

        ObservableList<Vehicle> listOfVehicles = FXCollections.observableArrayList(array);

        return listOfVehicles;
    }

}
