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
public class Veiculo implements Serializable{
    private String marca;
    private String modelo;
    private String tipo;
    private String cor;
    private String matricula;
    private String estado;
    private final int codigo;
    private static int counter=0;
    private double preco;
    

    public Veiculo(String marca, String modelo, String tipo, String cor, String matricula, String estado, double preco) {
        this.marca = marca;
        this.modelo = modelo;
        this.tipo = tipo;
        this.cor = cor;
        this.matricula = (matricula);
        this.estado = estado;
        this.preco = preco;
        this.codigo = ++counter;
    }
    
    public Veiculo(String marca, String modelo, String tipo, String cor, String matricula, double preco) {
        this.marca = marca;
        this.modelo = modelo;
        this.tipo = tipo;
        this.cor = cor;
        this.matricula = (matricula);
        this.estado = "DISPONIVEL";
        this.preco = preco;
        this.codigo = ++counter;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getCodigo() {
        return codigo;
    }

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        Veiculo.counter = counter;
    }
    
    @Override
    public String toString(){
        return ("Marca: " + this.getMarca() + "\nModelo: " + this.getModelo() + "\nTipo: " + this.getTipo() + 
                "\nCor: " + this.getCor() + "\nMatrícula: " + this.getMatricula() + "\nEstado: " + this.getEstado() + 
                 "\nPreço: " + this.getPreco() + "\nCódigo: " + this.getCodigo());
    }
    
    public String getInfo(){ //Não mostra matrícula
        return ("Marca: " + this.getMarca() + "\nModelo: " + this.getModelo() + "\nTipo: " + this.getTipo() + 
                "\nCor: " + this.getCor() + "\nEstado: " + this.getEstado() + 
                 "\nPreço: €" + this.getPreco() + "\nCódigo: " + this.getCodigo());
    }
    
    public Venda comprarVeiculo(Cliente c){
        Venda v = new Venda(c, this);
        this.estado = "VENDIDO";
        return v;
    }
    
    public void editarInfo(){
        System.out.println("O que quer editar?");
        System.out.println("1 - Marca");
        System.out.println("2 - Modelo");
        System.out.println("3 - Tipo");
        System.out.println("4 - Cor");
        System.out.println("5 - Matrícula");
        System.out.println("6 - Estado");
        System.out.println("7 - Preço");
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
                System.out.println("Introduza novo marca:");
                this.setMarca(input.nextLine());
                System.out.println("Marca alterada com sucesso.");
            break;
            case 2:
                System.out.println("Introduza novo modelo:");
                this.setModelo(input.nextLine());
                System.out.println("Modelo alterado com sucesso.");
            break;
            case 3:
                System.out.println("Introduza novo tipo:");
                this.setTipo(input.nextLine());
                System.out.println("Tipo alterado com sucesso.");
            break;
            case 4:
                System.out.println("Introduza nova cor:");
                this.setCor(input.nextLine());
                System.out.println("Cor alterada com sucesso.");
            break;
            case 5:
                System.out.println("Introduza novo matrícula:");
                this.setMatricula(input.nextLine());
                System.out.println("Matrícula alterado com sucesso.");
            break;
            case 6:
                System.out.println("Introduza novo estado:");
                this.setEstado(input.nextLine());
                System.out.println("Estado alterado com sucesso.");
            break;
            case 7:
                System.out.println("Introduza novo preço:");
                this.setPreco(parseDouble(input.nextLine()));
                System.out.println("Preço alterado com sucesso.");
            break;
        }
    }
    
    public void desativarVeiculo(){
        this.estado = "DESATIVADO";
    }
    
    public void ativarVeiculo(){
        this.estado = "DISPONIVEL";
    }
    
    /*
    protected static String obterMatriculaFormatada(String matricula) throws IllegalArgumentException {
        // colocar tudo em maísculas
        String matriculaUC = matricula.toUpperCase();

        if(matriculaUC.matches("[A-Z0-9]{6}")) {
            // processar entrada sem traços
            return String.format("%c%c-%c%c-%c%c",
                    matriculaUC.charAt(0),
                    matriculaUC.charAt(1),
                    matriculaUC.charAt(2),
                    matriculaUC.charAt(3),
                    matriculaUC.charAt(4),
                    matriculaUC.charAt(5)
                    );
        } else if(matriculaUC.matches("[A-Z0-9]{2}-[A-Z0-9]{2}-[A-Z0-9]{2}")) {
            // processar entrada com traços
            return matriculaUC;
        }
        throw new IllegalArgumentException(String.format("Matrícula inválida: \"%s\"", matricula));
    }
*/
    
    
    
}
