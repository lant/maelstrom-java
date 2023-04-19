package com.github.lant.maelstrom.responses;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Responses {
    ObjectMapper mapper = new ObjectMapper();

    public String generateInitOk(String myId, String dst, int reply_to) throws JsonProcessingException {
        return mapper.writeValueAsString(new InitOkResponse(myId, dst, reply_to));
    }

    public String generateEchoResponse(String myId, String dst, int reply_to, String echo, int msg_id) throws JsonProcessingException {
        return mapper.writeValueAsString(new EchoResponse(myId, dst, reply_to, echo, msg_id));
    }
}

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class InitOkResponse {
    private String src;
    private String dest;
    private InitOkResponseBody body;

    public InitOkResponse(String myId, String dst, int reply_to) {
        this.src = myId;
        this.dest = dst;
        this.body = new InitOkResponseBody(reply_to);
    }
}

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class InitOkResponseBody {
    private final String type = "init_ok";
    private final int in_reply_to;
    public InitOkResponseBody(int in_reply_to) {
        this.in_reply_to = in_reply_to;
    }
}

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class EchoResponse {
    private final String src;
    private final String dest;
    private EchoReponseBody body;

    public EchoResponse(String id, String dest, int reply_to, String echo, int msg_id) {
        this.src = id;
        this.dest = dest;
        this.body = new EchoReponseBody(reply_to, echo, msg_id);
    }
}

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
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