package racingcar;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.view.InputView;

public class CarTest {

    @DisplayName("입력받은 자동차 등록 테스트")
    @Test
    public void testCarRegistration() {

        String inputValue = "car1, car2";
        System.setIn(new ByteArrayInputStream(inputValue.getBytes()));

        InputView inputView = new InputView();
        List<String> carNames = inputView.readCarNames();
        List<Car> cars = new ArrayList<>();
        for(int i = 0; i < carNames.size(); i++){
            cars.add(new Car(carNames.get(i)));
        }

        assertEquals(2, cars.size());
        assertEquals("car1", cars.get(0).getName());
        assertEquals("car2", cars.get(1).getName());

    }
}
