package com.api_vendinha.api.domain.service;

import com.api_vendinha.api.domain.dtos.request.ProdutosRequestDto;
import com.api_vendinha.api.domain.dtos.response.ProdutosResponseDto;
import com.api_vendinha.api.domain.entities.Produtos;

import java.util.List;

public interface ProdutosServiceInterface {

    ProdutosResponseDto save(ProdutosRequestDto produtosRequestDto);
    ProdutosResponseDto update(Long id, ProdutosRequestDto produtosRequestDto);

    List<Produtos> listar();

    ProdutosResponseDto buscar(Long id);
}
