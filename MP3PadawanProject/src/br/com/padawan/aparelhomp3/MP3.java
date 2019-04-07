package br.com.padawan.aparelhomp3;

import br.com.padawan.Menu.Menu;
import br.com.padawan.Menu.opcoes.Opcao;
import br.com.padawan.musicas.Musica;

import java.time.LocalTime;
import java.util.*;

public class MP3 {

    private Menu menu;
    private Scanner respostaUsuario = new Scanner(System.in);
    private int opcaoEscolhida;

    public MP3(Menu menu) {
        this.menu = menu;
    }

    public void pegarOpcaoSelecionada() {
        try {
            opcaoEscolhida = respostaUsuario.nextInt();
        } catch (InputMismatchException e) {
            if (!respostaUsuario.hasNextInt()) {
                System.out.println("Por favor, digite apenas os digitos indicados antes das opções.");
                System.out.println();
                respostaUsuario.next();
                pegarOpcaoSelecionada();
            }
        }
    }

    public void liga() {
        menu.exibirMenu();
        pegarOpcaoSelecionada();
        executarOpcao(opcaoEscolhida);
    }

    private void executarOpcao(int opcaoSelecionada) {
        Opcao opcao = menu.getOpcao(opcaoSelecionada);
        opcao.executar();
        liga();
    }

}
