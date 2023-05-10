package com.github.lant.maelstrom.responses.broadcast;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class TopologyResponse {
    private final String src;
    private final String dest;
    private TopologyResponseBody body;

    public TopologyResponse(String source, String destination) {
        this.src = source;
        this.dest = destination;
        this.body = new TopologyResponseBody();
    }

    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    private class TopologyResponseBody {
        private final String type = "topology_ok";
    }
}
