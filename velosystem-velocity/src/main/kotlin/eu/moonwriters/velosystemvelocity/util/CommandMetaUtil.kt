package eu.moonwriters.velosystemvelocity.util

import com.velocitypowered.api.command.CommandManager
import com.velocitypowered.api.command.CommandMeta

class CommandMetaUtil(private val plugin: Any, private val commandManager: CommandManager) {

    fun create(commandName: String?, vararg aliases: String?): CommandMeta {
        return commandManager.metaBuilder(commandName)
            .aliases(*aliases)
            .plugin(plugin)
            .build()
    }
}
