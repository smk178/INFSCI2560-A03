/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import javax.ejb.Stateless;

/**
 *
 * @author SMK178
 */
@Stateless
public class Registration {
    private String username;
    private String password;
    private String confirm;

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

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }
    
    public boolean isValid(){
        return this.password.equals(confirm) && (!this.username.equalsIgnoreCase("") || !this.password.equalsIgnoreCase(""));
    }
    
}
