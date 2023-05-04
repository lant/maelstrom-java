package com.github.lant.maelstrom;

import com.fasterxml.jackson.databind.JsonNode;

public interface MaelstromHandler {
    void handleRequest(String messageType, JsonNode receivedValue) throws Exception;
}
