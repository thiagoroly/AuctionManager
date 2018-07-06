package gui;

import java.util.ArrayList;
import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.ListModel;
import negocio.*;

public class GerenciadorControlador {
    private GerenciadorFachada fachada;
    private ListaPessoasModel modelPessoasSaidaTexto;
    private ListaLeiloesModel modelLeiloesSaidaTexto;
    
    public GerenciadorControlador() throws GerenciadorException {
        fachada = new GerenciadorFachada();
        atualizaUsuarios();
        atualizaLeiloes();
    }

    public ComboBoxModel<String> getListaPessoasModel(){
        return modelPessoasSaidaTexto;
    }
    
    public ComboBoxModel<String> getListaLeiloesModel(){
        return modelLeiloesSaidaTexto;
    }
    
    private List<String> toListString(List<Pessoa> listaOrigem) {
        List<String> listaDestino = new ArrayList<>(listaOrigem.size());
        for(Pessoa p : listaOrigem) {
            listaDestino.add(p.toString());
        }
        return listaDestino;
    }
    
    public boolean adicionaUsuario(String nome, String email, String documento) throws GerenciadorException {
        Pessoa p = fachada.adicionarPessoa(nome, email, documento);
        if(p != null){
            modelPessoasSaidaTexto.add(p);
            return true;
        }
        return false;
    }

    public List<String> getUsuarioTodos() throws GerenciadorException {
        return toListString(fachada.buscarTodasPessoas());
    }
    
    public void atualizaUsuarios() throws GerenciadorException {
        modelPessoasSaidaTexto = new ListaPessoasModel(fachada.buscarTodasPessoas());
    }
    
    public void atualizaLeiloes() throws GerenciadorException {
        modelLeiloesSaidaTexto = new ListaLeiloesModel(fachada.buscarTodosLeiloesTitulo());
    }
    
    public String getUsuarioEmail(String documento) throws GerenciadorException {
        return fachada.buscarPessoaPorDocumento(documento).getEmail();
    }
    
    public String getUsuarioNome(String documento) throws GerenciadorException {
        return fachada.buscarPessoaPorDocumento(documento).getNome();
    }
    
    public List<String> getUsuarioTodosNomes() throws GerenciadorException {
        List<Pessoa> pessoas = fachada.buscarTodasPessoas();
        List<String> retorno = new ArrayList<>();
        for (Pessoa p : pessoas){
            retorno.add(p.getNome());
        }
        return retorno;
    }
    
    public String getLeilaoCategoria(String leilao) throws GerenciadorException{
        return fachada.buscarLeilaoCategoria(leilao);
    }

    public String getLeilaoDono(String leilao) throws GerenciadorException{
        return fachada.buscarLeilaoDono(leilao);
    }

    public String getLeilaoLanceMin(String leilao) throws GerenciadorException{
        return fachada.buscarLeilaoLanceMin(leilao);
    }

    public String getLeilaoStatus(String leilao) throws GerenciadorException{
        return fachada.buscarLeilaoStatus(leilao);
    }

    public ListModel getListaLancesModel(String leilao) {
        return new ListaLancesModel(fachada.buscarTodosLeilaoLances(leilao));
    }
}
