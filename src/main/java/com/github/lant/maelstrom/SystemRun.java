package com.github.lant.maelstrom;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.github.lant.maelstrom.inputs.*;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class SystemRun {

    private static final String INIT = "init";
    private static final String ECHO = "echo";
    private final ObjectMapper objectMapper = new ObjectMapper();

    public void run(MaelstromHandler handler) throws Exception {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String value = scanner.nextLine();
            System.err.println("We just received: " + value);
            JsonNode receivedValue = objectMapper.readTree(value);
            String messageType = receivedValue.get("body").get("type").asText();
            switch (messageType) {
                case INIT -> handler.handleInit(parseInit(receivedValue));
                case ECHO -> handler.handleEcho(parseEcho(receivedValue));
                default -> throw new Exception("Don't know how to handle message type: " + messageType);
            }
        }
    }

    public InitMessage parseInit(JsonNode value) throws IOException {
        InitMessage initMessage = new InitMessage();
        initMessage.dest = value.get("dest").asText();
        initMessage.src = value.get("src").asText();
        initMessage.initBody = new InitBody();
        initMessage.initBody.msg_id = value.get("body").get("msg_id").asInt();
        initMessage.initBody.node_id = value.get("body").get("node_id").asText();
        ObjectReader reader = objectMapper.readerFor(new TypeReference<List<String>>() { });
        initMessage.initBody.node_ids = reader.readValue(value.get("body").get("node_ids"));
        return initMessage;
    }

    public EchoMessage parseEcho(JsonNode value) throws IOException {
        System.err.println(objectMapper.writeValueAsString(value));
        EchoMessage echoMessage = new EchoMessage();
        echoMessage.dest = value.get("dest").asText();
        echoMessage.src = value.get("src").asText();
        echoMessage.echoBody = new EchoBody();
        echoMessage.echoBody.msg_id = value.get("body").get("msg_id").asInt();
        echoMessage.echoBody.echo = value.get("body").get("echo").asText();
        return echoMessage;
    }
}
