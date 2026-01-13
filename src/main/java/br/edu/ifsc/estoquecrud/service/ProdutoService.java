package br.edu.ifsc.estoquecrud.service;

import br.edu.ifsc.estoquecrud.entity.Produto;
import br.edu.ifsc.estoquecrud.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    //Cria um produto
    public String criarProduto(Produto produto,Long categoriaId) {
        this.produtoRepository.save(produto);
        return "Produto criado com sucesso";
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

        Produto produto = findById(id);

        produto.setNome(dadosAtualizados.getNome());
        produto.setDescricao(dadosAtualizados.getDescricao());
        produto.setPreco(dadosAtualizados.getPreco());
        produto.setQuantidade(dadosAtualizados.getQuantidade());

        return produtoRepository.save(produto);
    }





}
