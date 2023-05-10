package com.github.lant.maelstrom.responses.broadcast;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class BroadcastResponse {
    private final String src;
    private final String dest;
    private BroadcastResponseBody body;

    public BroadcastResponse(String source, String destination) {
        this.src = source;
        this.dest = destination;
        this.body = new BroadcastResponseBody();
    }

    private class BroadcastResponseBody {
        private final String type = "broadcast_ok";
    }
}
