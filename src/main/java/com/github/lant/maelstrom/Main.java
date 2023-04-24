package com.github.lant.maelstrom;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.lant.maelstrom.inputs.EchoMessage;
import com.github.lant.maelstrom.responses.Responses;

public class Main {
    public static void main(String[] args) throws Exception {
        Responses responses = new Responses();
        SystemRun run = new SystemRun();
        MaelstromHandler handler = new MaelstromHandler() {
            String myId;

            @Override
            public void handleEcho(EchoMessage parseEcho) throws JsonProcessingException {
                String response = responses.generateEchoResponse(
                        myId,
                        parseEcho.headers().src(),
                        parseEcho.msg_id(),
                        parseEcho.echo(),
                        1);
                System.err.println("Responding: " + response);
                System.out.println(response);
            }
        };
        run.run(handler);
    }
}
