package racingcar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.model.Car;
import racingcar.model.Cars;
import racingcar.utils.CarNameValidator;

public class CarTest {

    @DisplayName("입력받은 자동차 등록 테스트")
    @Test
    public void testCarRegistration() {
        Cars cars = new Cars(List.of("car1", "car2"));
        assertEquals("car1", cars.getCars().get(0).getName());
        assertEquals("car2", cars.getCars().get(1).getName());
    }


    @DisplayName("두 대 미만 자동차 입력 테스트")
    @Test
    public void testMinimumCarRegistration() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Cars(CarNameValidator.validate("car1"));
        });
    }

    @DisplayName("30대 초과 자동차 입력 테스트")
    @Test
    public void testMaximumCarRegistration() {
        List<String> carNames = IntStream.rangeClosed(1, 31)
                .mapToObj(i -> "car" + i)
                .collect(Collectors.toList());

        assertThrows(IllegalArgumentException.class, () -> {
            new Cars(CarNameValidator.validate(String.join(", ", carNames)));
        });
    }

    @DisplayName("자동차들의 현재 위치 확인 테스트")
    @Test
    public void testCheckingCarPosition() {
        Cars cars = new Cars(List.of("pobi", "woni", "jun"));
        assertEquals(0, cars.getCars().get(0).getPosition());
        assertEquals(0, cars.getCars().get(1).getPosition());
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
