package com.github.lant.maelstrom.inputs;

import com.fasterxml.jackson.databind.node.ObjectNode;

public interface Message {
    String destinationField = "dest";
    String sourceField = "src";
    String bodyField = "body";
}
