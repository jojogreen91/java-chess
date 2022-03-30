package chess.domain.move;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Board;
import chess.domain.board.CatchPieces;
import chess.domain.board.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BishopMoveStrategyTest {

    Board board;
    BishopMoveStrategy bishopMoveStrategy;
    CatchPieces catchPieces = new CatchPieces();

    @BeforeEach
    void setUp() {
        board = Board.createChessBoard();
        bishopMoveStrategy = new BishopMoveStrategy();
    }

    @Test
    @DisplayName("비숍이 이동할 수 있다.")
    void isMovable() {
        board.movePiece(Position.valueOf("d7"), Position.valueOf("d6"), catchPieces);

        Position source = Position.valueOf("c8");
        Position target = Position.valueOf("e6");

        assertThat(bishopMoveStrategy.isMovable(board, source, target)).isTrue();
    }

    @Test
    @DisplayName("양의 대각선으로 이동시 중간에 다른 기물이 존재하면 false")
    void isMovablePositiveDiagonalIfExistOtherPiece() {
        Position source = Position.valueOf("c8");
        Position target = Position.valueOf("a6");

        assertThat(bishopMoveStrategy.isMovable(board, source, target)).isFalse();
    }

    @Test
    @DisplayName("음의 대각선으로 이동시 중간에 다른 기물이 존재하면 false")
    void isMovableNegativeDiagonalIfExistOtherPiece() {
        Position source = Position.valueOf("c8");
        Position target = Position.valueOf("e6");

        assertThat(bishopMoveStrategy.isMovable(board, source, target)).isFalse();
    }
}
