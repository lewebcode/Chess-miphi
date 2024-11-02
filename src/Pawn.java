public class Pawn extends ChessPiece {

    public Pawn(String color) {
        super(color);
    }

    @Override
    public String getColor() {
        return super.getColor();
    }

    @Override
    public String getSymbol() {
        return "P";
    }

    public boolean canMoveToPosition(ChessBoard chessBoard, int line, int column, int toLine, int toColumn) {
        if (!(isValidMove(chessBoard, line, column, toLine, toColumn))) {
            return false;
        }
        //Движение пешки
        ChessPiece targetPiece = chessBoard.board[toLine][toColumn];

        int deltaLine = toLine - line;
        int deltaColumn = toColumn - column;

        if (color.equals("White")) {
            // Движение вперёд на две клетки с начальной позиции
            if (line == 1 && deltaLine == 2 && deltaColumn == 0 && targetPiece == null &&
                    chessBoard.board[line + 1][column] == null) {
                return true;
            }
            // Движение вперёд на одну клетку
            if (deltaLine == 1 && deltaColumn == 0 && targetPiece == null) {
                return true;
            }
            // Взятие наискосок
            if (deltaLine == 1 && Math.abs(deltaColumn) == 1 && targetPiece != null) {
                return true;
            }
        }

        // Проверка для чёрной пешки
        if (color.equals("Black")) {
            // Движение вперёд на две клетки с начальной позиции
            if (line == 6 && deltaLine == -2 && deltaColumn == 0 && targetPiece == null &&
                    chessBoard.board[line - 1][column] == null) {
                return true;
            }
            // Движение вперёд на одну клетку
            if (deltaLine == -1 && deltaColumn == 0 && targetPiece == null) {
                return true;
            }

            // Взятие наискосок
            return deltaLine == -1 && Math.abs(deltaColumn) == 1 && targetPiece != null;
        }
        return false;
    }
}
