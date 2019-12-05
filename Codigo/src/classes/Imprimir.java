package classes;

import java.awt.*;
import java.awt.print.*;

public class Imprimir {

    public String criarStringImpressao(FolhaPagamento fp) {
        String conteudo =
                "   Empresa de desenvolvimento de sistemas" + ";" +
                        "   Folha de Pagamento" + ";;" +
                        "   ====================================" + ";" +
                        "   Funcionário: " + fp.getFuncionario().getNome() + ";" +
                        "   Código: " + fp.getFuncionario().getCodigo() + ";" +
                        "   Setor: " + fp.getFuncionario().getSetor() + ";" +
                        "   Horas: " + fp.getFuncionario().getHorasTotais() + ";;" +

                        "   Salário bruto: R$ " + fp.getFuncionario().getSalarioBruto() + ";" +
                        "   INSS: R$ " + fp.descontoInss() + ";" +
                        "   IRRF: R$ " + fp.descontoIrrf() + ";" +
                        "   Vale transporte: R$ " + fp.descontoValeTransporte() + ";;" +

                        "   Total de descontos: R$ " + fp.totalDescontos() + ";" +
                        "   Salário líquido: R$ " + fp.getSalarioLiquido() + ";" +
                        "   ====================================";
        return conteudo;
    }

    ;

    public boolean imprimirFolha(FolhaPagamento fp) {
        String conteudo = criarStringImpressao(fp);

        final PrinterJob job = PrinterJob.getPrinterJob();
        Printable contentToPrint = new Printable() {

            public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
                Graphics2D g2d = (Graphics2D) graphics;
                g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
                g2d.setFont(new Font("Monospaced", Font.BOLD, 8));

                String[] billz = conteudo.split(";");
                int y = 15;
                //draw each String in a separate line
                for (int i = 0; i < billz.length; i++) {
                    graphics.drawString(billz[i], 5, y);
                    y = y + 15;
                }

                if (pageIndex > 0) {
                    return NO_SUCH_PAGE;
                } //Only one page

                return PAGE_EXISTS;
            }
        };

        PageFormat pageFormat = new PageFormat();
        pageFormat.setOrientation(PageFormat.PORTRAIT);
        Paper pPaper = pageFormat.getPaper();

        pPaper.setImageableArea(0, 0, pPaper.getWidth(), pPaper.getHeight() - 2);
        pageFormat.setPaper(pPaper);
        job.setPrintable(contentToPrint, pageFormat);

        try {
            job.print();

        } catch (PrinterException e) {
            System.err.println(e.getMessage());
        }
        return true;
    }

}