package br.com.projeto.regpasweb.dto;

import br.com.projeto.regpasweb.models.Passageiro;
import br.com.projeto.regpasweb.models.StatusPassageiro;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;
public class RequisicaoEditPassageiro {
    private String nome;
    private String sobrenome;
    private String rg;
    private String telefone;
    private String nascimento;
    private StatusPassageiro statusPassageiro;
    private Integer onibus;
    private String pagamento;


    public String getPagamento() {
        return pagamento;
    }

    public void setPagamento(String pagamento) {
        this.pagamento = pagamento;
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    public StatusPassageiro getStatusPassageiro() {
        return statusPassageiro;
    }

    public void setStatusPassageiro(StatusPassageiro statusPassageiro) {
        this.statusPassageiro = statusPassageiro;
    }

    public Integer getOnibus() {
        return onibus;
    }

    public void setOnibus(Integer onibus) {
        this.onibus = onibus;
    }

    public Passageiro toPassageiro(Passageiro passageiro)
    {
        passageiro.setNome(this.nome);
        passageiro.setSobrenome(this.sobrenome);
        passageiro.setRg(this.rg);
        passageiro.setTelefone(this.telefone);
        passageiro.setNascimento(this.nascimento);
        passageiro.setStatusPassageiro(this.statusPassageiro);
        passageiro.setOnibus(this.onibus);
        passageiro.setPagamento(this.pagamento);
        return passageiro;

    }
    public void fromPassageiro(Passageiro passageiro)
    {
        this.nome=passageiro.getNome();
        this.sobrenome=passageiro.getSobrenome();
        this.rg=passageiro.getRg();
        this.telefone=passageiro.getTelefone();
        this.nascimento=passageiro.getNascimento();
        this.statusPassageiro=passageiro.getStatusPassageiro();
        this.onibus=passageiro.getOnibus();
        this.pagamento=passageiro.getPagamento();
    }


}
