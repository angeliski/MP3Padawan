package br.com.padawan.Menu.opcoes;

import br.com.padawan.LeitorDeResposta;
import br.com.padawan.QuestionadorUsuario;
import br.com.padawan.musicas.Musica;
import br.com.padawan.musicas.MusicaDao;

import java.time.LocalTime;

public class OpcaoCadastrarMusica extends OpcaoQueRepete {

    private String nomeMusica;
    private String nomeArtistaMusica;
    private int minutosEscolhidos;
    private int segundosEscolhidos;

    //TODO IOC
    private MusicaDao musicaDao = new MusicaDao();


    @Override
    public String getDescricao() {
        return "Cadastrar músicas";
    }

    @Override
    public int getCodigo() {
        return 1;
    }

    @Override
    String questao() {
        return "Você quer cadastrar uma nova música?(S/N)";
    }

    private void receberDoUsuarioArtistaEMusica() {
        System.out.println();
        nomeMusica = QuestionadorUsuario.perguntarPorNomeDaMusica();

        LeitorDeResposta.nextLine();

        nomeArtistaMusica = QuestionadorUsuario.perguntarPorArtista();
    }

    public void pegarMinutosInputadosPeloUsuario() {
        System.out.println("Digite os minutos: ");
        minutosEscolhidos = LeitorDeResposta.nextInt();
    }

    public void pegarSegundosInputadosPeloUsuario() {
        System.out.println("Digite os segundos: ");
        segundosEscolhidos = LeitorDeResposta.nextInt();
    }


    @Override
    boolean executarUmaVez() {
        System.out.println("Cadastro de Músicas");

        receberDoUsuarioArtistaEMusica();

        System.out.println("Digite a duração da música: ");
        int horaMusica = 0;

        pegarMinutosInputadosPeloUsuario();
        pegarSegundosInputadosPeloUsuario();

        if (segundosEscolhidos >= 0 && segundosEscolhidos <= 59 && minutosEscolhidos >= 0 && minutosEscolhidos <= 59) {
            LocalTime duracao = LocalTime.of(horaMusica, minutosEscolhidos, segundosEscolhidos);
            Musica novaMusica = new Musica(nomeMusica, duracao, nomeArtistaMusica);

            musicaDao.add(novaMusica);

        } else {
            System.out.println("O formato do valor inserido para minutos/segundos é inválido, por favor siga o padrão (00 ~ 59)");
            return executarUmaVez();
        }

        return true;
    }
}
