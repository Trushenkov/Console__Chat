import org.junit.Test;
import ru.tds.consolechat.TCPConnection;
import ru.tds.consolechat.TCPConnectionListener;

import java.io.IOException;
import java.net.Socket;

import static org.junit.Assert.assertEquals;

public class TCPConnectionTest {

    @Test
    public void sendString() throws IOException {
        TCPConnectionListener tcpConnectionListener = new TCPConnectionListener() {
            @Override
            public void onConnectionReady(TCPConnection tcpConnection) {

            }

            @Override
            public void onReceiveString(TCPConnection tcpConnection, String string) {

            }

            @Override
            public void onDisconnect(TCPConnection tcpConnection) {

            }

            @Override
            public void onException(TCPConnection tcpConnection, IOException exception) {

            }
        };

        String nickname = "dmitry";

        TCPConnection tcpConnection = new TCPConnection(tcpConnectionListener, new Socket("192.168.0.9", 2445), nickname);

        String testString = "Привет";

        String actualValue = String.format("[%s] %s \n", nickname, testString);

        String expectedValue = String.format("[%s] %s \n", tcpConnection.getNickname(), testString);

        assertEquals(expectedValue, actualValue);
    }

}