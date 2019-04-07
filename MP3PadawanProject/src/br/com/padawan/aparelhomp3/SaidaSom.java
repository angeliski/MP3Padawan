package br.com.padawan.aparelhomp3;

import br.com.padawan.musicas.Musica;

public class SaidaSom {


    public void tocarMusica(Musica musica) {
        try {
            System.out.println("Tocando " + musica.getNome() + " de " + musica.getArtista());
            Thread.sleep(10000);

        } catch (InterruptedException ex) {
            System.out.println(ex);
        }
    }

}
