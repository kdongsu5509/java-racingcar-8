package racingcar;

import racingcar.controller.RacingController;
import racingcar.model.repository.CarRepository;
import racingcar.model.service.RacingService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class Application {
    public static void main(String[] args) {
        CarRepository carRepository = new CarRepository();
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        RacingService racingService = new RacingService(carRepository);
        RacingController controller = new RacingController(inputView, outputView, racingService);

        controller.start();
    }
}
