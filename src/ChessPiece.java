public abstract class ChessPiece {
    protected String color;
    public boolean check = true;

    public ChessPiece(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public boolean isValidMove(ChessBoard chessBoard, int line, int colum, int toLine, int toColum) {
        if (!chessBoard.checkPos(colum) || !chessBoard.checkPos(line)
                || !chessBoard.checkPos(toColum) || !chessBoard.checkPos(toLine)) {
            return false;
        }

        if (chessBoard.board[toLine][toColum] != null &&
                chessBoard.board[toLine][toColum].getColor().equals(chessBoard.board[line][colum].getColor())) {
            return false;
        }

        return (line != toLine || colum != toColum);
    }
    public boolean isPathClear(ChessBoard chessBoard, int line, int colum, int toLine, int toColum) {
        int deltaLine = toLine - line;
        int deltaColum = toColum - colum;
        int stepLine = Integer.signum(deltaLine);
        int stepColum = Integer.signum(deltaColum);
        int currentLine = line + stepLine;
        int currentColum = colum + stepColum;

        while (currentLine != toLine || currentColum != toColum) {
            if (currentLine < 0 || currentLine >= chessBoard.board.length ||
                    currentColum < 0 || currentColum >= chessBoard.board[currentLine].length) {
                return false;
            }

            if (chessBoard.board[currentLine][currentColum] != null) {
                return false;
            }
            currentLine += stepLine;
            currentColum += stepColum;
        }
        return true;
    }

    public abstract boolean canMoveToPosition(ChessBoard chessBoard, int line, int colum, int toLine, int toColum);

    public abstract String getSymbol();
}
