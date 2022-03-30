package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ColorTest {

    @Test
    @DisplayName("반대 Color 를 반환한다.")
    void oppositeColor() {
        assertThat(Color.WHITE.oppositeColor()).isEqualTo(Color.BLACK);
    }

    @Test
    @DisplayName("NONE 일 경우 반대 Color 를 반환할 수 없다.")
    void oppositeColor_WhenNONE() {
        assertThatThrownBy(() -> Color.NONE.oppositeColor())
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("[ERROR] 상대팀이 없습니다.");
    }
}