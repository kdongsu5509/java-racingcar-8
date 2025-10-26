package racingcar;

import java.util.Objects;

public record RacingCarStatus(
        String name,
        int position
) {
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RacingCarStatus that = (RacingCarStatus) o;
        return position == that.position && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, position);
    }

    @Override
    public String toString() {
        return name + " : " + "-".repeat(position);
    }
}
