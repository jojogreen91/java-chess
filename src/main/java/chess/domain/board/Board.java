package chess.domain.board;

import chess.domain.game.DeadPieces;
import chess.domain.piece.Blank;
import chess.domain.piece.Team;
import chess.domain.piece.Piece;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Board {

    private final Map<Position, Piece> board;

    public Board(final Map<Position, Piece> board) {
        this.board = new TreeMap<>(board);
    }

    public void movePiece(final Position source, final Position target, final DeadPieces deadPieces) {
        Piece targetPiece = board.getOrDefault(target, new Blank());
        board.put(target, getPiece(source));
        board.put(source, new Blank());
        deadPieces.add(targetPiece);
    }

    public Team getTeamOfPiece(final Position position) {
        return board.get(position).getTeam();
    }

    public Piece getPiece(final Position position) {
        return board.get(position);
    }

    public Map<Position, Piece> getBoard() {
        return Collections.unmodifiableMap(board);
    }

    public Map<String, Piece> getBoardForSpark() {
        return board.keySet().stream()
                .collect(Collectors.toMap(Position::generateRawPosition, this::getPiece));
    }
}
