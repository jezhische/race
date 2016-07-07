package cars;

/**
 * Created by Ежище on 29.05.2016.
 */
public class FerrariCar extends Vehicle {
    /** checkInTime - the calculated time of the whole race */
    public FerrariCar (CarModel car) {
        super(car);
    }

//    public FerrariCar(String name, double acceleration, double fullSpeed, double mobility) {
//        this.name = name;
//        setAcceleration(acceleration);
//        setFullSpeed(fullSpeed);
//        setMobility(mobility);
////        if (fullSpeed <= 0 || acceleration <= 0 || mobility > 1 || mobility < 0 )
////            checkParameters = true;
//    }
    @Override
    public Vehicle goVehicle () {
        // Поехали по трассе, но вначале вводим индекс k, который понадобится, если этот цикл прервется
        // и нужно будет начинать другой (см. ниже):
        int k = 0;
        for (int i = 0; i < 20; i++){
            super.goVehicle();
            registeredTime += directSegmentTime;
            initialSpeed=terminalSpeed * mobility;
            if (terminalSpeed == fullSpeed / (3.6))//поскольку vMax в км/ч, а vTerminal в м/с
            {fullSpeed += 0.1 * fullSpeed;
                k = i + 1;
                break;}
        }
        if (k != 0 && k != 20) {
            for (int j = k; j < 20; j++) {
                super.goVehicle();
                registeredTime += directSegmentTime;
                initialSpeed = terminalSpeed * mobility;
            }
        }
        return this;
    }
}
