package br.com.padawan.aparelhomp3;

import br.com.padawan.musicas.Musica;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class MP3 {

    private Scanner respostaUsuario = new Scanner(System.in);
    private Set<Musica> musicas = new LinkedHashSet<>();

   private void exibirMenu(){

        System.out.println("Menu: ");

        System.out.println("[1] - Cadastrar músicas." +
                        System.lineSeparator()+ "[2] - Buscar músicas." +
                        System.lineSeparator()+"[3] - Excluir músicas." +
                        System.lineSeparator()+ "Escolha uma opção :");
    }

    private int pegarOpcaoSelecionada(){



       return respostaUsuario.nextInt();
    }

   private void verificarOpcao(int op){

       if (op == 1){

           cadastrarMusica();
       }

    }

    private void cadastrarMusica(){



        System.out.println("Digite o nome da música: ");
        String nomeMusica = respostaUsuario.next();

        System.out.println("Digite o artista da música: ");
        String nomeArtistaMusica = respostaUsuario.next();

        System.out.println("Digite a duração da música: ");
        int horaMusica = 0;

        System.out.println("Digite os minutos: ");
        int minutoMusica = respostaUsuario.nextInt();

        System.out.println("Digite os segundos: ");
        int segundoMusica = respostaUsuario.nextInt();

        LocalTime duracao = LocalTime.of(horaMusica,minutoMusica,segundoMusica);

        Musica novaMusica =new Musica(nomeMusica, duracao, nomeArtistaMusica);

        musicas.add(novaMusica);

        System.out.println();

        System.out.println("Você que cadastrar uma nova musica?(S/N)");

        String resposta = respostaUsuario.next();

        if (resposta.equalsIgnoreCase("S")){

            cadastrarMusica();

        }else{
            liga();
        }



    }


    public void liga(){
        int opcaoEscolhida = 0;

        exibirMenu();

        opcaoEscolhida = pegarOpcaoSelecionada();

        verificarOpcao(opcaoEscolhida);

    }

}
