package application.connection;

import com.lambdaworks.redis.*;

public class RedisConnection {
    private static com.lambdaworks.redis.RedisConnection<String, String> link;
    static {
        connect();
    }

    public static void connect(){
        RedisClient client = createClient();
        link = client.connect();
    }

    private static RedisClient createClient() {
        return new RedisClient(
                RedisURI.create(
                        "redis://" +
                                System.getProperty("REDIS_PASSWORD") +
                                "@" +
                                System.getProperty("REDIS_HOST") +
                                ":" + System.getProperty("REDIS_PORT"))
        );
    }

    public static com.lambdaworks.redis.RedisConnection<String, String> getLink() {
        return link;
    }
}
