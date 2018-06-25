package negocio;

public class Pessoa {

    private String nome;
    private String email;
    private String documento;

    public Pessoa(String umNome, String umTelefone, String umDocumento) {
        this.nome = umNome;
        this.email = umTelefone;
        this.documento = umDocumento;
    }

    public String getNome() {
        return this.nome;
    }

    public String getEmail() {
        return this.email;
    }
    
    public String getDocumento() {
        return this.documento;
    }

    @Override
    public String toString() {
        return "[" + nome + "," + email + "," + documento + "]";
    }
}
