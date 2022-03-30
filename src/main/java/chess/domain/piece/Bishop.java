package chess.domain.piece;

import chess.domain.move.BishopMoveStrategy;
import chess.domain.move.MoveStrategy;

public final class Bishop extends ValidPiece {

    private static final double POINT = 3;

    public Bishop(final Color color) {
        super(color, POINT);
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public MoveStrategy getMoveStrategy() {
        return new BishopMoveStrategy();
    }
}
