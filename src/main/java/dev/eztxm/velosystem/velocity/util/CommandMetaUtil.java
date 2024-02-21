package dev.eztxm.velosystem.velocity.util;

import com.velocitypowered.api.command.CommandManager;
import com.velocitypowered.api.command.CommandMeta;

public class CommandMetaUtil {
    private final Object plugin;
    private final CommandManager commandManager;

    public CommandMetaUtil(Object plugin, CommandManager commandManager) {
        this.plugin = plugin;
        this.commandManager = commandManager;
    }

    public CommandMeta create(String commandName, String... aliases) {
        return commandManager.metaBuilder(commandName)
                .aliases(aliases)
                .plugin(plugin)
                .build();
    }
}
