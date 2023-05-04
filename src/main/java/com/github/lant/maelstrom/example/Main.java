package com.github.lant.maelstrom.example;

import com.github.lant.maelstrom.MaelstromHandler;
import com.github.lant.maelstrom.MaelstromRunner;
import com.github.lant.maelstrom.responses.Responses;

public class Main {
    public static void main(String[] args) throws Exception {
        Responses responses = new Responses();
        MaelstromRunner run = new MaelstromRunner();
        MaelstromHandler handler = new EchoMaelstromHandler(responses);
        run.run(handler);
    }
}
