package ru.tds.consolechat;

import java.io.IOException;

/**
 * Интерфейс, содержащий методы для работы с соединением
 *
 * @author Трушенков Дмитрий 15ИТ18
 */
public interface TCPConnectionListener {

    /**
     * Метод для информирования о подключении нового соединения
     *
     * @param tcpConnection новое соединение
     */
    void onConnectionReady(TCPConnection tcpConnection);

    /**
     * Метод для отправки сообщения соединением
     *
     * @param tcpConnection соединение, которое отправляет сообщение
     * @param string        сообщение, которое нужно отправить
     */
    void onReceiveString(TCPConnection tcpConnection, String string);

    /**
     * Метод для информирования об отключении соединения
     *
     * @param tcpConnection соединение
     */
    void onDisconnect(TCPConnection tcpConnection);

    /**
     * Метод для обрабатывания исключения, возникшего у соединения
     *
     * @param tcpConnection соединение
     * @param exception     исключение
     */
    void onException(TCPConnection tcpConnection, IOException exception);

}
