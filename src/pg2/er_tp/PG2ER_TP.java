/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pg2.er_tp;

import java.io.IOException;
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 * @author bruno
 */
public class PG2ER_TP {

    /**
     * @param args the command line arguments
     */
    
    private static Stand stand;
    
    private static boolean suc;
    private static int cs;
    private static int ts;
    
    public static void main(String[] args) throws Exception {
        carregar();
        menuInicial();
        menu2();
        salvarFicheiro();
    }
    
    public static void carregar(){
        System.out.println("A carregar...");
        stand = new Stand("C:\\Users\\bruno\\Desktop\\PG2-ER\\Trabalho\\PG2-ER_TP\\files\\stand");
        suc = false;
        cs = 0;
        ts = 0;
        int i=0;
        Veiculo.setCounter(stand.getVeiculos().size());
        Cliente.setCounter(stand.getClientes().size());
        Venda.setCounter(stand.getVendas().size());
        for(Cliente c: stand.clientes){
            for(Reserva r: c.getReservas()){
                i++;
            }
        }
        Reserva.setCounter(i);
    }
    
    public static void salvarFicheiro() throws IOException{
        stand.salvarFicheiro("C:\\Users\\bruno\\Desktop\\PG2-ER\\Trabalho\\PG2-ER_TP\\files\\stand");
    }
    
    public static void menuInicial() throws Exception{
        Scanner input = new Scanner(System.in);
        int opcao=0;
        do{
            System.out.println("GESTOR DE STANDS");
            System.out.println("Stand atual: " + stand.getNome());
            System.out.println("\n----MENU----\n");
            System.out.println("1 - Entrar como cliente");
            System.out.println("2 - Entrar como funcionario");
            System.out.println("3 - Informação sobre o stand");
            System.out.println("\n0- Sair");
            opcao = parseInt(input.nextLine());
            
            switch(opcao){                
                default:
                    System.out.println("Opção inválida.");
                break;
                
                case 0:
                    System.exit(0);
                break;
                
                case 1: //CLIENTE
                    System.out.println("--CLIENTE--\n");
                    System.out.println("1 - Login");
                    System.out.println("2 - Registar");
                    System.out.println("0 - Sair");
                    opcao = parseInt(input.nextLine());
                    switch(opcao){
                        case 0:
                        break;
                        
                        case 1:
                            loginCliente();
                        break;
                        
                        case 2:
                            registarCliente();
                        break;
                        
                        default:
                            System.out.println("Opcao Invalida.\n");
                        break;
                    }
                break;
                
                case 2: //FUNCIONARIO
                    System.out.println("--FUNCIONARIO--\n");
                    System.out.println("1 - Login");
                    System.out.println("0 - Sair");
                    opcao = parseInt(input.nextLine());
                    switch(opcao){
                        default:
                            System.out.println("Opção inválida.");
                        break;
                        
                        case 0:
                        break;
                        
                        case 1:
                            loginFuncionario();
                        break;
                    }
                break;
                
                case 3:
                    System.out.println("----STAND----");
                    System.out.println(stand.getInfo());
                    input.nextLine();
                break;
                    
            }
            
            
        }while( suc != true);
    }
    
    public static void menu2() throws Exception{
        switch(ts){
            default:
                throw new Exception("Erro no login.");
            case 1:
                menuCliente();
            break;
            
            case 2:
            case 3:
                menuFuncionarioInicial();
            break;
        }
    }
    
    public static void loginCliente() throws Exception{
        Scanner input = new Scanner(System.in);
        String username, password;
        System.out.println("--LOGIN CLIENTE--\n");
        System.out.println("Introduza o username:");
        username = input.nextLine();
        System.out.println("Introduza a password:");
        password = input.nextLine();
        
        for(Cliente c: stand.getClientes()){
            if(username.equals(c.getUsername())){
                if(password.equals(c.getPassword())){
                    System.out.println("Login feito com sucesso.\n");
                    cs = c.getCodigo();
                    ts = 1;
                    suc = true;
                    return;
                }
                else{
                    suc = false;
                     throw new Exception("Password não corresponde ao username.\n");
                }
            }
        }
        suc = false;
        throw new Exception("Username não encontrado.\n");
        
    }
    
