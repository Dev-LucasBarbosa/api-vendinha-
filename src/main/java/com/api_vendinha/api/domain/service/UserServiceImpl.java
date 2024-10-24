package com.api_vendinha.api.domain.service;

import com.api_vendinha.api.Infrastructure.repository.ProdutosRepository;
import com.api_vendinha.api.Infrastructure.repository.UserRepository;
import com.api_vendinha.api.domain.dtos.request.UserRequestDto;
import com.api_vendinha.api.domain.dtos.response.ProdutosResponseDto;
import com.api_vendinha.api.domain.dtos.response.UserResponseDto;
import com.api_vendinha.api.domain.entities.Produtos;
import com.api_vendinha.api.domain.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementação do serviço de usuários.
 * <p>
 * Esta classe fornece a implementação dos métodos definidos na interface UserServiceInterface,
 * lidando com a lógica de negócios relacionada aos usuários, como criar e atualizar usuários.
 */
@Service
public class UserServiceImpl implements UserServiceInterface {

    // Repositório para a persistência de dados de usuários.
    private final UserRepository userRepository;
    private final ProdutosRepository produtosRepository;

    /**
     * Construtor para injeção de dependência do UserRepository.
     *
     * @param userRepository O repositório de usuários a ser injetado.
     */
    @Autowired
    public UserServiceImpl(UserRepository userRepository, ProdutosRepository produtosRepository) {

        this.userRepository = userRepository;
        this.produtosRepository = produtosRepository;
    }

    /**
     * Salva um novo usuário ou atualiza um usuário existente.
     * <p>
     * Cria uma nova entidade User a partir dos dados fornecidos no UserRequestDto, persiste essa
     * entidade no banco de dados, e retorna um UserResponseDto com as informações do usuário salvo.
     *
     * @param userRequestDto DTO contendo os dados do usuário a ser salvo ou atualizado.
     * @return DTO com as informações do usuário salvo, incluindo o ID gerado e o nome.
     */
    @Override
    public UserResponseDto save(UserRequestDto userRequestDto) {
        // Cria uma nova instância de User.
        User user = new User();
        // Define o nome do usuário a partir do DTO.
        user.setName(userRequestDto.getName());
        user.setEmail(userRequestDto.getEmail());
        user.setPassword(userRequestDto.getPassword());
        user.setIs_active(userRequestDto.getIs_active());
        user.setCpf_cnpj(userRequestDto.getCpf_cnpj());

        // Salva o usuário no banco de dados e obtém a entidade persistida com o ID gerado.
        User savedUser = userRepository.save(user);

        if (!userRequestDto.getProdutosRequestDtos().isEmpty()) {
            List<Produtos> produtos = userRequestDto.getProdutosRequestDtos().stream().map(
                    dto -> {
                        Produtos produto = new Produtos();
                        produto.setNome(dto.getNome());
                        produto.setQuantidade(dto.getQuantidade());
                        produto.setPreco(dto.getPreco());
                        produto.setUser(savedUser);
                        return produto;
                    }).collect(Collectors.toList());

            produtosRepository.saveAll(produtos);
        }
        // Cria um DTO de resposta com as informações do usuário salvo.
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(savedUser.getId());
        userResponseDto.setName(savedUser.getName());
        userResponseDto.setEmail(savedUser.getEmail());
        userResponseDto.setPassword(savedUser.getPassword());
        userResponseDto.setIs_active(savedUser.getIs_active());
        userResponseDto.setCpf_cnpj(savedUser.getCpf_cnpj());

        // Retorna o DTO com as informações do usuário salvo.
        return userResponseDto;
    }

    @Override
    public UserResponseDto update(Long id, UserRequestDto userRequestDto) {
        User userExist = userRepository.findById(id).orElseThrow();
        userExist.setName(userRequestDto.getName());
        userExist.setEmail(userRequestDto.getEmail());
        userExist.setPassword(userRequestDto.getPassword());
        userExist.setIs_active(userRequestDto.getIs_active());
        userExist.setCpf_cnpj(userRequestDto.getCpf_cnpj());

        userRepository.save(userExist);

        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(userExist.getId());
        userResponseDto.setName(userExist.getName());
        userResponseDto.setEmail(userExist.getEmail());
        userResponseDto.setPassword(userExist.getPassword());
        userResponseDto.setIs_active(userExist.getIs_active());
        userResponseDto.setCpf_cnpj(userExist.getCpf_cnpj());

        return userResponseDto;
    }

    @Override
    public UserResponseDto buscar(Long id) {
        User userExist = userRepository.findById(id).orElseThrow();

        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(userExist.getId());
        userResponseDto.setName(userExist.getName());
        userResponseDto.setEmail(userExist.getEmail());
        userResponseDto.setPassword(userExist.getPassword());
        userResponseDto.setIs_active(userExist.getIs_active());
        userResponseDto.setCpf_cnpj(userExist.getCpf_cnpj());

        return userResponseDto;
    }

    @Override
    public UserResponseDto status(Long id, UserRequestDto userRequestDto) {
        User userExist = userRepository.findById(id).orElseThrow();
        userExist.setIs_active(userRequestDto.getIs_active());

        userRepository.save(userExist);

        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(userExist.getId());
        userResponseDto.setName(userExist.getName());
        userResponseDto.setEmail(userExist.getEmail());
        userResponseDto.setPassword(userExist.getPassword());
        userResponseDto.setIs_active(userExist.getIs_active());
        userResponseDto.setCpf_cnpj(userExist.getCpf_cnpj());

        return userResponseDto;
    }

    public List<UserResponseDto> listarTodos() {

        return userRepository.findAll().stream().map(user -> {

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

