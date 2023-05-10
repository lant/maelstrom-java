package com.github.lant.maelstrom.inputs.broadcast;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.lant.maelstrom.inputs.Headers;
import com.github.lant.maelstrom.inputs.Message;

public record ReadMessage(Headers headers) implements Message {
    public ReadMessage(JsonNode input) {
        this(new Headers(input.get(sourceField).asText(), input.get(destinationField).asText()));
    }
}
