package com.github.lant.maelstrom.inputs.echo;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.lant.maelstrom.inputs.Headers;
import com.github.lant.maelstrom.inputs.Message;

public record EchoMessage(Headers headers, int msg_id, String echo) implements Message {
    public EchoMessage(JsonNode input) {
        this(new Headers( input.get(sourceField).asText(), input.get(destinationField).asText()),
                input.get(bodyField).get("msg_id").asInt(),
                input.get(bodyField).get("echo").asText());
    }
}
