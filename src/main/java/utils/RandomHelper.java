package utils;

public class RandomHelper {

        public static String getRandomCode (){
            int code = (int) ((Math.random() * 8999) + 1000);
            String randCode = String.valueOf(code);
            return randCode;
        }
}