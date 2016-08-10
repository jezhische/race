package cars;

import entities.CarModel;

/**
 * Created by Ежище on 29.05.2016.
 */
public class FerrariCar extends Vehicle {
    /**
     * checkInTime - the calculated time of the whole race
     */
    public FerrariCar(String name, String marker, double acceleration, double fullSpeed, double mobility) {
        super(name, marker, acceleration, fullSpeed, mobility);
    }
    public FerrariCar (CarModel carModel) { super(carModel);}

    //    public FerrariCar(String name, double acceleration, double fullSpeed, double mobility) {
//        this.name = name;
//        setAcceleration(acceleration);
//        setFullSpeed(fullSpeed);
//        setMobility(mobility);
////        if (fullSpeed <= 0 || acceleration <= 0 || mobility > 1 || mobility < 0 )
////            checkParameters = true;
//    }
    @Override
    public Vehicle goVehicle() {
        // Поехали по трассе, но вначале вводим индекс k, который понадобится, если этот цикл прервется
        // и нужно будет начинать другой (см. ниже):
        int k = 0;
        for (int i = 0; i < 20; i++) {
            super.goVehicle();
            setRegisteredTime(getRegisteredTime() + getDirectSegmentTime());
            setInitialSpeed(getTerminalSpeed() * getMobility());
            if (getTerminalSpeed() == getFullSpeed() / (3.6 * 2))//поскольку vMax в км/ч, а vTerminal в м/с
            {
                setFullSpeed(getFullSpeed() + 0.1 * getFullSpeed());
                k = i + 1;
                break;
            }
        }
        if (k != 0 && k != 20) {
            for (int j = k; j < 20; j++) {
                super.goVehicle();
                setRegisteredTime(getRegisteredTime() + getDirectSegmentTime());
                setInitialSpeed(getTerminalSpeed() * getMobility());
            }
        }
        return this;
    }
}
