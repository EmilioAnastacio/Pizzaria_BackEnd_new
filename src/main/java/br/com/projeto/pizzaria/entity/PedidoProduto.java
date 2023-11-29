package br.com.projeto.pizzaria.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
public class PedidoProduto {
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	// @ManyToOne
	// @JoinColumn(name = "pedido_id")
	// private Pedido pedido;
	
	@ManyToOne
	@JoinColumn(name = "item_id")
	private Item item;
	
	@ManyToMany
	@JoinTable(name = "pedido_item_sabor",
	joinColumns = @JoinColumn(name = "pedido_item_id"),
	inverseJoinColumns = @JoinColumn(name = "sabor_id"))
	private List<Sabores> sabores;
}
