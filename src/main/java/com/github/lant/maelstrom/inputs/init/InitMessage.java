package com.github.lant.maelstrom.inputs.init;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.lant.maelstrom.inputs.Headers;
import com.github.lant.maelstrom.inputs.Message;

import java.io.IOException;
import java.util.List;

public record InitMessage(Headers headers, int msg_id, String node_id, List<String> node_ids) implements Message {
    public static final ObjectMapper mapper = new ObjectMapper();

    public InitMessage(JsonNode input) throws IOException {
        this(new Headers( input.get("src").asText(), input.get("dest").asText()),
                input.get("body").get("msg_id").asInt(),
                input.get("body").get("node_id").asText(),
                mapper.readerForListOf(String.class).readValue(input.get("body").get("node_ids")));
    }
}
