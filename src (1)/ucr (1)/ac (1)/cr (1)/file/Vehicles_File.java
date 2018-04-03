package ucr.ac.cr.file;

import ucr.ac.cr.domain.Vehicle;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Vehicles_File {

    //Atributos o Variables
    public RandomAccessFile randomAccessFileVehicle;
    private int regsQuantity;
    private int regSize;
    public String myFilePath;

    //Crear Archivo Acceso Aleatorio, Metodo ingresar, metodo buscar, metodo eliminar, modificar  v:
    //Constructor de la clase
    public Vehicles_File(File file) throws IOException {

        this.myFilePath = file.getPath();
        this.regSize = 150;

        if (file.exists() && !file.isFile()) {
            throw new IOException(file.getName() + " is an invalid file");
        } else {
            randomAccessFileVehicle = new RandomAccessFile(file, "rw");
        }

        this.regsQuantity = (int) Math.ceil((double) randomAccessFileVehicle.length() / (double) regSize);
    } //End Method

    //Metodo para insertar un nuevo Vehiculo
    public boolean putValue(int position, Vehicle vehicleToInsert) throws IOException {

        if (position >= 0 && position <= this.regsQuantity) {
            if (vehicleToInsert.sizeInBytes() > this.regSize) {
                System.out.println("Error 0001 - Record Size is too large");
                return false;
            }

            //insertar vehiculo al archivo
            // name, year, mileage, american, series
            randomAccessFileVehicle.seek(position * this.regSize);
            randomAccessFileVehicle.writeUTF(vehicleToInsert.getName());
            randomAccessFileVehicle.writeInt(vehicleToInsert.getYear());
            randomAccessFileVehicle.writeFloat(vehicleToInsert.getMileage());
            randomAccessFileVehicle.writeBoolean(vehicleToInsert.isAmerican());
            randomAccessFileVehicle.writeInt(vehicleToInsert.getSeries());

            return true;
        } else {
            System.err.println("Error 0002 - Position is out of bounds");
            return false;
        }
    }   //End Method

    //Insertar al final del archivo
    public boolean addEndRecord(Vehicle vehicle) throws IOException {

        boolean success = putValue(this.regsQuantity, vehicle);
        if (success) {
            ++this.regsQuantity;
        }
        return success;
    } //End Method

    //Metodo para buscar un vehiculo en un posicion
    public Vehicle getVehicle(int position) throws IOException {

        if (position >= 0 && position <= regsQuantity) {
            randomAccessFileVehicle.seek(position * this.regSize);

            Vehicle vehicleTemp = new Vehicle();

            vehicleTemp.setName(randomAccessFileVehicle.readUTF());
            vehicleTemp.setYear(randomAccessFileVehicle.readInt());
            vehicleTemp.setMileage(randomAccessFileVehicle.readFloat());
            vehicleTemp.setAmerican(randomAccessFileVehicle.readBoolean());
            vehicleTemp.setSeries(randomAccessFileVehicle.readInt());

            if (vehicleTemp.getSeries() == -1) {
                return null;
            } else {
                return vehicleTemp;
            }
        } else {
            System.err.println("Error 0003 - Position ir out of bonds");
            return null;
        }
    } // End method

    //Metodo para crear un ArrayList Vehiculo
    public ArrayList<Vehicle> getVehicleList() throws IOException {

        ArrayList<Vehicle> ArrayListofVehicle = new ArrayList<>();

        for (int i = 0; i < this.regsQuantity; i++) {
            Vehicle vehicleTemp = getVehicle(i);

            if (vehicleTemp != null) {
                ArrayListofVehicle.add(vehicleTemp);
            }

        }
        return ArrayListofVehicle;
    } //END Method

    //Metodo para borrar-eliminar un registro
    public boolean deleteRecord(int Serie) throws IOException {

        Vehicle vehicleTemp;

        for (int i = 0; i < regsQuantity; i++) {
            vehicleTemp = getVehicle(i);

            if (vehicleTemp != null) {

                if (vehicleTemp.getSeries() == Serie) {
                    vehicleTemp.setSeries(-1);

                    return this.putValue(i, vehicleTemp);

                }

            }

        }
        return false;
    }

    //Metodo que compara si la Serie de dos vehiculos es la misma o no
    public boolean compareRecord(int Serie) throws IOException {

        Vehicle vehicleTemp;

        for (int i = 0; i < regsQuantity; i++) {
            vehicleTemp = getVehicle(i);

            if (vehicleTemp != null) {

                if (vehicleTemp.getSeries() == Serie) {

                    return true;

                }

            }

        }
        return false;
    }

    //Metodo que busca por serie
    public Vehicle getVehicleForSerie(int serie) throws IOException {

        Vehicle vehicleTemp = null;

        for (int i = 0; i < regsQuantity; i++) {
            vehicleTemp = getVehicle(i);

            if (vehicleTemp != null && vehicleTemp.getSeries() == serie) {

                return vehicleTemp;
            }
        }

        return vehicleTemp;

    }

    //Metodo para actualizar un dato de un registro.
    public boolean updateVehicle(int serie, String attribute, String newValue) throws IOException {

        System.out.println("  123456  " + newValue);
        System.out.println(" 99999   " + attribute);
        //Este vehiculo lo sacamos usando el metodo getValue pero llamando los 2 en la interfaz
        Vehicle vehicleTemp;

        for (int i = 0; i < regsQuantity; i++) {
            vehicleTemp = getVehicle(i);

            if (vehicleTemp != null) {

                if (vehicleTemp.getSeries() == serie) {

                    switch (attribute) {

                        case "Name": {
                            vehicleTemp.setName(newValue);
                            putValue(i, vehicleTemp);
                            return true;

                        }
                        case "Year": {
                            vehicleTemp.setYear(Integer.parseInt(newValue));
                            putValue(i, vehicleTemp);
                            return true;
                        }
                        case "Mileage": {
                            vehicleTemp.setMileage(Float.parseFloat(newValue));
                            putValue(i, vehicleTemp);
                            return true;
                        }
                        case "American": {

                            System.out.println("12233555" + newValue);
                            if (newValue.equalsIgnoreCase("Yes")) {

                                vehicleTemp.setAmerican(true);
                                putValue(i, vehicleTemp);
                            } else {
                                vehicleTemp.setAmerican(false);
                                putValue(i, vehicleTemp);
                            }
                            putValue(i, vehicleTemp);
                            return true;

                        }

                    }
                    putValue(i, vehicleTemp);
                    JOptionPane.showMessageDialog(null, vehicleTemp.getName());
                }

            }
        }
        return false;
    }
}
