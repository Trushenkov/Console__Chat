package ru.tds.consolechat;

import java.io.IOException;

/**
 * @author Трушенков Дмитрий 15ИТ18
 */
public interface TCPConnectionListener {

    void onConnectionReady(TCPConnection tcpConnection);

    void onReceiveString(TCPConnection tcpConnection, String string);

    void onDisconnect(TCPConnection tcpConnection);

    void onException(TCPConnection tcpConnection, IOException exception);

}
