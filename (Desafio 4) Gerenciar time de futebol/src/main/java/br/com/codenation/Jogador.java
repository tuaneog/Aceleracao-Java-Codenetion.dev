package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;

public class Jogador  {

    private Long id, idTime;
    private String nome;
    private LocalDate dataNascimento;
    private int nivelHabilidade;
    private BigDecimal salario;

    public Jogador(Long id, Long idTime, String nome, LocalDate dataNascimento,
                   int nivelHabilidade, BigDecimal salario) {
        this.id = id;
        this.idTime = idTime;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.nivelHabilidade = nivelHabilidade;
        this.salario = salario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdTime() {
        return idTime;
    }

    public void setIdTime(Long idTime) {
        this.idTime = idTime;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public int getNivelHabilidade() {
        return nivelHabilidade;
    }

    public void setNivelHabilidade(int nivelHabilidade) {
        this.nivelHabilidade = nivelHabilidade;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public int getIdade() {
        int idadeEmAnos = LocalDate.now().getYear() - dataNascimento.getYear();
        if (LocalDate.now().getDayOfYear() <= dataNascimento.getDayOfYear()) {
            idadeEmAnos -= 1;
        }
        return idadeEmAnos;
    }

}
class JogadorComparator implements Comparator<Jogador> {
    @Override
    public int compare(Jogador j1, Jogador j2) {
        if(j1.getNivelHabilidade() > j2.getNivelHabilidade()) {
            return -1;

        } if(j2.getNivelHabilidade() > j1.getNivelHabilidade()){
            return 1;

        } else if(j1.getId() < j2.getId()){
            return -1;

        } else if(j2.getId() < j1.getId()){
            return 1;

        }
        return 0;
    }
}