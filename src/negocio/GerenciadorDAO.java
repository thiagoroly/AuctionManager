package negocio;

import dados.GerenciadorDAOException;
import java.util.List;

public interface GerenciadorDAO {
    boolean adicionar(Pessoa p) throws GerenciadorDAOException;
    Pessoa getPessoaPorNome(String n) throws GerenciadorDAOException;
    Pessoa getPessoaPorDocumento(String n) throws GerenciadorDAOException;
    List<Pessoa> getTodos() throws GerenciadorDAOException;
    List<Leilao> getTodosLeiloes() throws GerenciadorDAOException;
    String getLeilaoTitulo(String leilaoId) throws GerenciadorDAOException;
    
}
