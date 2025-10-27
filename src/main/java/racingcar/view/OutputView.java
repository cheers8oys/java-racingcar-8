package racingcar.view;

import java.util.List;
import racingcar.model.Car;

public class OutputView {

    public void printRoundResult(List<Car> cars) {
        for (Car car : cars) {
            String dashes = "-".repeat(car.getPosition());
            System.out.println(car.getName() + " : " + dashes);
        }
        System.out.println();
    }

    public void printWinners(List<String> winners) {
        System.out.println("최종 우승자 : " + String.join(", ", winners));
    }
}
