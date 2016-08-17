package cars;


import entities.CarModel;

/**
 * Created by Ежище on 04.05.2016.
 */
public class Vehicle {
    /**
     * the length of each straight segment, m:
     */
    final static int SPACING = 2000;
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

    public Vehicle(){};

    public Vehicle (CarModel carModel) {
        setName(carModel.name);
        setMarker(carModel.marker);
        setAcceleration(carModel.acceleration);
        setFullSpeed(carModel.fullSpeed);
        setMobility(carModel.mobility);
    }

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
            if (name != null)
                this.name = name;
            else {
                checkParameters = true;
                System.out.println("Не введено имя автомобиля класса " + getMarker() + ".");
            }
    }

    public void setMarker(String marker) {
        this.marker = marker;
    }

    public void setAcceleration(double acceleration) {
//        try {
            if (acceleration > 0)
                this.acceleration = acceleration;
            else {
                checkParameters = true;
                System.out.printf("Автомобиль %s: ускорение должно быть больше 0: ошибка ввода.\n", name);
//                throw new Exception();
            }
//        } catch (Exception e) {}
    }

    public void setFullSpeed(double fullSpeed) {
            if (fullSpeed > 0)
                this.fullSpeed = fullSpeed;
            else {
                checkParameters = true;
                System.out.printf("Автомобиль %s: максимальная скорость должна быть больше 0: ошибка ввода.\n", name);
            }
    }

    public void setMobility(double mobility) {
            if (1 >= mobility && mobility >= 0)
                this.mobility = mobility;
            else {
                checkParameters = true;
                System.out.printf("Автомобиль %s: коэффициент потери скорости \"маневренность\" " +
                        "указывается в пределах от 0 до 1: ошибка ввода.\n", name);
            }
    }

    // Метод для обнуления аргументов конструктора без ругательств со стороны сеттеров (нужен в InitialDataScannerSystemIn):
    public void setNullConstructorArguments(){
        name = null;
        marker = null;
        acceleration = 0;
        fullSpeed = 0;
        mobility = 0;
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
        // в терминах задачи это: acceleration * timeFull^2 / 2 + initialSpeed * timeFull - SPACING = 0;

        /** double Discriminant = discriminant of the quadratic equation;*/
        double Discriminant = Math.pow(initialSpeed, 2) - 4 * (acceleration / 2) * (-SPACING);
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
            directSegmentTime = (fullSpeed / 3.6 - initialSpeed) / acceleration + (SPACING - spacingX) / (fullSpeed / 3.6);
            terminalSpeed = fullSpeed / 3.6;//Note: vTerminal = m/sec and fullSpeed = km/h.
        }
        return this;
    }

    public static void main(String[] args) {
        Vehicle v = new Vehicle("v", "V", 12, 500, 0.3);
        v.goVehicle();
        System.out.println(v.terminalSpeed);
        v = new Vehicle(null, "cars.MashkaCar", 0, -5, 3);
        v.goVehicle();
        System.out.println(v.terminalSpeed);
    }
}
