package br.com.padawan.Menu;

public class Opcao {
    int codigoOpcao;
    String descricao;

    public Opcao(int codigoOpcao, String descricao){
        this.codigoOpcao = codigoOpcao;
        this.descricao = descricao;

    }

    @Override
    public String toString() {
        return  codigoOpcao +
                " - " + descricao;
    }
}
