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

    public void checkAllCarsStationary() {
        boolean anyMoved = cars.stream().anyMatch(car -> car.getPosition() > 0);
        if (!anyMoved) {
            throw new IllegalStateException("모든 자동차가 출발하지 않았습니다. 전원 실격 처리됩니다.");
        }
    }
}