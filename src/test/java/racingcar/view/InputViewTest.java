package racingcar.view;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import camp.nextstep.edu.missionutils.Console;
import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import racingcar.global.ErrorMessage;

class InputViewTest {

    @AfterEach
    void restoreSystemIn() {
        Console.close();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/success/carName.csv", numLinesToSkip = 1)
    @DisplayName("정상적인 자동차 이름에 문제가 없으면 파싱 후 반환")
    void inputCarName_success(String input, String expect) {
        // given
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        List<String> expected = findExpectedOutputFromCsvExpect(expect);

        // when
        InputView inputView = new InputView();
        List<String> actualOutput = assertDoesNotThrow(inputView::inputCarName);

        // then
        assertThat(actualOutput).containsAll(expected);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/fail/carName.csv", numLinesToSkip = 1)
    @DisplayName("입력한 차 이름이 너무 길면 IllegalArgumentException이 에러 메시지와 함께 출력된다")
    void inputCarName_fail(String input) {
        // given
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // when
        InputView inputView = new InputView();

        //then
        assertThatThrownBy(inputView::inputCarName)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.OVER_SIZE_FIVE.getMessage());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/success/tryCount.csv", numLinesToSkip = 1)
    @DisplayName("정상적인 시도 횟수 입력에 문제가 없으면 파싱 후 반환")
    void inputTryCount_success(String input, String expect) {
        // given
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        int expectedTryCount = Integer.parseInt(expect);

        // when
        InputView inputView = new InputView();
        int tryCount = inputView.inputTryCount();

        // then
        assertThat(tryCount).isEqualTo(expectedTryCount);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/fail/tryCount.csv", numLinesToSkip = 1)
    void inputTryCount_fail(String input, String expectErrorMessage) {
        // given
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // when
        InputView inputView = new InputView();
        assertThatThrownBy(
                inputView::inputTryCount
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(expectErrorMessage);
    }

    private static List<String> findExpectedOutputFromCsvExpect(String expect) {
        List<String> expected = Arrays.stream(expect.split(","))
                .map(String::trim)
                .filter(trim -> !trim.isEmpty())
                .toList();
        if (expect.isEmpty()) {
            expected = List.of();
        }
        return expected;
    }
}