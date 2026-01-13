package br.edu.ifsc.estoquecrud.repository;

import br.edu.ifsc.estoquecrud.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    public List<Produto> findByNome(String nome);
    List<Produto> findByCategoriaId(Long categoriaId);
}
