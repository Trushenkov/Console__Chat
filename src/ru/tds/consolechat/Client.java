package ru.tds.consolechat;

import java.io.IOException;
import java.util.Scanner;

/**
 * Класс обеспечивает работу программы в режиме клиента.
 *
 * @author Трушенков Дмитрий 15ИТ18
 */
public class Client implements TCPConnectionListener {

    private static final int PORT = 2445; //порт

    private String nickname; //никнейм

    Client() {

        Scanner scan = new Scanner(System.in);
        System.out.println("Введите IP для подключения к серверу.");
        System.out.println("Формат: xxx.xxx.xxx.xxx");

        String ip = scan.nextLine();

        try {

            System.out.println("Введите никнейм:");
            nickname = scan.nextLine();
            TCPConnection connection = new TCPConnection(this, ip, PORT, nickname);

            while (true) {
                String msg = scan.nextLine();
                if (msg.equals("/exit")) {
                    connection.disconnect();
                    System.exit(0);
                } else {
                    connection.sendString(msg);
                }
            }

        } catch (IOException e) {
            System.out.println("Connection exception: " + e);
        }
    }


    @Override
    public void onConnectionReady(TCPConnection tcpConnection) {
        System.out.println("Соединение установлено. " + "Ваш никнейм [" + nickname + "]");
    }

    @Override
    public void onReceiveString(TCPConnection tcpConnection, String string) {
        System.out.println(string);
    }

    @Override
    public void onDisconnect(TCPConnection tcpConnection) {
        System.out.println("Соединение закрыто.");
    }

    @Override
    public void onException(TCPConnection tcpConnection, IOException exception) {
        System.out.println("Connection exception :" + exception);
    }


}