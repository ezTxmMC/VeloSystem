package dev.eztxm.velosystem.backend.config;

import dev.eztxm.config.JsonConfig;
import lombok.Getter;

@Getter
public class MessageConfig {
    private final JsonConfig config;
    // Normal
    @Getter private String prefix;
    @Getter private String noPerms;
    @Getter private String notAPlayer;
    @Getter private String wrongArgsLength;
    @Getter private String playerNotOnline;
    // System
    @Getter private String invalidServer;
    @Getter private String alreadyConnected;
    @Getter private String connectedSuccessfully;
    @Getter private String connectionCanceled;


    public MessageConfig(JsonConfig config) {
        this.config = config;
        this.prefix = getOrSet("Prefix", "<color:#00afff>VeloSystem <color:dark_gray>| <color:gray>");
        this.noPerms = prefix + getOrSet("No permissions", "<color:red>You don't have permission to do this");
        this.notAPlayer = getOrSet("Not a player", "You are not a player");
        this.wrongArgsLength = prefix + getOrSet("Wrong argument length", "<color:red>The argument length is incorrect");
        this.playerNotOnline = prefix + getOrSet("Player not online", "<color:red>This player is currently not online");
        this.invalidServer = prefix + getOrSet("Invalid server", "<color:red>This server can't be found");
        this.alreadyConnected = prefix + getOrSet("Already connected", "<color:red>You are already connected to this server");
        this.connectedSuccessfully = prefix + getOrSet("Connected successfully", "You has been connected successfully to <color:aqua><server>");
        this.connectionCanceled = prefix + getOrSet("Connection canceled", "The connection to <color:aqua><server> <color:gray>was canceled by the proxy");
    }

    public void update() {
        this.prefix = getOrSet("Prefix", "<color:#00afff>VeloSystem <color:dark_gray>| <color:gray>");
        this.noPerms = prefix + getOrSet("No permissions", "<color:red>You don't have permission to do this");
        this.notAPlayer = getOrSet("Not a player", "You are not a player");
        this.wrongArgsLength = prefix + getOrSet("Wrong argument length", "<color:red>The argument length is incorrect");
        this.playerNotOnline = prefix + getOrSet("Player not online", "<color:red>This player is currently not online");
        this.invalidServer = prefix + getOrSet("Invalid server", "<color:red>This server can't be found");
        this.alreadyConnected = prefix + getOrSet("Already connected", "<color:red>You are already connected to this server");
        this.connectedSuccessfully = prefix + getOrSet("Connected successfully", "You has been connected successfully to <color:aqua><server>");
        this.connectionCanceled = prefix + getOrSet("Connection canceled", "The connection to <color:aqua><server> <color:gray>was canceled by the proxy");
    }

    private String getOrSet(String key, String set) {
        String value = config.getString(key);
        if (value == null) {
            config.setString(key, set);
            return config.getString(key);
        }
        return value;
    }
}
