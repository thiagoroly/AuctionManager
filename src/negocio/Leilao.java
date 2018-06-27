package negocio;

public class Leilao {
    private String titulo;
    private String categoria;
    private Object lote;
    private Pessoa dono;
    private Double precoMin;
    private String status;
    private Object lances;

    public Leilao(String titulo, String categoria, Object lote, Pessoa dono, Double precoMin, String status, Object lances) {
        this.titulo = titulo;
        this.categoria = categoria;
        this.lote = lote;
        this.dono = dono;
        this.precoMin = precoMin;
        this.status = status;
        this.lances = lances;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getCategoria() {
        return categoria;
    }

    public Object getLote() {
        return lote;
    }

    public Pessoa getDono() {
        return dono;
    }

    public Double getPrecoMin() {
        return precoMin;
    }

    public String getStatus() {
        return status;
    }

    public Object getLances() {
        return lances;
    }
    
    
}
