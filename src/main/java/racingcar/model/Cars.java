package racingcar.model;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;
import java.util.stream.Collectors;

public class Cars {

    private final List<Car> cars;

    public Cars(List<String> carNames) {
        this.cars = carNames.stream()
                .map(Car::new)
                .collect(Collectors.toList());
    }

    public List<Car> getCars() {
        return cars;
    }

    public void moveAllCarsRandomly() {
        cars.stream()
                .forEach(car -> {
                    int randomNumber = Randoms.pickNumberInRange(0, 9);
                    car.moveForward(randomNumber);
                });
    }
}