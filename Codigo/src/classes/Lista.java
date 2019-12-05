package classes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Lista {

    public ArrayList<Funcionario> gerarListaDesenvolvedores(int quantidade){
        String url = "http://www.curvello.com/gerador/nomecompleto/" + quantidade;
        List<String> listaNomes = gerarNomesAleatorios(url);
        ArrayList<Funcionario> listaDesenvolvedores = new ArrayList<Funcionario>();
        for (int i = 0 ; i < quantidade; i++){
            Funcionario f = new Funcionario((i+1),listaNomes.get(i), Setor.DESENVOLVEDOR);
            listaDesenvolvedores.add(f);
        }
        return listaDesenvolvedores;
    }

    public ArrayList<Funcionario> gerarListaAnalistas(int quantidade){
        String url = "http://www.curvello.com/gerador/nomecompleto/" + quantidade;
        List<String> listaNomes = gerarNomesAleatorios(url);
        ArrayList<Funcionario> listaAnalistas = new ArrayList<Funcionario>();
        for (int i = 0 ; i < quantidade; i++){
            Funcionario f = new Funcionario((i+1),listaNomes.get(i), Setor.ANALISTA);
            listaAnalistas.add(f);
        }
        return listaAnalistas;
    }

    public ArrayList<Funcionario> gerarListaGerentes(int quantidade){
        String url = "http://www.curvello.com/gerador/nomecompleto/" + quantidade;
        List<String> listaNomes = gerarNomesAleatorios(url);
        ArrayList<Funcionario> listaGerentes = new ArrayList<Funcionario>();
        for (int i = 0 ; i < quantidade; i++){
            Funcionario f = new Funcionario((i+1),listaNomes.get(i), Setor.GERENTE);
            listaGerentes.add(f);
        }
        return listaGerentes;
    }
    public List<String> gerarNomesAleatorios(String urlRequisicao) {
        List<String> lista = new ArrayList<String>();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        try {
            URL url = new URL(urlRequisicao);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Accept-Charset", "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String linha;
            while ((linha = bufferedReader.readLine()) != null) {
                Type listType = new TypeToken<ArrayList<String>>() { }.getType();
                lista = gson.fromJson(linha, listType);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return lista;
    }

}

