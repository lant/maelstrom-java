# Maelstrom-java

This library is a [maelstrom]("https://github.com/jepsen-io/maelstrom/") java wrapper, so you don't need to 
write the JSON boilerplate to send and receive the messages.

Fly.io published a set of exercises in order to test candidates is [hardcore distributed systems challenges](https://fly.io/dist-sys/), 
with that they also provided a really nice Golang library in order make it easier to code the examples.

This library aims to be exactly the same but in Java. It lets you define your own messages as well as automatically convert the 
inputs from the exercises to Java classes that will be very easy to handle. 

Currently, the library has implementations for all the messages that you would need to code: 

* [Exercise 1, Echo](https://fly.io/dist-sys/1/)
* [Exercise 2, Unique ID Generation](https://fly.io/dist-sys/2/)
* [Exercise 3, Broadcast](https://fly.io/dist-sys/3a/)

## Usage

The idea is that library should be easy to use, so you can focus on solving the distributed systems challenges. 

In the `com.github.lant.maelstrom.example` package you'll find an Example that solves exercise 1 and 2.

In your `main` method you'd need to use two classes: 

```java
public static void main(String[] args) throws Exception {
        MaelstromRunner run = new MaelstromRunner();           // handles input and output
        MaelstromHandler handler = new EchoMaelstromHandler(); // interface that you need to implement
        run.run(handler);
}
```

The interesting part is the `MaelstromHandler` interface. Let's have a look at the example: 

You'd need to implement the method `handleRequest` that will receive the `messageType` that the Runner will receive
and the full JSON of the message.

```java
@Override
public void handleRequest(String messageType, JsonNode receivedValue) throws Exception {
    switch (messageType) {
        case "init" -> handleInit(new InitMessage(receivedValue));
        case "echo" -> handleEcho(new EchoMessage(receivedValue));
        default -> throw new Exception("Don't know how to handle message type: " + messageType);
    }
}
```
Then you would be able to specify the different behaviour that your system will run when it finds
a specific type of message. In the previous example this implementation will know how to handle any message
that contains the `init` type or the `echo` type. Not that these two types of messages are standard
messages specified in the [Maelstrom Protocol](https://github.com/jepsen-io/maelstrom/blob/main/doc/protocol.md), so the library provides Java classes for them. You can just
create these classes passing the JSON itself.

If you wish to handle different messages you just need to add elements to the `switch` to execute your own logic.

So, let's see how the `handleInit` method is implemented: 

```java
private void handleInit(InitMessage parseInit) throws JsonProcessingException {
    myId = parseInit.node_id();
    // debugging info
    System.err.println("--> Received: " + parseInit);
    String response = generateInitOk(
            myId,
            parseInit.headers().src(),
            parseInit.msg_id());
    // debugging
    System.err.println("--> Responding: " + response);
    // what Maelstrom will read
    System.out.println(response);
}
```

`Maelstrom` uses the terminal (stdout) to "send" and "receive" messages, so our system just needs to use something like `System.out.println` to "send" a message
this is why this example is just using this. The `System.err` is debugging statements that will be ignored by `Maelstrom`. 

Notice that the response that needs to be sent is also a JSON message with its `headers` and `body`. The library also provides a helper for this part. 

The `Responses` class provides methods for all the standard messages specified in the protocol, as well the possibility to send your custom ones if you want.

## References
* [Jepsen maelstrom main repo](https://github.com/jepsen-io/maelstrom/)
* [Maelstrom JSON Protocol](https://github.com/jepsen-io/maelstrom/)
* [Fly.io distributed systems challenge](https://github.com/jepsen-io/maelstrom/)
