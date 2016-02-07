/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projeto.poo;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 *
 * @author Leon
 */
public class Placares extends JFrame{
    private Color corChocolate = new Color(210 ,105 ,30); 
    class Pontos {
        String texto;
        public Pontos(String texto) {
            this.texto = texto;
        }
        public String toString() {
            return texto;
        }
    }

    class MeuRenderer implements ListCellRenderer<Pontos> {
        public Component getListCellRendererComponent(JList<? extends Pontos> list, Pontos value, int index, boolean isSelected, boolean cellHasFocus) {
            JLabel component = new JLabel();
            component.setText(value.texto);
            component.setOpaque(true);
            component.setBackground(corChocolate);
            component.setForeground(Color.white);
            
            return component;
        }
    }

    public Placares(ArrayList<Placar> placar ) {
        Container raiz = getContentPane();
        Vector<Pontos> itens = new Vector<>();
        int a = 1;
        
        for(int i = 0; i< placar.size() ;i++){
             Placar p = placar.get(i);
             itens.add(new Pontos(" "+  a + "  =>  "+ p.getName() + " : " + p.getPonto() + " pontos "));
             a++;
        } 
        JList<Pontos> list = new JList<>(itens);
        list.setCellRenderer(new MeuRenderer());
        raiz.add(list);
        list.setBackground(corChocolate);
        
        
        setTitle("10 Melhores placares");
        setSize(300, 200);
        setVisible(true);    
    }
}
