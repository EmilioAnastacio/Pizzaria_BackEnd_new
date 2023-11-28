package br.com.projeto.pizzaria.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {

    private Long id;
    private String username;

    private String password;


    public LoginDTO(){

    }

    public LoginDTO(Long id, String email, String senha) {
        this.id = id;
        this.username = email;
        this.password = senha;
    }
}
