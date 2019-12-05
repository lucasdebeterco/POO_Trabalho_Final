package sample;

import classes.Funcionario;
import classes.Lista;
import classes.PersistenciaJson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;

public class Controller {

    @FXML private TextField numDev;
    @FXML private TextField numAnalistas;
    @FXML private TextField numGerentes;

    public void botaoGerarFunc(ActionEvent event) throws IOException {
        Lista l = new Lista();
        ArrayList<Funcionario> listaDesenvolvedores = l.gerarListaDesenvolvedores(Integer.parseInt(this.numDev.getText()));
        ArrayList<Funcionario> listaAnalistas = l.gerarListaAnalistas(Integer.parseInt(this.numAnalistas.getText()));
        ArrayList<Funcionario> listaGerentes = l.gerarListaGerentes(Integer.parseInt(this.numGerentes.getText()));

        PersistenciaJson pj = new PersistenciaJson();
        pj.gravarLista(listaDesenvolvedores,listaAnalistas,listaGerentes);
        trocarTela(event);
    }

    @FXML
    public void trocarTela(ActionEvent event) throws IOException {
        Parent view2 = FXMLLoader.load(getClass().getResource("sample2.fxml"));
        Scene scene2 = new Scene(view2);

        Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene2);
        window.show();
    }
}
