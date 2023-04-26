package com.github.lant.maelstrom.inputs;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.util.List;

public record InitMessage(Headers headers, int msg_id, String node_id, List<String> node_ids) implements Message {
    public static final ObjectMapper mapper = new ObjectMapper();
    public static final String type = "init";

    @Override
    public ObjectNode toJson() {
        ObjectNode message = mapper.createObjectNode();
        message.put("src", this.headers.src());
        message.put("dest", this.headers.dest());
        ObjectNode body = mapper.createObjectNode();
        body.put("type", type);
        body.put("msg_id", msg_id);
        body.put("node_id", node_id);
        ArrayNode list = mapper.createArrayNode();
        for (String node_id_it : node_ids) {
            list.add(node_id_it);
        }
        body.set("node_ids", list);
        message.set("body", body);
        return message;
    }

    public InitMessage(JsonNode input) throws IOException {
        this(new Headers( input.get("src").asText(), input.get("dest").asText()),
                input.get("body").get("msg_id").asInt(),
                input.get("body").get("node_id").asText(),
                mapper.readerForListOf(String.class).readValue(input.get("body").get("node_ids")));
    }
}
