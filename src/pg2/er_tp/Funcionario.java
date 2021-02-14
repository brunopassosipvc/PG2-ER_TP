/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pg2.er_tp;

import java.io.Serializable;
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import java.util.Scanner;

/**
 *
 * @author bruno
 */
public class Funcionario extends Pessoa implements Serializable{
    private String turnoComeco;
    private String turnoFim;
    private double salario;
    private String username;
    private String password;
    private final int codigo;
    private static int counter=0;

    public Funcionario(String nome, int telemovel, String username, String password, String turnoComeco, String turnoFim, double salario) {
        super(nome, telemovel);
        this.turnoComeco = turnoComeco;
        this.turnoFim = turnoFim;
        this.salario = salario;
        this.username = username;
        this.password = password;
        this.codigo = ++counter;
    }

    public Funcionario(String nome, int telemovel, String turnoComeco, String turnoFim, double salario) {
        super(nome, telemovel);
        this.turnoComeco = turnoComeco;
        this.turnoFim = turnoFim;
        this.salario = salario;
        this.username = null;
        this.password = null;
        this.codigo = ++counter;
    }

    public String getTurnoComeco() {
        return turnoComeco;
    }

    public void setTurnoComeco(String turnoComeco) {
        this.turnoComeco = turnoComeco;
    }

    public String getTurnoFim() {
        return turnoFim;
    }

    public void setTurnoFim(String turnoFim) {
        this.turnoFim = turnoFim;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
    
    public String getUsername() {
    return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getCodigo() {
        return codigo;
    }

    public static int getCounter() {
        return counter;
    }
    
    public void editarPerfil(){
        System.out.println("O que quer editar?");
        System.out.println("1 - Nome");
        System.out.println("2 - Telemovel");
        System.out.println("3 - Username");
        System.out.println("4 - Password");
        System.out.println("5 - Email");
        System.out.println("O código não é alterável.");
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
                System.out.println("Introduza novo telemovel:");
                this.setTelemovel(parseInt(input.nextLine()));
                System.out.println("Telemovel alterado com sucesso.");
            break;
            case 3:
                System.out.println("Introduza novo username:");
                this.setUsername(input.nextLine());
                System.out.println("Username alterado com sucesso.");
            break;
            case 4:
                System.out.println("Introduza nova password:");
                this.setPassword(input.nextLine());
                System.out.println("Password alterada com sucesso.");
            break;
            case 5:
                System.out.println("Introduza novo começo de turno:");
                this.setTurnoComeco(input.nextLine());
                System.out.println("Começo de turno alterado com sucesso.");
            break;
            case 6:
                System.out.println("Introduza novo fim de turno:");
                this.setTurnoFim(input.nextLine());
                System.out.println("Fim de turno alterado com sucesso.");
            break;
            case 7:
                System.out.println("Introduza novo salário:");
                this.setSalario(parseDouble(input.nextLine()));
                System.out.println("Salário alterado com sucesso.");
            break;
        }
    }
    
    @Override
    public String toString(){
        return ("Nome: " + this.getNome() + "\nTelemóvel: " + this.getTelemovel() + 
                "\nUsername: " + this.getUsername() + "\nPassword: " + this.getPassword() + 
                "\nComeço de turno: " + this.turnoComeco + "\nFim de turno: " + this.getTurnoFim() +
                "\nSalário: " + this.getSalario() + "\nCódigo: " + this.getCodigo() );
    }
    
    public String getInfo(){ //Sem password, código e salário
        return ("Nome: " + this.getNome() + "\nTelemóvel: " + this.getTelemovel() + 
                "\nUsername: " + this.getUsername() +  
                "\nComeço de turno: " + this.turnoComeco + "\nFim de turno: " + this.getTurnoFim() + "\n" );
    }
    
}
