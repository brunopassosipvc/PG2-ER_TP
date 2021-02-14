/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pg2.er_tp;

import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author bruno
 */
public class Cliente extends Pessoa {
    private String username;
    private String password;
    private String email;
    private final int codigo;
    private static int counter=0;
    private ArrayList<Reserva> reservas;

    public Cliente(String nome, int telemovel, String username, String password, String email) {
        super(nome, telemovel);
        this.username = username;
        this.password = password;
        this.email = email;
        this.codigo=++counter;
        this.reservas = new ArrayList<>();
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCodigo() {
        return codigo;
    }

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        Cliente.counter = counter;
    }

    public ArrayList<Reserva> getReservas() {
        return reservas;
    }
    
    public void addReserva(Reserva r) throws Exception{
        for(Reserva rv: this.reservas){
            if( rv.getCodigo() == r.getCodigo()){
                throw new Exception("Erro: Reserva já existente.");
            }
        }
        this.reservas.add(r);
        System.out.println("Reserva adicionada com sucesso.");
    }
    
    public void removeReserva(int codigo){
        for(Reserva r: this.getReservas()){
            if(r.getCodigo() == codigo){
                this.reservas.remove(r);
                System.out.println("Reserva removida.");
                return;
            }
        }
        System.out.println("Reserva não encontrada.");
    }
    
    @Override
    public String toString(){
        return ("Nome: " + this.getNome() + "\nTelemovel: " + this.getTelemovel() + "\nUsername: " + 
                this.getUsername() + "\nPassword: " + this.getPassword() + "\nEmail: " + this.getEmail() 
                + "\nCódigo: " + this.getCodigo());
    }
    
    public String getInfo(){ //Nome, telemovel e email
        return ("Nome: " + this.getNome() + "\nTelemovel: " + this.getTelemovel() + 
                "\nUsername: " + this.getUsername() + "\nCódigo: " + this.getCodigo() );
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
                System.out.println("Introduza novo email:");
                this.setEmail(input.nextLine());
                System.out.println("Email alterado com sucesso.");
            break;
        }
    }
    
    
    
}
