package br.com.padawan.Menu.opcoes;

import br.com.padawan.LeitorDeResposta;
import br.com.padawan.aparelhomp3.SaidaSom;
import br.com.padawan.musicas.Musica;
import br.com.padawan.musicas.MusicaService;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class OpcaoBibliotecaTocarTodasAsMusicas extends OpcaoQueRepete {

    //TODO ioc
    private MusicaService musicaService = new MusicaService();

    @Override
    String questao() {
        return "Deseja reproduzir novamente? (s/n)";
    }

    @Override
    public String getDescricao() {
        return "Tocar todas as musicas";
    }

    @Override
    public int getCodigo() {
        return 1;
    }

    @Override
    boolean executarUmaVez() {
        musicaService.listarMusicasParaOUsuario();

        System.out.println("Configuração de reprodução:");
        System.out.println("O padrão é sequencial deseja alterar para aleatório? (S/N)");
        String alterarAletorio = LeitorDeResposta.nextLine();

        if (alterarAletorio.equalsIgnoreCase("n")) {
            tocarTodasAsMusicasEmSequencia();
        } else if (alterarAletorio.equalsIgnoreCase("s")) {
            tocarTodasAsMusicasEmAleatorio();
        } else if (!alterarAletorio.equalsIgnoreCase("s") && !alterarAletorio.equalsIgnoreCase("n")) {
            System.out.println("Opção inválida");
            return false;
        }
        return true;
    }

    private void tocarTodasAsMusicasEmAleatorio() {
        musicaService.listarMusicasParaOUsuario();

        List<Musica> musicas = musicaService
                .listarMusicas()
                .stream()
                .collect(Collectors.toList());
        SaidaSom saidaSom = new SaidaSom();

        Collections.shuffle(musicas);
        System.out.println(musicas);
        musicas.forEach(saidaSom::tocarMusica);
    }

    private void tocarTodasAsMusicasEmSequencia() {
        musicaService.listarMusicasParaOUsuario();

        Set<Musica> musicas = musicaService.listarMusicas();
        SaidaSom saidaSom = new SaidaSom();

        musicas.forEach(saidaSom::tocarMusica);
    }
}
