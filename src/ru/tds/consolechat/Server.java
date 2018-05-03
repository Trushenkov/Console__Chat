package ru.tds.consolechat;

import java.io.*;
import java.net.ServerSocket;
import java.util.ArrayList;

/**
 * Класс обеспечивает работу программы в режиме сервера.
 *
 * @author Трушенков Дмитрий 15ИТ18
 */
public class Server implements TCPConnectionListener {

    private final ArrayList<TCPConnection> connections = new ArrayList<>(); //arrayList соединений,подключенных к серверу

    Server() {
        System.out.println("Server running");

        try (ServerSocket serverSocket = new ServerSocket(2445)) {
            while (true) {
                try {
                    new TCPConnection(this, serverSocket.accept(), "Сервер");
                } catch (IOException e) {
                    System.out.println("TCPConnection exception :" + e);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onConnectionReady(TCPConnection tcpConnection) {
        connections.add(tcpConnection);
        sendToAllConnections("Новое подключение :" + tcpConnection);
    }

    @Override
    public void onReceiveString(TCPConnection tcpConnection, String string) {
        sendToAllConnections(string);
    }

    @Override
    public void onDisconnect(TCPConnection tcpConnection) {
        connections.remove(tcpConnection);
        sendToAllConnections("Отключено соединение: " + tcpConnection);
    }

    @Override
    public void onException(TCPConnection tcpConnection, IOException exception) {
        System.out.println("TCPConnection exception :" + exception);
    }

    /**
     * Метод для отправки сообщения всем пользователям.
     *
     * @param value сообщение для отправки
     */
    private void sendToAllConnections(String value) {
        System.out.println(value);
        for (TCPConnection connection : connections) {
            connection.sendString(value);
        }
    }


}
