package com.cefet.CostureiraPlus.dto;

import com.cefet.CostureiraPlus.entities.Pagamento;

public class PagamentoDTO {

    private long id;
    private String data_vencimento;
    private String data_pagamento;
    private double valor;
    private Long idPedido;
    private String descricaoPedido;


    public PagamentoDTO() {

    }

    public PagamentoDTO(Pagamento pagamento){
        this.id = pagamento.getId();
        this.data_vencimento = pagamento.getDataVencimento();
        this.data_pagamento = pagamento.getDataPagamento();
        this.valor = pagamento.getValor();
        this.idPedido = pagamento.getPedido().getId();
        this.descricaoPedido = pagamento.getPedido().getDescricao();
    }

    public long getId() {
        return id;
    }

    public String getData_vencimento() {
        return data_vencimento;
    }

    public String getData_pagamento() {
        return data_pagamento;
    }

    public double getValor() {
        return valor;
    }

    public Long getIdPedido() {
        return idPedido;
    }

    public String getDescricaoPedido() {
        return descricaoPedido;
    }


}
