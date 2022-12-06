package project2_JavaToJSON.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class DownStreamResponse {

    @JsonProperty("response_code")
    private String responseCode;
    private List<DownStreamResult> results;

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public List<DownStreamResult> getResults() {
        return results;
    }

    public void setResults(List<DownStreamResult> results) {
        this.results = results;
    }
}
