package br.com.padawan.Menu.opcoes;

import br.com.padawan.aparelhomp3.Playlist;
import br.com.padawan.aparelhomp3.SaidaSom;
import br.com.padawan.musicas.PlaylistService;

public class OpcaoBibliotecaTocarPlaylist extends OpcaoQueRepete {

    //TODO IOC
    private PlaylistService playlistService = new PlaylistService();

    @Override
    String questao() {
        return "Deseja reproduzir novamente? (s/n)";
    }

    @Override
    public String getDescricao() {
        return "Escolher uma playlist";
    }

    @Override
    public int getCodigo() {
        return 2;
    }

    @Override
    boolean executarUmaVez() {
        boolean nenhumaPlaylistCadastrada = playlistService.nenhumaPlaylistCadastrada();

        if (nenhumaPlaylistCadastrada) {
            System.out.println("Não é possível tocar uma playlist porque não existe nenhuma playlist cadastrada. Por favor, selecione outra operação: ");
            System.out.println();
            return false;
        }

        tocarPlayList();
        return true;
    }

    private void tocarPlayList() {
        Playlist playlistEscolhida = playlistService.escolherPlaylist();

        System.out.println("A playlist escolhida foi: " + playlistEscolhida);

        tocarListaReproducao(playlistEscolhida);
    }

    public void tocarListaReproducao(Playlist playlist) {
        SaidaSom saidaSom = new SaidaSom();
        playlist.getMusicasPlayList().forEach(saidaSom::tocarMusica);
    }
}
