package application.connection;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class MongoConnection {
    private static MongoClient connection = null;
    private static String connection_string = null;

    static {
        if ( connection == null)
            connect();
    }

    MongoConnection(){}

    MongoConnection(String connection_string){
        MongoConnection.connection_string = connection_string;
    }

    public static MongoClient getConnection() {
        return connection;
    }

    private static void connect() {
        connection_string = System.getProperty("mongodb_uri");
        connection = new MongoClient(new MongoClientURI(connection_string));
    }

    public static void close() {
        connection.close();
    }


    public static void reconnect(){
        if (connection != null)
            close();
        connect();
    }

}
