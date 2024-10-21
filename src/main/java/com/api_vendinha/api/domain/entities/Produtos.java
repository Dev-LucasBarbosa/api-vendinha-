package com.api_vendinha.api.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "produtos") // Especifica o nome da tabela no banco de dados que será associada a esta entidade.
@NoArgsConstructor // Gera um construtor sem argumentos, necessário para a criação de instâncias da entidade pelo JPA.
@AllArgsConstructor // Gera um construtor que aceita argumentos para todos os campos, útil para criar instâncias com todos os dados.
@Data // Gera automaticamente métodos getters, setters, toString, equals e hashCode.
public class Produtos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Define que o valor do ID será gerado automaticamente pelo banco de dados (auto-incremento).
    private Long id;

    @Column(nullable = false) // Especifica que a coluna no banco de dados não pode ser nula.
    private String nome;

    @Column(name = "quantidade")
    private Integer quantidade;

    @Column(name = "preco")
    private Float preco;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "produtos")
    private List<Vendas> vendas;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Float getPreco() {
        return preco;
    }

    public void setPreco(Float preco) {
        this.preco = preco;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Vendas> getVendas() {
        return vendas;
    }

    public void setVendas(List<Vendas> vendas) {
        this.vendas = vendas;
    }
}
