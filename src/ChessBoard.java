public class ChessBoard {
    public ChessPiece[][] board = new ChessPiece[8][8]; // creating a field for game
    String nowPlayer;

    public ChessBoard(String nowPlayer) {
        this.nowPlayer = nowPlayer;
    }

    public String nowPlayerColor() {
        return this.nowPlayer;
    }

    public boolean moveToPosition(int startLine, int startColumn, int endLine, int endColumn) {
        if (checkPos(startLine) && checkPos(startColumn)) {
            ChessPiece piece = board[startLine][startColumn];

            if (!nowPlayer.equals(piece.getColor())) return false;

            if (piece.canMoveToPosition(this, startLine, startColumn, endLine, endColumn)) {
                if (piece instanceof King) {
                    if (isKingInCheckAfterMove(startLine, startColumn, endLine, endColumn)) {
                        System.out.println("Невозможно переместить короля в шах!");
                        return false;
                    }
                }
                board[endLine][endColumn] = piece;
                board[startLine][startColumn] = null;
                if (isKingInCheck()) {
                    board[startLine][startColumn] = piece;
                    board[endLine][endColumn] = null;
                    System.out.println("Невозможно выполнить этот ход: король в шаху!");
                    return false;
                }
                if (piece instanceof King || piece instanceof Rook) {
                    piece.check = false;
                }
                this.nowPlayer = this.nowPlayerColor().equals("White") ? "Black" : "White";
                if (isKingInCheck()) {
                    System.out.println("Король в шаху!");
                }

                return true;
            } else return false;
        } else return false;
    }

    public void printBoard() {
        System.out.println("Turn " + nowPlayer);
        System.out.println();
        System.out.println("Player 2(Black)");
        System.out.println();
        System.out.println("\t0\t1\t2\t3\t4\t5\t6\t7");

        for (int i = 7; i > -1; i--) {
            System.out.print(i + "\t");
            for (int j = 0; j < 8; j++) {
                if (board[i][j] == null) {
                    System.out.print(".." + "\t");
                } else {
                    System.out.print(board[i][j].getSymbol() + board[i][j].getColor().substring(0, 1).toLowerCase() + "\t");
                }
            }
            System.out.println();
            System.out.println();
        }
        System.out.println("Player 1(White)");
    }

    public boolean checkPos(int pos) {
        return pos >= 0 && pos <= 7;
    }

    public boolean castling0() {
        int line = nowPlayer.equals("White") ? 0 : 7;
        int kingColumn = 4;
        int rookColumn = 0;
        ChessPiece king = board[line][kingColumn];
        ChessPiece rook = board[line][rookColumn];

        // Проверяем, что это король и ладья

        if (!(king instanceof King && rook instanceof Rook)) {

            System.out.println("Фигура не король или не ладья");
            return false;
        }

        // Проверяем, что король и ладья не ходили
        if (!(king.check && rook.check)) {
            System.out.println("Король check: " + king.check + ", Ладья check: " + rook.check);
            System.out.println("Король или ладья ходили");
            return false;
        }

        // Проверяем, что между королем и ладьей нет фигур
        if (!(board[line][kingColumn - 1] == null && board[line][kingColumn - 2] == null)) {
            if (board[line][kingColumn - 1] != null) {
                System.out.println("Поле: " + line + "." + (kingColumn - 1) + " занято");
            }
            if (board[line][kingColumn - 2] != null) {
                System.out.println("Поле: " + line + "." + (kingColumn - 2) + " занято");
            }
            System.out.println("Между королем и ладьей есть другие фигуры");
            return false;
        }

        // Проверяем, что поля, по которым проходит король, не под атакой
        if (((King) king).isUnderAttack(this, line, kingColumn) ||
                ((King) king).isUnderAttack(this, line, kingColumn - 1) ||
                ((King) king).isUnderAttack(this, line, kingColumn - 2)) {
            System.out.println("Поля находятся под атакой");
            return false;
        }

        // Выполняем рокировку
        board[line][kingColumn] = null;
        board[line][kingColumn - 2] = king;
        board[line][rookColumn] = null;
        board[line][kingColumn - 1] = rook;

        king.check = false;
        rook.check = false;


        this.nowPlayer = this.nowPlayerColor().equals("White") ? "Black" : "White";
        return true;
    }

    public boolean castling7() {
        int line = nowPlayer.equals("White") ? 0 : 7;
        int kingColumn = 4;
        int rookColumn = 7;
        ChessPiece king = board[line][kingColumn];
        ChessPiece rook = board[line][rookColumn];

        // Проверяем, что это король и ладья

        if (!(king instanceof King && rook instanceof Rook)) {
            System.out.println("Король check: " + king.check + ", Ладья check: " + rook.check);
            System.out.println("Фигура не король или не ладья");
            return false;
        }

        // Проверяем, что король и ладья не ходили
        if (!(king.check || rook.check)) {
            System.out.println("Король или ладья ходили");
            return false;
        }

        // Проверяем, что между королем и ладьей нет фигур
        if (!(board[line][kingColumn + 1] == null && board[line][kingColumn + 2] == null)) {
            if (board[line][kingColumn + 1] != null) {
                System.out.println("Поле: " + line + "." + (kingColumn + 1) + " занято");
            }
            if (board[line][kingColumn + 2] != null) {
                System.out.println("Поле: " + line + "." + (kingColumn + 2) + " занято");
            }
            System.out.println("Между королем и ладьей есть другие фигуры");
            return false;
        }

        // Проверяем, что поля, по которым проходит король, не под атакой
        if (((King) king).isUnderAttack(this, line, kingColumn) ||
                ((King) king).isUnderAttack(this, line, kingColumn + 1) ||
                ((King) king).isUnderAttack(this, line, kingColumn + 2)) {
            System.out.println("Поля находятся под атакой");
            return false;
        }

        // Выполняем рокировку
        board[line][kingColumn] = null;
        board[line][kingColumn + 2] = king;
        board[line][rookColumn] = null;
        board[line][kingColumn + 1] = rook;

        king.check = false;
        rook.check = false;

        // Сменяем текущего игрока
        this.nowPlayer = this.nowPlayerColor().equals("White") ? "Black" : "White";
        return true;
    }

    public boolean isKingInCheck() {
        int kingLine = -1;
        int kingColumn = -1;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                ChessPiece piece = board[i][j];
                if (piece instanceof King && piece.getColor().equals(nowPlayer)) {
                    kingLine = i;
                    kingColumn = j;
                    break;
                }
            }
            if (kingLine != -1) break;
        }

        King king = (King) board[kingLine][kingColumn];
        return king.isUnderAttack(this, kingLine, kingColumn);
    }

    public boolean isKingInCheckAfterMove(int startLine, int startColumn, int endLine, int endColumn) {
        ChessPiece tempPiece = board[endLine][endColumn];
        board[endLine][endColumn] = board[startLine][startColumn];
        board[startLine][startColumn] = null;

        int kingLine = -1;
        int kingColumn = -1;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                ChessPiece piece = board[i][j];
                if (piece instanceof King && piece.getColor().equals(nowPlayer)) {
                    kingLine = i;
                    kingColumn = j;
                    break;
                }
            }
            if (kingLine != -1) break;
        }

        King king = (King) board[kingLine][kingColumn];
        boolean inCheck = king.isUnderAttack(this, kingLine, kingColumn);

        board[startLine][startColumn] = board[endLine][endColumn];
        board[endLine][endColumn] = tempPiece;

        return inCheck;
    }
}
