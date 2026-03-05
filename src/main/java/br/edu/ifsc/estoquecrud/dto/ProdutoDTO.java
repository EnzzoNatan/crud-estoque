package br.edu.ifsc.estoquecrud.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class ProdutoDTO {

    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private Integer quantidade;

    private Long categoriaId;
    private String categoriaNome;
}