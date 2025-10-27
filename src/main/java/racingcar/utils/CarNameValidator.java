package racingcar.utils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CarNameValidator {

    private static final int MAX_NAME_LENGTH = 5;

    public static List<String> validate(String intputValue) {
        List<String> names = parseAndValidate(intputValue);
        validateNotEmpty(names);
        validateLength(names);
        validateDuplicates(names);
        return names;
    }

    public static List<String> parseAndValidate(String inputValue) {
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
