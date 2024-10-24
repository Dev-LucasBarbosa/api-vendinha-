package com.api_vendinha.api.domain.service;

import com.api_vendinha.api.Infrastructure.repository.ProdutosRepository;
import com.api_vendinha.api.Infrastructure.repository.UserRepository;
import com.api_vendinha.api.domain.dtos.request.ProdutosRequestDto;
import com.api_vendinha.api.domain.dtos.response.ProdutosResponseDto;
import com.api_vendinha.api.domain.dtos.response.UserResponseDto;
import com.api_vendinha.api.domain.entities.Produtos;
import com.api_vendinha.api.domain.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProdutosServiceImpl implements ProdutosServiceInterface {

    // Repositório para a persistência de dados de usuários.
    private final ProdutosRepository produtosRepository;
    private final UserRepository userRepository;

    @Autowired
    public ProdutosServiceImpl(
            ProdutosRepository produtosRepository,
            UserRepository userRepository
    ) {
        this.produtosRepository = produtosRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ProdutosResponseDto save(ProdutosRequestDto produtosRequestDto) {

        // Cria uma nova instância de User.
        User user = userRepository.findById(produtosRequestDto.getUserId()).orElseThrow();
        Produtos produtos = new Produtos();
        // Define o nome do usuário a partir do DTO.
        produtos.setNome(produtosRequestDto.getNome());
        produtos.setQuantidade(produtosRequestDto.getQuantidade());
        produtos.setPreco(produtosRequestDto.getPreco());
        produtos.setUser(user);

        // Salva o usuário no banco de dados e obtém a entidade persistida com o ID gerado.
        Produtos savedProdutos = produtosRepository.save(produtos);

        // Cria um DTO de resposta com as informações do usuário salvo.
        ProdutosResponseDto produtosResponseDto = new ProdutosResponseDto();
        produtosResponseDto.setId(savedProdutos.getId());
        produtosResponseDto.setNome(savedProdutos.getNome());
        produtosResponseDto.setQuantidade(savedProdutos.getQuantidade());
        produtosResponseDto.setPreco(savedProdutos.getPreco());

        // Retorna o DTO com as informações do usuário salvo.
        return produtosResponseDto;
    }

    @Override
    public ProdutosResponseDto update(Long id, ProdutosRequestDto produtosRequestDto){
        Produtos produtosExist = produtosRepository.findById(id).orElseThrow();
        produtosExist.setNome(produtosRequestDto.getNome());
        produtosExist.setQuantidade(produtosRequestDto.getQuantidade());
        produtosExist.setPreco(produtosRequestDto.getPreco());

        produtosRepository.save(produtosExist);

        ProdutosResponseDto produtosResponseDto = new ProdutosResponseDto();
        produtosResponseDto.setId(produtosExist.getId());
        produtosResponseDto.setNome(produtosExist.getNome());
        produtosResponseDto.setQuantidade(produtosExist.getQuantidade());
        produtosResponseDto.setPreco(produtosExist.getPreco());

        return  produtosResponseDto;
    }

    @Override
    public List<Produtos> listar() {
        return produtosRepository.findAll();
    }

    @Override
    public ProdutosResponseDto buscar(Long id) {
        Produtos produtosExist = produtosRepository.findById(id).orElseThrow();

        ProdutosResponseDto produtosResponseDto = new ProdutosResponseDto();
        produtosResponseDto.setId(produtosExist.getId());
        produtosResponseDto.setNome(produtosExist.getNome());
        produtosResponseDto.setQuantidade(produtosExist.getQuantidade());
        produtosResponseDto.setPreco(produtosExist.getPreco());

        return produtosResponseDto;
    }
}
