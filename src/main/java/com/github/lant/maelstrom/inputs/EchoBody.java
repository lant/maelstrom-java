package com.github.lant.maelstrom.inputs;

// {"src": "c1", "dest": "n1", "body": {"msg_id": 1, "type": "echo", "echo": "hello there"}}
public class EchoBody {
    public final String type = "echo";
    public int msg_id;
    public String echo;
}
