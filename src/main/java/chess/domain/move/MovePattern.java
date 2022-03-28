package chess.domain.move;

import java.util.Arrays;

public enum MovePattern {

    NORTH(0, 1),
    SOUTH(0, -1),
    WEST(-1, 0),
    EAST(1, 0),
    NORTHEAST(1, 1),
    NORTHWEST(-1, 1),
    SOUTHEAST(1, -1),
    SOUTHWEST(-1, -1),
    PAWN_START_MOVE_BLACK(0, -2),
    PAWN_START_MOVE_WHITE(0, 2),
    NNW(-1, 2),
    NNE(1, 2),
    EEN(2, 1),
    EES(2, -1),
    SSE(1, -2),
    SSW(-1, -2),
    WWS(-2, -1),
    WWN(-2, 1);

    private final int horizon;
    private final int vertical;

    MovePattern(int horizon, int vertical) {
        this.horizon = horizon;
        this.vertical = vertical;
    }

    public static MovePattern of(final int horizon, final int vertical) {
        return Arrays.stream(values())
                .filter(value -> value.horizon == horizon && value.vertical == vertical)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[Error] 존재하지 않는 이동 전략입니다."));
    }
}
