package com.github.lant.maelstrom.responses.echo;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public
class EchoResponse {
    private final String src;
    private final String dest;
    private EchoReponseBody body;

    public EchoResponse(String id, String dest, int reply_to, String echo, int msg_id) {
        this.src = id;
        this.dest = dest;
        this.body = new EchoReponseBody(reply_to, echo, msg_id);
    }

    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    static
    class EchoReponseBody {
        private final String type = "echo_ok";
        private final int in_reply_to;
        private final String echo;
        private final int msg_id;

        public EchoReponseBody(int in_reply_to, String echo, int msg_id) {
            this.in_reply_to = in_reply_to;
            this.echo = echo;
            this.msg_id = msg_id;
        }
    }
}
