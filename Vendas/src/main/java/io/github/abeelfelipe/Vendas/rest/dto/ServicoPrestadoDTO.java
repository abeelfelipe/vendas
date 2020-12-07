package io.github.abeelfelipe.Vendas.rest.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class ServicoPrestadoDTO {
    @NotEmpty(message = "Campo descrição é obrigatório")
    private String descricao;

    @NotEmpty(message = "Campo valor é obrigatório")
    private String valor;

    @NotEmpty(message = "Campo data é obrigatório")
    private String data;

    @NotNull(message = "Campo cliente é obrigatório")
    private Integer idCliente;
}
