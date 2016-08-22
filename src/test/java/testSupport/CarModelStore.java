package testSupport;

import entities.CarModel;

/**
 * Created by Ежище on 20.08.2016.
 */
public final class CarModelStore {
    public final static CarModel MASHKA_MODEL = new CarModel("mashka", "Mashka", 123, 456, 0.9);
    public final static CarModel MASHKA_CAR_MODEL = new CarModel("mashka", "MashkaCar", 123, 456, 0.9);
    public final static CarModel BMW_MODEL = new CarModel("bmw", "BMW", 123, 456, 0.9);
    public final static CarModel BMW_CAR_MODEL = new CarModel("bmw", "BmwCar", 123, 456, 0.9);
    public final static CarModel FERRARI_MODEL = new CarModel("ferrari", "Ferrari", 123, 456, 0.9);
    public final static CarModel FERRARI_CAR_MODEL = new CarModel("ferrari", "FerrariCar", 123, 456, 0.9);
    public final static CarModel NEGATIVE_ACCELERATION_MODEL = new CarModel("mashka", "Mashka", -123, 456, 0.9);
    public final static CarModel NEGATIVE_FULL_SPEED_MODEL = new CarModel("mashka", "Mashka", 123, -456, 0.9);


}
