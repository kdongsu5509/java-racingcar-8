package racingcar.model.service;

import java.util.List;
import racingcar.global.ErrorMessage;

public class Validator {
    public void validateCarName(List<String> carNames) throws IllegalArgumentException {
        for (String carName : carNames) {
            validateNameNotBlankOrEmpty(carName);
            validateNameLength(carName);
        }
    }

    private void validateNameLength(String name) {
        if (name.length() > 5) {
            throw new IllegalArgumentException(
                    ErrorMessage.OVER_SIZE_FIVE.getMessage()
            );
        }
    }

    private void validateNameNotBlankOrEmpty(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    ErrorMessage.CANNOT_EMPTY.getMessage()
            );
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
            throw new IllegalArgumentException(
                    ErrorMessage.ALLOW_ONLY_POSITIVE_INTEGER.getMessage()
            );
        }
    }

    private void isOverZero(int number) {
        if (number <= 0) {
            throw new IllegalArgumentException(
                    ErrorMessage.ALLOW_ONLY_POSITIVE_INTEGER.getMessage()
            );
        }
    }
}
