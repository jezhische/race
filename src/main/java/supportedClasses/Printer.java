package supportedClasses;

import cars.Vehicle;

/**
 * Created by uaTex_32 on 19.05.2016.
 */
public class Printer {

    public static String printData(Vehicle vehicle){
        String result;
        if (vehicle.checkParameters)
            result = "Ошибка: исходные данные автомобиля " + vehicle.getName() + " введены неверно. Автомобиль дисквалифицирован.";
        else {
            int roundCheckInTime = (int) Math.round(vehicle.registeredTime);
            int hours = (int) Math.floor(roundCheckInTime / 3600);
            int min = (int) Math.floor((roundCheckInTime - hours * 3600) / 60);
            int sec = roundCheckInTime % 60;
            result ="Автомобиль " + vehicle.getName() + " прошел трассу за " + hours + " часов "
                    + min + " минут " + sec + " секунд;";
        }
        return result;
    }
}
