/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

/**
 *
 * @author Julio
 */
public class ListaPessoasModel implements ComboBoxModel<String> {
    private List<String> nomes;
    private String nomeSelecionado;
    private final static int FIRSTINDEX = 0;
    
    public ListaPessoasModel(){
        super();
        nomes = new ArrayList<>();
    }
    
    public ListaPessoasModel(List<String> dados){
        this();
        nomes.addAll(dados);
        if (getSize() > 0) { 
            setSelectedItem(this.nomes.get(FIRSTINDEX));
        }
    }
    
    public void add(String nome){
        nomes.add(nome);
    }
    
    @Override
    public int getSize() {
        return nomes.size();
    }

    @Override
    public String getElementAt(int index) {
        return nomes.get(index);
    }
    
    @Override
    public void setSelectedItem(Object o) {
        nomeSelecionado = (String)o;
    }

    @Override
    public Object getSelectedItem() {
        return nomeSelecionado;
    }

    @Override
    public void addListDataListener(ListDataListener ll) {
        
    }

    @Override
    public void removeListDataListener(ListDataListener ll) {
        
    }
}
