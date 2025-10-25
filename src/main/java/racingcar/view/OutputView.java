package racingcar.view;

import java.util.List;
import racingcar.RacingCarStatus;

public class OutputView {

    public void printRacingStatus(List<RacingCarStatus> statusList) {
        for (RacingCarStatus status : statusList) {
            System.out.println(formatRacingCarStatus(status));
        }
    }

    public void printRaceWinner(List<String> raceWinner) {
        System.out.println(
                formatToPrintWinner(raceWinner)
        );
    }

    private String formatToPrintWinner(List<String> raceWinner) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("최종 우승자 : ");
        for (String winnerName : raceWinner) {
            stringBuilder.append(winnerName);
            stringBuilder.append(", ");
        }
        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());

        return stringBuilder.toString();
    }

    private static StringBuilder formatRacingCarStatus(RacingCarStatus status) {
        StringBuilder sb = new StringBuilder();
        formatCarName(status, sb);
        formatCurrentPosition(status, sb);
        return sb;
    }

    private static void formatCurrentPosition(RacingCarStatus status, StringBuilder sb) {
        String dash = "-";
        sb.append(
                dash.repeat(
                        Math.max(0, status.position())
                )
        );
    }

    private static void formatCarName(RacingCarStatus status, StringBuilder sb) {
        sb.append(status.name());
        sb.append(" : ");
    }
}
