package racingcar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.model.Car;
import racingcar.model.Cars;
import racingcar.view.InputView;

public class CarTest {

    private List<Car> cars;

    @BeforeEach
    public void setUp() {
        String inputValue = "car1, car2\n";
        System.setIn(new ByteArrayInputStream(inputValue.getBytes()));

        InputView inputView = new InputView();
        List<String> carNames = inputView.readCarNames();

        cars = new ArrayList<>();
        for (int i = 0; i < carNames.size(); i++) {
            cars.add(new Car(carNames.get(i)));
        }
    }

    @DisplayName("입력받은 자동차 등록 테스트")
    @Test
    public void testCarRegistration() {
        assertEquals(2, cars.size());
        assertEquals("car1", cars.get(0).getName());
        assertEquals("car2", cars.get(1).getName());
    }

    @DisplayName("자동차들의 현재 위치 확인 테스트")
    @Test
    public void testCheckingCarPosition() {
        assertEquals(0, cars.get(0).getPosition());
        assertEquals(0, cars.get(1).getPosition());
    }

    @DisplayName("자동차 전진 기능 테스트")
    @Test
    public void testMoveForward() {
        Car car = new Car("car3");
        car.moveForward(4);
        assertEquals(1, car.getPosition());
    }

    @DisplayName("자동차 전진 기능 실패 테스트")
    @Test
    public void testMoveForwardFailure() {
        Car car = new Car("car4");
        car.moveForward(3);
        assertEquals(0, car.getPosition());
    }

    @DisplayName("한 명의 우승자 찾기 테스트")
    @Test
    public void testFindSingleWinner() {

        Cars cars = new Cars(List.of("pobi", "woni", "jun"));
        cars.getCars().get(0).moveForward(5); // pobi: position = 1
        cars.getCars().get(0).moveForward(5); // pobi: position = 2
        cars.getCars().get(1).moveForward(5); // woni: position = 1
        cars.getCars().get(2).moveForward(3); // jun: position = 0

        List<String> winners = cars.findWinners();

        assertEquals(1, winners.size());
        assertEquals("pobi", winners.get(0));
    }

    @DisplayName("공동 우승자 찾기 테스트")
    @Test
    public void testFindMultipleWinners() {

        Cars cars = new Cars(List.of("pobi", "woni", "jun"));
        cars.getCars().get(0).moveForward(5); // pobi: position = 1
        cars.getCars().get(0).moveForward(5); // pobi: position = 2
        cars.getCars().get(1).moveForward(5); // woni: position = 1
        cars.getCars().get(1).moveForward(5); // woni: position = 2
        cars.getCars().get(2).moveForward(3); // jun: position = 0

        List<String> winners = cars.findWinners();

        assertEquals(2, winners.size());
        assertTrue(winners.contains("pobi"));
        assertTrue(winners.contains("woni"));
    }
}
