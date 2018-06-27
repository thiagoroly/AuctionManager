package negocio;

public class GerenciadorException extends Exception {

    public GerenciadorException() {
    }

    public GerenciadorException(String msg) {
        super(msg);
    }

    public GerenciadorException(String message, Throwable cause) {
        super(message, cause);
    }
}
