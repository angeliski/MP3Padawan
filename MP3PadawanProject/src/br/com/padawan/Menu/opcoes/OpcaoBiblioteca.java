package br.com.padawan.Menu.opcoes;

import br.com.padawan.LeitorDeResposta;
import br.com.padawan.Menu.Menu;
import br.com.padawan.musicas.MusicaService;

public class OpcaoBiblioteca implements Opcao {

    //TODO ioc
    private MusicaService musicaService = new MusicaService();

    private Menu menuBiblioteca = new Menu();

    public OpcaoBiblioteca() {
        menuBiblioteca.adicionarOpcao(new OpcaoBibliotecaTocarTodasAsMusicas()); ;
        menuBiblioteca.adicionarOpcao(new OpcaoBibliotecaTocarPlaylist()); ;
    }

    @Override
    public String getDescricao() {
        return "Biblioteca / Playlist";
    }

    @Override
    public int getCodigo() {
        return 4;
    }

    @Override
    public void executar() {
        boolean nenhumaMusicaCadastrada = musicaService.nenhumaMusicaCadastrada();

        if (nenhumaMusicaCadastrada) {
            System.out.println("Não é possível acessar a biblioteca porque não existe nenhuma música cadastrada. Por favor, selecione outra operação: ");
            System.out.println();
            return;
        }

        menuBiblioteca.exibirMenu();
        int opcaoSelecionada = LeitorDeResposta.nextInt();
        Opcao opcaoDaBiblioteca = menuBiblioteca.getOpcao(opcaoSelecionada);

        opcaoDaBiblioteca.executar();

    }
}
