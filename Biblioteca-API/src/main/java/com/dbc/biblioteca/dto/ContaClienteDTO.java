package com.dbc.biblioteca.dto;

import com.dbc.biblioteca.entity.StatusCliente;
import com.dbc.biblioteca.entity.TipoCliente;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder(value = { "idCliente", "nome" }, alphabetic = true)
public class ContaClienteDTO extends ContaClienteCreateDTO{
    private Integer idCliente;

    private String nome;
    private String telefone;
    private String email;
    private TipoCliente tipoCliente;

    @ApiModelProperty(value = "Status do Cliente -> ATIVO, CANCELADO e BLOQUEADO")
    private StatusCliente status;

    @ApiModelProperty(value = "Quantidade de pontos fidelidade do cliente")
    private Integer pontosFidelidade;
}
