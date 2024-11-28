package xadrezdonotlim;

import xadrezdonotlim.domain.ChessGame;
import xadrezdonotlim.view.ChessView;

import java.util.Scanner;

public class Chess {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ChessView.cleanTerminal();
        System.out.println("Bem vindo ao xadrez de terminal do notlimneto\n");

        int option;
        do {
            showOptions();
            option = scanner.nextInt();

            if (option == 1) {
                new ChessGame();
            }
            ChessView.cleanTerminal();
        } while (option==1);
    }

    public static void showOptions() {
        System.out.println("Escolha uma das opções abaixo");
        System.out.print("1 - Iniciar nova partida\n0 - Sair\n");
    }
}