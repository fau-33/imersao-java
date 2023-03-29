import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        // Fazer uma conexão HTTP e buscar os 250 séries
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopTVs.json";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse <String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();
        

        // extrair as informações que são necessárias. (titulo, poster, classficação)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);
        
        // exibir e manipular os dados
        for (Map<String,String> filme : listaDeFilmes) {
            System.out.println("\u001b[30m \u001b[45mFilme:\u001b[m " + filme.get("title"));
            System.out.println("\u001b[32m \u001b[46mURL da imagem:\u001b[m " +filme.get("image"));
            double classficacao = Double.parseDouble(filme.get("imDbRating"));
            int numeroEstrelinhas = (int) classficacao;

            for(int n=1; n <= numeroEstrelinhas; n++) {
                System.out.print("⭐");
            }
            System.out.println("\n");

        }
    }
}
