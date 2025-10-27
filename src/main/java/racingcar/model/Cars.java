package racingcar.model;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;
import java.util.stream.Collectors;
import racingcar.utils.CarNameValidator;

public class Cars {

    private final List<Car> cars;

    public Cars(List<String> carNames) {
        CarNameValidator.validate(carNames);
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

    public List<String> findWinners() {
        int maxPosition = getMaxPosition();
        return cars.stream()
                .filter(car -> car.getPosition() == maxPosition)
                .map(Car::getName)
                .collect(Collectors.toList());
    }

    private int getMaxPosition() {
        return cars.stream()
                .mapToInt(Car::getPosition)
                .max()
                .orElse(0);
    }
}