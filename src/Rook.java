public class Rook extends ChessPiece {

    public Rook(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return super.getColor();
    }

    @Override
    public String getSymbol() {
        return "R";
    }

    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColum) {
        if (!(isValidMove(chessBoard, line, column, toLine, toColum))) {
            return false;
        }
        if (!(isPathClear(chessBoard, line, column, toLine, toColum))) {
            return false;
        }

        return line == toLine || column == toColum;
    }
}

