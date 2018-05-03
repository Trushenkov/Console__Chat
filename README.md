# Консольный чат
### В этом приложении реализовано:
1. Запуск приложения в двух режимах: в режиме сервера или в режиме клиента.
2. При запуске приложения в режиме сервера выполняется запуск сервера, который ожидает, пока к нему подключаться пользователи.
3. Когда пользователь подключается к серверу, сервер создает cокет для каждого пользователя и добавляет новое соединение в лист соединений.
4. При запуске приложения в режиме клиента, пользователю предлагается ввести ip-адрес сервера.
5. При соединении с сервером, пользователю предлагается ввести свой никнейм.
6. После ввода никнейма всем клиентам рассылается строка о том присоединилось новое подключение.
7. Организация диалога пользователей в чате. Пользователь может ввести сообщение и нажать клавишу “Enter” для его отправки. Это сообщение будет видно всем пользователям.
8. Вывод сообщения о том, что отключено соединение.
