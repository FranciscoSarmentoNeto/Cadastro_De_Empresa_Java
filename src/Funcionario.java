import java.time.LocalDateTime;

class Funcionario {
    String nome;
    double salario;
    LocalDateTime dataAdmissao;

    public Funcionario(String nome, double salario, LocalDateTime dataAdmissao) {
        this.nome = nome;
        this.salario = salario;
        this.dataAdmissao = dataAdmissao;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + " - Salário: " + salario + " - Data de Admissão: " + dataAdmissao;
    }
}