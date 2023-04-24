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
    public static final ObjectMapper objectMapper = new ObjectMapper();

    public void run(MaelstromHandler handler) throws Exception {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String value = scanner.nextLine();
            System.err.println("[maelstrom-java] We just received: " + value);
            JsonNode receivedValue = objectMapper.readTree(value);
            String messageType = receivedValue.get("body").get("type").asText();
            switch (messageType) {
                //case INIT -> handler.handleInit(new InitMessage(receivedValue));
                case ECHO -> handler.handleEcho(new EchoMessage(receivedValue));
                default -> throw new Exception("Don't know how to handle message type: " + messageType);
            }
        }
    }
}
