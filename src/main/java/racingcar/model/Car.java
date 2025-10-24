package racingcar.model;

public class Car {

    private final String name;
    private int position;

    public Car(String carName) {
        name = carName;
        position = 0;
    }

    public String getName() {
        return name;
    }
}
