import java.util.Random;
import java.lang.StringBuilder;
public class Game {

    private int[][] board;
    private int rows = 5;
    private int columns = 5;

    public Game() {

    }

    public void generateBoard(int tamanho){
        this.rows = tamanho;
        this.columns = tamanho;

        if ((tamanho % 5) != 0 ){
            System.out.println("Tamanho de tabela invalida");
        }

        board = new int[rows][columns];

        Random rand = new Random();

        for (int y = 0; y < rows; y++) {
            int somaLinha = 0;

            for (int x = 0; x < columns; x++){
                board[y][x] = rand.nextInt(2);
                somaLinha += board[y][x];
            }

            if (somaLinha == 0) {
                y = y - 1;
                System.out.println("[DEV] Houve a geracao de uma nova linha.");
            }

        }
    }

    public StringBuilder generateRowTips(int row){
        int count = 0;
        StringBuilder tip = new StringBuilder();

        for (int x = 0; x < columns; x++){
            int boardValue = board[row][x];

            if (boardValue == 1) {
                count += 1;

                if (x == columns - 1) {
                    tip.append(count + " ");
                }

            } else {

                if (count != 0) {
                    tip.append(count + " ");
                }

                count = 0;
            }
        }
        return tip;
    }

    public void printBoard(){

        for (int i = 0; i < columns * 3; i++) {
            System.out.print("_");
        }

        System.out.println("");

        for (int y = 0; y < board.length; y++){

            System.out.print("|");

            for (int x = 0; x < board[y].length; x++){
                System.out.print((board[y][x] == 1) ? "\u25A0|" : " |");
            }

            System.out.print(" " + generateRowTips(y));

            System.out.println(" ");

        }

    }

}