    public static void registarCliente() throws Exception{
        Scanner input = new Scanner(System.in);
        String username, password, nome, email; int telemovel;
        System.out.println("--REGISTAR CLIENTE--\n");
        System.out.println("Introduza o nome:");
        nome = input.nextLine();
        System.out.println("Introduza o username:");
        username = input.nextLine();
        System.out.println("Introduza a password:");
        password = input.nextLine();
        System.out.println("Introduza o email:");
        email = input.nextLine();
        System.out.println("Introduza o telemovel:");
        telemovel = parseInt(input.nextLine());
        
        for(Cliente ca: stand.getClientes()){
            if(ca.getUsername().equals(username)){
                throw new Exception("Username já em uso.");
            }
            if(ca.getEmail().equals(email)){
                throw new Exception("Email já em uso.");
            }
        }
        
        Cliente c = new Cliente(nome, telemovel, username, password, email);
        stand.addCliente(c);
        salvarFicheiro();
        System.out.println("Cliente adicionado com sucesso.\n");
    }
    
    public static void loginFuncionario() throws Exception{
        Scanner input = new Scanner(System.in);
        String username, password;
        System.out.println("--LOGIN FUNCIONARIO--\n");
        System.out.println("Introduza o username:");
        username = input.nextLine();
        System.out.println("Introduza a password:");
        password = input.nextLine();
        
        for(Funcionario f: stand.getFuncionarios() ){
            if(username.equals(f.getUsername())){
                if(password.equals(f.getPassword())){
                    System.out.println("Login feito com sucesso.\n");
                    cs = f.getCodigo();
                    suc = true;
                    if(f instanceof Dono){
                        ts = 3;
                    } else { 
                        ts = 2; 
                    }
                    return;
                }
                else{
                    suc = false;
                    throw new Exception("Password não corresponde ao username.\n");
                }
            }
        }
        suc = false;
        throw new Exception("Username não encontrado.\n");
        
    }
    
