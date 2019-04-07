package br.com.padawan.Menu.opcoes;

import br.com.padawan.LeitorDeResposta;

public abstract class OpcaoQueRepete implements Opcao {

    private boolean deveExecutarNovamente() {
        System.out.println();
        System.out.println(questao());

        String resposta = LeitorDeResposta.next();

        return resposta.equalsIgnoreCase("S");
    }

    abstract String questao();

    abstract boolean executarUmaVez();

    @Override
    public void executar() {
        while (executarUmaVez() && deveExecutarNovamente());
    }


}
