package br.com.padawan.Menu.opcoes;

import br.com.padawan.Executor;
import br.com.padawan.QuestionadorUsuario;
import br.com.padawan.aparelhomp3.Playlist;
import br.com.padawan.musicas.Musica;
import br.com.padawan.musicas.MusicaService;
import br.com.padawan.musicas.PlaylistService;

import java.util.Optional;

public class OpcaoCadastrarPlaylist implements Opcao {

    private static final Executor MUSICA_NAO_ENCONTRADA = () -> System.out.println("Música não encontrada!");

    //TODO ioc
    private MusicaService musicaService = new MusicaService();
    private PlaylistService playlistService = new PlaylistService();

    @Override
    public String getDescricao() {
        return "Cadastrar Playlist";
    }

    @Override
    public int getCodigo() {
        return 5;
    }

    @Override
    public void executar() {
        String nomePlaylist = QuestionadorUsuario.perguntarPeloNomeDaPlaylist();

        Playlist novaPlayList = new Playlist(nomePlaylist);

        musicaService.listarMusicasParaOUsuario();

        Optional<Musica> musicaEncontrada = musicaService.obterMusica();

        musicaEncontrada
                .map(m -> adicionarMusicaNaPlaylist(novaPlayList, m))
                .orElseGet(() -> MUSICA_NAO_ENCONTRADA);
    }

    private Executor adicionarMusicaNaPlaylist(Playlist novaPlayList, Musica musica) {
        return () -> {
            novaPlayList.adicionarMusica(musica);
            System.out.println("Música Adicionada com sucesso!");
            System.out.println(novaPlayList);
            playlistService.adicionar(novaPlayList);
        };
    }
}
