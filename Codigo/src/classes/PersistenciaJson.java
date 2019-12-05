package classes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class PersistenciaJson {

    public boolean gravarLista(ArrayList<Funcionario> listaDevs, ArrayList<Funcionario> listaAnalistas, ArrayList<Funcionario> listaGerentes ) {
        try {
            Gson gson = new Gson();
            gson = new GsonBuilder().create();

            FileWriter arquivoDevs = new FileWriter("src/persistencia/desenvolvedores.json");
            FileWriter arquivoAnalistas = new FileWriter("src/persistencia/analistas.json");
            FileWriter arquivoGerentes = new FileWriter("src/persistencia/gerentes.json");

            String strJsonDevs = gson.toJson(listaDevs);
            String strJsonAnalistas = gson.toJson(listaAnalistas);
            String strJsonGerentes = gson.toJson(listaGerentes);

            arquivoDevs.write(strJsonDevs);
            arquivoAnalistas.write(strJsonAnalistas);
            arquivoGerentes.write(strJsonGerentes);

            arquivoDevs.close();
            arquivoAnalistas.close();
            arquivoGerentes.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public ArrayList<Funcionario> ler(String arquivo) {
        ArrayList<Funcionario> lista = new ArrayList<>();
        Gson gson = new Gson();
        JsonParser jsonParser = new JsonParser();
            try {
                BufferedReader br = new BufferedReader(new FileReader("src/persistencia/" + arquivo + ".json"));
                JsonElement jsonElement = jsonParser.parse(br);

                Type type = new TypeToken<List<Funcionario>>() {
                }.getType();
                return gson.fromJson(jsonElement, type);

            } catch (IOException e) {
                e.printStackTrace();
            }
        return lista;

    }
}