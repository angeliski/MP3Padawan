package br.com.padawan.musicas;

import java.time.LocalTime;
import java.util.Objects;

public class Musica {

    private String nome;
    private LocalTime duracao;
    private String artista;
    private int contadorDeVezesTocadas;


    public Musica(String nome, LocalTime duracao, String artista) {
        this.nome = nome;
        this.duracao = duracao;
        this.artista = artista;
    }

    public String getNome() {
        return nome;
    }



    public LocalTime getDuracao() {
        return duracao;
    }



    public String getArtista() {
        return artista;
    }



    @Override
    public String toString() {
        return "Musicas{ " +
                "nome = '" + nome + '\'' +
                ", duracao = " + duracao +
                ", artista = '" + artista + '\'' +
                " }";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Musica)) return false;
        Musica musica = (Musica) o;
        return Objects.equals(getNome(), musica.getNome()) &&
                Objects.equals(getDuracao(), musica.getDuracao()) &&
                Objects.equals(getArtista(), musica.getArtista());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNome(), getDuracao(), getArtista());
    }


}