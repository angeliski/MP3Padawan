package br.com.padawan.musicas;

import br.com.padawan.LeitorDeResposta;
import br.com.padawan.QuestionadorUsuario;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class MusicaService {

    //TODO IOC
    private MusicaDao musicaDao = new MusicaDao();

    public Optional<Musica> obterMusica() {
        System.out.println();
        String nomeMusica = QuestionadorUsuario.perguntarPorNomeDaMusica();

        LeitorDeResposta.nextLine();

        String nomeArtistaMusica = QuestionadorUsuario.perguntarPorArtista();

        return musicaDao.obter(nomeMusica, nomeArtistaMusica);
    }

    public void excluir(Musica musica) {
        musicaDao.excluir(musica);
    }

    public void listarMusicasParaOUsuario() {
        Set<Musica> musicas = musicaDao.listar();
        System.out.println(musicas);
    }

    public boolean nenhumaMusicaCadastrada() {
        return musicaDao.listar().isEmpty();
    }

    public Set<Musica> listarMusicas() {
        return musicaDao.listar();
    }
}
