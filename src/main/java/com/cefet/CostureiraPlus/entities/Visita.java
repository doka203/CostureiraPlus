package com.cefet.CostureiraPlus.entities;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_visita")
public class Visita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private LocalDate data;
    @Column(nullable = false)
    private LocalTime hora;
    @Column(nullable = false)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "id_usuario_cliente")
    private Usuario usuarioCliente;

    @ManyToOne
    @JoinColumn(name = "id_usuario_costureira")
    private Usuario usuarioCostureira;

    public Visita() {

    }

    public Visita(long id, LocalDate data, LocalTime hora, String descricao, Usuario usuarioCliente,
            Usuario usuarioCostureira) {
        this.id = id;
        this.data = data;
        this.hora = hora;
        this.descricao = descricao;
        this.usuarioCliente = usuarioCliente;
        this.usuarioCostureira = usuarioCostureira;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Usuario getUsuarioCliente() {
        return usuarioCliente;
    }

    public void setUsuarioCliente(Usuario usuarioCliente) {
        this.usuarioCliente = usuarioCliente;
    }

    public Usuario getUsuarioCostureira() {
        return usuarioCostureira;
    }

    public void setUsuarioCostureira(Usuario usuarioCostureira) {
        this.usuarioCostureira = usuarioCostureira;
    }

    @Override
    public int hashCode() {
        // TODO Auto-generated method stub
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        // TODO Auto-generated method stub
        return super.equals(obj);
    }
}
