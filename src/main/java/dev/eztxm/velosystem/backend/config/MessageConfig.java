package dev.eztxm.velosystem.backend.config;

import dev.eztxm.config.JsonConfig;
import dev.eztxm.velosystem.backend.util.ValueType;
import lombok.Getter;

@Getter
public class MessageConfig {
    private final JsonConfig config;
    // Normal
    private String prefix;
    private String noPerms;
    private String notAPlayer;
    private String wrongArgsLength;
    private String playerNotOnline;
    // System
    private String invalidServer;
    private String alreadyConnected;
    private String connectedSuccessfully;
    private String connectionCanceled;


    public MessageConfig(JsonConfig config) {
        this.config = config;
    }

    private Object getOrSet(String key, ValueType type) {
        Object value = null;
        switch (type) {
            case STRING -> {
                value = config.getString(key);
                if (value == null) {
                    config.setString(key, "Insert " + key);
                    return config.getString(key);
                }
            }
            case BOOLEAN -> value = config.getBoolean(key);
        }
        return value;
    }
}
