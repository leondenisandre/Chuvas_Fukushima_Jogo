/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projeto.poo;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

/**
 *
 * @author Leon
 */
public class FrameParametro extends JDialog{
        public static final boolean OK = true;
        public static final boolean CANCELAR = false;
        JTextField gotasC = new JTextField();
        JTextField velocQueda = new JTextField();
        JTextField capacBalde = new JTextField();
        JTextField numGotasLetais = new JTextField();
        JTextField proporcaoTf = new JTextField();
     
        public FrameParametro(Parametro param,Timer timer){
                timer.stop();
                Container raiz = getContentPane();
                raiz.setLayout(new GridLayout(6, 1));
                raiz.add(new JLabel ("  Velocidade da bola:  "));
                raiz.add(velocQueda);
                raiz.add(new JLabel ("  Capacidade do Balde:  "));
                raiz.add(capacBalde);
                raiz.add(new JLabel ("  Gotas por Ciclo:  "));
                raiz.add(gotasC);
                raiz.add(new JLabel ("  Nº gotas limpas letais:  "));
                raiz.add(numGotasLetais);
                raiz.add(new JLabel ("  Proporção Média de gotas vermelhas:  "));
                raiz.add(proporcaoTf);
                JButton bOk = new JButton("Ok");
                bOk.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        try{
                        int vC = Integer.parseInt(velocQueda.getText());
                        int cB = Integer.parseInt(capacBalde.getText());
                        int gC = Integer.parseInt(gotasC.getText());
                        int nGC = Integer.parseInt(numGotasLetais.getText());
                        int propor = Integer.parseInt(proporcaoTf.getText());
                        param.setVelocidadeQueda(vC);
                        param.setCapacidadeBalde(cB);
                        param.setnGotasCiclo(gC);
                        param.setnGotasLetais(nGC);
                        param.setProporcao(propor);
                        setVisible(false);
                        timer.setDelay(vC);
                        timer.start();}
                        catch(NumberFormatException E){
                            System.out.println("erooooo");
                        }
                    }

                });
                JButton bCancelar = new JButton("Cancelar");
                bCancelar.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        setVisible(false);
                        timer.start();
                    }
                });
                
                raiz.add(bOk);
                raiz.add(bCancelar);
                pack();
                bOk.setBackground(new Color(0,205,0));
                bCancelar.setBackground(new Color(205,0,0));
                setTitle("Parâmetros");
                setVisible(true);
                setModal(true);
        }


}
