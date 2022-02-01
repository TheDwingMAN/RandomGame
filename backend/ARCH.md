# Backend Architecture
The server will be built smth like this:
* One server would act as the load balancer, which would direct clients to the respective game servers using some load balancing algorithm, possibly [weighted round-robin](https://kemptechnologies.com/load-balancer/load-balancing-algorithms-techniques/) (will be used for simplicity lol, otherwise there're definitely better ones).
* A single database in Redis or any other in-memory database that would be accessed by the servers, and contains some general stuff. Firstly, some general player info:
  * The player's name
  * Password, hashed & salted
  * Email
  * Health
  * Number of Coins
  * Inventory, as a set of ID's
* Each server would have a shared lock for writing. //TODO: make this actually efficent for, say, battle scenes, in which the health will be modified quite a bit
  * Perhaps we can for every battle scene point the two players to the same server, which would handle the scene
* The connection will be over TLS
  * If too preformance-heavy, we'll use TLS only for stuff like transfering the password, and otherwise just use HMACs and ECDH, to protect against MITM
  * In any case, it will be in TCP. 
# Rendering & Syncing
## Initial Rendering
Each client would spawn on some (kinda random, probably not _every_ place but it doesn't really matter rn) place, which would be given to him by the server.

The client will have the assets on his side. 

## Movement & Rendering
Every time it would want to move, it would send a move request to the server, and its location would be updated in the server side.


# Game Objects
# Packet Structure
## Header
* Timestamp (fixed length which I'll figure out later lol)
* Request ID - Size of byte
* Length (of data, not including the header) - Size of word/dword

### General ID's
These are packet types which don't depend on the party sending/reciving them.

| Type | ID | Description |
| --- | --- | --- |
| GENERAL_DATA | 0 | General data, can be interpeted as needed |

### Client2Server ID's

| Type | ID | Description |
| --- | --- | --- |
| INITIAL_AUTH | 1 | Contains the email, password hash, and username |
| CLIENT_ACTION | 2 | Interaction with a different client|
| ITEM_ACTION | 3 | Data related to interaction with items, basically like the "press E" kinda thing. |
| USER_MOVE_UP | 'w' | User moved up. Good for him. |
| USER_MOVE_DOWN | 's' | User moved down. Good for him. |
| USER_MOVE_LEFT | 'a' | User moved left. Good for him. |
| USER_MOVE_RIGHT | 'd' | User moved right. Good for him. |

### Server2Client ID's
| Type | ID | Description |
| --- | --- | --- |
| AUTH_STATUS | 4 | Returns if the authentication succeded. |
| EMAIL_AUTH_STATUS | 5 | Returns if the email authentication succeded. | 
| RENDER_PLAYER_AT | 6 | Rendering message. This is gonna be fun :) |


## GENERAL_DATA
Packets of type `GENERAL_DATA` will simply contain data.
## INITIAL_AUTH
* Email (constrained to 32 chars)
* Username (constrained to 32 chars)
* Password hash (256 bits)
## CLIENT_ACTION
`CLIENT_ACTION` packets will contain
* Client ID

## ITEM_ACTION
To be added when we know what are the possible ways you can interact with items lol

# General Game Flow
## Start
1. The client sends a password hash, user name and email. We send an authentication code in mail, the client sends it, and he's verified and ready to start playing.
2. \<Enter front end stuff for him to start playing>
3. He might choose a skin, in which case, the skin's ID will be sent to the server and added to him. It will also be saved on the client side.
## Game
* Either we start a TLS connection again, or just do ECDH and start adding HMACs to each message.



# Current Problems
* Redis doesn't really work on Windows, so either we all download WSL (which we should tbh but still), or we find an alternative in memory-database. Really really alternatively, we can try and create a small in-memory cache, that we'll manually transfer to some database, like MySQL or smth.