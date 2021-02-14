/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pg2.er_tp;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author bruno
 */
public class Stand implements Serializable{
    private String nome;
    private String morada;
    private int telefone;
    private String dataAbertura;
    private String dataFecho;
    ArrayList<Cliente> clientes;
    ArrayList<Funcionario> funcionarios;
    ArrayList<Veiculo> veiculos;
    ArrayList<Venda> vendas;
    private static final long serialVersionUID = 6529685098267757690L;

    public Stand(String nome, String morada, int telefone, String dataAbertura, String dataFecho) {
        this.nome = nome;
        this.morada = morada;
        this.telefone = telefone;
        this.dataAbertura = dataAbertura;
        this.dataFecho = dataFecho;
        this.clientes = new ArrayList<>();
        this.funcionarios = new ArrayList<>();
        this.veiculos = new ArrayList<>();
        this.vendas = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public String getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(String dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public String getDataFecho() {
        return dataFecho;
    }

    public void setDataFecho(String dataFecho) {
        this.dataFecho = dataFecho;
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }

    public ArrayList<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(ArrayList<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public ArrayList<Veiculo> getVeiculos() {
        return veiculos;
    }

    public void setVeiculos(ArrayList<Veiculo> veiculos) {
        this.veiculos = veiculos;
    }

    public ArrayList<Venda> getVendas() {
        return vendas;
    }

    public void setVendas(ArrayList<Venda> vendas) {
        this.vendas = vendas;
    }

    public void addCliente(Cliente c){
        this.clientes.add(c);
    }
    
    public void addFuncionario(Funcionario f){
        this.funcionarios.add(f);
    }
    
    public void addVeiculo(Veiculo v){
        this.veiculos.add(v);
    }
    
    public void addVenda(Venda v){
        this.vendas.add(v);
    }
    
    public void editarStand(){
        System.out.println("O que quer editar?");
        System.out.println("1 - Nome");
        System.out.println("2 - Morada");
        System.out.println("3 - Telefone");
        System.out.println("4 - Data de abertura");
        System.out.println("5 - Data de fecho");
        System.out.println("\n0 - Sair");
        Scanner input = new Scanner(System.in);
        int opcao=0;
        opcao = input.nextInt();
        input.nextLine();
        switch(opcao){
            default:
                System.out.println("Opção inválida");
            break;
            case 0:
            break;
            case 1:
                System.out.println("Introduza novo nome:"); 
                this.setNome(input.nextLine());
                System.out.println("Nome alterado com sucesso.");
            break;
            case 2:
                System.out.println("Introduza nova morada:");
                this.setMorada(input.nextLine());
                System.out.println("Morada alterada com sucesso.");
            break;
            case 3:
                System.out.println("Introduza novo telefone:");
                this.setTelefone(parseInt(input.nextLine()));
                System.out.println("Telefone alterado com sucesso.");
            break;
            case 4:
                System.out.println("Introduza nova data de abertura:");
                this.setDataAbertura(input.nextLine());
                System.out.println("Data de abertura alterada com sucesso.");
            break;
            case 5:
                System.out.println("Introduza nova data de fecho:");
                this.setDataFecho(input.nextLine());
                System.out.println("Data de fecho alterada com sucesso.");
            break;
        }
    }
    
    public String getInfo(){
        return ("Nome: " + this.getNome() + "\nMorada: " + this.getMorada() + "\nTelefone: " + this.getTelefone() +
                "\nData de Abertura: " + this.getDataAbertura() + "\nData de fecho: " + this.getDataFecho());
    }
    
    public void salvarFicheiro(String path) throws IOException{
        try (FileOutputStream file = new FileOutputStream(path)) {
            ObjectOutputStream obj = new ObjectOutputStream(file);
            obj.writeObject(this);
            obj.close();
            file.close();
            System.out.println("Gravado com sucesso.");
        }catch(Exception e){
            System.out.println("Erro ao gravar.\n");
            e.printStackTrace();
        }
    }
    
    public Stand(String path){ //Carregar de ficheiro
        try (FileInputStream file = new FileInputStream(path)) {
            ObjectInputStream obj = new ObjectInputStream(file);
            Stand s = (Stand) obj.readObject();
            obj.close();
            file.close();
            this.nome = s.nome;
            this.morada = s.morada;
            this.telefone = s.telefone;
            this.dataAbertura = s.dataAbertura;
            this.dataFecho = s.dataFecho;
            this.funcionarios = s.funcionarios;
            this.veiculos = s.veiculos;
            this.clientes = s.clientes;
            this.vendas = s.vendas;            
            System.out.println("Carregado com sucesso.");
        }catch(Exception e){
            System.out.println("Erro ao carregar.\n");
            e.printStackTrace();
        }
    }
    
        
    
    
    
}
