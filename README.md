# Simple netty server - example
Here is an example of a simple netty server, based on the framework I've written (https://github.com/Marcoral/SimpleNettyServer).
This minimalistic example illustrates how to accept client connections, and communicate with the client using selfdefined packets.
Example client is available at:
https://github.com/Marcoral/SimpleNettyClient-example.

## Launching
To start the server, run the program with the following arguments:

**1st:** Port (45256 if none specified)

You can additionally add flag **-l** to run server in loopback mode (thus preventing external connections).

For quick start, you can just run the compiled .jar file attached to the repository with the following command:

    java -jar SimpleNettyServer-example 45256

(in the above command I called the default argument explicitly).

## Specification
The server informs in case of establishing and losing connection with the clients. Below is a specification of the protocol used to interact with them.

### Acceptable packets
| Packet ID | Packet name | Expected payload | Behaviour
|--|--|--|--|
| 0 | Ping  | None| Responds with *Pong* packet|
| 1 | Login | String - name | See "*logging in*" section
| 2 | Example data packet | Integer - respresenting data sample | Displays data in the console

#### Logging in
The server supports simple login. Clients can send login request packets with a chosen name under which they will be recognized. If a client is already logged in, the selected name is shorter than 3 characters or longer than 16, the server will send a *Login error* packet - otherwise it will send a *Login success* packet (see below).

### Packets sent
| Packet ID | Packet name | Payload | Meaning
|--|--|--|--|
| 0 | Pong| None| Response to *Ping* packet|
| 1 | Login success | None | Packet indicating successful login
| 2 | Login error | String - error details | Packet providing login error data
