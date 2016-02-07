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
public class Parametro {
    private float velocidadeQueda;
    private float capacidadeBalde;
    private float nGotasCiclo;
    private float nGotasLetais;
    private float proporcao;

    public Parametro(float velocidadeQueda, float capacidadeBalde, float nGotasCiclo, float nGotasLetais, float proporcao) {
        this.velocidadeQueda = velocidadeQueda;
        this.capacidadeBalde = capacidadeBalde;
        this.nGotasCiclo = nGotasCiclo;
        this.nGotasLetais = nGotasLetais;
        this.proporcao = proporcao;
    }
    
    /**
     * @return the velocidadeQueda
     */
    public float getVelocidadeQueda() {
        return velocidadeQueda;
    }

    /**
     * @param velocidadeQueda the velocidadeQueda to set
     */
    public void setVelocidadeQueda(float velocidadeQueda) {
        this.velocidadeQueda = velocidadeQueda;
    }

    /**
     * @return the capacidadeBalde
     */
    public float getCapacidadeBalde() {
        return capacidadeBalde;
    }

    /**
     * @param capacidadeBalde the capacidadeBalde to set
     */
    public void setCapacidadeBalde(float capacidadeBalde) {
        this.capacidadeBalde = capacidadeBalde;
    }

    /**
     * @return the nGotasCiclo
     */
    public float getnGotasCiclo() {
        return nGotasCiclo;
    }

    /**
     * @param nGotasCiclo the nGotasCiclo to set
     */
    public void setnGotasCiclo(float nGotasCiclo) {
        this.nGotasCiclo = nGotasCiclo;
    }

    /**
     * @return the nGotasLetais
     */
    public float getnGotasLetais() {
        return nGotasLetais;
    }

    /**
     * @param nGotasLetais the nGotasLetais to set
     */
    public void setnGotasLetais(float nGotasLetais) {
        this.nGotasLetais = nGotasLetais;
    }

    /**
     * @return the proporcao
     */
    public float getProporcao() {
        return proporcao;
    }

    /**
     * @param proporcao the proporcao to set
     */
    public void setProporcao(float proporcao) {
        this.proporcao = proporcao;
    }
}
