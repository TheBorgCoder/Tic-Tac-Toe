package tictactoe;

import java.util.Scanner;

public class Main {

    private static boolean legalCoordinate;
    private static Scanner scanner = new Scanner(System.in);
    private static final int row = 3;
    private static final int column = 3;
    private static char[][] board = new char[row][column];
    private static int count;
    private static boolean winnerIsX;
    private static boolean winnerIsO;
    private static boolean isDraw;
    private static boolean end;

    public static void main(String[] args) {

        winnerIsX = false;
        winnerIsO = false;
        isDraw = false;
        board = new char[][]{{' ', ' ', ' '}, {' ', ' ', ' '}, {' ', ' ', ' '}};
        count = 0;
        drawBoard(board, row, column);

        end = false;

        while (!end) {

            move('X');
            winnerX();
            draw();
            if (winnerIsX) {
                end = true;
                break;
            } else if (isDraw) {
                end = true;
                break;
            }

            move('O');
            winnerO();
            draw();
            if (winnerIsO) {
                end = true;
                break;
            } else if (isDraw) {
                end = true;
                break;
            }

        }

    }

    private static boolean whoIsTheWinner(String betu) {
        if (String.valueOf(new char[] {board[0][0], board[0][1], board[0][2]}).equals(betu)
                || String.valueOf(new char[] {board[1][0], board[1][1], board[1][2]}).equals(betu)
                || String.valueOf(new char[] {board[2][0], board[2][1], board[2][2]}).equals(betu)
                || String.valueOf(new char[] {board[0][0], board[1][0], board[2][0]}).equals(betu)
                || String.valueOf(new char[] {board[0][1], board[1][1], board[2][1]}).equals(betu)
                || String.valueOf(new char[] {board[0][2], board[1][2], board[2][2]}).equals(betu)
                || String.valueOf(new char[] {board[0][0], board[1][1], board[2][2]}).equals(betu)
                || String.valueOf(new char[] {board[0][2], board[1][1], board[2][0]}).equals(betu)) {
            return true;
        }
        return false;
    }

    private static void winnerO() {
        if (whoIsTheWinner("OOO")) {
            winnerIsO = true;
            System.out.println("O wins !");
        }
    }

    private static void winnerX() {
        if (whoIsTheWinner("XXX")) {
            winnerIsX = true;
            System.out.println("X wins !");
        }
    }

    private static void draw() {
        if (count >= 9 && !winnerIsO && !winnerIsX) {
            isDraw = true;
            System.out.println("Draw!");
        }
    }

    public static void move(char player) {
        do {
            legalCoordinate = true;
            System.out.print("Enter the coordinates: ");
            try {
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                if (x > 3 || y > 3) {
                    System.out.println("Coordinates should be from 1 to 3!");
                    legalCoordinate = false;
                } else if (board[x - 1][y - 1] != ' ') {
                    System.out.println("This cell is occupied! Choose another one!");
                    legalCoordinate = false;
                } else {
                    board[x - 1][y - 1] = player;
                    drawBoard(board, row, column);
                    count++;
                }
            } catch (Exception e) {
                System.out.println("You should enter numbers!");
                legalCoordinate = false;
                scanner.next();
            }
        } while (!legalCoordinate);
    }


    public static void drawBoard(char[][] board, int row, int column) {
        System.out.println("---------");
        for (int i = 0; i < row; i++) {
            System.out.print("| ");
            for (int j = 0; j < column; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("---------");
    }
}