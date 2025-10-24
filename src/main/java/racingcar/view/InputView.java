package racingcar.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;
import racingcar.model.service.Validator;

public class InputView {

    private final Validator validator;

    public InputView() {
        this.validator = new Validator();
    }

    public List<String> inputCarName() {
        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");

        List<String> inputedCarNameByUser = Arrays.stream(
                Console.readLine().split(",")
        ).toList();

        validator.validateCarName(inputedCarNameByUser);
        return inputedCarNameByUser;
    }
}
