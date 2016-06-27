package cars;

/**
 * Created by Ежище on 29.05.2016.
 */
public class BmwCar extends Vehicle {
    /**
     * checkInTime - the calculated time of the whole race
     */
    public BmwCar(String name, double acceleration, double fullSpeed, double mobility) {
        this.name = name;
        setAcceleration(acceleration);
        setFullSpeed(fullSpeed);
        setMobility(mobility);
//        if (fullSpeed <= 0 || acceleration <= 0 || mobility > 1 || mobility < 0 )
//            checkParameters = true;
    }

    @Override
    public Vehicle goVehicle() {
        // Поехали по трассе:
        super.goVehicle();
        if (checkParameters) {return this;}
        else {
            for (int i = 0; i < 20; i++) {
                registeredTime += directSegmentTime;
                initialSpeed = terminalSpeed * mobility;
                if (terminalSpeed <= fullSpeed / (3.6 * 2))
                    setAcceleration(getAcceleration() * 2);
            }
            return this;
        }
    }
}

