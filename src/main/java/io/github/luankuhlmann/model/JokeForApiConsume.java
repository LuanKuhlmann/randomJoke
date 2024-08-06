package io.github.luankuhlmann.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"type", "setup", "punchline"})
public class JokeForApiConsume {
    @JsonProperty("type")
    public String type;

    @JsonProperty("setup")
    public String setup;

    @JsonProperty("punchline")
    public String punchline;

    public int id;

    public JokeForApiConsume() {
    }

    public JokeForApiConsume(String type, String setup, String punchline, int id) {
        this.type = type;
        this.setup = setup;
        this.punchline = punchline;
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSetup() {
        return setup;
    }

    public void setSetup(String setup) {
        this.setup = setup;
    }

    public String getPunchline() {
        return punchline;
    }

    public void setPunchline(String punchline) {
        this.punchline = punchline;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
