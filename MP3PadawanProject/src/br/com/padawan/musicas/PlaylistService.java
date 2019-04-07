package br.com.padawan.musicas;

import br.com.padawan.QuestionadorUsuario;
import br.com.padawan.aparelhomp3.Playlist;

import java.util.Set;

public class PlaylistService {

    //TODO IOC
    private PlaylistDao playlistDao = new PlaylistDao();

    public boolean nenhumaPlaylistCadastrada() {
        return playlistDao.listar().isEmpty();
    }

    public Set<Playlist> listarMusicas() {
        return playlistDao.listar();
    }

    public Playlist escolherPlaylist() {

        String nomePlaylistInputado = QuestionadorUsuario.perguntarPeloNomeDaPlaylist();


        return listarMusicas()
                .stream()
                .filter(playlist -> playlist.getNomePlaylist().equals(nomePlaylistInputado))
                .findFirst()
                .get();
    }

    public void adicionar(Playlist novaPlayList) {
        playlistDao.adicionar(novaPlayList);
    }
}
