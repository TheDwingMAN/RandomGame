"""Socket-Communcation API"""

import struct
import hmac
import time
import logging

from datetime import datetime
from socket import socket
from enum import IntEnum
from typing import NamedTuple
from consts import HMAC_SIZE, HEADER_SIZE


class PacketID(IntEnum):
    """Packet IDs"""

    GENERAL_DATA = 0
    INITIAL_AUTH = 1

    # Client actions
    CLIENT_ACTION = 2  # action with different clients
    ITEM_ACTION = 3  # action with items (like swords and stuff)

    # Server success response for requests
    CONFIRMED_STATUS = 4

    # User Movement
    MOVE_UP = ord("w")
    MOVE_DOWN = ord("s")
    MOVE_LEFT = ord("a")
    MOVE_RIGHT = ord("d")


class PacketInfo(NamedTuple):
    """Info relevant to a packet: the packet's type, the time it was sent, and its contents"""

    packet_type: PacketID
    time_sent: datetime
    content: bytes


def create_packet(content: bytes, type: PacketID, key: bytes) -> bytes:
    """Creates a packet from its data and some metadata. Appends in the beginning an md5 HMAC.

    Parameters
    ----------
    content : bytes
        packet's content
    type : PacketID
        type of packet
    key : bytes
        the shared key to be used for the HMAC

    Returns
    -------
    bytes
        the packet's data
    """
    encoded_time = struct.pack("f", time.mktime(datetime.now().timetuple()))
    header = int(type).to_bytes(1) + len(content).to_bytes(2, "big") + encoded_time
    print(header)
    data = header + content
    return hmac.digest(key, data, "md5") + data


def unpack_packet(conn: socket, key: bytes) -> PacketInfo:
    """Validates the packet data and returns the packet's content.
    Raises `ArgumentError` if the packet's given HMAC doesn't align with its actual HMAC.

    Parameters
    ----------
    conn: socket
        the connection to get the socket from
    key : bytes
        shared key that was used in HMAC

    Returns
    -------
    bytes
        the packet's contents
    """
    # parsing header
    header = conn.recv(HEADER_SIZE)
    msg_hmac, header = header[:HMAC_SIZE], header[HMAC_SIZE:]
    try:
        type, content_length, time_stamp = (
            PacketID(header[0]),
            int.from_bytes(header[0:1], "big"),
            datetime.fromtimestamp(struct.unpack("f", header[2:])),
        )
    except Exception:
        logging.warning(f"Invalid header was given to socket {conn}", exc_info=True)
        return

    content = conn.recv(content_length)
    if not hmac.compare_digest(msg_hmac, hmac.digest(key, header + content, "md5")):
        logging.critical(
            f"HMACs doesn't match in message given to {conn}: {content=},{time_stamp=},{content_length=},{type=},{msg_hmac=}"
        )
        return
    return PacketInfo(type, time_stamp, content)
