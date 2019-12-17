package application.adapter;

import application.connection.RabbitConnection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import java.io.IOException;

public class RabbitListen {

    private static String exchange = "root";

    public void topic(String route, DeliverCallback callable){
        try {
            Channel channel = RabbitConnection.getChannel();
            channel.exchangeDeclare(exchange, "topic");
            String queueName = channel.queueDeclare().getQueue();
            channel.queueBind(queueName, exchange, route);
            channel.basicConsume(queueName,true, callable, consumerTag -> { } );
        } catch (IOException exception) {
            System.out.println("ERROR EXCEPTION: " + exception.getMessage());
            System.exit(23);
        }
    }

}
