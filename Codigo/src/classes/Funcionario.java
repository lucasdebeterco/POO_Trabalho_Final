package classes;

import java.util.Random;

public class Funcionario {
    private int codigo;
    private String nome;
    private Setor setor;
    private float salarioBruto;
    private int horasTotais;
    private FolhaPagamento folhaPagamento;

    public Funcionario(int codigo, String nome, Setor setor) {
        this.codigo = codigo;
        this.nome = nome;
        this.setor = setor;
        this.horasTotais = gerarHoras();
        this.salarioBruto = calculaSalarioBruto();
    }

    public int getCodigo() { return codigo; }

    public void setCodigo(int codigo) { this.codigo = codigo; }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }

    public FolhaPagamento getFolhaPagamento() {
        return folhaPagamento;
    }

    public void setFolhaPagamento(FolhaPagamento folhaPagamento) {
        this.folhaPagamento = folhaPagamento;
    }

    public float calculaSalarioBruto() {
        float valorHora = 0;

        Random aleatorio = new Random();
        if (this.setor == Setor.DESENVOLVEDOR) {
            valorHora = aleatorio.nextInt(8) + 12;
        } else if (this.setor == Setor.ANALISTA) {
            valorHora = aleatorio.nextInt(8) + 18;
        } else if (this.setor == Setor.GERENTE) {
            valorHora = aleatorio.nextInt(20) + 40;
        }
        this.salarioBruto = valorHora * horasTotais;
        return salarioBruto;
    }

    public int gerarHoras() {
        Random aleatorio = new Random();
        return 200 + (aleatorio.nextInt(5));
    }

    public int getHorasTotais() {
        return this.horasTotais;
    }

    public float getSalarioBruto() {
        return this.salarioBruto;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(codigo).append(" - ").append(nome);
        return sb.toString();
    }
}