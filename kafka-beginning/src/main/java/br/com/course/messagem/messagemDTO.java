package br.com.course.messagem;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
public class messagemDTO {

    private int identifier;

    private String message;

    @JsonCreator
    public messagemDTO( @JsonProperty("identifier") int identifier,  @JsonProperty("message") String message){
        this.identifier = identifier;
        this.message = message;
    }


}
