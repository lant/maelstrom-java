package com.github.lant.maelstrom;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Scanner;

public class MaelstromRunner {

    public static final ObjectMapper objectMapper = new ObjectMapper();

    public void run(MaelstromHandler handler) throws Exception {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String value = scanner.nextLine();
            System.err.println("[maelstrom-java] We just received: " + value);
            JsonNode receivedValue = objectMapper.readTree(value);
            String messageType = receivedValue.get("body").get("type").asText();
            handler.handleRequest(messageType, receivedValue);
        }
    }
}
