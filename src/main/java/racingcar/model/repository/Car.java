package racingcar.model.repository;

import racingcar.global.CustomException.CarException;
import racingcar.global.ErrorMessage;

public class Car {

    private final String name;

    private int position;

    public Car(String name) {
        checkNameValidation(name);
        this.name = name;
        this.position = 0;
    }

    private void checkNameValidation(String name) {
        isNotNull(name);
        isNotOverLengthFive(name);
    }

    private static void isNotOverLengthFive(String name) {
        if (name.length() > 5) {
            throw new CarException(
                    ErrorMessage.OVER_SIZE_FIVE.getMessage()
            );
        }
    }

    private void isNotNull(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new CarException(
                    ErrorMessage.CANNOT_EMPTY.getMessage()
            );
        }
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public void go() {
        this.position++;
    }
}
