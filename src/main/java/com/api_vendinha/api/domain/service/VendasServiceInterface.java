package com.api_vendinha.api.domain.service;

import com.api_vendinha.api.domain.dtos.request.VendasRequestDto;
import com.api_vendinha.api.domain.dtos.response.VendasResponseDto;

public interface VendasServiceInterface {
    VendasResponseDto criaVenda(VendasRequestDto vendasRequestDto);
}
