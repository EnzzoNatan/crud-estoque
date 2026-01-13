package br.edu.ifsc.estoquecrud.controller;


import br.edu.ifsc.estoquecrud.entity.Categoria;
import br.edu.ifsc.estoquecrud.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @PostMapping("/criar")
    public ResponseEntity<String> criar(@RequestBody Categoria categoria) {
        try {
            String mensagem = this.categoriaService.criar(categoria);
            return new ResponseEntity<>(mensagem, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("null", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Categoria>> findAll() {
        try{
            List<Categoria> categoria = categoriaService.findAll();
            return new ResponseEntity<>(categoria, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>((HttpHeaders)  null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Categoria> findById(@PathVariable Long id) {
        try {
            Categoria categoria = this.categoriaService.findById(id);
            return new ResponseEntity<>(categoria, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>((HttpHeaders)  null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@RequestBody Categoria categoria,
                                         @PathVariable Long id ) {
        try{
            String mensagem = this.categoriaService.update(categoria, id);
            return new ResponseEntity<>(mensagem, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>("null", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        try{
            String mensagem = this.categoriaService.delete(id);
            return new ResponseEntity<>(mensagem, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>("null", HttpStatus.BAD_REQUEST);
        }
    }
}
