package sample;

import classes.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Controller2 {

    @FXML private ListView<Funcionario> listViewDevs;
    @FXML private ListView<Funcionario> listViewAnalistas;
    @FXML private ListView<Funcionario> listViewGerentes;

    public void botaoImport(ActionEvent event) {
        PersistenciaJson pj = new PersistenciaJson();
        ArrayList<Funcionario> listaDesenvolvedores = pj.ler("desenvolvedores");
        ArrayList<Funcionario> listaAnalistas = pj.ler("analistas");
        ArrayList<Funcionario> listaGerentes = pj.ler("gerentes");
        gerarListViews(listaDesenvolvedores, listaAnalistas, listaGerentes);
    }

    public void gerarListViews(ArrayList<Funcionario> listaDesenvolvedores, ArrayList<Funcionario> listaAnalistas, ArrayList<Funcionario> listaGerentes){
        for (Funcionario dev : listaDesenvolvedores){
            listViewDevs.getItems().add(dev);
        }
        for (Funcionario analista : listaAnalistas){
            listViewAnalistas.getItems().add(analista);
        }
        for (Funcionario gerente : listaGerentes){
            listViewGerentes.getItems().add(gerente);
        }
    }

    public void selecionarDev(ActionEvent event) throws IOException {
        Imprimir i = new Imprimir();
        Funcionario selecionado = listViewDevs.getSelectionModel().getSelectedItems().get(0);
        FolhaPagamento fp = new FolhaPagamento(selecionado);
        abrirJanelaFolha(fp);

    }
    public void selecionarAnalista(ActionEvent event) throws IOException {
        Funcionario selecionado  = (listViewAnalistas.getSelectionModel().getSelectedItems()).get(0);
        FolhaPagamento fp = new FolhaPagamento(selecionado);
        abrirJanelaFolha(fp);
    }
    public void selecionarGerente(ActionEvent event) throws IOException {
        Funcionario selecionado = listViewGerentes.getSelectionModel().getSelectedItems().get(0);
        FolhaPagamento fp = new FolhaPagamento(selecionado);
        abrirJanelaFolha(fp);
    }

    public void abrirJanelaFolha(FolhaPagamento fp) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sample3.fxml"));
        Parent view3 = (Parent) loader.load();
        Controller3 controller3 = loader.getController();
        controller3.setFolha(fp);
        Stage window = new Stage();
        window.setScene(new Scene(view3));
        window.setTitle("Trabalho Final");
        window.show();
    }

    public void gerarGraficoSetor(ActionEvent event) throws IOException {
        Grafico g = new Grafico();
        g.gerarGraficoSetores();
        Desktop desktop = Desktop.getDesktop();
        desktop.open(new File("src/persistencia/GráficoSetores.html"));
    }
}
