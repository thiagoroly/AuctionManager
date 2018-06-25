/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package negocio;

/**
 *
 * @author Julio
 */
public class ValidadorPessoa {
    public static boolean validaNome(String nome) {
        return nome.contains(" ");
    }
    public static boolean validaEmail(String email) {
        return (email.contains("@"));
    }
    public static boolean validaDocumento(String documento) {
        return (documento.length() == 11);
    }
}
