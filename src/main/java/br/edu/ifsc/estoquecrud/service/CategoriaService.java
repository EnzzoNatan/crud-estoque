package br.edu.ifsc.estoquecrud.service;


import br.edu.ifsc.estoquecrud.entity.Categoria;
import br.edu.ifsc.estoquecrud.exception.RegraNegocioException;
import br.edu.ifsc.estoquecrud.repository.CategoriaRepository;
import br.edu.ifsc.estoquecrud.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;



    //Cria categoria
    public String criar(Categoria categoria) {

        if (categoriaRepository.existsByNome(categoria.getNome())) {
            return "Impossível criar categoria com o mesmo nome!";
        }

        categoriaRepository.save(categoria);
        return "Categoria criada com sucesso";
    }


    //Lista todas as categorias
    public List<Categoria> findAll() {
        List<Categoria> categoria = categoriaRepository.findAll();
        return categoria;
    }

    //Busca categoria por ID
    public Categoria findById(Long id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada com id: " + id));
    }

    //Atualiza os dados da categoria
    public String update(Categoria categoria, Long id) {
        categoria.setId(id);
        this.categoriaRepository.save(categoria);
        return "Categoria atualizada com sucesso";
    }

    //Apaga a categoria
    public String delete(Long id) {
        this.categoriaRepository.deleteById(id);
        return "Categoria deletada com sucesso";
    }
}


