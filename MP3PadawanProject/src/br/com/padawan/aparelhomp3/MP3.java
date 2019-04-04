package br.com.padawan.aparelhomp3;

import br.com.padawan.Menu.Menu;
import br.com.padawan.musicas.Musica;

import java.time.LocalTime;
import java.util.*;

public class MP3 {

    Menu menu;
    public static final int cadastroMusica = 1;
    public static final int consultaMusica = 2;
    public static final int excluirMusica = 3;
    public static final int abrirBibliotecaEPlaylist = 4;
    public static final int cadastrarPlayList = 5;
    public static final int adicionarMusicaPlayList = 6;
    public static final int desligarMP3 = 7;
    private Scanner respostaUsuario = new Scanner(System.in);
    private Set<Musica> musicas = new LinkedHashSet<>();
    private Set<Playlist> playlists = new LinkedHashSet<>();
    private int opcaoEscolhida;
    private int minutosEscolhidos;
    private int segundosEscolhidos;
    private String nomeMusica;
    private String nomeArtistaMusica;
    private String nomePlaylistInputado;
    private boolean temPlaylistCadastrada = false;

    public MP3(Menu menu){
        this.menu = menu;
    }

    public void exibirMenu(){
        System.out.println("MP3 - Menu");
        menu.listaOpcoes.forEach(opcao -> System.out.println(opcao));
        System.out.println("Escolha uma opção: ");
    }

    private void desligarMP3(){
        System.exit(0);
    }

    public void pegarOpcaoSelecionada() {
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
        if (op == cadastroMusica) {
            cadastrarMusica();
        } else if (op == consultaMusica) {
            buscarMusica();
        } else if (op == excluirMusica) {
            excluirMusica();
        } else if (op == abrirBibliotecaEPlaylist) {
            confereSeExisteMusicaParaAbrirBiblioteca();
        } else if (op == cadastrarPlayList) {
            criarPlayList();
        } else if (op == adicionarMusicaPlayList){
            adicionaMusicasNaPlaylist();
        } else if(op == desligarMP3){
            desligarMP3();
        } else{
            System.out.println("Opção inválida.");
            liga();
        }
    }

    private void criarPlayList(){
        System.out.println("Digite o nome da Play List : ");
        String nomePlayList = respostaUsuario.next();

        Playlist novaPlayList = new Playlist(nomePlayList);
        novaPlayList.mostraMusicasExistentes(musicas);

        Optional<Musica> musicaEncontrada = verificaSeExisteMusica();
        if (musicaEncontrada.isPresent()) {
            novaPlayList.addMusica(musicaEncontrada.get());
            System.out.println("Música Adicionada com sucesso!");
            System.out.println(novaPlayList);
            playlists.add(novaPlayList);
            liga();
        }else{
            System.out.println("Música não encontrada!");
            liga();
        }

        temPlaylistCadastrada = true;
    }

    public void confereSeExistePlaylistParaTocarPlaylist() {
        int  verificaSeTemPlaylist = (int) playlists.stream().count();

        if (verificaSeTemPlaylist == 0) {
            System.out.println("Não é possível tocar uma playlist porque não existe nenhuma playlist cadastrada. Por favor, selecione outra operação: ");
            System.out.println();
            liga();
        } else {
           tocaPlayList();
        }
    }

    private Optional<Playlist> escolherPlaylist() {

        playlists.forEach(playlist -> System.out.println(playlist));
        recebeDoUsuarioPlayList();

        Optional<Playlist> playlistEscolhida = playlists
                .stream()
                .filter(playlist -> playlist.nomePlaylist.equals(nomePlaylistInputado))
                .findFirst();

        return playlistEscolhida;
    }

