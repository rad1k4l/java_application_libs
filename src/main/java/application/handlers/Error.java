package application.handlers;

import org.bson.Document;

import java.io.File;
import java.io.FileOutputStream;

public class Error {
    public static void sendReport(@org.jetbrains.annotations.NotNull Document errorReport) throws Exception{
        String unSerialized = errorReport.toJson();
        if (System.getProperty("initialized", "false").equals("true")) {
            sendToBroker(unSerialized);
        }
        writeToFile(unSerialized);
    }

    private static void writeToFile(String msg) throws Exception
    {
        msg = msg + '\n';
        File dir = new File(System.getProperty("error.folder"));
        if (!dir.exists()){
            boolean created = dir.mkdir();
        }

        try(FileOutputStream file = new FileOutputStream(System.getProperty("error.folder") + "/error.log" , true)){
            file.write(msg.getBytes(), 0, msg.getBytes().length);
        }catch (Exception err)
        {
            System.exit(10025);
        }

    }

    public static void sendToBroker(String message)
    {
        // TODO: send to rabbit
    }
}
