package br.com.padawan.aparelhomp3;

import br.com.padawan.musicas.Musica;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

public class MP3 {

    private Scanner respostaUsuario = new Scanner(System.in);
    public Set<Musica> musicas = new LinkedHashSet<>();
    int opcaoEscolhida;

    private void exibirMenu() {

        System.out.println("Menu: ");
        System.out.println();
        System.out.println("[1] - Cadastrar músicas." +
                System.lineSeparator() + "[2] - Buscar músicas." +
                System.lineSeparator() + "[3] - Excluir músicas." +
                System.lineSeparator() + "[4] - Criar Playlist." +
                System.lineSeparator() + "Escolha uma opção :" +
                System.lineSeparator());

    }

    private void pegarOpcaoSelecionada() {
        try {
           opcaoEscolhida = respostaUsuario.nextInt();
        } catch (InputMismatchException e) {
            if (!respostaUsuario.hasNextInt()) {
                System.out.println("Por favor, digite apenas os digitos indicados antes das opções.");
                System.out.println();
                respostaUsuario.next();
                pegarOpcaoSelecionada();
            }
        }
    }

    public void liga() {


        exibirMenu();

        pegarOpcaoSelecionada();
        verificarOpcao(opcaoEscolhida);

    }

    private void verificarOpcao(int op) {

          if (op == 1) {

              cadastrarMusica();
          } else if (op == 2) {
              buscarMusica(musicas);
          } else if (op == 3) {
              excluirMusica();
          } else if (op == 4) {
              new Playlist().criarPlaylist();
          } else {
              System.out.println("Opção inválida.");
              liga();
          }
    }

    private void cadastrarMusica() {
        System.out.println("Digite o nome da música: ");
        String nomeMusica = respostaUsuario.next();

        respostaUsuario.nextLine();

        System.out.println("Digite o artista da música: ");
        String nomeArtistaMusica = respostaUsuario.nextLine();

        System.out.println("Digite a duração da música: ");
        int horaMusica = 0;

        System.out.println("Digite os minutos: ");
        int minutoMusica = respostaUsuario.nextInt();

        System.out.println("Digite os segundos: ");
        int segundoMusica = respostaUsuario.nextInt();

        LocalTime duracao = LocalTime.of(horaMusica, minutoMusica, segundoMusica);

        Musica novaMusica = new Musica(nomeMusica, duracao, nomeArtistaMusica);

        musicas.add(novaMusica);

        System.out.println();

        System.out.println("Você que cadastrar uma nova musica?(S/N)");

        String resposta = respostaUsuario.next();

        if (resposta.equalsIgnoreCase("S")) {

            cadastrarMusica();

        } else {
            liga();
        }
    }

    private void buscarMusica(Set<Musica> musicas) {

        Optional<Musica> musicaEncontrada = verificaSeExisteMusica(musicas);

        if (musicaEncontrada.isPresent()) {
            System.out.println(musicaEncontrada.get());
            System.out.println("Deseja tocar a música?(S/N)");
            String tocarMusica = respostaUsuario.next();

            if (tocarMusica.equalsIgnoreCase("s")) {
                tocaMusica(musicaEncontrada);
            } else if (tocarMusica.equalsIgnoreCase("n")) {
                liga();
            } else {
                System.out.println("Opção inválida!");
                liga();
            }
        } else {
            System.out.println("Música não encontrada, deseja buscar novamente?(S/N)");
            String buscarNovamente = respostaUsuario.next();

            if (buscarNovamente.equalsIgnoreCase("s")) {
                buscarMusica(musicas);
            } else if (buscarNovamente.equalsIgnoreCase("n")) {
                liga();
            } else {
                System.out.println("Opção inválida!");
                liga();
            }
        }
    }

    public Optional<Musica> verificaSeExisteMusica(Set<Musica> musicas) {
        System.out.println("Digite o nome da música: ");
        String nomeMusica = respostaUsuario.next();

        respostaUsuario.nextLine();

        System.out.println("Digite o artista da música: ");
        String nomeArtistaMusica = respostaUsuario.nextLine();

        Optional<Musica> musicaSelecionada = musicas.stream()
                .filter(musica -> musica.getNome().equals(nomeMusica) && musica.getArtista().equals(nomeArtistaMusica))
                .findFirst();
       

        return musicaSelecionada;
    }

    private void tocaMusica(Optional<Musica> musicaEncontrada) {
        try {
            System.out.println("Tocando " + musicaEncontrada.get().getNome() + " de " + musicaEncontrada.get().getArtista());
            Thread.sleep(10000);

            liga();

        } catch (InterruptedException ex) {
            System.out.println(ex);
        }
    }

    private void excluirMusica() {

        Optional<Musica> musicaEncontrada = verificaSeExisteMusica(musicas);

        if (musicaEncontrada.isPresent()) {
            musicas.remove(musicaEncontrada.get());
            System.out.println("Música excluída com sucesso.");
            System.out.println(musicas);
            liga();
        } else {
            System.out.println("A música que você deseja excluir não existe.");
            liga();
        }
    }
}
