class Departamento {
    private String nome;
    Funcionario[] funcionarios = new Funcionario[100];
    private int numFuncionarios = 0;

    public Departamento(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void adicionarFuncionario(Funcionario funcionario) {
        if (numFuncionarios < 100) {
            funcionarios[numFuncionarios] = funcionario;
            numFuncionarios++;
        } else {
            System.out.println("Erro: Limite máximo de funcionários alcançado.");
        }
    }

    public Funcionario[] getFuncionarios() {
        return funcionarios;
    }

    public int getNumFuncionarios() {
        return numFuncionarios;
    }
}
