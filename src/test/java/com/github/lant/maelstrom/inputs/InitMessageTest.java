package com.github.lant.maelstrom.inputs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InitMessageTest {
    private final ObjectMapper objectMapper = new ObjectMapper();

    private final String jsonMessage = """
    {"src":"source","dest":"destination","body":{"type":"init","msg_id":1,"node_id":"n3","node_ids":["n1","n2","n3"]}}""";

    @Test
    void testToJson() throws JsonProcessingException {
        var message = new InitMessage(
                new Headers("source", "destination"),
                1, "n3", List.of("n1","n2","n3"));

        String json = objectMapper.writeValueAsString(message.toJson());
        assertEquals(jsonMessage, json);
    }

    @Test
    void testNonCanonicalConstructor() throws IOException {
        var message = new InitMessage(objectMapper.readTree(jsonMessage));
        assertEquals("source", message.headers().src());
        assertEquals("destination", message.headers().dest());
        assertEquals("n3", message.node_id());
        assertEquals(3, message.node_ids().size());
        assertTrue(message.node_ids().contains("n1"));
        assertTrue(message.node_ids().contains("n2"));
        assertTrue(message.node_ids().contains("n3"));
    }
}