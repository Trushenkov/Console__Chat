package ru.tds.consolechat;

import java.io.*;
import java.net.Socket;

/**
 * Класс,в котором создается и устанавливается соединение с сервером.
 *
 * @author Трушенков Дмитрий 15ИТ18
 */
public class TCPConnection {

    private final Socket socket;

    private final Thread thread;

    private final TCPConnectionListener eventListener;

    private final BufferedReader in;

    private final BufferedWriter out;

    private final String nickname;

    TCPConnection(TCPConnectionListener eventListener, String ipAddress, int port, String nickname) throws IOException {
        this(eventListener, new Socket(ipAddress, port), nickname);
    }

    TCPConnection(TCPConnectionListener eventListener, Socket socket, String nickname) throws IOException {
        this.socket = socket;
        this.nickname = nickname;
        this.eventListener = eventListener;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    eventListener.onConnectionReady(TCPConnection.this);
                    while (!thread.isInterrupted()) {
                        eventListener.onReceiveString(TCPConnection.this, in.readLine());
                    }
                } catch (IOException e) {
                    eventListener.onException(TCPConnection.this, e);
                } finally {
                    eventListener.onDisconnect(TCPConnection.this);
                }
            }
        });


        thread.start();

    }

    /**
     * Метод для отправки сообщения.
     *
     * @param value сообщение, которое нужно отправить
     */
    public synchronized void sendString(String value) {
        try {
            if (nickname.equals("Сервер")) {
                out.write(value + "\n");
                out.flush();
            } else {
                out.write(String.format("[%s] %s \n", nickname, value));
                out.flush();
            }

        } catch (IOException e) {
            eventListener.onException(this, e);
            disconnect();
        }
    }

    /**
     * Метод для отсоединения пользователя от сервера.
     */
    public synchronized void disconnect() {
        thread.interrupt();
        try {
            socket.close();
        } catch (IOException e) {
            eventListener.onException(this, e);
        }
    }

    @Override
    public String toString() {
        return "TCPConnection: " + socket.getInetAddress() + ": " + socket.getPort();
    }

}
