package br.edu.catolica.classes;

import java.io.Serializable;

public class PrestadorDeServico implements Serializable {

    private String registroCNH, nome, CPF, senha;

    public PrestadorDeServico(String nome, String registroCNH, String CPF, String senha) {
        this.registroCNH = registroCNH;
        this.nome = nome;
        this.CPF = CPF;
        this.senha = senha;
    }

    public PrestadorDeServico(String CPF, String senha) {
        this.CPF = CPF;
        this.senha = senha;
    }

    public String getRegistroCNH() {
        return registroCNH;
    }

    public void setRegistroCNH(String registroCNH) {
        this.registroCNH = registroCNH;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
