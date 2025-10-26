package racingcar.view;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ArgumentConverter;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
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

    @ParameterizedTest(name = "[{index}] 자동차 상태: {0}")
    @MethodSource("provideCarStatusesForPrinting")
    @DisplayName("자동차 상태 리스트를 올바르게 출력해야 한다.")
    void printRacingStatus_Parameterized(
            List<RacingCarStatus> statuses, List<String> expectedLines) {
        outputView.printRacingStatus(statuses);

        String actualOutput = outputStreamCaptor.toString();
        String expectedFullOutput = consistExpectOutput(expectedLines);

        assertThat(actualOutput).isEqualTo(expectedFullOutput);
    }

    @ParameterizedTest(name = "[{index}] {0} -> {1}")
    @CsvSource(value = {
            "pobi|최종 우승자 : pobi",
            "pobi,woni|최종 우승자 : pobi, woni",
            "pobi,woni,jun|최종 우승자 : pobi, woni, jun",
            "java|최종 우승자 : java"
    }, delimiter = '|')
    @DisplayName("우승자를 올바르게 출력한다.")
    void printRaceWinner_with_CsvSource(
            @ConvertWith(StringListConverter.class) List<String> winners,
            String expectedOutput
    ) {
        outputView.printRaceWinner(winners);
        assertThat(outputStreamCaptor.toString()).contains(expectedOutput);
    }

    private static String consistExpectOutput(List<String> expectedLines) {
        return expectedLines.stream()
                .collect(Collectors.joining(System.lineSeparator()))
                + System.lineSeparator();
    }

    static Stream<Arguments> provideCarStatusesForPrinting() {
        return Stream.of(
                singleCarStatus(),
                singleCarThatPlacedInZeroStatus(),
                multiCarsStatus()
        );
    }

    private static class StringListConverter implements ArgumentConverter {
        @Override
        public List<String> convert(Object source, ParameterContext context) throws ArgumentConversionException {
            String inputFromCsv = (String) source;
            return Arrays.stream(inputFromCsv.split(","))
                    .toList();
        }
    }

    private static Arguments singleCarStatus() {
        return arguments(
                List.of(new RacingCarStatus("pobi", 3)),
                List.of("pobi : ---")
        );
    }

    private static Arguments singleCarThatPlacedInZeroStatus() {
        return arguments(
                List.of(new RacingCarStatus("zero", 0)),
                List.of("zero : ")
        );
    }

    private static Arguments multiCarsStatus() {
        return arguments(
                List.of(
                        new RacingCarStatus("pobi", 3),
                        new RacingCarStatus("woni", 1),
                        new RacingCarStatus("jun", 0)
                ),
                List.of("pobi : ---", "woni : -", "jun : ")
        );
    }
}