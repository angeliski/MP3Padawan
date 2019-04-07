package br.com.padawan;

public class QuestionadorUsuario {

    public static String perguntarPorArtista(){
        System.out.println("Digite o artista da música: ");
        return LeitorDeResposta.nextLine();
    }

    public static String perguntarPorNomeDaMusica() {
        System.out.println("Digite o nome da música: ");
        return LeitorDeResposta.next();
    }

    public static String perguntarPeloNomeDaPlaylist() {
        System.out.println("Digite o nome da Playlist: ");
        return LeitorDeResposta.nextLine();
    }
}
