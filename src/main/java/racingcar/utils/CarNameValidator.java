package racingcar.utils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CarNameValidator {

    private static final int MAX_NAME_LENGTH = 5;
    private static final int MIN_CAR_COUNT = 2;
    private static final int MAX_CAR_COUNT = 30;

    public static List<String> validate(String inputValue) {
        List<String> names = parseAndValidate(inputValue);
        validateNotEmpty(names);
        validateLength(names);
        validateDuplicates(names);
        validateCount(names);  // 추가한 부분
        return names;
    }

    private static void validateCount(List<String> names) {
        if (names.size() < MIN_CAR_COUNT) {
            throw new IllegalArgumentException("자동차는 최소 2대 이상이어야 합니다.");
        }
        if (names.size() > MAX_CAR_COUNT) {
            throw new IllegalArgumentException("자동차는 최대 30대까지 가능합니다.");
        }
    }

    public static List<String> parseAndValidate(String inputValue) {
        if (inputValue == null) {
            return List.of();
        }
        List<String> carNames = Arrays.stream(inputValue.split(","))
                .map(String::trim)
                .collect(Collectors.toList());
        return carNames;
    }

    private static void validateNotEmpty(List<String> names) {
        if (names == null || names.isEmpty()) {
            throw new IllegalArgumentException("자동차 이름은 비어 있을 수 없습니다.");
        }

        for (String name : names) {
            if (name == null || name.isBlank()) {
                throw new IllegalArgumentException("자동차 이름은 비어 있을 수 없습니다.");
            }
        }
    }

    private static void validateLength(List<String> names) {
        for (String name : names) {
            if (name.length() > MAX_NAME_LENGTH) {
                throw new IllegalArgumentException("자동차 이름은 5자 이하여야 합니다.");
            }
        }
    }

    private static void validateDuplicates(List<String> names) {
        Set<String> uniqueNames = new HashSet<>(names);
        if (uniqueNames.size() != names.size()) {
            throw new IllegalArgumentException("자동차 이름은 중복될 수 없습니다.");
        }
    }
}
