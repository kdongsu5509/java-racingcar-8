package racingcar.controller;

import java.util.List;
import racingcar.RacingCarStatus;
import racingcar.model.service.RacingService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingCarController {

    private final InputView inputView;
    private final OutputView outputView;
    private final RacingService racingService;

    public RacingCarController(InputView inputView, OutputView outputView, RacingService racingService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.racingService = racingService;
    }

    public void start() {
        initializeCars();
        raceCarPerUserRequest();
        printRaceWinner();
    }

    private void printRaceWinner() {
        List<String> raceWinner = racingService.findRaceWinner();
        outputView.printRaceWinner(raceWinner);
    }

    private void initializeCars() {
        List<String> carsNames = inputView.inputCarName();
        racingService.setupRacingCars(carsNames);
    }

    private void raceCarPerUserRequest() {
        int tryCount = inputView.inputTryCount();
        for (int i = 0; i < tryCount; i++) {
            List<RacingCarStatus> currentRacingStatus = racingService.race();
            outputView.printRacingStatus(currentRacingStatus);
        }
    }
}