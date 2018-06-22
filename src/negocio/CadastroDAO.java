/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import dados.CadastroDAOException;
import java.util.List;

/**
 *
 * @author Julio
 */
public interface CadastroDAO {
    boolean adicionar(Pessoa p) throws CadastroDAOException;
    Pessoa getPessoaPorNome(String n) throws CadastroDAOException;
    List<Pessoa> getHomens() throws CadastroDAOException;
    List<Pessoa> getMulheres() throws CadastroDAOException;
    List<Pessoa> getTodos() throws CadastroDAOException;
}
