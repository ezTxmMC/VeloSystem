package eu.moonwriters.velosystemvelocity

import com.google.inject.Inject
import com.velocitypowered.api.event.Subscribe
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent
import com.velocitypowered.api.event.proxy.ProxyShutdownEvent
import com.velocitypowered.api.plugin.Plugin
import com.velocitypowered.api.proxy.ProxyServer
import dev.eztxm.config.JsonConfig
import eu.moonwriters.velosystemcore.config.MessageConfig
import eu.moonwriters.velosystemvelocity.command.GoToCommand
import eu.moonwriters.velosystemvelocity.command.TeamChatCommand
import eu.moonwriters.velosystemvelocity.util.CommandMetaUtil
import lombok.Getter
import org.slf4j.Logger

@Getter
@Plugin(name = "VeloSystem", id = "velosystem", version = "1.0", authors = ["ezTxmMC"], url = "https://eztxm.de")
class VeloSystem @Inject constructor(internal val server: ProxyServer, internal val logger: Logger) {
    internal var messageConfig: MessageConfig? = null

    @Subscribe
    fun onProxyInitialization(event: ProxyInitializeEvent?) {
        messageConfig = MessageConfig(JsonConfig("plugins/velosystem", "messages"))
        val commandManager = server.commandManager
        val gotoMeta = CommandMetaUtil(this, commandManager).create("goto")
        val teamChatMeta = CommandMetaUtil(this, commandManager).create("teamchat", "tc")
        commandManager.register(gotoMeta, GoToCommand(this))
        commandManager.register(teamChatMeta, TeamChatCommand(this))
    }
}
