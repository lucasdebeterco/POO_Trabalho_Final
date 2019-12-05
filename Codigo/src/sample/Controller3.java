package sample;

import classes.FolhaPagamento;
import classes.Imprimir;
import classes.PersistenciaPDF;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;

import java.io.IOException;

public class Controller3 {

    private FolhaPagamento folha;
    @FXML private Label codigo;
    @FXML private Label nome;
    @FXML private Label setor;
    @FXML private Label horas;

    @FXML private Label salarioBruto;
    @FXML private Label inss;
    @FXML private Label irrf;
    @FXML private Label valeTrans;

    @FXML private Label totalDescontos;
    @FXML private Label salarioLiquido;


    public FolhaPagamento getFolha() {
        return folha;
    }

    public void setFolha(FolhaPagamento folha) {
        this.folha = folha;
        escreveFolha();
    }

    public void escreveFolha() {
        nome.setText("Funcionário: " + folha.getFuncionario().getNome());
        codigo.setText("Código do Funcionário: " + folha.getFuncionario().getCodigo());
        setor.setText("Setor: " + folha.getFuncionario().getSetor());
        horas.setText("Horas: " + folha.getFuncionario().getHorasTotais());

        salarioBruto.setText("Salário bruto: R$ " + folha.getFuncionario().getSalarioBruto());
        inss.setText("Desconto de INSS: R$ " + folha.descontoInss());
        irrf.setText("Desconto de IRRF: R$ " + folha.descontoIrrf());
        valeTrans.setText("Desconto de Vale Transporte: R$ " + folha.descontoValeTransporte());

        totalDescontos.setText("Total dos descontos: R$ " + folha.totalDescontos());
        salarioLiquido.setText("Salário Líquido: R$ " + folha.getSalarioLiquido());

    }

    public void imprimir(ActionEvent event) {
        Imprimir imp = new Imprimir();
        imp.imprimirFolha(folha);
    }

    public void botaoPdf(ActionEvent event) throws IOException {
        PersistenciaPDF pdf = new PersistenciaPDF();
        pdf.gerarPdf(folha);
    }
}
