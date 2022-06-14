package tictactoe;

import java.util.InputMismatchException;
import java.util.Scanner;

import static java.lang.Math.abs;


public class Main {
    public static char[][] table = new char[3][3]; //Game table
    public static Scanner scanner = new Scanner(System.in);
    public static int xPos;
    public static int yPos;


    public static void main(String[] args) {
        for(int i=0; i<3 ;i++){
            for(int j=0;j<3;j++){
                table[i][j]= ' ';
            }
        }


        drawTable();
        while(isGameFinished()){

            enterCoordinates();
            drawNew('X');
            checkWinner();
            enterCoordinates();
            drawNew('O');
            checkWinner();

        }




    }


    private static void checkWinner() {
        if (isImpossible()) {
            System.out.println("Impossible");
            System.exit(0);
        } else if (isWinner('X')) {
            System.out.println("X wins");
            System.exit(0);
        } else if (isWinner('O')) {
            System.out.println("O wins");
            System.exit(0);
        } else if (!isWinner('X') && !isWinner('O') && !isEmpty()) {
            System.out.println("Draw");
            System.exit(0);
        }

    }



    private static boolean isGameFinished(){
        return (!isWinner('X') && !isWinner('O') && isEmpty());
    }

    private static void drawTable() {

        System.out.println("---------");

        //First row
        System.out.print("| ");
        for (int i = 0; i < 3; i++) {
            System.out.print(table[0][i]);
            System.out.print(" ");
        }
        System.out.println("|");


        //Second row
        System.out.print("| ");
        for (int i = 0; i < 3; i++) {
            System.out.print(table[1][i]);
            System.out.print(" ");
        }
        System.out.println("|");


        //Third row
        System.out.print("| ");
        for (int i = 0; i < 3; i++) {
            System.out.print(table[2][i]);
            System.out.print(" ");
        }
        System.out.println("|");
        System.out.println("---------");
    }

    private static boolean isWinner(char symbol) {
        return (table[0][0] == symbol && table[0][1] == symbol && table[0][2] == symbol) ||
                (table[1][0] == symbol && table[1][1] == symbol && table[1][2] == symbol) ||
                (table[2][0] == symbol && table[2][1] == symbol && table[2][2] == symbol) ||

                (table[0][0] == symbol && table[1][0] == symbol && table[2][0] == symbol) ||
                (table[0][1] == symbol && table[1][1] == symbol && table[2][1] == symbol) ||
                (table[0][2] == symbol && table[1][2] == symbol && table[2][2] == symbol) ||

                (table[0][0] == symbol && table[1][1] == symbol && table[2][2] == symbol) ||
                (table[0][2] == symbol && table[1][1] == symbol && table[2][0] == symbol);

    }

    private static boolean isEmpty() {
        return (table[0][0] == ' ' || table[0][1] == ' ' || table[0][2] == ' ' ||
                table[1][0] == ' ' || table[1][1] == ' ' || table[1][2] == ' ' ||
                table[2][0] == ' ' || table[2][1] == ' ' || table[2][2] == ' ');
    }

    private static int counter(char symbol) {
        int counter = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (table[i][j] == symbol) {
                    counter++;
                }
            }
        }
        return counter;
    }

    private static int difference() {
        return abs(counter('X') - counter('O'));
    }

    private static boolean isImpossible() {
        return ((isWinner('X') && isWinner('O')) || difference() >= 2);
    }

    private static void enterCoordinates() {


            try {
                System.out.print("Enter the coordinates: ");
                xPos = scanner.nextInt();
                yPos = scanner.nextInt();

            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println("You should enter numbers!");
                enterCoordinates();
            }catch (ArrayIndexOutOfBoundsException e){
                scanner.nextLine();
            }
            boundCheck();
            occupiedError();
            //drawNew();






    }


        private static void drawNew (char symbol) {

            table[xPos - 1][yPos - 1] = symbol;

            drawTable();
        }

        private static void occupiedError () {
            boolean check = false;
            if (table[xPos - 1][yPos - 1] != ' ') {
                check = true;
            }
            if (check) {
                System.out.println("This cell is occupied! Choose another one!");
                enterCoordinates();

            }

        }

        private static void boundCheck(){
        if(xPos < 1 || xPos > 3 || yPos < 1 || yPos > 3){
            System.out.println("Coordinates should be from 1 to 3!");
            enterCoordinates();
        }
        }
    }












