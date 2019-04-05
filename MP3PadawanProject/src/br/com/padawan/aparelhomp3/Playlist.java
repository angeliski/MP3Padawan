package br.com.padawan.aparelhomp3;

import br.com.padawan.musicas.Musica;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;


public class Playlist {

    Set<Musica> musicasPlayList = new HashSet<>();
    String nomePlaylist;


    public Playlist(String nomePlaylist){

        this.nomePlaylist = nomePlaylist;
    }

    public void mostrarMusicasExistentes(Set<Musica> musicas) {

        musicas.forEach(musica -> System.out.println(musica));
    }

    public void adicionarMusica(Musica musica){
        musicasPlayList.add(musica);
    }

    @Override
    public String toString() {
        return  "NomePlaylist = '" + nomePlaylist + '\'' +
                " MusicasPlayList = " + musicasPlayList;
    }
}
