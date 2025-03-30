package utils;

import io.github.cdimascio.dotenv.Dotenv;

public class Env {
    static Dotenv env = Dotenv.load();

    public static String get(String value){
        return env.get(value);
    }
}
