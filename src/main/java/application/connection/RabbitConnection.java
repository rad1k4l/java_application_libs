package application.connection;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitConnection {

    private static Connection link;
    private static Channel channel;
    private static ConnectionFactory factory;
    static {
        connect();
    }

    public static boolean isConnected(){
        return link != null;
    }

    private static void connect(){
        factory = getConfiguredFactory(new ConnectionFactory());
        try {
            link = factory.newConnection();
            channel = link.createChannel();
        }catch (IOException e ){
            System.out.println("io Exception " + e.getMessage());
            System.exit(1002);
        }catch (TimeoutException e){
            System.out.println("Timeout exception " + e.getMessage());
            System.exit(10034);
        }
    }

    public static Channel getChannel() {
        return channel;
    }

    public static Connection getLink() {
        return link;
    }

    private static ConnectionFactory getConfiguredFactory(ConnectionFactory factory)
    {
        factory.setHost(System.getProperty("RABBIT_HOST"));
        factory.setPassword(System.getProperty("RABBIT_PASSWORD"));
        factory.setPort(Integer.parseInt(System.getProperty("RABBIT_PORT")));
        factory.setUsername(System.getProperty("RABBIT_USERNAME"));
        return factory;
    }

}
