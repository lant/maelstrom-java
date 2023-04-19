package com.github.lant.maelstrom;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.lant.maelstrom.inputs.EchoMessage;
import com.github.lant.maelstrom.inputs.InitMessage;
import com.github.lant.maelstrom.responses.Responses;

public class Main {
    public static void main(String[] args) throws Exception {
        Responses responses = new Responses();
        SystemRun run = new SystemRun();
        MaelstromHandler handler = new MaelstromHandler() {
            String myId;

            @Override
            public void handleInit(InitMessage parseInit) throws JsonProcessingException {
                myId = parseInit.initBody.node_id;
                String response = responses.generateInitOk(myId, parseInit.src, parseInit.initBody.msg_id);
                System.err.println("Responding: " + response);
                System.out.println(response);
            }

            @Override
            public void handleEcho(EchoMessage parseEcho) throws JsonProcessingException {
                String response = responses.generateEchoResponse(myId, parseEcho.src, parseEcho.echoBody.msg_id, parseEcho.echoBody.echo, 1);
                System.err.println("Responding: " + response);
                System.out.println(response);
            }
        };
        run.run(handler);
    }
}
