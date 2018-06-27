/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dados;

/**
 *
 * @author Júlio
 */
public class GerenciadorDAOException extends Exception {

    /**
     * Creates a new instance of
     * <code>CadastroDAOException</code> without detail message.
     */
    public GerenciadorDAOException() {
    }

    /**
     * Constructs an instance of
     * <code>CadastroDAOException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public GerenciadorDAOException(String msg) {
        super(msg);
    }

    public GerenciadorDAOException(String message, Throwable cause) {
        super(message, cause);
    }
    
    
}
