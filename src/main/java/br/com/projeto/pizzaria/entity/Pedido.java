package br.com.projeto.pizzaria.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "observacao")
    private String observacao;

    @Column(name = "valorTotal")
    private Float valorTotal;

    @Column(name = "entrega")
    private Boolean entrega;

    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(name = "item_pedidos",
      joinColumns = @JoinColumn(name = "pedido_fk"),
      inverseJoinColumns = @JoinColumn(name = "item_fk"))
    private List<Item> item;

    @ManyToOne
    @JoinColumn(name = "usuario_fk")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "funcionario_fk")
    private Funcionario funcionario;

    public Pedido(){

    }

    public Pedido(Long id, String nome, String observacao, Float valorTotal, Usuario usuario, Funcionario funcionario) {
        this.id = id;
        this.nome = nome;
        this.observacao = observacao;
        this.usuario = usuario;
        this.valorTotal = valorTotal;
        this.funcionario = funcionario;
    }
}