    public static void menuCliente() throws Exception{        
        Scanner input = new Scanner(System.in);
        int opcao=0;
        do{
            System.out.println("\n\n----MENU CLIENTE----\n");
            System.out.println("Bem vindo " + stand.getClientes().get(cs-1).getNome() + "!");
            System.out.println("1 - Ver o meu perfil");
            System.out.println("2 - Editar o meu perfil");
            System.out.println("3 - Ver listagem de veículos");
            System.out.println("4 - Ver informação de veículo");
            System.out.println("5 - Reservar veículo");
            System.out.println("6 - Comprar veículo");
            System.out.println("7 - Ver as minhas reservas");
            System.out.println("8 - Ver as minhas compras");
            System.out.println("9 - Histórico de vendas do Stand");
            System.out.println("\n0- Sair");
            int codV=0;
            boolean enc=false;
            opcao = parseInt(input.nextLine());
            switch(opcao){
                default:
                    System.out.println("Opção inválida.");
                break;

                case 0:
                break;

                case 1:
                    System.out.println("--MEU PERFIL--");
                    System.out.println(stand.getClientes().get(cs-1).toString());
                    input.nextLine();
                break;

                case 2:
                    System.out.println("--EDITAR PERFIL");
                    stand.getClientes().get(cs-1).editarPerfil();
                break;

                case 3:
                    System.out.println("--VER LISTAGEM DE VEÍCULOS--");
                    System.out.println("Nota: apenas veículos disponíveis para compra são mostrados.");
                    if(stand.getVeiculos().isEmpty()){
                        System.out.println("Não existem veículos.");
                    }
                    else{
                        for(Veiculo v: stand.getVeiculos()){
                            if( v.getEstado().equals("DISPONIVEL") ){
                                System.out.println(v.getInfo()+"\n");
                            }
                        }
                    }
                    input.nextLine();
                break;

                case 4:
                    System.out.println("--INFORMAÇÃO DE VEÍCULO--");
                    System.out.println("Introduza o código do veiculo:");
                    codV = parseInt(input.nextLine());
                    for(Veiculo v: stand.getVeiculos()){
                        if(v.getCodigo() == codV){
                            System.out.println("\nVeículo encontrado:");
                            System.out.println(v.toString());
                        }
                    }
                    if(codV == 0){
                        throw new Exception("Código não existente.");
                    }
                    input.nextLine();
                break;

                case 5:
                    System.out.println("--RESERVAR VEÍCULOS--");
                    String dataCompra=null;
                    Date date = null;
                    String dateFormat = "dd/MM/yyyy";
                    System.out.println("Introduza o código do veículo a reservar:");
                    codV = parseInt(input.nextLine());
                    for(Veiculo v: stand.getVeiculos()){
                        if(v.getCodigo() == codV && v.getEstado().equals("RESERVADO")){
                            throw new Exception("Veículo já reservado por outro cliente.");
                        }
                        if(v.getCodigo() == codV && v.getEstado().equals("VENDIDO")){
                            throw new Exception("Veículo já vendido.");
                        }
                    }
                    System.out.println("Introduza uma data para comprar o veículo (dd-mm-yyyy).\nDeixe em branco se não tem a certeza.");
                    dataCompra = input.nextLine();
                    if(!dataCompra.isEmpty()){
                        date = new SimpleDateFormat("dd-MM-yyyy").parse(dataCompra);
                    }
                    for(Veiculo v: stand.getVeiculos()){
                        if(v.getCodigo() == codV){
                            if(dataCompra.isEmpty()){
                                Reserva r = new Reserva(stand.getClientes().get(cs-1), stand.getVeiculos().get(codV-1));
                                stand.getClientes().get(cs-1).addReserva(r);
                                v.setEstado("RESERVADO");
                            }
                            else{
                                Reserva r = new Reserva(date, stand.getClientes().get(cs-1), stand.getVeiculos().get(codV-1));
                                stand.getClientes().get(cs-1).addReserva(r);
                                v.setEstado("RESERVADO");
                            }
                        }
                    }
                    if(codV == 0){
                        throw new Exception("Código não existente.");
                    }
                break;

                case 6:
                    System.out.println("--COMPRAR VEÍCULO--");
                    enc=false;
                    System.out.println("Introduza o código do veículo a comprar:");
                    codV = parseInt(input.nextLine());
                    for(Veiculo v: stand.getVeiculos()){
                        if(v.getCodigo() == codV){
                            for(Reserva r: stand.getClientes().get(cs-1).getReservas()){
                                if(r.getCodVeiculo() == codV){
                                    if(r.getCodCliente() != stand.getClientes().get(cs-1).getCodigo()){
                                        throw new Exception("Veículo reservado por outro cliente.");
                                    }
                                    //stand.getClientes().get(cs-1).removeReserva(r.getCodigo());
                                    r.setEstado("VENDIDO");
                                }
                            }
                            stand.getVendas().add(v.comprarVeiculo(stand.getClientes().get(cs-1)));
                            System.out.println("Compra realizada com sucesso.");
                            enc=true;
                        }
                    }
                    if(enc=false)
                        System.out.println("Código não existente.");
                break;

                case 7:
                    System.out.println("--MINHAS RESERVAS--");
                    System.out.println("Nota: apenas reservas ativas são mostradas.");
                    if(stand.getClientes().get(cs-1).getReservas().isEmpty()){
                        System.out.println("Não exitem reservas ativas.");
                    }
                    else{
                        for(Reserva r: stand.getClientes().get(cs-1).getReservas()){
                            if(r.getEstado().equals("RESERVADO")){
                                System.out.println("-Reserva-\n" + r.getInfo() + 
                                        "\n-Veículo- \n" + stand.getVeiculos().get(r.getCodVeiculo()-1).getInfo());   
                                System.out.println("\n");
                            }
                        }
                    }
                    input.nextLine();
                break;

                case 8:
                    System.out.println("--MINHAS COMPRAS--");
                    for(Venda v: stand.getVendas()){
                        if(v.getCodCliente() == stand.getClientes().get(cs-1).getCodigo()){
                            System.out.println("\nVeículo:");
                            System.out.println("Marca: " + stand.getVeiculos().get(v.getCodVeiculo()-1).getMarca() +
                                    "\nModelo: " + stand.getVeiculos().get(v.getCodVeiculo()-1).getModelo() + 
                                    "\nCor: " + stand.getVeiculos().get(v.getCodVeiculo()-1).getCor() + 
                                    "\nMatrícula: " + stand.getVeiculos().get(v.getCodVeiculo()-1).getMatricula() +
                                    "\nPreço total: " + v.getPrecoTotal() + "\nData da compra: " + v.getDataVenda());
                            enc=true;
                        }
                    }
                    if(!enc)
                        System.out.println("Nada Encontrado.");
                    input.nextLine();
                break;
                
                case 9:
                    System.out.println("--HISTÓRICO DE VENDAS--");
                    if(stand.getVendas().isEmpty()){
                        System.out.println("Não existem vendas.");
                    }
                    else{
                        for(Venda v: stand.getVendas()){
                            System.out.println("\nVenda:");
                            System.out.println(v.getInfo() + 
                                    "\nMarca: " + stand.getVeiculos().get(v.getCodVeiculo()-1).getMarca() +
                                    "\nModelo: " + stand.getVeiculos().get(v.getCodVeiculo()-1).getModelo() + 
                                    "\nCor: " + stand.getVeiculos().get(v.getCodVeiculo()-1).getCor() +
                                    "\nCliente: " + stand.getClientes().get(v.getCodCliente()-1).getNome() );
                        }
                    }
                    input.nextLine();
                break;
            }
        }while(opcao !=0);
    }
    
