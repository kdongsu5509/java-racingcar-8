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

    public int validateTryCount(String userInputTryCount) {
        isEmpty(userInputTryCount);
        int tryCount = isPositiveInteger(userInputTryCount);
        isOverZero(tryCount);
        return tryCount;
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

    private void isEmpty(String input) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    ErrorMessage.CANNOT_EMPTY.getMessage()
            );
        }
    }

    private int isPositiveInteger(String input) {
        try {
            isLowerThanIntegerMinAndMaxValue(input);
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                    ErrorMessage.ALLOW_ONLY_POSITIVE_INTEGER.getMessage()
            );
        }
    }

    private void isLowerThanIntegerMinAndMaxValue(String input) {
        long parsedLong = Long.parseLong(input);
        if (parsedLong > Integer.MAX_VALUE || parsedLong < Integer.MIN_VALUE) {
            throw new IllegalArgumentException(
                    ErrorMessage.UNTIL_INTEGER_VALUE.getMessage()
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
