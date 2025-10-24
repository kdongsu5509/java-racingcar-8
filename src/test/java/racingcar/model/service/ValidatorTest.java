package racingcar.model.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ValidatorTest {

    @Test
    @DisplayName("정상적인 자동차 이름은 예외가 발생하지 않는다.")
    void validateCarName_success() {
        Validator validator = new Validator();
        assertDoesNotThrow(() -> validator.validateCarName(
                java.util.Arrays.asList("car1", "car2", "car3")
        ));
    }

    @Test
    @DisplayName("자동차 이름의 길이가 5자를 초과하면 `IllegalArgumentException`이 발생한다.")
    void validateCarName_nameLengthExceeds_throwsException() {
        //given
        List<String> mockCarNames = List.of("tooLongName1", "tooLongName2");
        Validator validator = new Validator();

        //when, then
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> validator.validateCarName(mockCarNames)
        );
    }

    @Test
    @DisplayName("자동차 이름이 공백이거나 빈 문자열이면 `IllegalArgumentException`이 발생한다.")
    void validateCarName_nameIsBlankOrEmpty_throwsException() {
        //given
        List<String> mockCarNames = List.of("   ", "");
        Validator validator = new Validator();

        //when, then
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> validator.validateCarName(mockCarNames)
        );
    }

    @Test
    @DisplayName("정상적인 시도 횟수는 양의 정수를 반환한다.")
    void validateTryCount_success() {
        String mockTryCount = "5";
        Validator validator = new Validator();
        Assertions.assertEquals(5, validator.validateTryCount(mockTryCount));
    }

    @Test
    @DisplayName("시도 횟수가 정수 형식이 아니면 `Illegal")
    void validateTryCount_notNumber_throwsException() {
        //given
        String mockTryCount = "five";
        Validator validator = new Validator();

        //when, then
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> validator.validateTryCount(mockTryCount)
        );
    }

    @Test
    @DisplayName("시도 횟수가 소수 형식이면 `Illegal")
    void validateTryCount_notFloat_throwsException() {
        //given
        String mockTryCount = "0.35";
        Validator validator = new Validator();

        //when, then
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> validator.validateTryCount(mockTryCount)
        );
    }
}