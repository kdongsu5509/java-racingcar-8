package racingcar.model.service;

import java.util.List;

public class Validator {
    public void validateCarName(List<String> carNames) throws IllegalArgumentException {
        for (String carName : carNames) {
            validateNameNotBlankOrEmpty(carName);
            validateNameLength(carName);
        }
    }

    private void validateNameLength(String name) {
        if (name.length() > 5) {
            throw new IllegalArgumentException("자동차 이름은 5자를 초과할 수 없습니다.");
        }
    }

    private void validateNameNotBlankOrEmpty(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("자동차 이름은 공백이거나 빈 문자열일 수 없습니다.");
        }
    }

    public int validateTryCount(String userInputTryCount) {
        int tryCount = isNumber(userInputTryCount);
        isOverZero(tryCount);
        return tryCount;
    }

    private int isNumber(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("시도 횟수는 정수 형식이어야 합니다.");
        }
    }

    private void isOverZero(int number) {
        if (number <= 0) {
            throw new IllegalArgumentException("시도 횟수는 양의 정수여야 합니다.");
        }
    }
}
