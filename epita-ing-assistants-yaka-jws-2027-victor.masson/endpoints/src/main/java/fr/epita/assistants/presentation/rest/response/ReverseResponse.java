package fr.epita.assistants.presentation.rest.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ReverseResponse {
    @JsonProperty
    String original;
    @JsonProperty
    String reversed;
}
