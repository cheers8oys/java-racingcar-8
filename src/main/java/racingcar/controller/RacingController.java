package racingcar.controller;

import java.util.List;
import racingcar.model.Cars;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingController {

    private final InputView inputView;
    private final OutputView outputView;

    public RacingController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {

        List<String> carNames = inputView.readCarNames();
        int attemptCount = inputView.readAttemptCount();
        Cars cars = new Cars(carNames);

        System.out.println("\n실행 결과");
        playRacing(cars, attemptCount);
        displayWinners(cars);
    }

    private void playRacing(Cars cars, int attemptCount) {
        for (int i = 0; i < attemptCount; i++) {
            cars.moveAllCarsRandomly();
            outputView.printRoundResult(cars.getCars());
        }
    }

    private void displayWinners(Cars cars) {
        List<String> winners = cars.findWinners();
        outputView.printWinners(winners);
    }
}