    private void adicionaMusicasNaPlaylist(){
        try {
            Playlist playlistEscolhida = escolherPlaylist().get();

            Musica musicaSelecionada = verificaSeExisteMusica().get();

            playlistEscolhida.addMusica(musicaSelecionada);
            System.out.println("Música Adicionada com sucesso!");

            liga();
        } catch (NoSuchElementException e) {
            System.out.println();
            System.out.println("Não existe uma playlist.");
            System.out.println("Por favor cadastre uma playlist antes de adicionar musicas.");
            System.out.println();
            liga();
        }
    }

    private void abrirBiblioteca() {
        System.out.println("[1] - Tocar todas as musicas" + System.lineSeparator() +
                "[2] - Escolher uma playlist" + System.lineSeparator() +
                "Escolha uma opção: " + System.lineSeparator());

        try {
            int opcaoSelecionada = respostaUsuario.nextInt();


            if (opcaoSelecionada == 1) {
                configurarListaReprodução();
            } else if (opcaoSelecionada == 2) {
                confereSeExistePlaylistParaTocarPlaylist();
            } else {
                System.out.println("Opção selecionada inválida");
                abrirBiblioteca();
            }
        } catch (InputMismatchException e) {
            if (!respostaUsuario.hasNextInt()){
                System.out.println("Opção selecionada inválida");
                respostaUsuario.next();
                abrirBiblioteca();
            }
        }
    }

    private void tocaPlayList () {
        Playlist playlistEscolhida = escolherPlaylist().get();

        System.out.println("A playlist escolhida foi: " + playlistEscolhida);

        tocarListaReproducao(playlistEscolhida);
        liga();
    }

    private void confereSeExisteMusicaParaAbrirBiblioteca() {
        int  verificaSeTemMusica = (int) musicas.stream().count();

      if (verificaSeTemMusica == 0) {
          System.out.println("Não é possível acessar a biblioteca porque não existe nenhuma música cadastrada. Por favor, selecione outra operação: ");
          System.out.println();
          liga();
      } else {
          abrirBiblioteca();
      }

    }

    private void configurarListaReprodução() {
        System.out.println(musicas);

        System.out.println("Configuração de reprodução:");
        System.out.println("O padrão é sequencial deseja alterar para aleatório? (S/N)");
        String alterarAletorio = respostaUsuario.next();

        if (alterarAletorio.equalsIgnoreCase("n")) {
            System.out.println(musicas);
            List<Musica> musicasEmLista = new ArrayList<>(musicas);
           tocarListaReproducao(musicasEmLista);
        } else if(alterarAletorio.equalsIgnoreCase("s")){
            List<Musica> musicasEmbaralhadas = new ArrayList<>(musicas);
            Collections.shuffle(musicasEmbaralhadas);
            System.out.println(musicasEmbaralhadas);
            tocarListaReproducao(musicasEmbaralhadas);
        } else if (!alterarAletorio.equalsIgnoreCase("s") && !alterarAletorio.equalsIgnoreCase("n")){
            System.out.println("Opção inválida");
            abrirBiblioteca();
        }
        System.out.println("Deseja reproduzir novamente? (s/n)");
        String escolhaRepeticao = respostaUsuario.next();

        if (escolhaRepeticao.equalsIgnoreCase("s")){
            configurarListaReprodução();
        }
            else if (escolhaRepeticao.equalsIgnoreCase("n")){
                liga();
            }
        else{
            System.out.println("Opção inválida");
            liga();
        }

    }

    public void tocarListaReproducao(List<Musica> musicas) {

        musicas.forEach(musica -> tocarMusica(musica));
    }


    public void tocarListaReproducao(Playlist playlist) {

        playlist.musicasPlayList.forEach(musica -> tocarMusica(musica));
    }

    public void pegaMinutosInputadosPeloUsuario() {
        try {
            System.out.println("Digite os minutos: ");
            minutosEscolhidos = respostaUsuario.nextInt();
        } catch (InputMismatchException ex) {
            if (!respostaUsuario.hasNextInt()) {
                System.out.println("O valor digitado é inválido");
                System.out.println();
                respostaUsuario.next();
                cadastrarMusica();
            }
        }
    }

