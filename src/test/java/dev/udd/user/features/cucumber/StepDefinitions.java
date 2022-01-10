package dev.udd.user.features.cucumber;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitions {

    private final String URL = "http://localhost:";
    private final RestTemplate client = new RestTemplate();
    @LocalServerPort
    private int port;

    private ResponseEntity<String> response;

    @When("^I send a POST request to (.+) with body:$")
    public void iSendARequestToWithBody(final String path, final String bodyJson) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(bodyJson, headers);

        response = this.client.postForEntity(URL + port + path, entity, String.class);
    }

    @When("^I send a PUT request to (.+) with body:$")
    public void iSendAPUTRequestToWithBody(final String path, final String bodyJson) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        response = null;
        HttpEntity<String> entity = new HttpEntity<>(bodyJson, headers);
        this.client.put(URL + port + path, entity);
    }

    @Then("^I should receive an empty body$")
    public void iShouldReceiveAnEmptyBody() {

        assertThat(response.getBody()).isNull();
    }

    @And("status code should be {int}")
    public void statusCodeShouldBe(int statusCode) {

        assertThat(response.getStatusCode().value()).isEqualTo(statusCode);
    }

    @When("^I send a GET request to (.+)$")
    public void iSendAGETRequestTo(final String path) {

        response = null;
        try {
            response = this.client.getForEntity(URL + port + path, String.class);
        } catch (HttpClientErrorException.NotFound e) {
            response = new ResponseEntity<String>(e.getResponseBodyAsString(), e.getStatusCode());
        }
    }

    @When("^I send a DELETE request to (.+)$")
    public void iSendADeleteRequestTo(final String path) {

        this.client.delete(URL + port + path);
    }

    @Then("I should receive the following body:")
    public void iShouldReceiveTheFollowingBody(final String bodyJson) throws JSONException {

        JSONAssert.assertEquals(response.getBody(), bodyJson, true);
    }

}
