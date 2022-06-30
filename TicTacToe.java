
import java.util.Scanner;
import java.util.Random;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

public class TicTacToe {

    static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();
    static ArrayList<Integer> player2Positions = new ArrayList<Integer>();

    public static void main(String[] args) {

        char[][] gameBoard = {{' ', '|', ' ', '|', ' '},
        {'-', '+', '-', '+', '-'},
        {' ', '|', ' ', '|', ' '},
        {'-', '+', '-', '+', '-'},
        {' ', '|', ' ', '|', ' '}};

        while (true) {
            Scanner scan = new Scanner(System.in);
            System.out.println("Choose an option:\n1:Player vs CPU\n"
                    + "2:Player vs Player\nInput number of your choice (1,2)");
            int choice = scan.nextInt();
            if (choice == 1) {
                vsCpu(gameBoard);
            }
            if (choice == 2) {
                twoPlayer(gameBoard);
            }
        }

    }

    public static void vsCpu(char[][] gameBoard) {

        while (true) {
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter your placement");
            int playerPos = scan.nextInt();

            while (playerPositions.contains(playerPos) || cpuPositions.contains(playerPos)) {
                System.out.println("Position taken! Enter elsewhere");
                playerPos = scan.nextInt();
            }

            placePiece(gameBoard, playerPos, "player");
            String result = checkWinner();
            if (result.length() > 0) {
                System.out.println(result);
                break;
            }

            Random rand = new Random();
            int cpuPos = rand.nextInt(9) + 1;
            while (playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos)) {
                cpuPos = rand.nextInt(9) + 1;
            }
            placePiece(gameBoard, cpuPos, "cpu");

            printGameBoard(gameBoard);

            result = checkWinner();
            if (result.length() > 0) {
                System.out.println(result);
                break;
            }
            System.out.println(result);
        }
    }
    
    public static void twoPlayer(char[][] gameBoard) {

        while (true) {
            Scanner scan = new Scanner(System.in);
            System.out.println("Player 1, enter your placement");
            int playerPos = scan.nextInt();

            while (playerPositions.contains(playerPos) || player2Positions.contains(playerPos)) {
                System.out.println("Position taken! Enter elsewhere");
                playerPos = scan.nextInt();
            }

            placePiece(gameBoard, playerPos, "player");
            
            printGameBoard(gameBoard);
            
            String result = checkWinner();
            if (result.length() > 0) {
                System.out.println(result);
                break;
            }

            System.out.println("Player 2, enter your placement");
            int player2Pos = scan.nextInt();
            while (playerPositions.contains(player2Pos) || player2Positions.contains(player2Pos)) {
                System.out.println("Position taken! Enter elsewhere");
                player2Pos = scan.nextInt();
            }
            placePiece(gameBoard, player2Pos, "player2");

            printGameBoard(gameBoard);

            result = checkWinner();
            if (result.length() > 0) {
                System.out.println(result);
                break;
            }
            System.out.println(result);
        }
    }

    public static void printGameBoard(char[][] gameBoard) {

        for (char[] row : gameBoard) {
            for (char c : row) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static void placePiece(char[][] gameBoard, int pos, String user) {
        char symbol = ' ';

        if (user.equals("player")) {
            symbol = 'X';
            playerPositions.add(pos);
        } else if (user.equals("cpu")) {
            symbol = 'O';
            cpuPositions.add(pos);
        } else if (user.equals("player2")) {
            symbol = 'O';
            player2Positions.add(pos);
        }

        switch (pos) {
            case 1:
                gameBoard[0][0] = symbol;
                break;
            case 2:
                gameBoard[0][2] = symbol;
                break;
            case 3:
                gameBoard[0][4] = symbol;
                break;
            case 4:
                gameBoard[2][0] = symbol;
                break;
            case 5:
                gameBoard[2][2] = symbol;
                break;
            case 6:
                gameBoard[2][4] = symbol;
                break;
            case 7:
                gameBoard[4][0] = symbol;
                break;
            case 8:
                gameBoard[4][2] = symbol;
                break;
            case 9:
                gameBoard[4][4] = symbol;
                break;
            default:
                break;
        }
    }

    public static String checkWinner() {
        List topRow = Arrays.asList(1, 2, 3);
        List midRow = Arrays.asList(4, 5, 6);
        List botRow = Arrays.asList(7, 8, 9);
        List leftCol = Arrays.asList(1, 4, 7);
        List midCol = Arrays.asList(2, 5, 8);
        List rightCol = Arrays.asList(3, 6, 9);
        List cross1 = Arrays.asList(1, 5, 9);
        List cross2 = Arrays.asList(3, 5, 7);

        List<List> winning = new ArrayList<List>();
        winning.add(topRow);
        winning.add(midRow);
        winning.add(botRow);
        winning.add(leftCol);
        winning.add(midCol);
        winning.add(rightCol);
        winning.add(cross1);
        winning.add(cross2);

        for (List l : winning) {
            if (playerPositions.containsAll(l)) {
                return "player wins!";
            } else if (cpuPositions.containsAll(l)) {
                return "CPU wins.";
            } else if (player2Positions.containsAll(l)) {
                return "player2 wins.";
            }
        }

        if (playerPositions.size() + cpuPositions.size() == 9 || 
                playerPositions.size() + player2Positions.size() == 9) {
            return "It's a draw.";
        }

        return "";
    }
}
