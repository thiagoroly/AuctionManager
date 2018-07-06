package negocio;

import dados.GerenciadorDAOException;
import java.util.List;

public interface GerenciadorDAO {
    boolean adicionar(Pessoa p) throws GerenciadorDAOException;
    Pessoa getPessoaPorNome(String n) throws GerenciadorDAOException;
    Pessoa getPessoaPorDocumento(String n) throws GerenciadorDAOException;
    List<Pessoa> getTodos() throws GerenciadorDAOException;
    List<Leilao> getTodosLeiloes() throws GerenciadorDAOException;
    List<String> getTodosLeiloesTitulo() throws GerenciadorDAOException;
    String getLeilaoCategoria(String leilao)throws GerenciadorDAOException;
    String getLeilaoDono(String leilao)throws GerenciadorDAOException;
    String getLeilaoLanceMin(String leilao)throws GerenciadorDAOException;
    String getLeilaoStatus(String leilao)throws GerenciadorDAOException;
    
}
