package com.github.lant.maelstrom.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.lant.maelstrom.MaelstromHandler;
import com.github.lant.maelstrom.inputs.init.InitMessage;
import com.github.lant.maelstrom.inputs.uniqueids.GenerateMessage;
import com.github.lant.maelstrom.responses.Responses;

import java.util.UUID;

class GenerateMaelstromHandler implements MaelstromHandler {
    private final Responses responses;
    String myId;

    public GenerateMaelstromHandler(Responses responses) {
        this.responses = responses;
    }

    public void handleInit(InitMessage parseInit) throws JsonProcessingException {
        myId = parseInit.node_id();
        System.err.println("--> Received: " + parseInit);
        String response = responses.generateInitOk(
                myId,
                parseInit.headers().src(),
                parseInit.msg_id());
        System.err.println("--> Responding: " + response);
        System.out.println(response);

    }

    public void handleGenerate(GenerateMessage generateMessage) throws JsonProcessingException {
        String response = responses.generateGenerateResponse(
                myId,
                generateMessage.headers().src(),
                generateMessage.msg_id(),
                UUID.randomUUID().toString());
        System.err.println("Responding: " + response);
        System.out.println(response);
    }

    @Override
    public void handleRequest(String messageType, JsonNode receivedValue) throws Exception {
        switch (messageType) {
            case "init" -> handleInit(new InitMessage(receivedValue));
            case "generate" -> handleGenerate(new GenerateMessage(receivedValue));
            default -> throw new Exception("Don't know how to handle message type: " + messageType);
        }
    }
}
