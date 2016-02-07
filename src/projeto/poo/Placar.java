/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projeto.poo;

import java.io.BufferedWriter;
import java.io.IOException;
/**
 *
 * @author Leon
 */
public class Placar {
    private String name;
    private int ponto;

    public Placar(String name, int ponto) {
        this.name = name;
        this.ponto = ponto;
    }
    /**
     * @return the ponto
     */
    public int getPonto() {
        return ponto;
    }

    /**
     * @param ponto the ponto to set
     */
    public void setPonto(int ponto) {
        this.ponto = ponto;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    
    public void salvar(BufferedWriter writer) throws IOException {
        writer.write ("pontos;"+getName()+";"+getPonto());
    }
    

}
