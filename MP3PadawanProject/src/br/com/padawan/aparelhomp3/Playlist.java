package br.com.padawan.aparelhomp3;

import br.com.padawan.musicas.Musica;

import java.util.Scanner;
import java.util.Set;

public class Playlist {
    Musica musica;
    String nomePlaylist;
    Scanner respostaDoUsuario = new Scanner(System.in);

    public void perguntaNomePlaylist () {

        System.out.println("Digite o nome da playlist");
        nomePlaylist = respostaDoUsuario.next();

    }

    public void mostraMusicasExistentes(Set<Musica> musicas) {
        musicas.forEach(musica -> System.out.println(musica));
    }

}