    public static void menuFuncionarioInicial() throws Exception{
        Scanner input = new Scanner(System.in);
        int opcao=0, i=0;
        do{
            System.out.println("----MENU FUNCIONÁRIO----");
            System.out.println("1 - Operações sobre Clientes");
            System.out.println("2 - Operações sobre Veículos");
            System.out.println("3 - Operações sobre Funcionários");
            System.out.println("4 - Operações adicionais");
            System.out.println("0 - Sair");
            opcao = parseInt(input.nextLine());
            switch(opcao){
                default:
                    System.out.println("Opção inválida.\n");
                break;
                
                case 0:
                break;
                
                case 1:
                    menuFuncionario1();
                break;
                
                case 2:
                    menuFuncionario2();
                break;
                
                case 3:
                    menuFuncionario3();
                break;
                
                case 4:
                    menuFuncionario4();
                break;
            }
        }while(opcao!=0);
    }
    
    public static void menuFuncionario1() throws Exception{
        Scanner input = new Scanner(System.in);
        int opcao=0, i=0;
        do{
            System.out.println("\n---OPERAÇÕES SOBRE CLIENTES--");
            System.out.println("1 - Ver lista de clientes");
            System.out.println("2 - Adicionar cliente");
            System.out.println("3 - Ver informação de um cliente");
            System.out.println("4 - Editar informação de um cliente");
            System.out.println("\n0 - Voltar");
            boolean enc=false;
            int codCliente = 0, codVeiculo = 0;
            opcao = parseInt(input.nextLine());
            switch(opcao){
                default:
                    System.out.println("Opção inválida.");
                break;

                case 0:
                break;

                case 1:
                    System.out.println("--VER LISTA DE CLIENTES--");
                    for(Cliente c: stand.getClientes()){
                        System.out.println(c.getInfo() + "\n");
                        enc=true;
                    }
                    if(!enc)
                        System.out.println("Nada encontrado.");
                    input.nextLine();
                break;

                case 2:
                    System.out.println("--ADICIONAR CLIENTE--");
                    registarCliente();
                break;

                case 3:
                    System.out.println("--VER INFORMAÇÃO DE UM CLIENTE--");
                    System.out.println("Introduza o código do cliente:");
                    codCliente = parseInt(input.nextLine());
                    for(Cliente c: stand.getClientes()){
                        if(c.getCodigo() == codCliente){
                            System.out.println(c.toString());
                            enc=true;
                        }
                    }
                    if(!enc)
                        System.out.println("Nada encontrado.");
                    input.nextLine();
                break;

                case 4:
                    System.out.println("--EDITAR INFORMAÇÃO DE UM CLIENTE--");
                    System.out.println("Introduza o código do cliente a editar:");
                    codCliente = parseInt(input.nextLine());
                    for(Cliente c: stand.getClientes()){
                        if(c.getCodigo() == codCliente){
                            c.editarPerfil();
                            enc=true;
                        }
                    }
                    if(!enc)
                        System.out.println("Código não encontrado.");
                break;
            }
        }while(opcao!=0);
    }
    
