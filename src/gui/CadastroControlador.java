package gui;

import java.util.ArrayList;
import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.ListModel;
import negocio.*;

public class CadastroControlador {
    private CadastroFachada fachada;
    private ListaPessoasModel modelSaidaTexto;

    public CadastroControlador() throws CadastroException {
        fachada = new CadastroFachada();
        modelSaidaTexto = new ListaPessoasModel(this.getTodosNomes());
    }

    public ComboBoxModel<String> getListaPessoasModel(){
        return modelSaidaTexto;
    }
    
    private List<String> toListString(List<Pessoa> listaOrigem) {
        List<String> listaDestino = new ArrayList<String>(listaOrigem.size());
        for(Pessoa p : listaOrigem) {
            listaDestino.add(p.toString());
        }
        return listaDestino;
    }
    
    public boolean adicionarPessoa(String nome, String email, String documento) throws CadastroException {
        Pessoa p = fachada.adicionarPessoa(nome, email, documento);
        if(p != null){
            modelSaidaTexto.add(nome);
            return true;
        }
        return false;
    }

    public List<String> getTodos() throws CadastroException {
        return toListString(fachada.buscarTodos());
    }
    
    public void atualiza() throws CadastroException {
        modelSaidaTexto = new ListaPessoasModel(toListString(fachada.buscarTodos()));
    }
    
    public String getTelefone(String pessoa) throws CadastroException {
        return fachada.buscarPessoaPorNome(pessoa).getEmail();
    }
    
    public List<String> getTodosNomes() throws CadastroException {
        List<Pessoa> pessoas = fachada.buscarTodos();
        List<String> retorno = new ArrayList<>();
        for (Pessoa p : pessoas){
            retorno.add(p.getNome());
        }
        return retorno;
    }
}
