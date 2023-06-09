package com.github.lant.maelstrom.responses;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ResponsesTest {

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    void generateInitOk() throws JsonProcessingException {
        assertEquals("""
                {"src":"this_is_my_id","dest":"destination","body":{"type":"init_ok","in_reply_to":10}}
                """.trim(),
                Responses.generateInitOk("this_is_my_id", "destination", 10));
    }

    @Test
    void generateEchoResponse() throws JsonProcessingException {
        assertEquals("""
                {"src":"my_node_id","dest":"destination","body":{"type":"echo_ok","in_reply_to":8,"echo":"echo","msg_id":10}}
                """.trim(),
                Responses.generateEchoResponse("my_node_id", "destination", 8, "echo", 10)
        );
    }

    @Test
    void generateGenerateResponse() throws JsonProcessingException {
        assertEquals("""
                {"src":"my_node_id","dest":"destination","body":{"type":"generate_ok","in_reply_to":10,"id":"unique"}}
                """.trim(),
                Responses.generateGenerateResponse("my_node_id", "destination", 10, "unique")
        );
    }

    @Test
    void generateCustomResponse() throws JsonProcessingException {
        ObjectNode node = mapper.createObjectNode();
        node.put("this", "that");
        node.put("other_this", "other_that");
        assertEquals("""
                {"src":"my_node_id","dest":"destination","body":{"this":"that","other_this":"other_that"}}
                """.trim(),
                Responses.generateCustomResponse("my_node_id", "destination", node));
    }

    @Test
    void generateBroadcastResponse() throws JsonProcessingException {
        assertEquals("""
                {"src":"my_node_id","dest":"destination","body":{"type":"broadcast_ok"}}
                """.trim(),
                Responses.generateBroadcastResponse("my_node_id", "destination"));
    }

    @Test
    void testGenerateBroadcastReadResponse() throws JsonProcessingException {
        assertEquals("""
                {"src":"my_node_id","dest":"destination","body":{"type":"read_ok","messages":[1,2,3,4,5]}}
                """.trim(), Responses.generateBroadcastReadResponse("my_node_id", "destination",
                List.of(1,2,3,4,5)));
    }

    @Test
    void testGenerateBroadcastTopologyResponse() throws JsonProcessingException {
        assertEquals("""
                {"src":"my_node_id","dest":"destination","body":{"type":"topology_ok"}}
                """.trim(), Responses.generateBroadcastTopologyResponse("my_node_id", "destination"));
    }
}