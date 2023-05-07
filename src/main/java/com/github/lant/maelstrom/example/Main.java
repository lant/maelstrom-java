package com.github.lant.maelstrom.example;

import com.github.lant.maelstrom.MaelstromHandler;
import com.github.lant.maelstrom.MaelstromRunner;

public class Main {
    public static void main(String[] args) throws Exception {
        MaelstromRunner run = new MaelstromRunner();
        MaelstromHandler handler = new EchoMaelstromHandler();
        run.run(handler);
    }
}
