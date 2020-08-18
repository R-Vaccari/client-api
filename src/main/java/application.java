

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class application {

    public static void main(String[] args) {

        Client client = ClientBuilder.newClient();
        WebTarget authTarget = client.target("http://localhost:8181").path("authenticate");
        WebTarget getTarget = client.target("http://localhost:8181").path("students");

        Invocation.Builder invocationBuilder;

        Response authentication = authTarget.request(MediaType.APPLICATION_JSON_TYPE).post();
        String jwt;

        Response response = getTarget.request(MediaType.APPLICATION_JSON_TYPE)
                .header("Authorization", "Bearer ")
                .get();


        System.out.println(response.getStatus());
        System.out.println("Test");

    }
}
