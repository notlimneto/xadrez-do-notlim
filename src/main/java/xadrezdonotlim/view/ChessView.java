package xadrezdonotlim.view;

public class ChessView {
    private static final String os = System.getProperty("os.name");

    public static void cleanTerminal() {
        try {
            if (os.contains("Windows")) new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else new ProcessBuilder("clear").inheritIO().start().waitFor();
        }
        catch (final Exception e)
        {
            System.out.println("Erro ao limpar terminal: " + e.getMessage());
        }
    }
}
