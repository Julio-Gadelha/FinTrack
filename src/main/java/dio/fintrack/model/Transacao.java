package dio.fintrack.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Transacao {
    private Integer id;
    private String descricao;
    private BigDecimal valor;
    private Tipo Tipo;
    private LocalDate data;

    public Transacao(String salário, BigDecimal bigDecimal, Tipo receita, LocalDate now) {

    }

    public Transacao(Integer id, String descricao, BigDecimal valor, Tipo tipo, LocalDate data) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        Tipo = tipo;
        this.data = data;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Tipo getTipo() {
        return Tipo;
    }

    public void setTipo(Tipo tipo) {
        Tipo = tipo;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
}