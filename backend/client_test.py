from comms import * 

if __name__ == "__main__":
    with socket() as client:
        client.connect(("127.0.0.1", 10001))
        key = get_shared_key(client)
        print(key)             
    