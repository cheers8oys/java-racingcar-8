package racingcar;

import java.util.List;
import racingcar.model.Cars;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        List<String> carNames = inputView.readCarNames();
        int attemptCount = inputView.readAttemptCount();

        Cars cars = new Cars(carNames);

        System.out.println("\n실행 결과");
        for (int i = 0; i < attemptCount; i++) {
            cars.moveAllCarsRandomly(); // 각 자동차마다 개별 랜덤 숫자 생성
            outputView.printRoundResult(cars.getCars());
        }

        List<String> winners = cars.findWinners();
        outputView.printWinners(winners);
    }
}
