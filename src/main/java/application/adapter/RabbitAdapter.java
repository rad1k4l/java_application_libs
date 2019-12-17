package application.adapter;

import application.connection.RabbitConnection;
import com.rabbitmq.client.Connection;

public class RabbitAdapter {

    public static RabbitListen listen;
    public static RabbitSend send;
    public static Connection connection;

    static {
        send = new RabbitSend();
        listen = new RabbitListen();

        connection = RabbitConnection.getLink();
    }
}
