package negocio;

import java.util.ArrayList;

public class Leilao {
    private String id;
    private String titulo;
    private String categoria;
    private ArrayList<Bem> lote;
    private String dono;
    private Double precoMin;
    private String status;
    private ArrayList<Lance> lances;

    public Leilao(String id, String titulo, String categoria, ArrayList<Bem> lote, String dono, Double precoMin, String status, ArrayList<Lance> lances) {
        this.id = id;
        this.titulo = titulo;
        this.categoria = categoria;
        this.lote = lote;
        this.dono = dono;
        this.precoMin = precoMin;
        this.status = status;
        this.lances = lances;
    }
    
    public Leilao(String id, String titulo, String categoria, String dono, Double precoMin, String status) {
        this.id = id;
        this.titulo = titulo;
        this.categoria = categoria;
        this.dono = dono;
        this.precoMin = precoMin;
        this.status = status;
    }

    public void setLote(ArrayList<Bem> lote) {
        this.lote = lote;
    }

    public void setLances(ArrayList<Lance> lances) {
        this.lances = lances;
    }
    
    public String getId() {
        return id;
    }
    
    public String getTitulo() {
        return titulo;
    }

    public String getCategoria() {
        return categoria;
    }

    public ArrayList<Bem> getLote() {
        return lote;
    }

    public String getDono() {
        return dono;
    }

    public Double getPrecoMin() {
        return precoMin;
    }

    public String getStatus() {
        return status;
    }

    public ArrayList<Lance> getLances() {
        return lances;
    }
}
