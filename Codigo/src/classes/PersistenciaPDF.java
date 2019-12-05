package classes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.awt.Desktop;

public class PersistenciaPDF {

    public void gerarPdf(FolhaPagamento fp) throws IOException {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("src/persistencia/FolhaPDF.pdf"));
            document.open();

            Paragraph p = new Paragraph("Folha de Pagamento");
            p.setAlignment(Element.ALIGN_CENTER);
            document.add(p);
            p = new Paragraph("Empresa X de desenvolvimento de sistemas");
            p.setAlignment(Element.ALIGN_CENTER);
            document.add(p);
            p = new Paragraph(" ");
            document.add(p);

            p = new Paragraph("Funcionário:     " + fp.getFuncionario().getNome());
            document.add(p);
            p = new Paragraph("Código:    " + fp.getFuncionario().getCodigo());
            document.add(p);
            p = new Paragraph("Setor:   " + fp.getFuncionario().getSetor());
            document.add(p);
            p = new Paragraph(" ");
            document.add(p);

            p = new Paragraph("Horas:   " + fp.getFuncionario().getHorasTotais());
            document.add(p);
            p = new Paragraph("Salário bruto:   R$ " + fp.getFuncionario().getSalarioBruto());
            document.add(p);
            p = new Paragraph("INSS:    R$ " + fp.descontoInss());
            document.add(p);
            p = new Paragraph("IRRF:    R$ " + fp.descontoIrrf());
            document.add(p);
            p = new Paragraph("Vale transporte:     R$ " + fp.descontoValeTransporte());
            document.add(p);
            p = new Paragraph(" ");
            document.add(p);

            p = new Paragraph("Total de descontos:  R$ " + fp.totalDescontos());
            document.add(p);
            p = new Paragraph("Salário líquido:     R$ " + fp.getSalarioLiquido());
            document.add(p);

        } catch (DocumentException | IOException de) {
            System.err.println(de.getMessage());
        }
        document.close();

        Desktop desktop = Desktop.getDesktop();
        desktop.open(new File("src/persistencia/FolhaPDF.pdf"));
    }
}

