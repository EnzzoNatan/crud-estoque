package br.edu.ifsc.estoquecrud.service;

import br.edu.ifsc.estoquecrud.dto.ProdutoDTO;
import br.edu.ifsc.estoquecrud.entity.Categoria;
import br.edu.ifsc.estoquecrud.entity.Produto;
import br.edu.ifsc.estoquecrud.repository.CategoriaRepository;
import br.edu.ifsc.estoquecrud.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
     private CategoriaRepository categoriaRepository;

    //Cria um produto
    public ProdutoDTO criarProduto(ProdutoDTO dto) {

        if (dto.getPreco() == null || dto.getPreco().compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("Preço do produto não pode ser negativo ou nulo");
        }

        if (dto.getQuantidade() == null || dto.getQuantidade() <= 0) {
            throw new RuntimeException("Quantidade do produto não pode ser negativa");
        }

        if (dto.getCategoriaId() == null) {
            throw new RuntimeException("Categoria é obrigatória");
        }

        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        Produto produto = toEntity(dto, categoria);

        produto = produtoRepository.save(produto);

        return toDTO(produto);
    }

    public ProdutoDTO toDTO(Produto produto) {

        ProdutoDTO dto = new ProdutoDTO();

        dto.setId(produto.getId());
        dto.setNome(produto.getNome());
        dto.setDescricao(produto.getDescricao());
        dto.setPreco(produto.getPreco());
        dto.setQuantidade(produto.getQuantidade());

        if (produto.getCategoria() != null) {
            dto.setCategoriaId(produto.getCategoria().getId());
            dto.setCategoriaNome(produto.getCategoria().getNome());
        }

        return dto;
    }


    //Lista todos os produtos
    public List<ProdutoDTO> findAll(){

        List<Produto> produtos = produtoRepository.findAll();

        return produtos.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    //Busca por ID
    public ProdutoDTO findById(Long id){

        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        return toDTO(produto);
    }



    //Busca por categoria
    public List<ProdutoDTO> findByCategoria(Long categoriaId) {

        List<Produto> produtos = produtoRepository.findByCategoriaId(categoriaId);

        if (produtos.isEmpty()) {
            throw new RuntimeException("Nenhum produto encontrado para essa categoria");
        }

        return produtos.stream()
                .map(this::toDTO)
                .toList();
    }

    //Atualiza um pedido
    public Produto atualizar(Long id, Produto dadosAtualizados) {

        //Garante que o produto existe
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        //Validação de preço
        if (dadosAtualizados.getPreco() == null ||
                dadosAtualizados.getPreco().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Preço do produto não pode ser negativo ou nulo");
        }

        //Validação de quantidade
        if (dadosAtualizados.getQuantidade() == null ||
                dadosAtualizados.getQuantidade() <= 0) {
            throw new RuntimeException("Quantidade do produto não pode ser negativa");
        }

        //Validação de categoria
        if (dadosAtualizados.getCategoria() == null ||
                dadosAtualizados.getCategoria().getId() == null) {
            throw new RuntimeException("Categoria é obrigatória");
        }

        Categoria categoria = categoriaRepository
                .findById(dadosAtualizados.getCategoria().getId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        // Atualiza campos
        produto.setNome(dadosAtualizados.getNome());
        produto.setDescricao(dadosAtualizados.getDescricao());
        produto.setPreco(dadosAtualizados.getPreco());
        produto.setQuantidade(dadosAtualizados.getQuantidade());

        // Atualiza relação corretamente
        produto.setCategoria(categoria);

        return produtoRepository.save(produto);

    }




    public Produto toEntity(ProdutoDTO dto, Categoria categoria){

        Produto produto = new Produto();

        produto.setId(dto.getId());
        produto.setNome(dto.getNome());
        produto.setDescricao(dto.getDescricao());
        produto.setPreco(dto.getPreco());
        produto.setQuantidade(dto.getQuantidade());

        produto.setCategoria(categoria);

        return produto;
    }

    //Deletar um produto
    public void delete(Long id){

        if (!produtoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado");
        }

        produtoRepository.deleteById(id);
    }


}
