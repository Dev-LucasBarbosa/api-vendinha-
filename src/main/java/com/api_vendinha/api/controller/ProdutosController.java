package com.api_vendinha.api.controller;

import com.api_vendinha.api.domain.dtos.request.ProdutosRequestDto;
import com.api_vendinha.api.domain.dtos.response.ProdutosResponseDto;
import com.api_vendinha.api.domain.dtos.response.UserResponseDto;
import com.api_vendinha.api.domain.entities.Produtos;
import com.api_vendinha.api.domain.entities.User;
import com.api_vendinha.api.domain.service.ProdutosServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/produtos/{id}") // Define que este método lida com requisições HTTP POST.
    public ProdutosResponseDto buscar(@PathVariable Long id) {
        // Chama o serviço para salvar o usuário e retorna a resposta.
        return produtosService.buscar(id);
    }

    @GetMapping("/produtos/listar") // Define que este método lida com requisições HTTP POST.
    public List<Produtos> listar() {
        // Chama o serviço para salvar o usuário e retorna a resposta.
        return produtosService.listar();
    }

    @GetMapping("/produtos/listar/{userId}")
    public List<Produtos> listarPorUsuario(@PathVariable Long userId) {
        return produtosService.listarPorUsuario(userId);
    }
}
