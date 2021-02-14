/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pg2.er_tp;

import java.util.Date;

/**
 *
 * @author bruno
 */
public class Dono extends Funcionario {
    private String username;
    private String password;

    public Dono(String nome, int telemovel, String username, String password, String turnoComeco, String turnoFim, double salario) {
        super(nome, telemovel, username, password, turnoComeco, turnoFim, salario);
    }

    
    
}
