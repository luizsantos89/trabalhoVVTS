package br.com.lojaWEB.model;

public class Fornecedor {
    
    private String codigo;
    private String nome;
    private String endereco;
    private String cnpj;

    public Fornecedor(String codigo, String nome, String endereco, String cnpj) {
        this.codigo = codigo;
        this.nome = nome;
        this.endereco = endereco;
        this.cnpj = cnpj;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
    
    
    
}
