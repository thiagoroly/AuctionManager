package negocio;

import dados.GerenciadorDAOException;
import dados.GerenciadorDAOJavaDb;
import java.util.List;

public class GerenciadorFachada {
    private GerenciadorDAO dao;
    
    public GerenciadorFachada() throws GerenciadorException {
        try {
            dao = GerenciadorDAOJavaDb.getInstance();
        } catch (GerenciadorDAOException e) {
            throw new GerenciadorException("Falha de criação da fachada!", e);
        }
    }
    
    public Pessoa adicionarPessoa(String nome, String email, String documento) throws GerenciadorException{
        if(!ValidadorPessoa.validaNome(nome)) {
            throw new GerenciadorException("Nome de pessoa inválido!");
        }
        if(!ValidadorPessoa.validaEmail(email)) {
            throw new GerenciadorException("Endereço de e-mail inválido!");
        }
        if(!ValidadorPessoa.validaDocumento(documento)) {
            throw new GerenciadorException("Número de documento inválido!");
        }
        Pessoa p = new Pessoa(nome, email, documento);
        try {
            boolean ok = dao.adicionar(p);
            if(ok) {
                return p;
            }
            return null;
        } catch (GerenciadorDAOException e) {
            throw new GerenciadorException("Falha ao adicionar pessoa!", e);
        }
    }

    public List<Pessoa> buscarTodasPessoas() throws GerenciadorException{
        try {
            return dao.getTodos();
        } catch (GerenciadorDAOException e) {
            throw new GerenciadorException("Falha ao buscar pessoas!", e);
        }
    }

    public Pessoa buscarPessoaPorNome(String nome) throws GerenciadorException{
        try{
            return dao.getPessoaPorNome(nome);
        } catch(GerenciadorDAOException e) {
            throw new GerenciadorException("Falha ao buscar pessoa", e);
        }
    }
    
    public Pessoa buscarPessoaPorDocumento(String documento) throws GerenciadorException{
        try{
            return dao.getPessoaPorDocumento(documento);
        } catch(GerenciadorDAOException e) {
            throw new GerenciadorException("Falha ao buscar pessoa", e);
        }
    }
    
    public List<Leilao> buscarTodosLeiloes() throws GerenciadorException{
        try {
            return dao.getTodosLeiloes();
        } catch (GerenciadorDAOException e) {
            throw new GerenciadorException("Falha ao buscar leilões!", e);
        }
    }
    
}
