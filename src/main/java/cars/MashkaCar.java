package cars;

import entities.CarModel;

import java.io.Serializable;

/**
 * Created by Ежище on 28.05.2016.
 */
public class MashkaCar extends Vehicle implements Serializable {

    public MashkaCar(String name, String marker, double acceleration, double fullSpeed, double mobility) {
        super(name, marker, acceleration, fullSpeed, mobility);
    }

    public MashkaCar(CarModel carModel) {
        super(carModel);
    }

    // переопределенный метод, который плюс ко времени на прямой участок определяет также изменение параметров
    // на повороте, а затем сумму = общему времени прохождения трассы.
    @Override
    public Vehicle goVehicle() {
        // Поехали по трассе:
        for (int i = 0; i < 20; i++) {
            super.goVehicle();
            setRegisteredTime(getRegisteredTime() + getDirectSegmentTime());
            if (getTerminalSpeed() <= getFullSpeed() / (3.6 * 2))
                setInitialSpeed(getTerminalSpeed() * getMobility());
            else if ((getMobility() + (getTerminalSpeed() - getFullSpeed() / (3.6 * 2)) * 0.5 / 100) <= 1)
                setInitialSpeed(getTerminalSpeed() * (getMobility() + (getTerminalSpeed() - getFullSpeed() / (3.6 * 2))
                        * 0.5 / 100));
            else
                setInitialSpeed(getTerminalSpeed());//имеется в виду initialSpeed=terminalSpeed*1, поскольку здесь mobility=1.
        }
        return this;
    }
}
