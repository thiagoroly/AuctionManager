package negocio;

public class Bem {
    private String id;
    private String nome;
    private String desc_simples;
    private String desc_completa;

    public Bem(String id, String nome, String desc_simples, String desc_completa) {
        this.id = id;
        this.nome = nome;
        this.desc_simples = desc_simples;
        this.desc_completa = desc_completa;
    }
    
    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDesc_simples() {
        return desc_simples;
    }

    public String getDesc_completa() {
        return desc_completa;
    }
}
