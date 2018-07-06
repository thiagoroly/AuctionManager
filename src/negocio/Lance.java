package negocio;

public class Lance {
    private String id;
    private Double valor;
    private String pessoa_documento;

    public Lance(String id, Double valor, String pessoa_documento) {
        this.id = id;
        this.valor = valor;
        this.pessoa_documento = pessoa_documento;
    }

    public String getId() {
        return id;
    }
    
    public Double getValor() {
        return valor;
    }

    public String getPessoa_documento() {
        return pessoa_documento;
    }
}
