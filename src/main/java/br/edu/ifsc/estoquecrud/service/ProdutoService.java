package br.edu.ifsc.estoquecrud.service;

import br.edu.ifsc.estoquecrud.entity.Categoria;
import br.edu.ifsc.estoquecrud.entity.Produto;
import br.edu.ifsc.estoquecrud.repository.CategoriaRepository;
import br.edu.ifsc.estoquecrud.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
     private CategoriaRepository categoriaRepository;

    //Cria um produto
    public Produto criarProduto(Produto produto) {

        // 🔒 Validação de preço
        if (produto.getPreco() == null || produto.getPreco().compareTo(BigDecimal.ZERO) < 0) {
            throw new RuntimeException("Preço do produto não pode ser negativo ou nulo");
        }

        // 🔒 Validação de quantidade
        if (produto.getQuantidade() == null || produto.getQuantidade() <= 0) {
            throw new RuntimeException("Quantidade do produto não pode ser negativa");
        }

        // 🔒 Validação de categoria
        if (produto.getCategoria() == null || produto.getCategoria().getId() == null) {
            throw new RuntimeException("Categoria é obrigatória");
        }

        Long categoriaId = produto.getCategoria().getId();

        Categoria categoria = categoriaRepository.findById(categoriaId)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        produto.setCategoria(categoria);

        produtoRepository.save(produto);
        return produtoRepository.save(produto);

    }


    //Lista todos os produtos
    public List<Produto> findAll(){
        List<Produto> produtos = this.produtoRepository.findAll();
        return produtos;
    }

    //Busca por ID
    public Produto findById(Long id){
        return produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    //Busca por categoria
    public List<Produto> findByCategoria(Long categoriaId) {

        List<Produto> produto = produtoRepository.findByCategoriaId(categoriaId);

        if (produto.isEmpty()) {
            throw new RuntimeException("Nenhum produto encontrado para essa categoria");
        }

        return produto;

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

    //Deletar um produto
    public String delete(Long id){
        produtoRepository.deleteById(id);

        return "Produto deletado com sucesso";
    }

}
