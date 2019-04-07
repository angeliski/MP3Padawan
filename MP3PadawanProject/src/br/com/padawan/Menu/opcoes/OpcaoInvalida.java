package br.com.padawan.Menu.opcoes;

public class OpcaoInvalida implements Opcao {

    private static final Opcao INSTANCE = new OpcaoInvalida();

    private OpcaoInvalida() {

    }

    public static Opcao getInstance() {
        return INSTANCE;
    }

    @Override
    public String getDescricao() {
        return "Opção Inválida";
    }

    @Override
    public int getCodigo() {
        return 0;
    }

    @Override
    public void executar() {
        System.out.println(getDescricao());
    }
}
