package ucr.ac.cr.domain;

//Crea la clase del objeto Vehiculo
public class Vehicle {

    //Creacion de los atributos de la clase
    private String name;
    private int year;
    private float mileage;
    private boolean american;
    private int series;

    //Constructores del objeto Vehiculo
    public Vehicle() {
        this.name = "";
        this.year = 0;
        this.mileage = 0;
        this.american = false;
        this.series = 0;
    }

    public Vehicle(String name, int year, Float mileage, boolean american, int series) {
        this.name = name;
        this.year = year;
        this.mileage = mileage;
        this.american = american;
        this.series = series;
    }

    //Creacion de los accesores del objeto vehiculo
    public Vehicle(String text) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public float getMileage() {
        return mileage;
    }

    public void setMileage(float mileage) {
        this.mileage = mileage;
    }

    public boolean isAmerican() {
        return american;
    }

    public void setAmerican(boolean american) {
        this.american = american;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    //ToString del objeto vehiculo
    @Override
    public String toString() {
        return "Vehicle{" + "name=" + name + ", year=" + year + ", kilometraje=" + mileage + ", americano=" + american + ", series=" + series + '}';
    }

    //Metodo que da el tamanno en bytes del objeto.
    public int sizeInBytes() {
        //retornar la suma en bytes en todos los atributos

        return 13 + this.getName().length() * 2;

    }

}
