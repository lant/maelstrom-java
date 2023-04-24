package com.github.lant.maelstrom.inputs;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public record EchoMessage(Headers headers, int msg_id, String echo) implements Message {
    public static final ObjectMapper mapper = new ObjectMapper();
    public static final String type = "echo";

    @Override
    public ObjectNode toJson() {
        ObjectNode message = mapper.createObjectNode();
        message.put("src", this.headers.src());
        message.put("dest", this.headers.dest());
        ObjectNode body = mapper.createObjectNode();
        body.put("echo", echo);
        body.put("msg_id", msg_id);
        message.set("body", body);
        return message;
    }

    public EchoMessage(JsonNode input) {
        this(new Headers( input.get("src").asText(), input.get("dest").asText()),
                input.get("body").get("msg_id").asInt(),
                input.get("body").get("echo").asText());
    }
}
