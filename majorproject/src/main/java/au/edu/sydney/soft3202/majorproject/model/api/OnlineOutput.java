package au.edu.sydney.soft3202.majorproject.model.api;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OnlineOutput implements Output {
  private final String TWILIO_API_SID = System.getenv("TWILIO_API_SID");
  private final String TWILIO_API_KEY = System.getenv("TWILIO_API_KEY");
  private final String TWILIO_API_FROM = System.getenv("TWILIO_API_FROM");
  private final String TWILIO_API_TO = System.getenv("TWILIO_API_TO");

  @Override
  public String sendReport(List reportData) {
    if (reportData == null || reportData.isEmpty()) {
      return "Message is Empty ot Null, cannot send report";
    }

    List<NameValuePair> parameters = new ArrayList<>();
    parameters.add(new BasicNameValuePair("To", TWILIO_API_TO));
    parameters.add(new BasicNameValuePair("From", TWILIO_API_FROM));
    parameters.add(new BasicNameValuePair("Body", reportData.toString()));

    List<NameValuePair> credentials = new ArrayList<>();
    credentials.add(new BasicNameValuePair("user", TWILIO_API_SID));
    credentials.add(new BasicNameValuePair("password", TWILIO_API_KEY));

    try (CloseableHttpClient client = HttpClientBuilder.create().build()) {
      var request =
          new HttpPost(
              "https://"
                  + TWILIO_API_SID
                  + ":"
                  + TWILIO_API_KEY
                  + "@api.twilio.com/2010-04-01/Accounts/"
                  + TWILIO_API_SID
                  + "/Messages.json");
      request.setEntity(new UrlEncodedFormEntity(parameters));

      CloseableHttpResponse response = client.execute(request);

      int statusCode = response.getStatusLine().getStatusCode();
      String headers = Arrays.toString(response.getAllHeaders());
      String body = response.getStatusLine().getReasonPhrase();

      System.out.println("status code: " + statusCode);
      System.out.println(headers);
      System.out.println(body);
      return reportData.toString();
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Something went wrong with our request!");
      System.out.println(e.getMessage());
      return e.getMessage();
    }
  }
}
