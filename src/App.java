import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;


public class App {
    static Empresa empresa;
    static Departamento[] departamentos = new Departamento[10];
    static int numDepartamentos = 0;
    static Funcionario[] funcionarios = new Funcionario[100];
    static int numFuncionarios = 0;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Cadastro de Empresas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel titleLabel = new JLabel("Cadastro de Empresas");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBounds(150, 20, 300, 30);
        panel.add(titleLabel);

        JButton cadastrarEmpresaButton = new JButton("Cadastrar Empresa");
        cadastrarEmpresaButton.setBounds(200, 70, 200, 25);
        panel.add(cadastrarEmpresaButton);

        JButton cadastrarDepartamentoButton = new JButton("Cadastrar Departamento");
        cadastrarDepartamentoButton.setBounds(200, 110, 200, 25);
        panel.add(cadastrarDepartamentoButton);

        JButton cadastrarFuncionarioButton = new JButton("Cadastrar Funcionário");
        cadastrarFuncionarioButton.setBounds(200, 150, 200, 25);
        panel.add(cadastrarFuncionarioButton);

        JButton imprimirDepartamentosButton = new JButton("Imprimir Departamentos");
        imprimirDepartamentosButton.setBounds(200, 190, 200, 25);
        panel.add(imprimirDepartamentosButton);

        JButton imprimirFuncionariosButton = new JButton("Imprimir Funcionários");
        imprimirFuncionariosButton.setBounds(200, 230, 200, 25);
        panel.add(imprimirFuncionariosButton);

        JButton aumentarSalarioButton = new JButton("Aumentar Salário");
        aumentarSalarioButton.setBounds(200, 270, 200, 25);
        panel.add(aumentarSalarioButton);

        JButton imprimirTodosFuncionariosButton = new JButton("Imprimir Todos Funcionários");
        imprimirTodosFuncionariosButton.setBounds(200, 310, 200, 25);
        panel.add(imprimirTodosFuncionariosButton);

        cadastrarEmpresaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarEmpresa();
            }
        });

        cadastrarDepartamentoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarDepartamento();
            }
        });

        cadastrarFuncionarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarFuncionario();
            }
        });

        imprimirDepartamentosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                imprimirDepartamentos();
            }
        });

        imprimirFuncionariosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                imprimirFuncionarios();
            }
        });

        aumentarSalarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aumentarSalario();
            }
        });

        imprimirTodosFuncionariosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                imprimirTodosFuncionarios();
            }
        });
    }

    private static void cadastrarEmpresa() {
        JTextField nomeField = new JTextField();
        JTextField cnpjField = new JTextField();
        Object[] message = {
            "Nome da Empresa:", nomeField,
            "CNPJ da Empresa:", cnpjField
        };
        int option = JOptionPane.showConfirmDialog(null, message, "Cadastrar Empresa", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            empresa = new Empresa();
            empresa.nome = nomeField.getText();
            empresa.cnpj = cnpjField.getText();
            JOptionPane.showMessageDialog(null, "Empresa cadastrada com sucesso!");
        }
    }

    private static void cadastrarDepartamento() {
        JTextField nomeDepartamentoField = new JTextField();
        Object[] message = {
            "Nome do Departamento:", nomeDepartamentoField
        };
        int option = JOptionPane.showConfirmDialog(null, message, "Cadastrar Departamento", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            Departamento departamento = new Departamento(nomeDepartamentoField.getText());
            if (numDepartamentos < departamentos.length) {
                departamentos[numDepartamentos++] = departamento;
                JOptionPane.showMessageDialog(null, "Departamento cadastrado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Erro: Limite máximo de departamentos alcançado.");
            }
        }
    }

    private static void cadastrarFuncionario() {
        JTextField nomeFuncionarioField = new JTextField();
        JTextField salarioFuncionarioField = new JTextField();
        JTextField nomeDepartamentoField = new JTextField();
        Object[] message = {
            "Nome do Funcionário:", nomeFuncionarioField,
            "Salário do Funcionário:", salarioFuncionarioField,
            "Departamento:", nomeDepartamentoField
        };
        int option = JOptionPane.showConfirmDialog(null, message, "Cadastrar Funcionário", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String nomeFuncionario = nomeFuncionarioField.getText();
            double salarioFuncionario = Double.parseDouble(salarioFuncionarioField.getText());
            LocalDateTime dataAdmissao = LocalDateTime.now();
            Funcionario funcionario = new Funcionario(nomeFuncionario, salarioFuncionario, dataAdmissao);
            String nomeDepartamentoPesquisado = nomeDepartamentoField.getText();
            boolean departamentoEncontrado = false;
            for (int i = 0; i < numDepartamentos; i++) {
                if (nomeDepartamentoPesquisado.equalsIgnoreCase(departamentos[i].getNome())) {
                    departamentos[i].adicionarFuncionario(funcionario);
                    funcionarios[numFuncionarios++] = funcionario;
                    departamentoEncontrado = true;
                    JOptionPane.showMessageDialog(null, "Funcionário cadastrado com sucesso!");
                    break;
                }
            }
            if (!departamentoEncontrado) {
                JOptionPane.showMessageDialog(null, "Erro: Departamento não encontrado.");
            }
        }
    }

    private static void imprimirDepartamentos() {
        StringBuilder sb = new StringBuilder();
        sb.append("========== DEPARTAMENTOS ==========\n");
        for (int i = 0; i < numDepartamentos; i++) {
            sb.append((i + 1) + "º departamento: " + departamentos[i].getNome() + "\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString(), "Departamentos", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void imprimirFuncionarios() {
        JTextField nomeDepartamentoField = new JTextField();
        Object[] message = {
            "Nome do Departamento:", nomeDepartamentoField
        };
        int option = JOptionPane.showConfirmDialog(null, message, "Imprimir Funcionários", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String nomeDepartamentoPesquisado = nomeDepartamentoField.getText();
            boolean departamentoEncontrado = false;
            for (int i = 0; i < numDepartamentos; i++) {
                if (nomeDepartamentoPesquisado.equalsIgnoreCase(departamentos[i].getNome())) {
                    departamentoEncontrado = true;
                    StringBuilder sb = new StringBuilder();
                    sb.append("========== FUNCIONÁRIOS ==========\n");
                    sb.append("Departamento: ").append(departamentos[i].getNome()).append("\n");
                    for (int j = 0; j < departamentos[i].getNumFuncionarios(); j++) {
                        sb.append(departamentos[i].getFuncionarios()[j]).append("\n");
                    }
                    JOptionPane.showMessageDialog(null, sb.toString(), "Funcionários", JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
            }
            if (!departamentoEncontrado) {
                JOptionPane.showMessageDialog(null, "Erro: Departamento não encontrado.");
            }
        }
    }

    private static void aumentarSalario() {
        JTextField nomeDepartamentoField = new JTextField();
        Object[] message = {
            "Nome do Departamento:", nomeDepartamentoField
        };
        int option = JOptionPane.showConfirmDialog(null, message, "Aumentar Salário", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String nomeDepartamentoPesquisado = nomeDepartamentoField.getText();
            boolean departamentoEncontrado = false;
            for (int i = 0; i < numDepartamentos; i++) {
                if (nomeDepartamentoPesquisado.equalsIgnoreCase(departamentos[i].getNome())) {
                    departamentoEncontrado = true;
                    for (int j = 0; j < departamentos[i].getNumFuncionarios(); j++) {
                        Funcionario funcionario = departamentos[i].getFuncionarios()[j];
                        funcionario.salario *= 1.10;
                    }
                    JOptionPane.showMessageDialog(null, "Salários aumentados com sucesso no departamento: " + nomeDepartamentoPesquisado);
                    break;
                }
            }
            if (!departamentoEncontrado) {
                JOptionPane.showMessageDialog(null, "Erro: Departamento não encontrado.");
            }
        }
    }

    private static void imprimirTodosFuncionarios() {
        StringBuilder sb = new StringBuilder();
        sb.append("========== ").append(empresa.nome).append(" ==========\n");
        sb.append("Funcionários:\n");
        for (int i = 0; i < numDepartamentos; i++) {
            Departamento departamento = departamentos[i];
            for (int j = 0; j < departamento.getNumFuncionarios(); j++) {
                Funcionario funcionario = departamento.getFuncionarios()[j];
                if (funcionario != null) {
                    sb.append("Nome: ").append(funcionario.nome)
                            .append(" - Salário: ").append(funcionario.salario)
                            .append(" - Departamento: ").append(departamento.getNome())
                            .append(" - Data de Admissão: ").append(funcionario.dataAdmissao)
                            .append("\n");
                }
            }
        }
        JOptionPane.showMessageDialog(null, sb.toString(), "Todos os Funcionários", JOptionPane.INFORMATION_MESSAGE);
    }
}
