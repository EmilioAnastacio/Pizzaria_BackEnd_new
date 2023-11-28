package br.com.projeto.pizzaria.entity;


import br.com.projeto.pizzaria.enums.Roles;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "CPF")
    private String cpf;

    @JsonIgnoreProperties("usuario")
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Endereco> enderecos;

    @OneToOne
    @JoinColumn(name = "usuario")
    private Login login;

    public Usuario(){

    }

    public Usuario(Long id, String nome, String telefone, String cpf, Login login) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.cpf = cpf;
        this.login = login;
    }
}
