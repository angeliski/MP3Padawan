package br.com.padawan;

import br.com.padawan.Menu.Menu;
import br.com.padawan.Menu.opcoes.*;
import br.com.padawan.aparelhomp3.MP3;

public class Main {

    public static void main(String[] args) {

        Opcao cadastrarMusica = new OpcaoCadastrarMusica();
        Opcao consultarMusica = new OpcaoConsultarMusica();
        Opcao excluirMusica = new OpcaoExcluirMusica();
        Opcao consultarBibliotecaPlayList = new OpcaoBiblioteca();
        Opcao cadastrarPlayList = new OpcaoCadastrarPlaylist();
        Opcao adicionarMusicasPlayList = new OpcaoAdicionarMusicaNaPlaylist();
        Opcao desligarMp3 = new OpcaoDesligar();

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
