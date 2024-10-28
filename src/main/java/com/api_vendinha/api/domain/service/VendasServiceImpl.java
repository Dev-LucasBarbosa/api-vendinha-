package com.api_vendinha.api.domain.service;

import com.api_vendinha.api.Infrastructure.repository.ProdutosRepository;
import com.api_vendinha.api.Infrastructure.repository.UserRepository;
import com.api_vendinha.api.Infrastructure.repository.VendasRepository;
import com.api_vendinha.api.domain.dtos.request.VendasRequestDto;
import com.api_vendinha.api.domain.dtos.response.ProdutosResponseDto;
import com.api_vendinha.api.domain.dtos.response.UserResponseDto;
import com.api_vendinha.api.domain.dtos.response.VendasResponseDto;
import com.api_vendinha.api.domain.entities.Produtos;
import com.api_vendinha.api.domain.entities.Vendas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class VendasServiceImpl implements VendasServiceInterface {

    // Repositório para a persistência de dados de usuários.
    private final VendasRepository vendasRepository;
    private final ProdutosRepository produtosRepository;
    private final UserRepository userRepository;

    @Autowired
    public VendasServiceImpl(VendasRepository vendasRepository, ProdutosRepository produtosRepository, UserRepository userRepository) {

        this.vendasRepository = vendasRepository;
        this.produtosRepository = produtosRepository;
        this.userRepository = userRepository;
    }

    @Override
    public VendasResponseDto salvar(VendasRequestDto vendasRequestDto) {

        // Obtém o Produto pelo ID
        Produtos produto = produtosRepository.findById(vendasRequestDto.getProduct_id())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        //Verifica a quantidade disponível
        if (vendasRequestDto.getQuantity() > produto.getQuantidade()){
            throw new RuntimeException("Quantidade solicitada excede a disponível em estoque");
        }

        // Cria uma nova venda.
        Vendas venda = new Vendas();
        venda.setUser(userRepository.findById(vendasRequestDto.getUser_id())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado")));
        venda.setProdutos(produto);
        venda.setQuantity(vendasRequestDto.getQuantity());
        venda.setPrice(produto.getPreco() * vendasRequestDto.getQuantity());

        // Salva a venda
        Vendas savedVenda = vendasRepository.save(venda);

        // Atualiza o estoque do produto
        produto.setQuantidade(produto.getQuantidade() - vendasRequestDto.getQuantity());
        produtosRepository.save(produto); //Salva a atualização do estoque

        // Cria e retorna o DTO de resposta
        VendasResponseDto vendasResponseDto = new VendasResponseDto();
        vendasResponseDto.setId(savedVenda.getId());
        vendasResponseDto.setUser_id(savedVenda.getUser().getId());
        vendasResponseDto.setProduct_id(savedVenda.getProdutos().getId());
        vendasResponseDto.setQuantity(savedVenda.getQuantity());
        vendasResponseDto.setPrice(savedVenda.getPrice());

        // Retorna o DTO com as informações do usuário salvo.
        return vendasResponseDto;
    }

    @Override
    public List<VendasResponseDto> listar() {
        return VendasRepository.findAll().stream().map(venda -> {

            UserResponseDto userResponseDto = new UserResponseDto();
            userResponseDto.setId(user.getId());
            userResponseDto.setName(user.getName());
            userResponseDto.setEmail(user.getEmail());
            userResponseDto.setPassword(user.getPassword());
            userResponseDto.setIs_active(user.getIs_active());
            userResponseDto.setCpf_cnpj(user.getCpf_cnpj());


            List<ProdutosResponseDto> produtosResponseDto =  user.getProdutos().stream().map(pr->{
                ProdutosResponseDto produto = new ProdutosResponseDto();
                produto.setId(pr.getId());
                produto.setNome(pr.getNome());
                produto.setQuantidade(pr.getQuantidade());
                produto.setPreco(pr.getPreco());
                return produto;
            }).toList();

            userResponseDto.setProdutos(produtosResponseDto);
            return userResponseDto;
        }).toList();
    }
}
