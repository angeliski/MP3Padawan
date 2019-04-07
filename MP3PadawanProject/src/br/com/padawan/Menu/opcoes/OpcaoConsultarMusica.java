package br.com.padawan.Menu.opcoes;

import br.com.padawan.LeitorDeResposta;
import br.com.padawan.QuestionadorUsuario;
import br.com.padawan.aparelhomp3.SaidaSom;
import br.com.padawan.musicas.Musica;
import br.com.padawan.musicas.MusicaDao;
import br.com.padawan.musicas.MusicaService;

import java.util.Optional;

public class OpcaoConsultarMusica extends OpcaoQueRepete {

    //TODO IOC
    private MusicaService musicaService = new MusicaService();


    @Override
    public String getDescricao() {
        return "Consultar músicas";
    }

    @Override
    public int getCodigo() {
        return 2;
    }

    @Override
    String questao() {
        return "Música não encontrada, deseja buscar novamente?(S/N)";
    }

    @Override
    boolean executarUmaVez() {
        Optional<Musica> musicaEncontrada = musicaService.obterMusica();

        musicaEncontrada.ifPresent(this::tentarExecutar);

        if (musicaEncontrada.isPresent()) {

        } else {
            return true;
        }
        return false;
    }

    private void tentarExecutar(Musica musica) {
        System.out.println(musica);
        System.out.println("Deseja tocar a música?(S/N)");
        String tocarMusica = LeitorDeResposta.next();

        if (tocarMusica.equalsIgnoreCase("s")) {
            new SaidaSom().tocarMusica(musica);
        } else if (tocarMusica.equalsIgnoreCase("n")) {
            //
        } else {
            System.out.println("Opção inválida!");
            //
        }
    }

}
