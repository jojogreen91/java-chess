package chess.domain.move;

import chess.domain.board.Board;
import chess.domain.board.Position;
import java.util.stream.IntStream;

public abstract class LinearMoveStrategy extends FirstRowMoveStrategy {

    private static final int ADD_START_UNIT = 1;
    private static final int REVERSE_DIRECTION = -1;

    protected boolean isPieceExistWhenHorizon(final Board board,
                                              final Position smallerPosition,
                                              final Distance distance) {
        return IntStream.range(ADD_START_UNIT, Math.abs(distance.getHorizon()))
                .mapToObj(moveUnit -> smallerPosition.move(moveUnit, Distance.NOT_MOVE))
                .anyMatch(position -> isPieceExist(board, position));
    }

    protected boolean isPieceExistWhenVertical(final Board board,
                                               final Position smallerPosition,
                                               final Distance distance) {
        return IntStream.range(ADD_START_UNIT, Math.abs(distance.getVertical()))
                .mapToObj(moveUnit -> smallerPosition.move(Distance.NOT_MOVE, moveUnit * REVERSE_DIRECTION))
                .anyMatch(position -> isPieceExist(board, position));
    }

    protected boolean isPieceExistWhenPositiveDiagonal(final Board board,
                                                       final Position smallerPosition,
                                                       final Distance distance) {
        return IntStream.range(ADD_START_UNIT, Math.abs(distance.getVertical()))
                .mapToObj(moveUnit -> smallerPosition.move(moveUnit * REVERSE_DIRECTION, moveUnit * REVERSE_DIRECTION))
                .anyMatch(position -> isPieceExist(board, position));
    }

    protected boolean isPieceExistWhenNegativeDiagonal(final Board board,
                                                       final Position smallerPosition,
                                                       final Distance distance) {
        return IntStream.range(ADD_START_UNIT, Math.abs(distance.getVertical()))
                .mapToObj(moveUnit -> smallerPosition.move(moveUnit, moveUnit * REVERSE_DIRECTION))
                .anyMatch(position -> isPieceExist(board, position));
    }

    protected boolean isPieceExist(final Board board, final Position position) {
        return !board.getPiece(position).isBlank();
    }
}
