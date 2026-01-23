package br.edu.ifsc.estoquecrud.controller;

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

    @PostMapping("/criar")
    public ResponseEntity<Produto> criarProduto(@RequestBody Produto produto) {
        Produto produtoSalvo = produtoService.criarProduto(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoSalvo);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Produto>>findAll(){
        try{
            List<Produto> produtros = this.produtoService.findAll();
            return new ResponseEntity<>(produtros, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>((HttpHeaders) null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Produto> findById(@PathVariable Long id){
        try{
            Produto produto = this.produtoService.findById(id);
            return new ResponseEntity<>(produto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>((HttpHeaders) null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findByCategoria/{id}")
    public ResponseEntity<List<Produto>> findByCategoria(@PathVariable Long id){
        try{
            List<Produto> produtos = this.produtoService.findByCategoria(id);
            return new ResponseEntity<>(produtos, HttpStatus.OK);
        }  catch (Exception e) {
            return new ResponseEntity<>((HttpHeaders) null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/atualizar/{id}")

    public ResponseEntity<Produto> atualizar(@PathVariable Long id, @RequestBody Produto dadosAtualizados) {

        Produto produtoAtualizado = produtoService.atualizar(id, dadosAtualizados);
        return ResponseEntity.ok(produtoAtualizado);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id){
        try{
            String mensagem = this.produtoService.delete(id);
            return new ResponseEntity<>(mensagem, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("null", HttpStatus.BAD_REQUEST);
        }
    }


}
