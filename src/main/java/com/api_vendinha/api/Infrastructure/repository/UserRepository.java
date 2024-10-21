package com.api_vendinha.api.Infrastructure.repository;

import com.api_vendinha.api.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositório para a entidade User.
 *
 * Esta interface estende JpaRepository, o que fornece uma série de métodos prontos para realizar
 * operações de persistência no banco de dados para a entidade User.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Busca um usuário pelo nome.
     *
     * @param name Nome do usuário a ser pesquisado.
     * @return Um Optional contendo o usuário encontrado, se existir; caso contrário, um Optional vazio.
     */
    Optional<User> findByName(String name);
}
