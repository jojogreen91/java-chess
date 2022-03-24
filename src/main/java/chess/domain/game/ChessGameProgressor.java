package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Piece;
import java.util.List;
import java.util.Map;

public class ChessGameProgressor {

    private static final int COMMAND_INDEX = 0;
    private static final int SOURCE_INDEX = 1;
    private static final int TARGET_INDEX = 2;
    private static final String END_COMMAND = "end";

    private final ChessGame chessGame;
    private final GameSwitch gameSwitch;

    public ChessGameProgressor() {
        this.chessGame = new ChessGame(Board.create());
        this.gameSwitch = new GameSwitch();
    }

    public Map<Position, Piece> getCurrentBoard() {
        return chessGame.getBoard();
    }

    public boolean isOn() {
        return gameSwitch.isOn();
    }

    public void progress(final List<String> inputs) {
        if (inputs.get(COMMAND_INDEX).equals(END_COMMAND)) {
            gameSwitch.turnOff();
            return;
        }
        chessGame.move(Position.valueOf(inputs.get(SOURCE_INDEX)), Position.valueOf(inputs.get(TARGET_INDEX)));
    }
}
