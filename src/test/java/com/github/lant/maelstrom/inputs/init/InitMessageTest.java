package com.github.lant.maelstrom.inputs.init;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.lant.maelstrom.inputs.init.InitMessage;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InitMessageTest {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testNonCanonicalConstructor() throws IOException {
        String jsonMessage = """
                {"src":"source","dest":"destination","body":{"type":"init","msg_id":1,"node_id":"n3","node_ids":["n1","n2","n3"]}}""";
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