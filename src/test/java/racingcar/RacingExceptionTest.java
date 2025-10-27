package racingcar;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import racingcar.utils.AttemptCountValidator;
import racingcar.utils.CarNameValidator;

public class RacingExceptionTest {

    @Test
    @DisplayName("자동차 이름 5글자 이상인 경우 예외처리 테스트")
    public void testCarnameLength() {
        String carNames = "pobi, verywell";

        assertThrows(IllegalArgumentException.class,
                () -> CarNameValidator.validate(carNames));
    }

    @Test
    @DisplayName("자동차 이름 중복")
    public void testCarnameDuplicates() {
        String carNames = "pobi, pobi";

        assertThrows(IllegalArgumentException.class,
                () -> CarNameValidator.validate(carNames));
    }

    /**
     * 리팩토링으로 인한 테스트 변경 필요
     */

//    static Stream<List<String>> invalidCarName() {
//        return Stream.of(
//                null,                                   // 리스트 자체가 null
//                List.of(),                              // 빈 리스트
//                Arrays.asList("pobi", null),            // 중간에 null
//                List.of(""),                            // 빈 문자열
//                List.of("pobi", ""),                    // 중간에 빈 문자열
//                List.of(" "),                           // 공백 문자열
//                List.of("\t"),                          // 탭 문자
//                List.of("\n"),                          // 개행 문자
//                List.of("pobi", "   "),                 // 중간에 공백만
//                Arrays.asList("", " ", null, "\t", "\n")// 여러 케이스 혼합
//        );
//    }
//
//    @DisplayName("자동차 이름이 null/빈/공백/탭/개행/빈문자 중 하나라도 있는 경우 예외 발생")
//    @ParameterizedTest
//    @MethodSource("invalidCarName")
//    void testInvalidCarNames(List<String> carNames) {
//        assertThrows(IllegalArgumentException.class,
//                () -> CarNameValidator.validate(carNames));
//    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "\t", "\n"})
    @DisplayName("입력값이 null, 빈문자열, 공백일 경우 IllegalArgumentException 발생")
    void testNullOrBlankInput(String inputValue) {
        assertThrows(IllegalArgumentException.class, () -> {
            AttemptCountValidator.validate(inputValue);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"abc", "1a", "!@#", "가나다"})
    @DisplayName("숫자가 아닌 문자열 입력 시 IllegalArgumentException 발생")
    void testNonNumericInput(String inputValue) {
        assertThrows(IllegalArgumentException.class, () -> {
            AttemptCountValidator.validate(inputValue);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "-1", "101"})
    @DisplayName("0 또는 음수, 100회 초과 입력 시 IllegalArgumentException 발생")
    void testInValidNumberRange(String inputValue) {
        assertThrows(IllegalArgumentException.class, () -> {
            AttemptCountValidator.validate(inputValue);
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "10", "100"})
    @DisplayName("유효한 양수 범위 입력 시 예외가 발생하지 않고 정상 처리됨")
    void testValidNumberRange(String input) {
        assertDoesNotThrow(() -> {
            int result = AttemptCountValidator.validate(input);
            assertEquals(Integer.parseInt(input), result);
        });
    }

}
