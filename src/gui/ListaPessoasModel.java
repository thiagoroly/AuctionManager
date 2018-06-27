package gui;

import java.util.ArrayList;
import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;
import negocio.Pessoa;

public class ListaPessoasModel implements ComboBoxModel<String> {
    private List<Pessoa> pessoas;
    private String documentoSelecionado;
    private final static int FIRSTINDEX = 0;
    
    public ListaPessoasModel(){
        super();
        pessoas = new ArrayList<>();
    }
    
    public ListaPessoasModel(List<Pessoa> dados){
        this();
        pessoas.addAll(dados);
        if (getSize() > 0) { 
            setSelectedItem(this.pessoas.get(FIRSTINDEX));
        }
    }
    
    public void add(Pessoa pessoa){
        pessoas.add(pessoa);
    }
    
    @Override
    public int getSize() {
        return pessoas.size();
    }

    @Override
    public String getElementAt(int index) {
        return pessoas.get(index).getDocumento();
    }
    
    @Override
    public void setSelectedItem(Object o) {
        if(o instanceof Pessoa){
            documentoSelecionado = ((Pessoa)o).getDocumento();
        }
        else {
            documentoSelecionado = (String)o;
        }
    }

    @Override
    public Object getSelectedItem() {
        return documentoSelecionado;
    }

    @Override
    public void addListDataListener(ListDataListener ll) {
        
    }

    @Override
    public void removeListDataListener(ListDataListener ll) {
        
    }
}
