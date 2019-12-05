package classes;

public class FolhaPagamento {

    private float horas;
    private float horasExtras;
    private float salarioLiquido;
    private Funcionario funcionario;

    public float getHoras() {
        return horas;
    }

    public void setHoras(float horas) {
        this.horas = horas;
    }

    public float getHorasExtras() {
        return horasExtras;
    }

    public void setHorasExtras(float horasExtras) {
        this.horasExtras = horasExtras;
    }

    public float getSalarioLiquido() {
        return salarioLiquido;
    }

    public void setSalarioLiquido(float salarioLiquido) {
        this.salarioLiquido = salarioLiquido;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public FolhaPagamento(Funcionario funcionario) {
        this.funcionario = funcionario;
        funcionario.setFolhaPagamento(this);
        calculaDescontos();
    }

    public float descontoInss(){
        float desconto = 0;
        float sb = funcionario.getSalarioBruto();
        if (sb > 0 && sb < 1693.72){
            desconto = sb * 0.08f;
        }
        else if (sb >= 1693.72 && sb < 2822.90){
            desconto = sb * 0.09f;
        }
        else if (sb >= 2822.90 && sb < 5839.45){
            desconto = sb * 0.11f;
        }
        else {
            desconto = 642.34f;
        }
        return desconto;
    }

    public float descontoIrrf(){
        float desconto = 0;
        float sb = funcionario.getSalarioBruto();

        float baseFgts = sb - descontoInss();
        if (baseFgts > 1903.98 && baseFgts < 2826.65) {
            desconto = baseFgts * 0.075f;
            desconto -= 142.80;
        }
        else if (baseFgts >= 2826.65 && baseFgts < 3751.05) {
            desconto = baseFgts * 0.15f;
            desconto -= 354.80;
        }
        else if (baseFgts >= 3751.05 && baseFgts < 4664.68) {
            desconto = baseFgts * 0.225f;
            desconto -= 636.13;
        }
        else if (baseFgts >= 4664.68) {
            desconto = baseFgts * 0.275f;
            desconto -= 636.13;
        }
        return desconto;
    }

    public float descontoValeTransporte(){
        return funcionario.getSalarioBruto()*0.06f;
    }

    public float totalDescontos(){
        return descontoIrrf() + descontoInss() + descontoValeTransporte();
    }

    public float calculaDescontos(){
        this.salarioLiquido  = funcionario.getSalarioBruto() - totalDescontos();
        return this.salarioLiquido;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("FolhaPagamento{");
        sb.append(" Horas = ").append(horas);
        sb.append(", Horas Extras = ").append(horasExtras);
        sb.append(", Salário Bruto = ").append(funcionario.getSalarioBruto());
        sb.append(", Salário Líquido = ").append(salarioLiquido);
        sb.append(", Código do funcionário = ").append(funcionario.getCodigo());
        sb.append(", Nome do funcionário = ").append(funcionario.getNome());
        sb.append(", Setor do funcionário = ").append(funcionario.getSetor());
        sb.append('}');
        return sb.toString();
    }
}