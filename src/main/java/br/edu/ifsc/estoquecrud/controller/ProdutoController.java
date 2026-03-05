package br.edu.ifsc.estoquecrud.controller;

import br.edu.ifsc.estoquecrud.dto.ProdutoDTO;
import br.edu.ifsc.estoquecrud.entity.Produto;
import br.edu.ifsc.estoquecrud.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<ProdutoDTO> criarProduto(@RequestBody ProdutoDTO dto){

        ProdutoDTO produtoSalvo = produtoService.criarProduto(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(produtoSalvo);
    }

    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> findAll(){

        List<ProdutoDTO> produtos = produtoService.findAll();

        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDTO> findById(@PathVariable Long id){

        ProdutoDTO produto = produtoService.findById(id);

        return ResponseEntity.ok(produto);
    }

    @GetMapping("/findByCategoria/{id}")
    public ResponseEntity<List<ProdutoDTO>> findByCategoria(@PathVariable Long id){

        List<ProdutoDTO> produtos = produtoService.findByCategoria(id);

        return ResponseEntity.ok(produtos);
    }

    @PutMapping("/{id}")

    public ResponseEntity<Produto> atualizar(@PathVariable Long id, @RequestBody Produto dadosAtualizados) {

        Produto produtoAtualizado = produtoService.atualizar(id, dadosAtualizados);
        return ResponseEntity.ok(produtoAtualizado);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        try{
            this.produtoService.delete(id);
            return ResponseEntity.ok("Produto deletado com sucesso");

        } catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }



}
