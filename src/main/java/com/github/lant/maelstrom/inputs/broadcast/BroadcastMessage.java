package com.github.lant.maelstrom.inputs.broadcast;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.lant.maelstrom.inputs.Headers;
import com.github.lant.maelstrom.inputs.Message;

public record BroadcastMessage(Headers headers, int message) implements Message {
    public BroadcastMessage(JsonNode input) {
        this(new Headers(
                input.get(sourceField).asText(),
                input.get(destinationField).asText()), input.get(bodyField).get("message").asInt());
    }
}
