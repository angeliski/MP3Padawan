package br.com.padawan.Menu.opcoes;

public class OpcaoDesligar implements Opcao {
    @Override
    public String getDescricao() {
        return "Desligar MP3";
    }

    @Override
    public int getCodigo() {
        return 7;
    }

    @Override
    public void executar() {
        System.exit(0);
    }
}
