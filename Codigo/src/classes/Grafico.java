package classes;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Grafico {
    public void gerarGraficoSetores() throws IOException {

        PersistenciaJson pj = new PersistenciaJson();
        ArrayList<Funcionario> listaDesenvolvedores = pj.ler("desenvolvedores");
        ArrayList<Funcionario> listaAnalistas = pj.ler("analistas");
        ArrayList<Funcionario> listaGerentes = pj.ler("gerentes");
        int totalDesenvolvedores = 0;
        int totalAnalistas = 0;
        int totalGerentes = 0;

        for (Funcionario dev : listaDesenvolvedores){
            totalDesenvolvedores += dev.getSalarioBruto();
        }

        for (Funcionario analista : listaAnalistas){
            totalAnalistas += analista.getSalarioBruto();
        }

        for (Funcionario gerente : listaGerentes){
            totalGerentes += gerente.getSalarioBruto();
        }

        FileWriter graficoPizza = new FileWriter("src/persistencia/GráficoSetores.html");
        PrintWriter gravarPizza = new PrintWriter(graficoPizza);

        gravarPizza.printf("<html>\r\n  <head>\r\n    <script type=\"text/javascript\" src=\"https://www.gstatic.com/charts/loader.js\"></script>\r\n    <script type=\"text/javascript\">\r\n      google.charts.load(\"current\", {packages:[\"corechart\"]});\r\n      google.charts.setOnLoadCallback(drawChart);\r\n      function drawChart() {\r\n        var data = google.visualization.arrayToDataTable([\r\n          ['Setor', 'Gasto'],\r\n");

        gravarPizza.printf("          ['Desenvolvedores'," + totalDesenvolvedores + "],\r\n");
        gravarPizza.printf("          ['Analistas'," + totalAnalistas + "],\r\n");
        gravarPizza.printf("          ['Gerentes'," + totalGerentes + "],\r\n");

        gravarPizza.printf("]);\r\n\r\n        var options = {\r\n          title: 'Gasto total por setores',\r\n          is3D: true,\r\n        };\r\n\r\n        var chart = new google.visualization.PieChart(document.getElementById('piechart_3d'));\r\n        chart.draw(data, options);\r\n      }\r\n    </script>\r\n  </head>\r\n  <body>\r\n    <div id=\"piechart_3d\" style=\"width: 600px; height: 500px;\"></div>\r\n  </body>\r\n</html>");
        graficoPizza.close();
    }
}
