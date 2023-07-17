package br.com.projeto.regpasweb.models;

import jakarta.persistence.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
@Entity
public class Passageiro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String sobrenome;
    @Column(nullable = false)
    private String rg;
    @Column
    private String nascimento;
    @Column(nullable = false)
    private String telefone;
    @Column
    private StatusPassageiro statusPassageiro;
    @Column
    private Integer onibus;
    @Column
    private String pagamento;
    @Column
    private String nomeImagem;

    @Column
    private String tipoImagem;

    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private byte[] dadosImagem;


    public String getPagamento() {
        return pagamento;
    }

    public void setPagamento(String pagamento) {
        this.pagamento = pagamento;
    }

    public Integer getOnibus() {
        return onibus;
    }

    public void setOnibus(Integer onibus) {
        this.onibus = onibus;
    }

    public String getNomeImagem() {
        return nomeImagem;
    }

    public void setNomeImagem(String nomeImagem) {
        this.nomeImagem = nomeImagem;
    }

    public String getTipoImagem() {
        return tipoImagem;
    }

    public void setTipoImagem(String tipoImagem) {
        this.tipoImagem = tipoImagem;
    }

    public byte[] getDadosImagem() {
        return dadosImagem;
    }

    public void setDadosImagem(byte[] dadosImagem) {
        this.dadosImagem = dadosImagem;
    }

    public Passageiro() {
    }

    public Passageiro(String nome, String sobrenome, String rg, String telefone, StatusPassageiro statusPassageiro) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.rg = rg;
        this.telefone = telefone;
        this.statusPassageiro = statusPassageiro;
    }
    public Passageiro(String nome, String sobrenome, String rg, String telefone, String nascimento, StatusPassageiro statusPassageiro) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.rg = rg;
        this.telefone = telefone;
        this.nascimento = nascimento;
        this.statusPassageiro = statusPassageiro;
    }
    public Passageiro(String nome, String sobrenome, String rg, String telefone, String nascimento, StatusPassageiro statusPassageiro, Integer onibus, String pagamento) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.rg = rg;
        this.telefone = telefone;
        this.nascimento = nascimento;
        this.statusPassageiro = statusPassageiro;
        this.onibus = onibus;
        this.pagamento = pagamento;
    }

    public Passageiro(String nome, String sobrenome, String rg, String telefone) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.rg = rg;
        this.telefone = telefone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public StatusPassageiro getStatusPassageiro() {
        return statusPassageiro;
    }

    public void setStatusPassageiro(StatusPassageiro statusPassageiro) {
        this.statusPassageiro = statusPassageiro;
    }

}
