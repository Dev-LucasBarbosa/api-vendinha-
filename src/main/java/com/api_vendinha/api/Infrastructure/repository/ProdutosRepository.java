package com.api_vendinha.api.Infrastructure.repository;

import com.api_vendinha.api.domain.entities.Produtos;
import com.api_vendinha.api.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutosRepository extends JpaRepository<Produtos, Long> {
    List<Produtos> findByUserId(Long userId);
}
