public class King extends ChessPiece {
    public King(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return super.getColor();

    }

    @Override
    public String getSymbol() {
        return "K";
    }

    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (!(isValidMove(chessBoard, line, column, toLine, toColumn))) {
            return false;
        }
        if (!(isPathClear(chessBoard, line, column, toLine, toColumn))) {
            return false;
        }

        int deltaLine = Math.abs(line - toLine);
        int deltaColumn = Math.abs(column - toColumn);

        return (deltaLine <= 1 && deltaColumn <= 1);
    }

    public boolean isUnderAttack(ChessBoard board, int line, int column) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                ChessPiece piece = board.board[i][j];
                if (piece != null && !piece.getColor().equals(getColor())) {
                    if (piece instanceof Pawn) {

                        int direction = piece.getColor().equals("White") ? 1 : -1;
                        if ((line == i + direction) && (Math.abs(column - j) == 1)) {
                            return true;
                        }
                    } else if (piece.canMoveToPosition(board, i, j, line, column)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
