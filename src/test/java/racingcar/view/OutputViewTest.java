package racingcar.view;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.RacingCarStatus;

class OutputViewTest {

    private PrintStream originalOut;
    private ByteArrayOutputStream outputStreamCaptor;
    private OutputView outputView;

    @BeforeEach
    void setUp() {
        originalOut = System.out;
        outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        outputView = new OutputView();
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    @DisplayName("단일 자동차의 상태 출력 테스트")
    void printSingleCarStatus() {
        RacingCarStatus status = new RacingCarStatus("pobi", 3);
        List<RacingCarStatus> statusList = List.of(status);

        outputView.printRacingStatus(statusList);

        assertThat(outputStreamCaptor.toString()).contains("pobi : ---");
    }

    @Test
    @DisplayName("여러 자동차의 상태 출력 테스트")
    void printMultipleCarStatuses() {
        RacingCarStatus status1 = new RacingCarStatus("pobi", 3);
        RacingCarStatus status2 = new RacingCarStatus("woni", 1);
        RacingCarStatus status3 = new RacingCarStatus("jun", 0);
        List<RacingCarStatus> statusList = List.of(status1, status2, status3);

        outputView.printRacingStatus(statusList);

        assertThat(outputStreamCaptor.toString())
                .contains("pobi : ---", "woni : -", "jun : ");
    }

    @Test
    @DisplayName("포지션이 0일 때 출력 테스트")
    void printStatusWhenPositionIsZero() {
        RacingCarStatus status = new RacingCarStatus("pobi", 0);
        List<RacingCarStatus> statusList = List.of(status);

        outputView.printRacingStatus(statusList);

        assertThat(outputStreamCaptor.toString()).contains("pobi : ");
    }
}