package cars;

import entities.CarModel;

/**
 * Created by Ежище on 29.05.2016.
 */
public class BmwCar extends Vehicle {
    /**
     * checkInTime - the calculated time of the whole race
     */
    public BmwCar(String name, String marker, double acceleration, double fullSpeed, double mobility) {
        super(name, marker, acceleration, fullSpeed, mobility);
    }
    public BmwCar (CarModel carModel) { super(carModel);}

    @Override
    public Vehicle goVehicle() {
        // Поехали по трассе:
            for (int i = 0; i < 20; i++) {
                super.goVehicle();
                setRegisteredTime(getRegisteredTime()+getDirectSegmentTime());
                setInitialSpeed(getTerminalSpeed() * getMobility());
                if (getTerminalSpeed() <= getFullSpeed() / (3.6 * 2))
                    setAcceleration(getAcceleration() * 2);
            }
            return this;
    }
}

