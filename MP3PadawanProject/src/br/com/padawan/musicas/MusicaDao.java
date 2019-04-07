package br.com.padawan.musicas;

import java.util.*;

public class MusicaDao {

    private static Set<Musica> MUSICAS = new LinkedHashSet<>();

    public void add(Musica musica) {
        MUSICAS.add(musica);
    }

    public Optional<Musica> obter(String nomeMusica, String nomeArtistaMusica) {
        Optional<Musica> musicaSelecionada = MUSICAS
                .stream()
                .filter(musica -> musica.getNome().equalsIgnoreCase(nomeMusica))
                .filter(musica -> musica.getArtista().equalsIgnoreCase(nomeArtistaMusica))
                .findFirst();

        return musicaSelecionada;
    }

    public void excluir(Musica musica) {
        MUSICAS.remove(musica);
    }

    public Set<Musica> listar() {
        return Collections.unmodifiableSet(MUSICAS);
    }
}
