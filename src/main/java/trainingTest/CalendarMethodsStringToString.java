package trainingTest;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by Ежище on 21.06.2016.
 */
public class CalendarMethodsStringToString {
    public static void main(String[] args) {
        User user = new User("Эдуард", "Пупырышкин", 1990);
        System.out.println(user);
        user.calendarMethodInvestigation();
    }
}

class User {
    private String name;
    private String surname;
    private int birthYear;
    User(String name, String surname, int birthYear)
    {
        this.name = name;
        this.surname = surname;
        this.birthYear = birthYear;
    }

    @Override
    public String toString()
    {
        return this.name+" "+this.surname+", "+getAge()+" года";
    }

    private int getAge()
    {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault());
        calendar.setTime(new Date());
        int currentYear = calendar.get(Calendar.YEAR);
        return currentYear - birthYear;
    }
    void calendarMethodInvestigation(){
        System.out.println("TimeZone.getDefault() = " + TimeZone.getDefault());
        System.out.println("Locale.getDefault() = " + Locale.getDefault());
        System.out.println("new Date() = " + new Date());
        System.out.println("Calendar.YEAR = " + Calendar.YEAR);
    }

}
