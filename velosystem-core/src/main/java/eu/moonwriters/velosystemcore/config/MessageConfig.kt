package eu.moonwriters.velosystemcore.config

import dev.eztxm.config.JsonConfig
import lombok.Getter

@Getter
class MessageConfig(private val config: JsonConfig) {
    // Normal
    @Getter
    var prefix: String

    @Getter
    var noPerms: String

    @Getter
    var notAPlayer: String

    @Getter
    var wrongArgsLength: String

    @Getter
    var playerNotOnline: String

    // System
    @Getter
    var invalidServer: String

    @Getter
    var alreadyConnected: String

    @Getter
    var connectedSuccessfully: String

    @Getter
    var connectionCanceled: String


    init {
        this.prefix = getOrSet("Prefix", "<color:#00afff>VeloSystem <color:dark_gray>| <color:gray>")
        this.noPerms = prefix + getOrSet("No permissions", "<color:red>You don't have permission to do this")
        this.notAPlayer = getOrSet("Not a player", "You are not a player")
        this.wrongArgsLength = prefix + getOrSet("Wrong argument length", "<color:red>The argument length is incorrect")
        this.playerNotOnline = prefix + getOrSet("Player not online", "<color:red>This player is currently not online")
        this.invalidServer = prefix + getOrSet("Invalid server", "<color:red>This server can't be found")
        this.alreadyConnected =
            prefix + getOrSet("Already connected", "<color:red>You are already connected to this server")
        this.connectedSuccessfully =
            prefix + getOrSet("Connected successfully", "You has been connected successfully to <color:aqua><server>")
        this.connectionCanceled = prefix + getOrSet(
            "Connection canceled",
            "The connection to <color:aqua><server> <color:gray>was canceled by the proxy"
        )
    }

    fun update() {
        this.prefix = getOrSet("Prefix", "<color:#00afff>VeloSystem <color:dark_gray>| <color:gray>")
        this.noPerms = prefix + getOrSet("No permissions", "<color:red>You don't have permission to do this")
        this.notAPlayer = getOrSet("Not a player", "You are not a player")
        this.wrongArgsLength = prefix + getOrSet("Wrong argument length", "<color:red>The argument length is incorrect")
        this.playerNotOnline = prefix + getOrSet("Player not online", "<color:red>This player is currently not online")
        this.invalidServer = prefix + getOrSet("Invalid server", "<color:red>This server can't be found")
        this.alreadyConnected =
            prefix + getOrSet("Already connected", "<color:red>You are already connected to this server")
        this.connectedSuccessfully =
            prefix + getOrSet("Connected successfully", "You has been connected successfully to <color:aqua><server>")
        this.connectionCanceled = prefix + getOrSet(
            "Connection canceled",
            "The connection to <color:aqua><server> <color:gray>was canceled by the proxy"
        )
    }

    private fun getOrSet(key: String, set: String): String {
        val value = config[key].asString()
        if (value == null) {
            config[key] = set
            return config[key].asString()
        }
        return value
    }
}
