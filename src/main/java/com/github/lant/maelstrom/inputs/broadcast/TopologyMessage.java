package com.github.lant.maelstrom.inputs.broadcast;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.lant.maelstrom.inputs.Headers;
import com.github.lant.maelstrom.inputs.Message;

import java.util.List;
import java.util.Map;

public record TopologyMessage(Headers headers, Map<String, List<String>> nodes) implements Message {
    private static final ObjectMapper mapper = new ObjectMapper();

    public TopologyMessage(JsonNode input) {
        this(new Headers(
                input.get(sourceField).asText(),
                input.get(destinationField).asText()),
                mapper.convertValue(
                        input.get(bodyField),
                        new TypeReference<>() { }
                ));
    }
}
