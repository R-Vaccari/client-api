import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class application {

    public static void main(String[] args) {

        ObjectMapper om = new ObjectMapper();

        Client client = ClientBuilder.newClient();
        WebTarget authTarget = client.target("http://localhost:8181").path("authenticate");
        WebTarget getTarget = client.target("http://localhost:8181").path("students");

        Invocation.Builder invocationBuilder;

        String login = "{ \"username\" : \"user\" , \"password\" : \"password\" }";

        Response authentication = authTarget.request(MediaType.APPLICATION_JSON_TYPE).post(Entity.json(login));
        String jwt = authentication.readEntity(String.class);

        Response response = getTarget.request(MediaType.APPLICATION_JSON_TYPE)
                .header("Authorization", "Bearer " + jwt)
                .get();

        System.out.println(response.getStatus());
        System.out.println(jwt);

    }
}
