package cars;

/**
 * Created by Ежище on 04.05.2016.
 */
public class Vehicle {
    /** the length of each straight segment, m */
    final static int spacing = 2000;
    /** the acceleration of the car, m/sec^2 */
   private  double acceleration; //здесь везде достаточно package access.
    /** the maneuverability of the car when cornering */
    double mobility;
    /** the car full speed, km/h */
    double fullSpeed;
    public double registeredTime = 0;
    public String name;
    double directSegmentTime;
    /** the initial car speed on each direct segment */
    double initialSpeed = 0;
    /** the terminal car speed on each direct segment */
    double terminalSpeed;
    /** переменная для того, чтобы гневно ругаться при неправильно введенных исходниках: */
    public boolean checkParameters = false;

     public void setAcceleration(double acceleration){
        if (acceleration > 0)
            this.acceleration = acceleration;
        else {
            checkParameters = true;
            System.out.println("Автомобиль " + name + ": ускорение должно быть больше 0: ошибка ввода.");
//            this.acceleration = 0;
        }
    }

    void setMobility(double mobility){
        if (1 >= mobility && mobility >= 0)
            this.mobility = mobility;
        else {
            checkParameters = true;
            System.out.println("Автомобиль " + name + ": коэффициент потери скорости \"маневренность\" \nуказывается в пределах от 0 до 1: ошибка ввода.");
//            this.mobility = 0;
        }
    }
    void setFullSpeed(double fullSpeed){
        if (fullSpeed > 0)
            this.fullSpeed = fullSpeed;
        else {
            checkParameters = true;
            System.out.println("Автомобиль " + name + ": максимальная скорость должна быть больше 0: ошибка ввода.");
//            this.fullSpeed = 0/0;
        }
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

    public String getName() {
        return name;
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

    Vehicle dataInput () {
        return this;
    }

    /** the time of passing each whole direct segment */
    //Это метод для вычисления времени на прямом отрезке пути.
    public Vehicle goVehicle() {
    double greatestPossibleSpeed;// m/sec
    double timeFull;// sec
    double spacingX;// m

    /** double Discriminant = discriminant of the quadratic equation;*/
    double Discriminant = Math.pow(initialSpeed, 2) - 4 * (acceleration / 2) * (-spacing);
    // При нулевом ускорении: directSegmentTime=s/initialSpeed;
    // Исключение для initialSpeed=0:
    if (acceleration == 0) {
        try {
            directSegmentTime = spacing / initialSpeed;
            terminalSpeed = initialSpeed;
        } catch (ArithmeticException e) {}
    }
    else {
        timeFull = (-initialSpeed + Math.pow(Discriminant, 0.5)) / acceleration;//это формула +корня кв.уравн.
        /** Расчет скорости greatestPossibleSpeed и условие для fullSpeed: */
        greatestPossibleSpeed = initialSpeed + acceleration * timeFull;
        // Перевод м/с в км/ч: greatestPossibleSpeed*3600/1000 = greatestPossibleSpeed*3.6
        if (greatestPossibleSpeed * 3.6 <= fullSpeed) {
            directSegmentTime = timeFull;
            terminalSpeed = greatestPossibleSpeed;
        }
        else {
            //из формулы spacingX = initialSpeed*tX+acceleration*tX^2/2:
            spacingX = initialSpeed * (fullSpeed / 3.6 - initialSpeed) / acceleration
                    + Math.pow((fullSpeed / 3.6 - initialSpeed), 2) / (2 * acceleration);
            directSegmentTime = (fullSpeed / 3.6 - initialSpeed) / acceleration + (spacing - spacingX) / (fullSpeed / 3.6);
            terminalSpeed = fullSpeed / 3.6;//Note: vTerminal = m/sec and fullSpeed = km/h.
        }
    }
        return this;
    }
}
