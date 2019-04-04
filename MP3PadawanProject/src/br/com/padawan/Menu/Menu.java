package br.com.padawan.Menu;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    public List<Opcao> listaOpcoes = new ArrayList<>();

    @Override
    public String toString() {
        return "Menu : " + listaOpcoes;
    }

    public void adicionarOpcao(Opcao opcaoLista){

        listaOpcoes.add(opcaoLista);
    }
}
