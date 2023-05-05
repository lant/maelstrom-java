package com.github.lant.maelstrom.responses.generate;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public
class GenerateResponse {
    private final String src;
    private final String dest;
    private GenerateReponseBody body;

    public GenerateResponse(String id, String dest, int replyTo, String uniqueId) {
        this.src = id;
        this.dest = dest;
        this.body = new GenerateReponseBody(replyTo, uniqueId);
    }

    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    static
    class GenerateReponseBody {
        private final String type = "generate_ok";
        private final int in_reply_to;
        private final String id;

        public GenerateReponseBody(int inReplyTo, String uniqueId) {
            this.in_reply_to = inReplyTo;
            this.id = uniqueId;
        }
    }
}
