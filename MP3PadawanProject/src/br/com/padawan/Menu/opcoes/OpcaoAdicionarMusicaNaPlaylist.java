package br.com.padawan.Menu.opcoes;

import br.com.padawan.aparelhomp3.Playlist;
import br.com.padawan.musicas.Musica;
import br.com.padawan.musicas.MusicaService;
import br.com.padawan.musicas.PlaylistService;

import java.util.NoSuchElementException;

public class OpcaoAdicionarMusicaNaPlaylist implements Opcao {

    private PlaylistService playlistService = new PlaylistService();
    private MusicaService musicaService = new MusicaService();

    @Override
    public String getDescricao() {
        return "Adicionar Músicas na Playlist";
    }

    @Override
    public int getCodigo() {
        return 6;
    }

    @Override
    public void executar() {

        try {
            Playlist playlistEscolhida = playlistService.escolherPlaylist();

            Musica musicaSelecionada = musicaService.obterMusica().get();

            playlistEscolhida.adicionarMusica(musicaSelecionada);
            System.out.println("Música Adicionada com sucesso!");

        } catch (NoSuchElementException e) {
            System.out.println();
            System.out.println("Não existe uma playlist.");
            System.out.println("Por favor cadastre uma playlist antes de adicionar musicas.");
            System.out.println();
        }
    }

}
