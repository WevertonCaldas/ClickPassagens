package br.edu.catolica.classes;

import java.io.Serializable;

public class Passagem implements Serializable {
    private String origem, destino, nome, idsecundario;
    private Float valor;
    private String data;
    private String hora;
    private int id;


    public Passagem(int id, String origem, String destino, String nome, Float valor, String data, String hora, String idsecundario) {
        this.id = id;
        this.origem = origem;
        this.destino = destino;
        this.nome = nome;
        this.valor = valor;
        this.data = data;
        this.hora = hora;
        this.idsecundario = idsecundario;
    }

    public Passagem(int id, String origem, String destino, String nome, Float valor, String data, String hora) {
        this.id = id;
        this.origem = origem;
        this.destino = destino;
        this.nome = nome;
        this.valor = valor;
        this.data = data;
        this.hora = hora;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getIdsecundario() {
        return idsecundario;
    }

    public void setIdsecundario(String idsecundario) {
        this.idsecundario = idsecundario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
