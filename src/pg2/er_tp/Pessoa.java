/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pg2.er_tp;

import java.io.Serializable;

/**
 *
 * @author bruno
 */
public abstract class Pessoa implements Serializable{
    private String nome;
    private int telemovel;

    public Pessoa(String nome, int telemovel) {
        this.nome = nome;
        this.telemovel = telemovel;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getTelemovel() {
        return telemovel;
    }

    public void setTelemovel(int telemovel) {
        this.telemovel = telemovel;
    }
    
    
}
