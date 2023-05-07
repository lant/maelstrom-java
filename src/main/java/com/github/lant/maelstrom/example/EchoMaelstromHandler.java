package com.github.lant.maelstrom.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.lant.maelstrom.MaelstromHandler;
import com.github.lant.maelstrom.inputs.echo.EchoMessage;
import com.github.lant.maelstrom.inputs.init.InitMessage;

import static com.github.lant.maelstrom.responses.Responses.*;
import static com.github.lant.maelstrom.responses.Responses.generateInitOk;


class EchoMaelstromHandler implements MaelstromHandler {
    String myId;

    private void handleInit(InitMessage parseInit) throws JsonProcessingException {
        myId = parseInit.node_id();
        System.err.println("--> Received: " + parseInit);
        String response = generateInitOk(
                myId,
                parseInit.headers().src(),
                parseInit.msg_id());
        System.err.println("--> Responding: " + response);
        System.out.println(response);

    }

    private void handleEcho(EchoMessage parseEcho) throws JsonProcessingException {
        String response = generateEchoResponse(
                myId,
                parseEcho.headers().src(),
                parseEcho.msg_id(),
                parseEcho.echo(),
                1);
        System.err.println("Responding: " + response);
        System.out.println(response);
    }

    @Override
    public void handleRequest(String messageType, JsonNode receivedValue) throws Exception {
        switch (messageType) {
            case "init" -> handleInit(new InitMessage(receivedValue));
            case "echo" -> handleEcho(new EchoMessage(receivedValue));
            default -> throw new Exception("Don't know how to handle message type: " + messageType);
        }
    }
}
