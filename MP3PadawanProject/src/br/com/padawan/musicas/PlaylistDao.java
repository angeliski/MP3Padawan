package br.com.padawan.musicas;

import br.com.padawan.aparelhomp3.Playlist;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public class PlaylistDao {

    private static Set<Playlist> PLAYLISTS = new LinkedHashSet<>();
    
    public Set<Playlist> listar() {
        return Collections.unmodifiableSet(PLAYLISTS);
    }

    public void adicionar(Playlist novaPlayList) {
        PLAYLISTS.add(novaPlayList);
    }
}
