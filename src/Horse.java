public class Horse extends ChessPiece {

    public Horse(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return super.getColor();
    }

    @Override
    public String getSymbol() {
        return "H";
    }

    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int colum, int toLine, int toColumn) {
        if (!(isValidMove(chessBoard, line, colum, toLine, toColumn))) {
            return false;
        }

        int deltaLine = Math.abs(line - toLine);
        int deltaColumn = Math.abs(colum - toColumn);

        return ((deltaLine == 2 && deltaColumn == 1) || (deltaLine == 1 && deltaColumn == 2));
    }
}
