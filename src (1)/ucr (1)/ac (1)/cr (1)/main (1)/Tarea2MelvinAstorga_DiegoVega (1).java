
package ucr.ac.cr.main;

import java.io.IOException;
import javafx.application.Application;
import javafx.stage.Stage;


public class Tarea2MelvinAstorga_DiegoVega extends Application {

    Main_Interface m_Interface = new Main_Interface();

    @Override
    public void start(Stage primaryStage) throws IOException {

        //Se le da titulo a la ventana, y llama la escena principal
        primaryStage.setTitle("Homework 2 -MelvinAstorga/DiegoVega-");
        primaryStage.setScene(m_Interface.Interface());
        primaryStage.show();
        primaryStage.setResizable(false);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
