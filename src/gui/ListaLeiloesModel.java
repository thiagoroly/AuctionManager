package gui;

import java.util.ArrayList;
import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;
import negocio.Leilao;

public class ListaLeiloesModel  implements ComboBoxModel<String> {
    private List<String> leiloes;
    
    private String leilaoSelecionado;
    private final static int FIRSTINDEX = 0;

    @Override
    public void setSelectedItem(Object o) {
        if(o instanceof Leilao){
            leilaoSelecionado = ((Leilao)o).getId();
        }
        else {
            leilaoSelecionado = (String)o;
        }
    }
    
    public ListaLeiloesModel(){
        super();
        leiloes = new ArrayList<>();
    }
    
    public ListaLeiloesModel(List<String> dados){
        this();
        leiloes.addAll(dados);
        if (getSize() > 0) { 
            setSelectedItem(this.leiloes.get(FIRSTINDEX));
        }
    }
    
    public void add(String leilao){
        leiloes.add(leilao);
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
        return leiloes.get(index);
    }

    @Override
    public void addListDataListener(ListDataListener ll) {

    }

    @Override
    public void removeListDataListener(ListDataListener ll) {
        
    }
    
}
