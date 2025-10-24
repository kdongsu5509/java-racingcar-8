package racingcar.view;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InputViewTest {

    private final InputStream standardIn = System.in; // 원본 System.in 백업

    @AfterEach
    void restoreSystemIn() {
        System.setIn(standardIn);
    }

    @Test
    @DisplayName("정상적인 자동차 이름에 문제가 없으면 파싱 후 반환")
    void inputCarName_success() {
        // given
        String input = "alpha,beta,gamma\n";
        System.setIn(new ByteArrayInputStream(input.getBytes())); // System.in 교체

        // when
        InputView inputView = new InputView();
        List<String> userInput = inputView.inputCarName();

        // then
        assertThat(userInput).containsExactly("alpha", "beta", "gamma");
    }
}