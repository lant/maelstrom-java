package com.github.lant.maelstrom.responses.broadcast;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.List;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ReadResponse {
    private final String src;
    private final String dest;
    private ReadResponseBody body;

    public ReadResponse(String source, String destination, List<Integer> messages ) {
        this.src = source;
        this.dest = destination;
        this.body = new ReadResponseBody(messages);
    }


    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    private static class ReadResponseBody {
        private final String type = "read_ok";
        private final List<Integer> messages;

        public ReadResponseBody(List<Integer> messages) {
            this.messages = messages;
        }
    }
}
