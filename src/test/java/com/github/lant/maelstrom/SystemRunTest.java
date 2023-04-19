package com.github.lant.maelstrom;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.lant.maelstrom.inputs.InitMessage;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class SystemRunTest {

    private final ObjectMapper mapper = new ObjectMapper();
    private final SystemRun systemRun = new SystemRun();

    @Test
    void parseInit() throws IOException {
        String message = "{\"id\":0,\"src\":\"c0\",\"dest\":\"n0\",\"body\":{\"type\":\"init\",\"node_id\":\"n0\",\"node_ids\":[\"n0\"],\"msg_id\":1}}\n";
        InitMessage initMessage = systemRun.parseInit(mapper.readTree(message));
        assertEquals("n0", initMessage.initBody.node_id);
        assertTrue(initMessage.initBody.node_ids.contains("n0"));
        assertEquals(1, initMessage.initBody.node_ids.size());
    }
}