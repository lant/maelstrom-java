package com.github.lant.maelstrom.inputs.uniqueids;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.lant.maelstrom.inputs.Headers;
import com.github.lant.maelstrom.inputs.Message;

public record GenerateMessage(Headers headers, int msg_id) implements Message {
    public GenerateMessage(JsonNode input) {
        this(new Headers( input.get(sourceField).asText(), input.get(destinationField).asText()),
                input.get(bodyField).get("msg_id").asInt());
    }
}
