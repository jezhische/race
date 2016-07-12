package cars;

/**
 * Created by Ежище on 04.05.2016.
 */
public class Vehicle {
    /**
     * the length of each straight segment, m:
     */
    final static int spacing = 2000;
    /* name of the car **/
    private String name;
    /* marker to get typr of the car if it's necessary **/
    private String marker;
    /**
     * the acceleration of the car, m/sec^2:
     */
    private double acceleration;
    /**
     * the car full speed, km/h:
     */
    private double fullSpeed;
    /**
     * the maneuverability of the car when cornering:
     */
    private double mobility;
    /**
     * registered time of the hole race:
     */
    private double registeredTime = 0;
    /**
     * the time of passing each whole direct segment:
     */
    private double directSegmentTime;
    /**
     * the initial car speed on each direct segment:
     */
    private double initialSpeed = 0;
    /**
     * the terminal car speed on each direct segment:
     */
    private double terminalSpeed;
    /**
     * переменная для того, чтобы гневно ругаться при неправильно введенных исходниках:
     */
    private boolean checkParameters = false;


    public Vehicle(String name, String marker, double acceleration, double fullSpeed, double mobility) {
        setName(name);
        setAcceleration(acceleration);
        setFullSpeed(fullSpeed);
        setMobility(mobility);
        setMarker(marker);
//        if (getName() == null || fullSpeed <= 0 || getAcceleration() <= 0 || mobility > 1 || mobility < 0 )
//            checkParameters = true;
    }

    public void setName(String name) {
        try {
            if (name != null)
                this.name = name;
            else {
                checkParameters = true;
                throw new Exception("Не введено имя автомобиля.");
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public void setMarker(String marker) {
        this.marker = marker;
    }

    public void setAcceleration(double acceleration) {
        if (acceleration <= 0)
            try {
                checkParameters = true;
                throw new Exception("Автомобиль " + name + ": ускорение должно быть больше 0: ошибка ввода.");
            }
            catch (Exception e) {
                e.getMessage();
            }
        else
        this.acceleration = acceleration;
//        try {
//            if (acceleration > 0)
//                this.acceleration = acceleration;
//            else {
//                checkParameters = true;
//                throw new Exception("Автомобиль " + name + ": ускорение должно быть больше 0: ошибка ввода.");
//            }
//        } catch (Exception e) {
//            e.getMessage();
//        }
    }

    public void setFullSpeed(double fullSpeed) {
        try {
            if (fullSpeed > 0)
                this.fullSpeed = fullSpeed;
            else {
                checkParameters = true;
                System.out.println("Автомобиль " + name + ": максимальная скорость должна быть больше 0: ошибка ввода.");
    //            this.fullSpeed = 0/0;
            }
        } catch (Exception e) {
            e.getMessage();
        }

    }

    public void setMobility(double mobility) {
        try {
            if (1 >= mobility && mobility >= 0)
                this.mobility = mobility;
            else {
                checkParameters = true;
                throw new Exception("Автомобиль " + name + ": коэффициент потери скорости \"маневренность\" " +
                        "\nуказывается в пределах от 0 до 1: ошибка ввода.");
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public void setRegisteredTime(double registeredTime) {
        this.registeredTime = registeredTime;
    }

    public void setDirectSegmentTime(double directSegmentTime) {
        this.directSegmentTime = directSegmentTime;
    }

    public void setInitialSpeed(double initialSpeed) {
        this.initialSpeed = initialSpeed;
    }

    public void setTerminalSpeed(double terminalSpeed) {
        this.terminalSpeed = terminalSpeed;
    }

    public void setCheckParameters(boolean checkParameters) {
        this.checkParameters = checkParameters;
    }

    public String getName() {
        return name;
    }

    public String getMarker() {
        return marker;
    }

    public double getAcceleration() {
        return acceleration;
    }

    public double getMobility() {
        return mobility;
    }

    public double getFullSpeed() {
        return fullSpeed;
    }

    public double getRegisteredTime() {
        return registeredTime;
    }

    public double getDirectSegmentTime() {
        return directSegmentTime;
    }

    public double getInitialSpeed() {
        return initialSpeed;
    }

    public double getTerminalSpeed() {
        return terminalSpeed;
    }

    public boolean isCheckParameters() {
        return checkParameters;
    }


    //Это метод для вычисления времени на прямом отрезке пути:
    public Vehicle goVehicle() {
        /* максимально возможная скорость, если бы машина двигалась с равномерным ускорением и не было бы
        * ограничений по максимальной скорости: **/
        double greatestPossibleSpeed;// m/sec
        /* время движения машины с ускорением: **/
        double timeFull;// sec
        /* отрезок пути, пройденный с ускорением: **/
        double spacingX;// m

        // вычисляем время прохождения прямого участка пути по физической формуле: s = a*t^2/2+v0*t;
        // в терминах задачи это: acceleration * timeFull^2 / 2 + initialSpeed * timeFull - spacing = 0;

        /** double Discriminant = discriminant of the quadratic equation;*/
        double Discriminant = Math.pow(initialSpeed, 2) - 4 * (acceleration / 2) * (-spacing);
            timeFull = (-initialSpeed + Math.pow(Discriminant, 0.5)) / acceleration;//(это формула положительного
        // корня кв.уравн.)
            /** Расчет скорости greatestPossibleSpeed и условие для fullSpeed: */
            greatestPossibleSpeed = initialSpeed + acceleration * timeFull;
            // Перевод м/с в км/ч: greatestPossibleSpeed*3600/1000 = greatestPossibleSpeed*3.6
            if (greatestPossibleSpeed * 3.6 <= fullSpeed) {
                directSegmentTime = timeFull;
                terminalSpeed = greatestPossibleSpeed;
            } else {
                //из формулы spacingX = initialSpeed*tX+acceleration*tX^2/2:
                spacingX = initialSpeed * (fullSpeed / 3.6 - initialSpeed) / acceleration
                        + Math.pow((fullSpeed / 3.6 - initialSpeed), 2) / (2 * acceleration);
                directSegmentTime = (fullSpeed / 3.6 - initialSpeed) / acceleration + (spacing - spacingX) / (fullSpeed / 3.6);
                terminalSpeed = fullSpeed / 3.6;//Note: vTerminal = m/sec and fullSpeed = km/h.
            }
        return this;
    }

    public static void main(String[] args) {
        Vehicle v = new Vehicle("v", "V", 12, 500, 0.3);
        v.goVehicle();
        System.out.println(v.terminalSpeed);

    }
}
