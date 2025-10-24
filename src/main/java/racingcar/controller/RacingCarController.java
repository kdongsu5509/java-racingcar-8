package racingcar.controller;

import java.util.List;
import racingcar.RacingCarStatus;
import racingcar.model.service.RacingService;
import racingcar.view.InputView;

public class RacingCarController {

    private final InputView inputView;
    private final RacingService racingService;

    public RacingCarController(InputView inputView, RacingService racingService) {
        this.inputView = inputView;
        this.racingService = racingService;
    }

    public void start() {
        initializeCars();
        racePerUserRequest();
    }

    private void initializeCars() {
        List<String> carsNames = inputView.inputCarName();
        racingService.setupRacingCars(carsNames);
    }

    private void racePerUserRequest() {
        int tryCount = inputView.inputTryCount();
        for (int i = 0; i < tryCount; i++) {
            List<RacingCarStatus> currentRacingStatus = racingService.race();
        }
    }
}