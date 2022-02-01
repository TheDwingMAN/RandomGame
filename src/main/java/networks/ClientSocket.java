package networks;

import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.MD5Digest;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.params.KeyParameter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientSocket {
    /**
     * Socket for communicating with the server as the client.
     */

    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    private HMac hMac;
    private byte[] key;

    public void startConnection(String ip, int port) {
        try {
            this.socket = new Socket(ip, port);
        } catch (IOException e) {
            // TODO: spunk change this to some logger or smth idk I suffered enough java for today
            System.out.printf("Couldn't connect to socket with given parameters ip=%s, port=%d\n" +
                    "Thrown Exception " + e, ip, port);
        }
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            System.out.println("Damn this socket sucs");
        }
        this.hMac = new HMac(new MD5Digest());
    }

    public void updateSharedKey() {

    }

    public String sendMessage(String msg) throws IOException {
        out.println(msg);
        return in.readLine();
    }

    public void stopConnection() throws IOException {
        in.close();
        out.close();
        socket.close();
    }

    private byte[] getHmac(byte[] data, byte[] key) {
        hMac.update(data, 0, data.length);
        byte[] hmacOut = new byte[hMac.getMacSize()];
        hMac.doFinal(hmacOut, 0);
        return hmacOut;
    }
}