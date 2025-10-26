package racingcar.global;

public class CustomException {

    public static class CarException extends RuntimeException {
        public CarException(String message) {
            super(message);
        }
    }

    public static class NotExistException extends RuntimeException {
        public NotExistException(String message) {
            super(message);
        }
    }
}
