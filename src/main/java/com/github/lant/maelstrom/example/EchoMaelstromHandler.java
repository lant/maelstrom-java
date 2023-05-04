package com.github.lant.maelstrom.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.lant.maelstrom.MaelstromHandler;
import com.github.lant.maelstrom.inputs.echo.EchoMessage;
import com.github.lant.maelstrom.inputs.init.InitMessage;
import com.github.lant.maelstrom.responses.Responses;


class EchoMaelstromHandler implements MaelstromHandler {
    private final Responses responses;
    String myId;

    public EchoMaelstromHandler(Responses responses) {
        this.responses = responses;
    }

    private void handleInit(InitMessage parseInit) throws JsonProcessingException {
        myId = parseInit.node_id();
        System.err.println("--> Received: " + parseInit);
        String response = responses.generateInitOk(
                myId,
                parseInit.headers().src(),
                parseInit.msg_id());
        System.err.println("--> Responding: " + response);
        System.out.println(response);

    }

    private void handleEcho(EchoMessage parseEcho) throws JsonProcessingException {
        String response = responses.generateEchoResponse(
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
