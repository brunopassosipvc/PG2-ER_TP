/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pg2.er_tp;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author bruno
 */
public class Venda implements Serializable{
    private Date dataVenda;
    private static final double imposto=0.23;
    private double precoTotal;
    private String estado;
    private final int codigo;
    private static int counter=0;
    private int codCliente;
    private int codVeiculo;

    public Venda(Cliente cliente, Veiculo veiculo) {
        this.dataVenda = new Date();
        this.precoTotal = ((veiculo.getPreco() * imposto) + veiculo.getPreco());
        this.estado = "VENDIDO";
        this.codCliente = cliente.getCodigo();
        this.codVeiculo = veiculo.getCodigo();
        this.codigo = ++counter;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    public double getPrecoTotal() {
        return precoTotal;
    }

    public void setPrecoTotal(double precoTotal) {
        this.precoTotal = precoTotal;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(int codCliente) {
        this.codCliente = codCliente;
    }

    public int getCodVeiculo() {
        return codVeiculo;
    }

    public void setCodVeiculo(int codVeiculo) {
        this.codVeiculo = codVeiculo;
    }

    public static double getImposto() {
        return imposto;
    }

    public int getCodigo() {
        return codigo;
    }

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        Venda.counter = counter;
    }
    
    @Override
    public String toString(){
        return ("Data: " + this.getDataVenda() + "\nEstado: " + this.getEstado() + 
                "\nPreço Total: €" + this.getPrecoTotal() + "\nImposto: " + this.getImposto() + "%" + 
                "\nCódigo do cliente: " + this.getCodCliente() + "\nCódigo do Veículo: " + this.getCodVeiculo());
    }
    
    public String getInfo(){ //Data e preço total
        return ("Data: " + this.getDataVenda() + "\nPreço Total: €" + this.getPrecoTotal() );
    }
    
    
    
    
}
