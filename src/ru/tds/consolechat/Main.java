package ru.tds.consolechat;

import java.util.Scanner;

/**
 * Класс для запуска приложения в режиме сервера или в режиме клиента.
 *
 * @author Трушенков Дмитрий 15ИТ18
 */
public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Выберите режим работы программы ? (S(erver) / C(lient))");
        while (true) {
            char answer = Character.toLowerCase(scan.nextLine().charAt(0));
            if (answer == 's') {
                new Server();
                break;
            } else if (answer == 'c') {
                new Client();
                break;
            } else {
                System.out.println("Некорректный ввод.");
            }
        }
    }
}
