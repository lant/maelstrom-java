package com.github.lant.maelstrom.responses.generate;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public
class GenerateResponse {
    private final String src;
    private final String dest;
    private GenerateReponseBody body;

    public GenerateResponse(String id, String dest, int reply_to, String uniqueId) {
        this.src = id;
        this.dest = dest;
        this.body = new GenerateReponseBody(reply_to, uniqueId);
    }

    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    static
    class GenerateReponseBody {
        private final String type = "generate_ok";
        private final int in_reply_to;
        private final String id;

        public GenerateReponseBody(int in_reply_to, String uniqueId) {
            this.in_reply_to = in_reply_to;
            this.id = uniqueId;
        }
    }
}