    public static void menuFuncionario2() throws Exception{
        Scanner input = new Scanner(System.in);
        int opcao=0, i=0;
        do{
            System.out.println("--OPERAÇÕES SOBRE VEÍCULOS--");
            System.out.println("1 - Ver lista de veículos disponíveis");
            System.out.println("2 - Ver lista completa de veículos");
            System.out.println("3 - Adicionar veículo");
            System.out.println("4 - Editar dados de veículo");
            System.out.println("5 - Desativar veículo");
            System.out.println("6 - Registar venda de veículo");
            System.out.println("\n0 - Voltar");
            boolean enc=false;
            int codCliente = 0, codVeiculo = 0;
            opcao = parseInt(input.nextLine());
            switch(opcao){
                default:
                    System.out.println("Opção inválida.");
                break;

                case 0:
                break;

                case 1:
                    System.out.println("--VER LISTA DE VEÍCULOS DISPONÍVEIS");
                    if(stand.getVeiculos().isEmpty()){
                        System.out.println("Não existem veículos.");
                    }
                    else{
                        for(Veiculo v: stand.getVeiculos()){
                            if( (v.getEstado().equals("DISPONIVEL"))  ){
                                System.out.println(v.getInfo()+"\n");
                            }
                        }
                    }
                    input.nextLine();
                break;

                case 2:
                    System.out.println("--VER LISTA COMPLETA DE VEÍCULOS");
                    if(stand.getVeiculos().isEmpty()){
                        System.out.println("Não existem veículos.");
                    }
                    else{
                        for(Veiculo v: stand.getVeiculos()){
                                System.out.println(v.getInfo()+"\n");

                        }
                    }
                    input.nextLine();
                break;

                case 3:
                    System.out.println("--ADICIONAR VEÍCULO");
                    adicionarVeiculo();
                break;

                case 4:
                    System.out.println("--EDITAR DADOS DE VEÍCULO");
                    System.out.println("Introduza o código do veículo: ");
                    codVeiculo = parseInt(input.nextLine());
                    for(Veiculo v: stand.getVeiculos()){
                        if(v.getCodigo() == codVeiculo){
                            v.editarInfo();
                            enc=true;
                        }
                    }
                    if(!enc)
                        System.out.println("Veículo não encontrado.");
                break;

                case 5:
                    System.out.println("--DESATIVAR VEÍCULO--");
                    System.out.println("Introduza o código do veículo:");
                    codVeiculo = parseInt(input.nextLine());
                    for(Veiculo v: stand.getVeiculos()){
                        if(v.getCodigo() == codVeiculo){
                            v.desativarVeiculo();
                            System.out.println("Veículo desativado com sucesso.");
                            enc=true;
                        }
                    }
                    if(!enc)
                        System.out.println("Veículo não encontrado.");
                break;

                case 6:
                    System.out.println("--REGISTAR VENDA DE VEÍCULO--");
                    System.out.println("Introduza o código do cliente: ");
                    codCliente = parseInt(input.nextLine());
                    for(Cliente c: stand.getClientes()){
                        if(c.getCodigo() == codCliente){
                            enc=true;
                        }
                    }
                    if(!enc){
                        throw new Exception("Código não encontrado.");
                    }
                    System.out.println("Introduza o código do veículo: ");
                    codVeiculo = parseInt(input.nextLine());
                    for(Veiculo v: stand.getVeiculos()){
                        if(v.getCodigo() == codVeiculo){
                            enc=true;
                        }
                    }
                    if(!enc){
                        throw new Exception("Código não encontrado.");
                    }
                    Venda vn = stand.getVeiculos().get(codVeiculo).comprarVeiculo(stand.getClientes().get(codCliente));
                    stand.addVenda(vn);
                    System.out.println("Venda realizada com sucesso.");
                break;
            }
        }while(opcao!=0);
    }
    
