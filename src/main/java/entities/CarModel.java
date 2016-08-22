package entities;

/**
 * Created by Ежище on 04.08.2016.
 */
public class CarModel {
    public String name;
    public  String marker;
    public double acceleration;
    public double fullSpeed;
    public double mobility;
    public CarModel(){};
    public CarModel(String name, String marker, double acceleration, double fullSpeed, double mobility){
        this.name = name;
        this.marker = marker;
        this.acceleration = acceleration;
        this.fullSpeed = fullSpeed;
        this.mobility = mobility;
    }
}
