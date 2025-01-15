public class Main {
    public static void main(String[] args) {

        // Instancia um novo jogo
        Game game = new Game();

        // Define o tamanho do tabuleiro
        game.generateBoard(5);

        // Printa o Jogo no Terminal
        game.printBoard();

    }
}