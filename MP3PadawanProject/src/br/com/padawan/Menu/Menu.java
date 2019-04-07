package br.com.padawan.Menu;

import br.com.padawan.Menu.opcoes.Opcao;
import br.com.padawan.Menu.opcoes.OpcaoInvalida;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private List<Opcao> listaOpcoes = new ArrayList<>();

    @Override
    public String toString() {
        return "Menu : " + listaOpcoes;
    }

    public void adicionarOpcao(Opcao opcaoLista) {

        listaOpcoes.add(opcaoLista);
    }

    public void exibirMenu() {
        System.out.println("MP3 - Menu");
        this.listaOpcoes.forEach(opcao -> System.out.printf("%s - %s\n", opcao.getCodigo(), opcao.getDescricao()));
        System.out.println("Escolha uma opção: ");
    }

    public Opcao getOpcao(int opcaoSelecionada) {
        return listaOpcoes
                .stream()
                .filter(opcao -> opcao.getCodigo() == opcaoSelecionada)
                .findFirst()
                .orElse(OpcaoInvalida.getInstance());
    }
}