    public static void menuFuncionario3() throws Exception{
        Scanner input = new Scanner(System.in);
        int opcao=0, i=0;
        do{
            System.out.println("--OPERAÇÕES SOBRE FUNCIONÁRIOS--");
            System.out.println("1 - Listar Funcionários");
            System.out.println("2 - Adicionar funcionário");
            System.out.println("3 - Editar informação de um funcionário");
            System.out.println("4 - Ver informação de funcionário");
            System.out.println("\n0 - Voltar");
            boolean enc=false;
            int codCliente = 0, codVeiculo = 0;
            opcao = parseInt(input.nextLine());
            switch(opcao){
                default:
                    System.out.println("Opção inválida.");
                break;

                case 0:
                break;

                case 1:
                    System.out.println("--LISTAR FUNCIONÁRIOS--");
                    if(stand.getFuncionarios().isEmpty()){
                        System.out.println("Não existem funcionários");
                    }
                    else{
                        for(Funcionario f: stand.getFuncionarios()){
                            switch(ts){
                                default:
                                    throw new Exception("Erro de sessão.");
                                case 2:
                                    System.out.println(f.getInfo());
                                break;
                                case 3:
                                    System.out.println(f.toString());
                                break;
                            }
                            System.out.println("\n");
                        }
                    }
                    input.nextLine();
                break;

                case 2:
                    System.out.println("--ADICIONAR FUNCIONÁRIO--");
                    if(ts!=3){
                        throw new Exception("Apenas admins pódem usar esta opção.");
                    }
                    registarFuncionario();
                break;

                case 3:
                    System.out.println("--EDITAR INFORMAÇÃO DE FUNCIONÁRIO--");
                    if(ts!=3){
                        throw new Exception("Apenas admins pódem usar esta opção.");
                    }
                    System.out.println("Introduza o código do funcionário:");
                    i = parseInt(input.nextLine());
                    for(Funcionario f: stand.getFuncionarios()){
                        if(f.getCodigo() == i){
                            f.editarPerfil();
                            enc=true;
                        }
                    }
                    if(!enc){
                        System.out.println("Funcionário não encontrado.");
                    }
                break;
                
                case 4:
                    System.out.println("--INFORMAÇÃO DE FUNCIONÁRIO--");
                    if(ts!=3){
                        throw new Exception("Apenas admins pódem usar esta opção.");
                    }
                    System.out.println("Introduza o código do funcionário:");
                    i = parseInt(input.nextLine());
                    for(Funcionario f: stand.getFuncionarios()){
                        if(f.getCodigo() == i){
                            System.out.println(f.toString());
                            enc=true;
                        }
                    }
                    if(!enc){
                        System.out.println("Funcionário não encontrado.");
                    }
                    input.nextLine();
                break;
            }
        }while(opcao!=0);
    }
    
    public static void menuFuncionario4() throws Exception{
        Scanner input = new Scanner(System.in);
        int opcao=0, i=0;
        do{
            System.out.println("--OPERAÇÕES ADICIONAIS--");
            System.out.println("1 - Ver lista de vendas");
            System.out.println("2 - Ver lista de veiculos reservados");
            System.out.println("3 - Listar Reservas");
            System.out.println("4 - Validar Reserva");
            System.out.println("5 - Cancelar Reserva");
            System.out.println("6 - Editar informação do stand");
            System.out.println("7 - Próximas compras agendadas");
            System.out.println("\n0 - Voltar");
            boolean enc=false;
            int codCliente = 0, codVeiculo = 0;
            opcao = parseInt(input.nextLine());
            switch(opcao){
                default:
                    System.out.println("Opção inválida.");
                break;

                case 0:
                break;

                case 1:
                    System.out.println("--VER LISTA DE VENDAS--");
                    if(stand.getVendas().isEmpty()){
                        System.out.println("Não existem vendas.");
                    }
                    else{
                        for(Venda v: stand.getVendas()){
                            System.out.println(v.toString());
                        }
                    }
                    input.nextLine();
                break;

                case 2:
                    System.out.println("--VER LISTA DE VEÍCULOS RESERVADOS--");
                    for(Veiculo v: stand.getVeiculos()){
                        if(v.getEstado().equals("RESERVADO")){
                            System.out.println(v.toString());
                            enc=true;
                        }
                    }
                    if(!enc)
                        System.out.println("Nada encontrado.");
                    input.nextLine();
                break;

                case 3:
                    System.out.println("--LISTAR RESERVAS--");
                    for(Cliente c: stand.getClientes()){
                        for(Reserva r: c.getReservas()){
                            System.out.println(r.getInfo());
                            enc=true;
                        }
                    }
                    if(!enc)
                        System.out.println("Nada encontrado.");
                    input.nextLine();
                break;

                case 4:
                    System.out.println("--VALIDAR RESERVA--"); //comprar veículo???
                    System.out.println("Introduza o código da reserva:");
                    i = parseInt(input.nextLine());
                    for(Cliente c: stand.getClientes()){
                        for(Reserva r: c.getReservas()){
                            if(r.getCodigo() == i){
                                r.setEstado("VALIDADA");
                                enc=true;
                            }
                        }
                    }
                    if(!enc)
                        System.out.println("Reserva não encontrada.");
                break;

                case 5:
                    System.out.println("--CANCELAR RESERVA--");
                    System.out.println("Introduza o código da reserva:");
                    i = parseInt(input.nextLine());
                    for(Cliente c: stand.getClientes()){
                        for(Reserva r: c.getReservas()){
                            if(r.getCodigo() == i){
                                r.cancelaReserva();
                                stand.getVeiculos().get(r.getCodVeiculo()).ativarVeiculo();
                                System.out.println("Reserva cancelada com sucesso.");
                                enc=true;
                            }
                        }
                    }
                    if(!enc)
                        System.out.println("Reserva não encontrada.");
                break;

                case 6:
                    System.out.println("--EDITAR INFORMAÇÃO DO STAND--");
                    if(ts!=3){
                        throw new Exception("Apenas admins pódem usar esta opção.");
                    }
                    stand.editarStand();
                break;

                case 7:
                    System.out.println("--PRÓXIMAS COMPRAS AGENDADAS--");
                    Date dataInicio=null, dataFim=null;
                    System.out.println("Introduza a data início da pesquisa: (dd-mm-yyyy)");
                    String data1 = input.nextLine();
                    System.out.println("Introduza a data fim da pesquisa: (dd-mm-yyyy)");
                    String data2 = input.nextLine();
                    try{
                        dataInicio = new SimpleDateFormat("dd-MM-yyyy").parse(data1);
                        dataFim = new SimpleDateFormat("dd-MM-yyyy").parse(data2);
                    }catch(Exception e){
                        System.out.println("Erro no parsing das datas.");
                    };
                    if( (dataInicio == null) || (dataFim == null)){
                        throw new Exception("As datas não podem ser nulas.");
                    }
                    for(Cliente c: stand.getClientes()){
                        for(Reserva r: c.getReservas()){
                            if( (r.getDataCompra().after(dataInicio)) && r.getDataCompra().before(dataFim) ){
                                r.toString();
                                enc=true;
                            }
                        }
                    }
                    if(!enc)
                        System.out.println("Nada encontrado.");
                    input.nextLine();
                break;
            }
        }while(opcao!=0);
    }
    
