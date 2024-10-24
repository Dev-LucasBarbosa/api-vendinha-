package com.api_vendinha.api.controller;

import com.api_vendinha.api.domain.dtos.request.VendasRequestDto;
import com.api_vendinha.api.domain.dtos.response.VendasResponseDto;
import com.api_vendinha.api.domain.entities.Vendas;
import com.api_vendinha.api.domain.service.VendasServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api") // Define o caminho base para as requisições deste controlador.
public class VendasController {

    private final VendasServiceInterface vendasService;

    @Autowired
    public VendasController(VendasServiceInterface vendasService) {
        this.vendasService = vendasService;
    }

    @PostMapping("/vendas") // Define que este método lida com requisições HTTP POST.
    public VendasResponseDto criarVenda(@RequestBody VendasRequestDto vendasRequestDto) {

        return vendasService.criaVenda(vendasRequestDto);
    }

    @GetMapping("/vendas/listar") // Define que este método lida com requisições HTTP POST.
    public List<Vendas> listar() {
        // Chama o serviço para salvar o usuário e retorna a resposta.
        return vendasService.listar();
    }
}
