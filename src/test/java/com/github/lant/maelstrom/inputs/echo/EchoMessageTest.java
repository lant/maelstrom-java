package com.github.lant.maelstrom.inputs.echo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EchoMessageTest {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testNonCanonicalConstructor() throws JsonProcessingException {
        String jsonMessage = """
                {"src":"source","dest":"destination","body":{"echo":"echo_echo","msg_id":0}}""";
        var message = new EchoMessage(objectMapper.readTree(jsonMessage));
        assertEquals("source", message.headers().src());
        assertEquals("destination", message.headers().dest());
        assertEquals("echo_echo", message.echo());
        assertEquals(0, message.msg_id());
    }
}