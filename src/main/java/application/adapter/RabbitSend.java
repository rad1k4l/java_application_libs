package application.adapter;

import application.connection.RabbitConnection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

public class RabbitSend {
    private static String exchange = "root";

    public void topic(String topic, String message) throws IOException
    {
        try {
            Channel channel = channel();
            channel.exchangeDeclare(exchange, "topic");
            channel.basicPublish(exchange, topic, null, message.getBytes());
            System.out.println(" [x] Sent '" + topic + "':'" + message + "'");
        } catch (IOException exception) {
            System.out.println("ERROR EXCEPTION: " + exception.getMessage());
            System.exit(21);
        }
    }
    public static Connection connection()
    {
        return RabbitConnection.getLink();
    }


    public static Channel channel(){
        return RabbitConnection.getChannel();
    }
}
