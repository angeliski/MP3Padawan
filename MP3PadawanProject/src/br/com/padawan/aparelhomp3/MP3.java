package br.com.padawan.aparelhomp3;

import br.com.padawan.Menu.Menu;
import br.com.padawan.musicas.Musica;

import java.time.LocalTime;
import java.util.*;

public class MP3 {

    Menu menu;
    public static final int CADASTRO_MUSICA = 1;
    public static final int CONSULTA_MUSICA = 2;
    public static final int EXCLUIR_MUSICA = 3;
    public static final int ABRIR_BIBLIOTECA_E_PLAYLIST = 4;
    public static final int CADASTRAR_PLAY_LIST = 5;
    public static final int ADICIONAR_MUSICA_PLAY_LIST = 6;
    public static final int DESLIGAR_MP3 = 7;
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
        if (op == CADASTRO_MUSICA) {
            cadastrarMusica();
        } else if (op == CONSULTA_MUSICA) {
            buscarMusica();
        } else if (op == EXCLUIR_MUSICA) {
            excluirMusica();
        } else if (op == ABRIR_BIBLIOTECA_E_PLAYLIST) {
            conferirSeExisteMusicaParaAbrirBiblioteca();
        } else if (op == CADASTRAR_PLAY_LIST) {
            criarPlayList();
        } else if (op == ADICIONAR_MUSICA_PLAY_LIST){
            adicionarMusicasNaPlaylist();
        } else if(op == DESLIGAR_MP3){
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
        novaPlayList.mostrarMusicasExistentes(musicas);

        Optional<Musica> musicaEncontrada = verificarSeExisteMusica();
        if (musicaEncontrada.isPresent()) {
            novaPlayList.adicionarMusica(musicaEncontrada.get());
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

    public void conferirSeExistePlaylistParaTocarPlaylist() {
        int  verificaSeTemPlaylist = (int) playlists.stream().count();

        if (verificaSeTemPlaylist == 0) {
            System.out.println("Não é possível tocar uma playlist porque não existe nenhuma playlist cadastrada. Por favor, selecione outra operação: ");
            System.out.println();
            liga();
        } else {
           tocarPlayList();
        }
    }

    private Optional<Playlist> escolherPlaylist() {

        playlists.forEach(playlist -> System.out.println(playlist));
        receberDoUsuarioPlayList();

        Optional<Playlist> playlistEscolhida = playlists
                .stream()
                .filter(playlist -> playlist.nomePlaylist.equals(nomePlaylistInputado))
                .findFirst();

        return playlistEscolhida;
    }

    private void adicionarMusicasNaPlaylist(){
        try {
            Playlist playlistEscolhida = escolherPlaylist().get();

            Musica musicaSelecionada = verificarSeExisteMusica().get();

            playlistEscolhida.adicionarMusica(musicaSelecionada);
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
                conferirSeExistePlaylistParaTocarPlaylist();
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

    private void tocarPlayList() {
        Playlist playlistEscolhida = escolherPlaylist().get();

        System.out.println("A playlist escolhida foi: " + playlistEscolhida);

        tocarListaReproducao(playlistEscolhida);
        liga();
    }

    private void conferirSeExisteMusicaParaAbrirBiblioteca() {
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

    public void pegarMinutosInputadosPeloUsuario() {
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

    public void pegarSegundosInputadosPeloUsuario() {
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

    private void receberDoUsuarioArtistaEMusica() {
        System.out.println();
        System.out.println("Digite o nome da música: ");
        nomeMusica = respostaUsuario.next();

        respostaUsuario.nextLine();

        System.out.println("Digite o artista da música: ");
        nomeArtistaMusica = respostaUsuario.nextLine();
    }
    private void receberDoUsuarioPlayList() {
        System.out.println();
        System.out.println("Digite o nome da Playlist: ");
        nomePlaylistInputado = respostaUsuario.next();

    }


    private void cadastrarMusica() {
        System.out.println("Cadastro de Músicas");

        receberDoUsuarioArtistaEMusica();

        System.out.println("Digite a duração da música: ");
        int horaMusica = 0;

        pegarMinutosInputadosPeloUsuario();
        pegarSegundosInputadosPeloUsuario();

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

        Optional<Musica> musicaEncontrada = verificarSeExisteMusica();

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

    private Optional<Musica> verificarSeExisteMusica() {
        receberDoUsuarioArtistaEMusica();

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

        Optional<Musica> musicaEncontrada = verificarSeExisteMusica();

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
