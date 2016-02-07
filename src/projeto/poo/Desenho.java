/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projeto.poo;

/**
 *
 * @author Leon
 */
public class Desenho {
    private int posicaoX;
    private int posicaoY;
    private int largura;
    private int altura;
    private Double cor;

    public Desenho(int posicaoX, int posicaoY, int largura, int altura,double cor) {
        this.posicaoX = posicaoX;
        this.posicaoY = posicaoY;
        this.largura = largura;
        this.altura = altura;
        this.cor = cor;
    }

    /**
     * @return the posicaoX
     */
    public int getPosicaoX() {
        return posicaoX;
    }

    /**
     * @param posicaoX the posicaoX to set
     */
    public void setPosicaoX(int posicaoX) {
        this.posicaoX = posicaoX;
    }

    /**
     * @return the posicaoY
     */
    public int getPosicaoY() {
        return posicaoY;
    }

    /**
     * @param posicaoY the posicaoY to set
     */
    public void setPosicaoY(int posicaoY) {
        this.posicaoY = posicaoY;
    }

    /**
     * @return the largura
     */
    public int getLargura() {
        return largura;
    }

    /**
     * @param largura the largura to set
     */
    public void setLargura(int largura) {
        this.largura = largura;
    }

    /**
     * @return the altura
     */
    public int getAltura() {
        return altura;
    }

    /**
     * @param altura the altura to set
     */
    public void setAltura(int altura) {
        this.altura = altura;
    }

    /**
     * @return the cor
     */
    public Double getCor() {
        return cor;
    }

    /**
     * @param cor the cor to set
     */
    public void setCor(Double cor) {
        this.cor = cor;
    }
    
}
