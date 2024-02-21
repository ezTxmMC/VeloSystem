package dev.eztxm.velosystem.velocity;

import com.google.inject.Inject;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyShutdownEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;
import org.slf4j.Logger;

@Plugin(
        name = "VeloSystem",
        id = "velosystem",
        version = "1.0",
        authors = {"ezTxmMC"},
        url = "https://eztxm.de"
)
public class VeloSystem {
    private static VeloSystem instance;
    private final ProxyServer server;
    private final Logger logger;

    @Inject
    public VeloSystem(ProxyServer server, Logger logger) {
        this.server = server;
        this.logger = logger;
    }

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        instance = this;
    }

    @Subscribe
    public void onProxyShutdown(ProxyShutdownEvent event) {
        instance = null;
    }

    public Logger getLogger() {
        return logger;
    }

    public ProxyServer getServer() {
        return server;
    }

    public static VeloSystem getInstance() {
        return instance;
    }
}
