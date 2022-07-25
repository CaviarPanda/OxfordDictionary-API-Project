package au.edu.sydney.soft3202.majorproject.model.api;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.Gson;

public class OnlineInput implements Input {

  private String INPUT_API_KEY = System.getenv("INPUT_API_KEY");
  private String INPUT_API_APP_ID = System.getenv("INPUT_API_APP_ID");

  public String errorMessage(String response) {
    Gson gson = new Gson();
    ErrorSchema errorSchema = gson.fromJson(response, ErrorSchema.class);
    System.out.println(errorSchema);
    return errorSchema.toString();
  }

  /** Check if status code returned is successful (return true) or not (Return false) */
  public boolean successfulStatusCodeCheck(HttpResponse<String> response) {
    if (response.statusCode() == 200
        || response.statusCode() == 201
        || response.statusCode() == 202) {
      return true;
    }
    return false;
  }


  @Override
  public String getLemma(String wordId) {
    try {
      String url =
          String.format("https://od-api.oxforddictionaries.com/api/v2/lemmas/en/%s", wordId);
      HttpRequest request =
          HttpRequest.newBuilder(new URI(url))
              .header("Accept", "application/json")
              .header("app_id", INPUT_API_APP_ID)
              .header("app_key", INPUT_API_KEY)
              .GET()
              .build();

      HttpClient client = HttpClient.newBuilder().build();
      HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

      System.out.println("Response status code was: " + response.statusCode());
      System.out.println("Response headers were: " + response.headers());
      System.out.println("Response body was:\n" + response.body());

      if (successfulStatusCodeCheck(response)) {
        Gson gson = new Gson();
        Lemma lemma = gson.fromJson(response.body(), Lemma.class);
        System.out.println(lemma);
        return lemma.toString();
      } else {
        return errorMessage(response.body());
      }

    } catch (IOException | InterruptedException e) {
      System.out.println("Something went wrong with our request!");
      System.out.println(e.getMessage());
    } catch (URISyntaxException ignored) {
      // This would mean our URI is incorrect - this is here because often the URI you use will not
      // be (fully)
      // hard-coded and so needs a way to be checked for correctness at runtime.
    }

    return null;
  }


  @Override
  public String getLexicalEntries(String wordId) {
    try {
      String url =
          String.format("https://od-api.oxforddictionaries.com/api/v2/lemmas/en/%s", wordId);
      HttpRequest request =
          HttpRequest.newBuilder(new URI(url))
              .header("Accept", "application/json")
              .header("app_id", INPUT_API_APP_ID)
              .header("app_key", INPUT_API_KEY)
              .GET()
              .build();

      HttpClient client = HttpClient.newBuilder().build();
      HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

      System.out.println("Response status code was: " + response.statusCode());
      System.out.println("Response headers were: " + response.headers());
      System.out.println("Response body was:\n" + response.body());

      if (successfulStatusCodeCheck(response)) {
        Gson gson = new Gson();
        Lemma lemma = gson.fromJson(response.body(), Lemma.class);
        System.out.println(lemma);
        return lemma.lexicalEntries().toString();
      } else {
        return errorMessage(response.body());
      }

    } catch (IOException | InterruptedException e) {
      System.out.println("Something went wrong with our request!");
      System.out.println(e.getMessage());
    } catch (URISyntaxException ignored) {
      // This would mean our URI is incorrect - this is here because often the URI you use will not
      // be (fully)
      // hard-coded and so needs a way to be checked for correctness at runtime.
    }

    return null;
  }

  @Override
  public String getEntry(String wordId) {
    try {
      String url =
          String.format(
              "https://od-api.oxforddictionaries.com/api/v2/entries/%s/%s", "en-gb", wordId);
      HttpRequest request =
          HttpRequest.newBuilder(new URI(url))
              .header("Accept", "application/json")
              .header("app_id", INPUT_API_APP_ID)
              .header("app_key", INPUT_API_KEY)
              .GET()
              .build();

      HttpClient client = HttpClient.newBuilder().build();
      HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

      System.out.println("Response status code was: " + response.statusCode());
      System.out.println("Response headers were: " + response.headers());
      System.out.println("Response body was:\n" + response.body());

      if (successfulStatusCodeCheck(response)) {
        Gson gson = new Gson();
        Entry entry = gson.fromJson(response.body(), Entry.class);
        System.out.println(entry);
        return entry.toString();
      } else {
        return errorMessage(response.body());
      }

    } catch (IOException | InterruptedException e) {
      System.out.println("Something went wrong with our request!");
      System.out.println(e.getMessage());
    } catch (URISyntaxException ignored) {
      // This would mean our URI is incorrect - this is here because often the URI you use will not
      // be (fully)
      // hard-coded and so needs a way to be checked for correctness at runtime.
    }

    return null;
  }

  @Override
  public String getPronunciationEntry(String wordId) {
    try {
      String url =
          String.format(
              "https://od-api.oxforddictionaries.com/api/v2/entries/%s/%s", "en-gb", wordId);
      HttpRequest request =
          HttpRequest.newBuilder(new URI(url))
              .header("Accept", "application/json")
              .header("app_id", INPUT_API_APP_ID)
              .header("app_key", INPUT_API_KEY)
              .GET()
              .build();

      HttpClient client = HttpClient.newBuilder().build();
      HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

      System.out.println("Response status code was: " + response.statusCode());
      System.out.println("Response headers were: " + response.headers());
      System.out.println("Response body was:\n" + response.body());

      if (successfulStatusCodeCheck(response)) {
        Gson gson = new Gson();
        Entry entry = gson.fromJson(response.body(), Entry.class);
        System.out.println(entry);
        return entry.audioPronunciationListToString();
      } else {
        return errorMessage(response.body());
      }

    } catch (IOException | InterruptedException e) {
      System.out.println("Something went wrong with our request!");
      System.out.println(e.getMessage());
    } catch (URISyntaxException ignored) {
      // This would mean our URI is incorrect - this is here because often the URI you use will not
      // be (fully)
      // hard-coded and so needs a way to be checked for correctness at runtime.
    }

    return null;
  }

  @Override
  public String getSynonymEntry(String wordId) {
    try {
      String url =
          String.format(
              "https://od-api.oxforddictionaries.com/api/v2/entries/%s/%s", "en-gb", wordId);
      HttpRequest request =
          HttpRequest.newBuilder(new URI(url))
              .header("Accept", "application/json")
              .header("app_id", INPUT_API_APP_ID)
              .header("app_key", INPUT_API_KEY)
              .GET()
              .build();

      HttpClient client = HttpClient.newBuilder().build();
      HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

      System.out.println("Response status code was: " + response.statusCode());
      System.out.println("Response headers were: " + response.headers());
      System.out.println("Response body was:\n" + response.body());

      if (successfulStatusCodeCheck(response)) {
        Gson gson = new Gson();
        Entry entry = gson.fromJson(response.body(), Entry.class);
        System.out.println(entry);
        return entry.synonymResultListToString();
      } else {
        return errorMessage(response.body());
      }

    } catch (IOException | InterruptedException e) {
      System.out.println("Something went wrong with our request!");
      System.out.println(e.getMessage());
    } catch (URISyntaxException ignored) {
      // This would mean our URI is incorrect - this is here because often the URI you use will not
      // be (fully)
      // hard-coded and so needs a way to be checked for correctness at runtime.
    }

    return null;
  }
}
