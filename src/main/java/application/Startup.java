package application;

import application.handlers.Error;
import org.bson.Document;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Startup {

    private static String confFile = ".env";

    public static void init() throws Exception
    {
        confFile = System.getProperty("config", ".env");
        initConfig();
    }

    private static void initConfig() throws Exception
    {
        try {
            File file = new File(confFile);
            if (!file.exists())
            {

                throw new FileNotFoundException("config file not found ");
            }
            if ( !file.isFile()){
                throw new FileNotFoundException("config not a file");
            }
            if (!file.canRead()){
                throw new FileNotFoundException("config file not readable");
            }


            BufferedReader bufferedReader = new BufferedReader(new FileReader(confFile));
            String line;
            List<String> lines = new ArrayList<>();
            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }
            String [] config = lines.toArray(new String[lines.size()]);
            setConfig(config);
        }catch (Exception error) {
            System.out.println("error ocurred " + error.getMessage());
            String errorTitle = "Undefined ERROR ocurred on Startup while reads config file";
            if (error instanceof FileNotFoundException)
                errorTitle = String.format("The config file '%s' not found", confFile);

            Document report = new Document().append("status", "error").append("title", errorTitle)
                    .append("message", error.getMessage())
                    .append("time",0);
            Error.sendReport(report);
            System.exit(100051);
        }
    }


    private static void setConfig(@NotNull String[] conf) {

        for (String config :
                conf) {
            if (config.isEmpty()) continue;
            String[] split = config.split("=");
            System.setProperty(split[0], "");
            if (split.length >= 2)
                System.setProperty(split[0], split[1]);
        }
    }
}
