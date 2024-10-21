package com.api_vendinha.api.Infrastructure.repository;

import com.api_vendinha.api.domain.entities.Vendas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendasRepository extends JpaRepository<Vendas, Long> {


}
