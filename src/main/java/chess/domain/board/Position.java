package chess.domain.board;

import static java.util.stream.Collectors.toMap;

import chess.domain.piece.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Position implements Comparable<Position> {

    private final static Map<String, Position> CACHE;

    static {
        CACHE = createAll().stream()
                .collect(toMap(Position::createKey, position -> position));
    }

    private final Column column;
    private final Row row;

    private Position(final Column column, final Row row) {
        this.column = column;
        this.row = row;
    }

    private static List<Position> createAll() {
        List<Position> positions = new ArrayList<>();
        for (Row row : Row.values()) {
            for (Column column : Column.values()) {
                positions.add(new Position(column, row));
            }
        }
        return positions;
    }

    public static Position valueOf(final String rawPosition) {
        if (!CACHE.containsKey(rawPosition)) {
            throw new IllegalArgumentException("[ERROR] 범위를 초과하였습니다.");
        }
        return CACHE.get(rawPosition);
    }

    public int subtractColumn(final Position position) {
        return this.column.subtract(position.column);
    }

    public int subtractRow(final Position position) {
        return this.row.subtract(position.row);
    }

    public boolean isPawnStartPosition(final Color color) {
        if (color == Color.BLACK && row == Row.SEVEN) {
            return true;
        }
        return color == Color.WHITE && row == Row.TWO;
    }

    public Position move(int horizon, int vertical) {
        String column = this.column.move(horizon).getName();
        int row = this.row.move(vertical).getValue();
        return Position.valueOf(column + row);
    }

    private String createKey() {
        return column.getName() + row.getValue();
    }

    public Position compareSmaller(Position position) {
        if (compareTo(position) > 0) {
            return position;
        }
        return this;
    }

    public Column getColumn() {
        return column;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Position position = (Position) o;
        return column == position.column && row == position.row;
    }

    @Override
    public int hashCode() {
        return Objects.hash(column, row);
    }

    @Override
    public int compareTo(Position position) {
        if (this.row.getValue() == position.row.getValue()) {
            return this.column.getValue() - position.column.getValue();
        }
        return position.row.getValue() - this.row.getValue();
    }
}
