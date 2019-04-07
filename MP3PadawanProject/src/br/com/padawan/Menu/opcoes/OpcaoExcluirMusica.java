package br.com.padawan.Menu.opcoes;

import br.com.padawan.Executor;
import br.com.padawan.musicas.Musica;
import br.com.padawan.musicas.MusicaService;

import java.util.Optional;

public class OpcaoExcluirMusica implements Opcao {

    private static final Executor MUSICA_NAO_ENCONTRADA = () -> {
        System.out.println("A música que você deseja excluir não existe.");
    };

    //TODO ioc
    private MusicaService musicaService = new MusicaService();

    @Override
    public String getDescricao() {
        return "Excluir músicas";
    }

    @Override
    public int getCodigo() {
        return 3;
    }

    @Override
    public void executar() {
        Optional<Musica> musicaEncontrada = musicaService.obterMusica();

        Executor toExecute = musicaEncontrada
                .map(this::excluirMusica)
                .orElseGet(() -> MUSICA_NAO_ENCONTRADA);

        toExecute.execute();
    }

    private Executor excluirMusica(Musica musica) {
        return () -> {
            musicaService.excluir(musica);
            System.out.println("Música excluída com sucesso.");
            musicaService.listarMusicasParaOUsuario();
        };
    }

}
