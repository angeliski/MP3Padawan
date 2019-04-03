package br.com.padawan;

import br.com.padawan.Menu.Menu;
import br.com.padawan.Menu.Opcao;
import br.com.padawan.aparelhomp3.MP3;

public class Main {

    public static void main(String[] args) {

        Opcao cadastrarMusica = new Opcao(1,"Cadastrar músicas");
        Opcao consultarMusica = new Opcao(2,"Consultar músicas");
        Opcao excluirMusica = new Opcao(3,"Excluir músicas");
        Opcao consultarBibliotecaPlayList = new Opcao(4,"Biblioteca / PlayList");
        Opcao cadastrarPlayList = new Opcao(5,"Cadastrar PlayList");
        Opcao adicionarMusicasPlayList = new Opcao(6,"Adicionar Músicas na Playlist");
        Opcao desligarMp3 = new Opcao(7,"Desligar MP3");

        Menu menuOpcoesMp3 = new Menu();
        menuOpcoesMp3.adicionarOpcao(cadastrarMusica);
        menuOpcoesMp3.adicionarOpcao(consultarMusica);
        menuOpcoesMp3.adicionarOpcao(excluirMusica);
        menuOpcoesMp3.adicionarOpcao(consultarBibliotecaPlayList);
        menuOpcoesMp3.adicionarOpcao(cadastrarPlayList);
        menuOpcoesMp3.adicionarOpcao(adicionarMusicasPlayList);
        menuOpcoesMp3.adicionarOpcao(desligarMp3);

        MP3 aparelhoMP3 = new MP3(menuOpcoesMp3);

        aparelhoMP3.liga();

    }


}