    public void pegaSegundosInputadosPeloUsuario() {
        try {
            System.out.println("Digite os segundos: ");
            segundosEscolhidos = respostaUsuario.nextInt();
        } catch (InputMismatchException ex) {
            if (!respostaUsuario.hasNextInt()) {
                System.out.println("O valor digitado é inválido");
                System.out.println();
                respostaUsuario.next();
                cadastrarMusica();
            }
        }
    }

    private void recebeDoUsuarioArtistaEMusica() {
        System.out.println();
        System.out.println("Digite o nome da música: ");
        nomeMusica = respostaUsuario.next();

        respostaUsuario.nextLine();

        System.out.println("Digite o artista da música: ");
        nomeArtistaMusica = respostaUsuario.nextLine();
    }
    private void recebeDoUsuarioPlayList() {
        System.out.println();
        System.out.println("Digite o nome da Playlist: ");
        nomePlaylistInputado = respostaUsuario.next();

    }


    private void cadastrarMusica() {
        System.out.println("Cadastro de Músicas");

        recebeDoUsuarioArtistaEMusica();

        System.out.println("Digite a duração da música: ");
        int horaMusica = 0;

        pegaMinutosInputadosPeloUsuario();
        pegaSegundosInputadosPeloUsuario();

        if (segundosEscolhidos >= 0 && segundosEscolhidos <= 59 && minutosEscolhidos >= 0 && minutosEscolhidos <= 59) {
            LocalTime duracao = LocalTime.of(horaMusica, minutosEscolhidos, segundosEscolhidos);
            Musica novaMusica = new Musica(nomeMusica, duracao, nomeArtistaMusica);

            musicas.add(novaMusica);

            System.out.println();

            System.out.println("Você quer cadastrar uma nova música?(S/N)");

            String resposta = respostaUsuario.next();

            if (resposta.equalsIgnoreCase("S")) {

                cadastrarMusica();

            } else {
                liga();
            }
        } else {
            System.out.println("O formato do valor inserido para minutos/segundos é inválido, por favor siga o padrão (00 ~ 59)");
            cadastrarMusica();
        }
    }

    private void buscarMusica() {

        Optional<Musica> musicaEncontrada = verificaSeExisteMusica();

        if (musicaEncontrada.isPresent()) {
            System.out.println(musicaEncontrada.get());
            System.out.println("Deseja tocar a música?(S/N)");
            String tocarMusica = respostaUsuario.next();

            if (tocarMusica.equalsIgnoreCase("s")) {
                tocarMusica(musicaEncontrada);
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
                buscarMusica();
            } else if (buscarNovamente.equalsIgnoreCase("n")) {
                liga();
            } else {
                System.out.println("Opção inválida!");
                liga();
            }
        }
    }

    private Optional<Musica> verificaSeExisteMusica() {
        recebeDoUsuarioArtistaEMusica();

        Optional<Musica> musicaSelecionada = musicas.stream()
                .filter(musica -> musica.getNome().equalsIgnoreCase(nomeMusica))
                .filter(musica -> musica.getArtista().equalsIgnoreCase(nomeArtistaMusica))
                .findFirst();

        return musicaSelecionada;
    }

    private void tocarMusica(Optional<Musica> musicaEncontrada) {
        try {
            System.out.println("Tocando " + musicaEncontrada.get().getNome() + " de " + musicaEncontrada.get().getArtista());
            Thread.sleep(10000);

            liga();
        } catch (InterruptedException ex) {
            System.out.println(ex);
        }
    }

    private void tocarMusica(Musica musica) {
        try {
            System.out.println("Tocando " + musica.getNome() + " de " + musica.getArtista());
            Thread.sleep(10000);

        } catch (InterruptedException ex) {
            System.out.println(ex);
        }
    }

    private void excluirMusica() {

        Optional<Musica> musicaEncontrada = verificaSeExisteMusica();

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
