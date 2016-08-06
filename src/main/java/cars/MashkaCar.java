package cars;

import entities.CarModel;

/**
 * Created by Ежище on 28.05.2016.
 */
public class MashkaCar extends Vehicle {

    public MashkaCar (String name, String marker, double acceleration, double fullSpeed, double mobility) {
        super(name, marker, acceleration, fullSpeed, mobility);
    }
    public MashkaCar (CarModel carModel) { super(carModel);}

    //    public MashkaCar(String name, double acceleration, double fullSpeed, double mobility){
//        super.name = name; // здесь вместо super годится также и this. Почему?
//        setAcceleration(acceleration);
//        setFullSpeed(fullSpeed);
//        setMobility(mobility);
////        if (fullSpeed <= 0 || acceleration <= 0 || mobility > 1 || mobility < 0 )
////            checkParameters = true;
//    }
    // переопределенный метод, который плюс времени на прямой участок определяет также изменение параметров
    // на повороте, а затем сумму = общему времени прохождения трассы.
    @Override
    public Vehicle goVehicle() {
        // Поехали по трассе:
        for (int i = 0; i < 20; i++){
//            calcDirectTime(acceleration, fullSpeed);
            super.goVehicle();
            setRegisteredTime(getRegisteredTime()+getDirectSegmentTime());
            if (getTerminalSpeed() <= getFullSpeed() / (3.6 * 2))
               setInitialSpeed(getTerminalSpeed() * getMobility());
            else
            if ((getMobility() + (getTerminalSpeed() - getFullSpeed() / (3.6 * 2)) * 0.5 / 100) <= 1)
                setInitialSpeed(getTerminalSpeed()*(getMobility()+(getTerminalSpeed() - getFullSpeed() / (3.6 * 2)) * 0.5 / 100));
            else
                setInitialSpeed(getTerminalSpeed());//имеется в виду initialSpeed=terminalSpeed*1, поскольку здесь mobility=1.
        }
        return this;
    }
}
