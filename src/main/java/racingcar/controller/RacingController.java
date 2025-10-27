package racingcar.controller;

import java.util.List;
import racingcar.model.Cars;
import racingcar.utils.AttemptCountValidator;
import racingcar.utils.CarNameValidator;
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

        String carNames = inputView.readCarNames();
        String attemptCount = inputView.readAttemptCount();
        List<String> parsedCarNames = CarNameValidator.validate(carNames);
        int parsedAttemptCount = AttemptCountValidator.validate(attemptCount);
        Cars cars = new Cars(parsedCarNames);

        System.out.println("\n실행 결과");
        playRacing(cars, parsedAttemptCount);
        displayWinners(cars);
    }

    private void playRacing(Cars cars, int attemptCount) {
        for (int i = 0; i < attemptCount; i++) {
            cars.moveAllCarsRandomly();
            outputView.printRoundResult(cars.getCars());
        }
        cars.checkAllCarsStationary();
    }

    private void displayWinners(Cars cars) {
        List<String> winners = cars.findWinners();
        outputView.printWinners(winners);
    }
}
