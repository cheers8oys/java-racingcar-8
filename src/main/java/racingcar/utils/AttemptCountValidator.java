package racingcar.utils;

public class AttemptCountValidator {

    public static int validate(String inputValue) {
        validateNotNullAndBlank(inputValue);
        int attemptCount = parseIntegerAttemptCount(inputValue);
        validatePositiveNumber(attemptCount);
        return attemptCount;
    }

    private static void validateNotNullAndBlank(String inputValue) {
        if (inputValue == null || inputValue.isBlank()) {
            throw new IllegalArgumentException("시도 횟수를 입력해주세요.");
        }
    }

    private static int parseIntegerAttemptCount(String inputValue) {
        try {
            return Integer.parseInt(inputValue.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자가 아닌 문자는 입력할 수 없습니다.");
        }
    }

    private static void validatePositiveNumber(int attemptCount) {
        if (attemptCount <= 0) {
            throw new IllegalArgumentException("시도 횟수는 1 이상의 정수여야 합니다.");
        }
    }
}