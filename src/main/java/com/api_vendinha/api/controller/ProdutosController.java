package com.api_vendinha.api.controller;

import com.api_vendinha.api.domain.dtos.request.ProdutosRequestDto;
import com.api_vendinha.api.domain.dtos.response.ProdutosResponseDto;
import com.api_vendinha.api.domain.service.ProdutosServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@CrossOrigin("*")
@RestController
@RequestMapping("/api") // Define o caminho base para as requisições deste controlador.
public class ProdutosController {

    // Injeção de dependência do serviço de usuários.
    private final ProdutosServiceInterface produtosService;

    @Autowired
    public ProdutosController(ProdutosServiceInterface produtosService) {
        this.produtosService = produtosService;
    }

    @PostMapping("/produtos") // Define que este método lida com requisições HTTP POST.
    public ProdutosResponseDto salvar(@RequestBody ProdutosRequestDto produtosRequestDto) {
        // Chama o serviço para salvar o usuário e retorna a resposta.
        return produtosService.save(produtosRequestDto);
    }

    @PutMapping("/produtos/{id}") // Define que este método lida com requisições HTTP POST.
    public ProdutosResponseDto update(@PathVariable Long id, @RequestBody ProdutosRequestDto produtosRequestDto) {
        // Chama o serviço para salvar o usuário e retorna a resposta.
        return produtosService.update(id, produtosRequestDto);
    }
}
