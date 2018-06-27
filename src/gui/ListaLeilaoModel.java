package gui;

import java.util.ArrayList;
import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;
import negocio.Leilao;

public class ListaLeilaoModel  implements ComboBoxModel<String> {
    private List<Leilao> leiloes;
    private String leilaoSelecionado;
    private final static int FIRSTINDEX = 0;

    @Override
    public void setSelectedItem(Object o) {
        if(o instanceof Leilao){
            leilaoSelecionado = ((Leilao)o).getTitulo();
        }
        else {
            leilaoSelecionado = (String)o;
        }
    }
    
    public ListaLeilaoModel(){
        super();
        leiloes = new ArrayList<>();
    }
    
    public ListaLeilaoModel(List<Leilao> dados){
        this();
        leiloes.addAll(dados);
        if (getSize() > 0) { 
            setSelectedItem(this.leiloes.get(FIRSTINDEX));
        }
    }
    
    public void add(Leilao pessoa){
        leiloes.add(pessoa);
    }

    @Override
    public Object getSelectedItem() {
        return leilaoSelecionado;
    }

    @Override
    public int getSize() {
        return leiloes.size();
    }

    @Override
    public String getElementAt(int index) {
        return leiloes.get(index).getTitulo();
    }

    @Override
    public void addListDataListener(ListDataListener ll) {

    }

    @Override
    public void removeListDataListener(ListDataListener ll) {
        
    }
    
}
