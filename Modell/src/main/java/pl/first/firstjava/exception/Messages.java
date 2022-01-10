package pl.first.firstjava.exception;

import java.util.Locale;
import java.util.ResourceBundle;

public class Messages {

    private Messages() {
    }

    public static String getMessageForLocale(String messageKey, Locale locale) {
        return ResourceBundle.getBundle("language", locale).getString(messageKey);
    }

}
