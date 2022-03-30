package chess.domain.move;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.board.Board;
import chess.domain.board.CatchPieces;
import chess.domain.board.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RookMoveStrategyTest {

    Board board;
    RookMoveStrategy rookMoveStrategy;
    CatchPieces catchPieces = new CatchPieces();

    @BeforeEach
    void setUp() {
        board = Board.createChessBoard();
        rookMoveStrategy = new RookMoveStrategy();
    }

    @Test
    @DisplayName("룩이 수직 이동할 수 있다.")
    void isMovableVertical() {
        board.movePiece(Position.valueOf("a7"), Position.valueOf("b6"), catchPieces);

        Position source = Position.valueOf("a8");
        Position target = Position.valueOf("a4");

        assertThat(rookMoveStrategy.isMovable(board, source, target)).isTrue();
    }

    @Test
    @DisplayName("룩이 수평 이동할 수 있다.")
    void isMovableHorizon() {
        board.movePiece(Position.valueOf("b8"), Position.valueOf("b6"), catchPieces);

        Position source = Position.valueOf("a8");
        Position target = Position.valueOf("b8");

        assertThat(rookMoveStrategy.isMovable(board, source, target)).isTrue();
    }

    @Test
    @DisplayName("수직이동시 중간에 다른 기물이 존재하면 false")
    void isMovableVerticalIfExistOtherPiece() {
        Position source = Position.valueOf("a8");
        Position target = Position.valueOf("a4");

        assertThat(rookMoveStrategy.isMovable(board, source, target)).isFalse();
    }

    @Test
    @DisplayName("수평이동시 중간에 다른 기물이 존재하면 false")
    void isMovableHorizontalIfExistOtherPiece() {
        Position source = Position.valueOf("a8");
        Position target = Position.valueOf("h8");

        assertThat(rookMoveStrategy.isMovable(board, source, target)).isFalse();
    }
}
