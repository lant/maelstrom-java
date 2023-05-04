package com.github.lant.maelstrom.responses;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.lant.maelstrom.responses.echo.EchoResponse;
import com.github.lant.maelstrom.responses.generate.GenerateResponse;
import com.github.lant.maelstrom.responses.init.InitOkResponse;

public class Responses {
    private static final ObjectMapper mapper = new ObjectMapper();

    /**
     * Generate a response to the Init Request.
     * @param myNodeId The node where you're responding from.
     * @param dst
     * @param reply_to
     * @return
     * @throws JsonProcessingException
     */
    public String generateInitOk(String myNodeId, String dst, int reply_to) throws JsonProcessingException {
        return mapper.writeValueAsString(new InitOkResponse(myNodeId, dst, reply_to));
    }

    public String generateEchoResponse(String myNodeId, String dst, int reply_to, String echo, int msg_id) throws JsonProcessingException {
        return mapper.writeValueAsString(new EchoResponse(myNodeId, dst, reply_to, echo, msg_id));
    }

    public String generateGenerateResponse(String myNodeId, String dst, int reply_to, String uniqueId) throws JsonProcessingException {
        return mapper.writeValueAsString(new GenerateResponse(myNodeId, dst, reply_to, uniqueId));
    }

    public String generateCustomResponse(String myNodeId, String dst, JsonNode body) throws JsonProcessingException {
        ObjectNode node = mapper.createObjectNode();
        node.put("src", myNodeId);
        node.put("dest", dst);
        node.set("body", body);
        return mapper.writeValueAsString(node);
    }
}

