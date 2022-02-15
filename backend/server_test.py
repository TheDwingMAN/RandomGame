from comms import *

if __name__ == "__main__":
    logging.basicConfig(filename="serverlog.log", filemode="a+", format="%(name)s - %(levelname)s - %(message)s")
    with socket() as server:
        server.bind(("127.0.0.1", 10001))
        server.listen()
        while True:
            conn, addr = server.accept()
            key = get_shared_key(conn)
            print(key)         
            