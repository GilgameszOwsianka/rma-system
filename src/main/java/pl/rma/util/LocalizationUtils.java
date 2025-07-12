package pl.rma.util;

import java.util.Locale;
import java.util.ResourceBundle;

public class LocalizationUtils {
    private ResourceBundle messages;

    public LocalizationUtils(Locale locale) {
        messages = ResourceBundle.getBundle("messages", locale);
    }

    public String getMessage(String key) {
        return messages.getString(key);
    }
}

