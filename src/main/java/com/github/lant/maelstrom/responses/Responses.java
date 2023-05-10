package com.github.lant.maelstrom.responses;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.lant.maelstrom.responses.broadcast.BroadcastResponse;
import com.github.lant.maelstrom.responses.broadcast.ReadResponse;
import com.github.lant.maelstrom.responses.broadcast.TopologyResponse;
import com.github.lant.maelstrom.responses.echo.EchoResponse;
import com.github.lant.maelstrom.responses.generate.GenerateResponse;
import com.github.lant.maelstrom.responses.init.InitOkResponse;

import java.util.List;

/**
 * Stateless
 */
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
    public static String generateInitOk(String myNodeId, String dst, int reply_to) throws JsonProcessingException {
        return mapper.writeValueAsString(new InitOkResponse(myNodeId, dst, reply_to));
    }

    public static String generateEchoResponse(String myNodeId, String dst, int reply_to, String echo, int msg_id) throws JsonProcessingException {
        return mapper.writeValueAsString(new EchoResponse(myNodeId, dst, reply_to, echo, msg_id));
    }

    public static String generateGenerateResponse(String myNodeId, String dst, int reply_to, String uniqueId) throws JsonProcessingException {
        return mapper.writeValueAsString(new GenerateResponse(myNodeId, dst, reply_to, uniqueId));
    }

    public static String generateBroadcastResponse(String myNodeId, String dst) throws JsonProcessingException {
        return mapper.writeValueAsString(new BroadcastResponse(myNodeId, dst));
    }

    public static String generateBroadcastReadResponse(String myNodeId, String dst, List<Integer> messages) throws JsonProcessingException {
        return mapper.writeValueAsString(new ReadResponse(myNodeId, dst, messages ));
    }

    public static String generateBroadcastTopologyResponse(String myNodeId, String dst) throws JsonProcessingException {
        return mapper.writeValueAsString(new TopologyResponse(myNodeId, dst));
    }

    public static String generateCustomResponse(String myNodeId, String dst, JsonNode body) throws JsonProcessingException {
        ObjectNode node = mapper.createObjectNode();
        node.put("src", myNodeId);
        node.put("dest", dst);
        node.set("body", body);
        return mapper.writeValueAsString(node);
    }
}

