import java.util.Arrays;
import java.util.Random;
import java.lang.StringBuilder;

public class Game {

    // Criacao do tabuleiro e definicao de valores padroes de tamanho
    private int[][] board;
    private int rows = 5;
    private int columns = 5;

    // Construtor vazio, atributos sao definidos por metodos publicos
    public Game() {

    }

    // Responsavel por criar e randomizar o tabuleiro. 
    public void generateBoard(int tamanho){
        this.rows = tamanho;
        this.columns = tamanho;

        // Tamanho do tabuleiro precisa ser divisivel por 5 
        if ((tamanho % 5) != 0 ){
            System.out.println("Tamanho de tabela invalida");
        }

        // Matriz de criacao do tabuleiro
        board = new int[rows][columns];

        Random rand = new Random();

        //Criacao dos blocos do tabuleiro
        for (int y = 0; y < rows; y++) {
            int somaLinha = 0;

            // Metodo nextInt do rand é resposável por gerar os blocos, de forma aleatória. 
            for (int x = 0; x < columns; x++){
                board[y][x] = rand.nextInt(2);
                somaLinha += board[y][x];
            }

            // Se uma linha for vazia, ele gera o tabuleiro novamente. (Falta implementar a mesma condicao para comportamento nas colunas.)
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

    public void generateColumnTips(){
        int count;
        int maxRows;
        StringBuilder sb = new StringBuilder();

        String[] results = new String[columns];

        for (int x = 0; x < columns; x++){
            count = 0;

            for (int y = 0; y < rows; y++){

                int boardValue = board[y][x];

                if (boardValue == 1){
                    count += 1;

                    if (y == rows - 1) sb.append(count);

                } else {

                    if (count != 0) sb.append(count);
                    count = 0;

                }
            }

            results[x] = sb.toString();
            sb.setLength(0);

        }

        printColumnTips(results);
    }

    public void printColumnTips(String[] tipsArray){
        int maxRows = Arrays.stream(tipsArray).mapToInt(String::length).max().orElse(0);

        for (int i = 0; i < maxRows; i++){
            
            System.out.print(" ");

            for (int x = 0; x < columns; x++){
                if (tipsArray[x].length() <= maxRows){
                    try{
                        System.out.print(""+tipsArray[x].charAt(i) + " ");
                    } catch (StringIndexOutOfBoundsException e) {
                        System.out.print("  ");
                    }
                }
            }

            System.out.println();
        }

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
        generateColumnTips();

    }

}
