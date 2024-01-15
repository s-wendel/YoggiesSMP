package shwendel.yoggiessmp.util;

public class CaseUtil {

    public static String toProperCase(String text) {

        String newText = "";

        String[] split = text.split("_");

        for(int i = 0; i < split.length; i++) {

            String word = split[i];

            newText += word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase() + " ";

        }

        return newText.substring(0, newText.length() - 1);

    }

}
