package eu.moonwriters.velosystemvelocity;

import com.google.inject.Inject;
import com.velocitypowered.api.command.CommandManager;
import com.velocitypowered.api.command.CommandMeta;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.event.proxy.ProxyShutdownEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;
import dev.eztxm.config.JsonConfig;
import eu.moonwriters.velosystemcore.config.MessageConfig;
import eu.moonwriters.velosystemvelocity.command.GoToCommand;
import eu.moonwriters.velosystemvelocity.command.TeamChatCommand;
import eu.moonwriters.velosystemvelocity.util.CommandMetaUtil;
import lombok.Getter;
import org.slf4j.Logger;

@Getter
@Plugin(
        name = "VeloSystem",
        id = "velosystem",
        version = "1.0",
        authors = {"ezTxmMC"},
        url = "https://eztxm.de"
)
public class VeloSystem {
    @Getter
    private static VeloSystem instance;
    private final ProxyServer server;
    private final Logger logger;
    private MessageConfig messageConfig;

    @Inject
    public VeloSystem(ProxyServer server, Logger logger) {
        this.server = server;
        this.logger = logger;
    }

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        instance = this;
        messageConfig = new MessageConfig(new JsonConfig("plugins/velosystem", "messages"));
        CommandManager commandManager = server.getCommandManager();
        CommandMeta gotoMeta = new CommandMetaUtil(this, commandManager).create("goto");
        CommandMeta teamChatMeta = new CommandMetaUtil(this, commandManager).create("teamchat", "tc");
        commandManager.register(gotoMeta, new GoToCommand());
        commandManager.register(teamChatMeta, new TeamChatCommand());
    }

    @Subscribe
    public void onProxyShutdown(ProxyShutdownEvent event) {
        instance = null;
    }

}
