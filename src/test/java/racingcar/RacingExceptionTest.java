package racingcar;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import racingcar.utils.CarNameValidator;

public class RacingExceptionTest {

    @Test
    @DisplayName("자동차 이름 5글자 이상인 경우 예외처리 테스트")
    public void testCarnameLength() {
        List<String> carNames = List.of("pobi", "verywell");

        assertThrows(IllegalArgumentException.class,
                () -> CarNameValidator.validate(carNames));
    }

    @Test
    @DisplayName("자동차 이름 중복")
    public void testCarnameDuplicates() {
        List<String> carNames = List.of("pobi", "pobi");

        assertThrows(IllegalArgumentException.class,
                () -> CarNameValidator.validate(carNames));
    }

    static Stream<List<String>> invalidCarName() {
        return Stream.of(
                null,                                   // 리스트 자체가 null
                List.of(),                              // 빈 리스트
                Arrays.asList("pobi", null),            // 중간에 null
                List.of(""),                            // 빈 문자열
                List.of("pobi", ""),                    // 중간에 빈 문자열
                List.of(" "),                           // 공백 문자열
                List.of("\t"),                          // 탭 문자
                List.of("\n"),                          // 개행 문자
                List.of("pobi", "   "),                 // 중간에 공백만
                Arrays.asList("", " ", null, "\t", "\n")// 여러 케이스 혼합
        );
    }

    @DisplayName("자동차 이름이 null/빈/공백/탭/개행/빈문자 중 하나라도 있는 경우 예외 발생")
    @ParameterizedTest
    @MethodSource("invalidCarName")
    void testInvalidCarNames(List<String> carNames) {
        assertThrows(IllegalArgumentException.class,
                () -> CarNameValidator.validate(carNames));
    }

}
