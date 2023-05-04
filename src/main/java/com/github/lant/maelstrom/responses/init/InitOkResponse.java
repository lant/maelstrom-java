package com.github.lant.maelstrom.responses.init;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public
class InitOkResponse {
    private String src;
    private String dest;
    private InitOkResponseBody body;

    public InitOkResponse(String myNodeId, String dst, int reply_to) {
        this.src = myNodeId;
        this.dest = dst;
        this.body = new InitOkResponseBody(reply_to);
    }

    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    static
    class InitOkResponseBody {
        private final String type = "init_ok";
        private final int in_reply_to;

        public InitOkResponseBody(int in_reply_to) {
            this.in_reply_to = in_reply_to;
        }
    }
}
