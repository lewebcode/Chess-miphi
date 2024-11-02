public class Bishop extends ChessPiece {

    public Bishop(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return super.getColor();
    }

    @Override
    public String getSymbol() {
        return "B";
    }

    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (!(isValidMove(chessBoard, line, column, toLine, toColumn))) {
            return false;
        }
        if (!(isPathClear(chessBoard, line, column, toLine, toColumn))) {
            return false;
        }

        int deltaLine = Math.abs(line - toLine);
        int deltaColum = Math.abs(column - toColumn);

        return (deltaColum == deltaLine);
    }
}
