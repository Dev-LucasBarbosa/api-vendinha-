package com.api_vendinha.api.domain.service;

import com.api_vendinha.api.domain.dtos.request.VendasRequestDto;
import com.api_vendinha.api.domain.dtos.response.VendasResponseDto;
import com.api_vendinha.api.domain.entities.Vendas;

import java.util.List;

public interface VendasServiceInterface {
    VendasResponseDto criaVenda(VendasRequestDto vendasRequestDto);

    List<Vendas> listar();
}
