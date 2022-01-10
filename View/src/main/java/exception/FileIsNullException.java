package exception;

import pl.first.firstjava.exception.Messages;

import java.util.Locale;

public class FileIsNullException extends NullPointerException {

    private final String messageKey;
    private final Locale locale;

    public FileIsNullException(String messageKey) {
        this(messageKey, Locale.getDefault());
    }

    public FileIsNullException(String messageKey, Locale locale) {
        this.messageKey = messageKey;
        this.locale = locale;
    }

    public String getLocalizedMessage() {
        return Messages.getMessageForLocale(messageKey, locale);
    }
}
