package negocio;

import dados.CadastroDAOException;
import dados.CadastroDAOJavaDb;
import java.util.List;

public class CadastroFachada {
    private CadastroDAO dao;
    
    public CadastroFachada() throws CadastroException {
        try {
            dao = CadastroDAOJavaDb.getInstance();
        } catch (CadastroDAOException e) {
            throw new CadastroException("Falha de criação da fachada!", e);
        }
    }
    
    public Pessoa adicionarPessoa(String nome, String email, String documento) throws CadastroException{
        if(!ValidadorPessoa.validaNome(nome)) {
            throw new CadastroException("Nome de pessoa inválido!");
        }
        if(!ValidadorPessoa.validaEmail(email)) {
            throw new CadastroException("Endereço de e-mail inválido!");
        }
        if(!ValidadorPessoa.validaDocumento(documento)) {
            throw new CadastroException("Número de documento inválido!");
        }
        Pessoa p = new Pessoa(nome, email, documento);
        try {
            boolean ok = dao.adicionar(p);
            if(ok) {
                return p;
            }
            return null;
        } catch (CadastroDAOException e) {
            throw new CadastroException("Falha ao adicionar pessoa!", e);
        }
    }

    public List<Pessoa> buscarTodos() throws CadastroException{
        try {
            return dao.getTodos();
        } catch (CadastroDAOException e) {
            throw new CadastroException("Falha ao buscar pessoas!", e);
        }
    }

    public Pessoa buscarPessoaPorNome(String n) throws CadastroException{
        try{
            return dao.getPessoaPorNome(n);
        } catch(CadastroDAOException e) {
            throw new CadastroException("Falha ao buscar pessoa", e);
        }
    }
}
