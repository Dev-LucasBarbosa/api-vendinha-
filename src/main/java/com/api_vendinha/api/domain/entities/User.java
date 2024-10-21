package com.api_vendinha.api.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Entidade representando um usuário no sistema.
 *
 * Esta classe é mapeada para a tabela "users" no banco de dados e representa a estrutura dos dados
 * relacionados a um usuário.
 */
@Entity
@Table(name = "users") // Especifica o nome da tabela no banco de dados que será associada a esta entidade.
@NoArgsConstructor // Gera um construtor sem argumentos, necessário para a criação de instâncias da entidade pelo JPA.
@AllArgsConstructor // Gera um construtor que aceita argumentos para todos os campos, útil para criar instâncias com todos os dados.
@Data // Gera automaticamente métodos getters, setters, toString, equals e hashCode.
public class User {

    /**
     * Identificador único do usuário.
     *
     * Este campo é a chave primária da entidade e será gerado automaticamente pelo banco de dados.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Define que o valor do ID será gerado automaticamente pelo banco de dados (auto-incremento).
    private Long id;

    /**
     * Nome do usuário.
     *
     * Este campo é obrigatório e não pode ser nulo, refletindo a necessidade de um nome para cada usuário.
     */
    @Column(nullable = false) // Especifica que a coluna no banco de dados não pode ser nula.
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "is_active")
    private Boolean is_active;

    @Column(name = "cpf_cnpj")
    private String cpf_cnpj;

    //mappedBy é aonde não tem a chave estrangeira
    @OneToMany(mappedBy = "user")
    private List<Produtos> produtos;

    @OneToMany(mappedBy = "user")
    private List<Vendas> vendas;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getIs_active() {
        return is_active;
    }

    public void setIs_active(Boolean is_active) {
        this.is_active = is_active;
    }

    public String getCpf_cnpj() {
        return cpf_cnpj;
    }

    public void setCpf_cnpj(String cpf_cnpj) {
        this.cpf_cnpj = cpf_cnpj;
    }

    public List<Produtos> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produtos> produtos) {
        this.produtos = produtos;
    }

    public List<Vendas> getVendas() {
        return vendas;
    }

    public void setVendas(List<Vendas> vendas) {
        this.vendas = vendas;
    }
}
