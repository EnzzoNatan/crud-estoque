package br.edu.ifsc.estoquecrud.repository;

import br.edu.ifsc.estoquecrud.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    boolean existsByNome(String nome);
}
