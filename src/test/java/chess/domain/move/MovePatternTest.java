package chess.domain.move;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MovePatternTest {

    @Test
    @DisplayName("horizon, vertical 을 입력받아 MovePattern 을 찾는다")
    void of() {
        assertThat(MovePattern.of(1, 0)).isEqualTo(MovePattern.EAST);
    }
}