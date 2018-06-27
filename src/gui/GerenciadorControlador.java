package gui;

import java.util.ArrayList;
import java.util.List;
import javax.swing.ComboBoxModel;
import negocio.*;

public class GerenciadorControlador {
    private GerenciadorFachada fachada;
    private ListaPessoasModel modelSaidaTexto;

    public GerenciadorControlador() throws GerenciadorException {
        fachada = new GerenciadorFachada();
        atualiza();
    }

    public ComboBoxModel<String> getListaPessoasModel(){
        return modelSaidaTexto;
    }
    
    private List<String> toListString(List<Pessoa> listaOrigem) {
        List<String> listaDestino = new ArrayList<>(listaOrigem.size());
        for(Pessoa p : listaOrigem) {
            listaDestino.add(p.toString());
        }
        return listaDestino;
    }
    
    public boolean adicionarPessoa(String nome, String email, String documento) throws GerenciadorException {
        Pessoa p = fachada.adicionarPessoa(nome, email, documento);
        if(p != null){
            modelSaidaTexto.add(p);
            return true;
        }
        return false;
    }

    public List<String> getTodos() throws GerenciadorException {
        return toListString(fachada.buscarTodasPessoas());
    }
    
    public void atualiza() throws GerenciadorException {
        modelSaidaTexto = new ListaPessoasModel(fachada.buscarTodasPessoas());
    }
    
    public String getEmail(String documento) throws GerenciadorException {
        return fachada.buscarPessoaPorDocumento(documento).getEmail();
    }
    
    public String getNome(String documento) throws GerenciadorException {
        return fachada.buscarPessoaPorDocumento(documento).getNome();
    }
    
    public List<String> getTodosNomes() throws GerenciadorException {
        List<Pessoa> pessoas = fachada.buscarTodasPessoas();
        List<String> retorno = new ArrayList<>();
        for (Pessoa p : pessoas){
            retorno.add(p.getNome());
        }
        return retorno;
    }
}
