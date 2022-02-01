from comms import * 

if __name__ == "__main__":
    with socket() as client:
        client.connect(("127.0.0.1", 10001))
        client.send(create_packet("Hello there!".encode(), PacketID.CLIENT_ACTION, "MY_K3Y".encode()))
