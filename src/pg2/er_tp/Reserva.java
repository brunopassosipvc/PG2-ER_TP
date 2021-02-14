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
public class Reserva implements Serializable{
    private Date dataReserva;
    private Date dataCompra;
    private String estado;
    private int codigo;
    private static int counter=0;
    private int codCliente;
    private int codVeiculo;

    public Reserva(Date dataCompra, Cliente cliente, Veiculo veiculo) {
        this.dataReserva = new Date();
        this.dataCompra = dataCompra;
        this.estado = "RESERVADO";
        this.codigo = ++counter;
        this.codCliente = cliente.getCodigo();
        this.codVeiculo = veiculo.getCodigo();
    }
    
    public Reserva(Cliente cliente, Veiculo veiculo) {
        this.dataReserva = new Date();
        this.dataCompra = null;
        this.estado = "RESERVADO";
        this.codigo = ++counter;
        this.codCliente = cliente.getCodigo();
        this.codVeiculo = veiculo.getCodigo();
    }
    

    public Date getDataReserva() {
        return dataReserva;
    }

    public void setDataReserva(Date dataReserva) {
        this.dataReserva = dataReserva;
    }

    public Date getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(Date dataCompra) {
        this.dataCompra = dataCompra;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getCodigo() {
        return codigo;
    }

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        Reserva.counter = counter;
    }

    public int getCodCliente() {
        return codCliente;
    }

    public int getCodVeiculo() {
        return codVeiculo;
    }
    
    @Override
    public String toString(){
        return ("Código: " + this.getCodigo() + "\nEstado: " + this.getEstado() + 
                "\nData da reserva: " + this.getDataReserva() + "\nData marcada para compra: " + this.getDataCompra() + 
                "\nCódigo do cliente: " + this.getCodCliente() + "\nCódigo do veículo: " + this.getCodVeiculo());
    }
    
    public String getInfo(){ //não mostra codigos e estado
        return ("Código: " + this.getCodigo() +  
                "\nDataReserva: " + this.getDataReserva() + "\nDataCompra: " + this.getDataCompra());
    }
    
    public void cancelaReserva(){
        this.estado = "CANCELADA";
    }
    
    
}
