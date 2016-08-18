package testSupport;

/**
 * Created by WORK on 18.08.2016.
 */
public enum UserInputEnam {
    EMPTY_LINE(""),
    MASHKA_RIGHT_CAR("mashka, Mashka, 123, 456, 0.98");

    private String userInputSample;
    UserInputEnam(String userInput) {this.userInputSample = userInput;}
    public String getUserInputSample() {return userInputSample;}
}
