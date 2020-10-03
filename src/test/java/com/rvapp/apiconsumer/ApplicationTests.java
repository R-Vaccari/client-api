package com.rvapp.apiconsumer;

import com.rvapp.apiconsumer.resources.CourseResource;
import com.rvapp.apiconsumer.resources.util.AuthenticationResource;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApplicationTests {


    @Test
    @DisplayName("Authentication resource testing.")
    void authenticationTest() {
        Response response = AuthenticationResource.getResponse();


        assertEquals(200, response.getStatus());
        assertEquals(MediaType.APPLICATION_JSON_TYPE, response.getMediaType());
    }

    @Test
    void courseEndpoint() {
        CourseResource resourceCourses = new CourseResource();
        WebTarget target = resourceCourses.getWebTarget();
        Response getResponse = target.request(MediaType.APPLICATION_JSON_TYPE)
                .header("Authorization", "Bearer " + AuthenticationResource.authenticate())
                .get();

        assertEquals(200, getResponse.getStatus());
        assertEquals(MediaType.APPLICATION_JSON_TYPE, getResponse.getMediaType());
    }
}
