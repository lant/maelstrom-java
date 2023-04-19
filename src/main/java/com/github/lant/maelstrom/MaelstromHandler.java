package com.github.lant.maelstrom;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.lant.maelstrom.inputs.EchoMessage;
import com.github.lant.maelstrom.inputs.InitMessage;

public interface MaelstromHandler {
    void handleInit(InitMessage parseInit) throws JsonProcessingException;
    void handleEcho(EchoMessage parseEcho) throws JsonProcessingException;
}