    public static void adicionarVeiculo() throws Exception{
        Scanner input = new Scanner(System.in);
        System.out.println("Introduza a marca: ");
        String marca = input.nextLine();
        System.out.println("Introduza o modelo: ");
        String modelo = input.nextLine();
        System.out.println("Introduza o tipo: ");
        String tipov = input.nextLine();
        System.out.println("Introduza a cor: ");
        String cor = input.nextLine();
        System.out.println("Introduza a matricula: ");
        String matricula = input.nextLine();
        if(!matricula.matches("[A-Z0-9]{2}-[A-Z0-9]{2}-[A-Z0-9]{2}")){
            throw new IllegalArgumentException("Matrícula inválida.");
        }
        System.out.println("Introduza o preço: ");
        double preco = parseDouble(input.nextLine());
        
        Veiculo v = new Veiculo(marca, modelo, tipov, cor, matricula, preco);
        for(Veiculo ve: stand.getVeiculos()){
            if(ve.getMatricula().equals(matricula)){
                throw new IllegalArgumentException("Matrícula já em uso.");
            }
        }
        stand.addVeiculo(v);
        salvarFicheiro();
        System.out.println("Veículo adicionado com sucesso.\n");
    }
    
    public static void registarFuncionario() throws Exception{
        Scanner input = new Scanner(System.in);
        String username, password, nome, turnoComeco, turnoFim; int telemovel; double salario;
        System.out.println("Introduza o nome:");
        nome = input.nextLine();
        System.out.println("Introduza o username:");
        username = input.nextLine();
        System.out.println("Introduza a password:");
        password = input.nextLine();
        System.out.println("Introduza o começo do turno:");
        turnoComeco = input.nextLine();
        System.out.println("Introduza o fim to turno:");
        turnoFim = input.nextLine();
        System.out.println("Introduza o salário:");
        salario = parseDouble(input.nextLine());
        System.out.println("Introduza o telemovel:");
        telemovel = parseInt(input.nextLine());
        
        for(Funcionario f: stand.getFuncionarios()){
            if(f.getUsername().equals(username)){
                throw new Exception("Username já em uso.");
            }
        }
        
        Funcionario fa = new Funcionario(nome, telemovel, username, password, turnoComeco, turnoFim, salario);
        stand.addFuncionario(fa);
        salvarFicheiro();
        System.out.println("Funcionário adicionado com sucesso.\n");
    }
    
}
