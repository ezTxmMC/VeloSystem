package eu.moonwriters.velosystemvelocity.command

import com.velocitypowered.api.command.SimpleCommand
import com.velocitypowered.api.proxy.Player
import eu.moonwriters.velosystemvelocity.VeloSystem
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage

class TeamChatCommand(
    private val veloSystem: VeloSystem
) : SimpleCommand {

    private val prefix = "<color:red><bold>Teamchat<reset> <color:dark_gray>| "

    override fun execute(invocation: SimpleCommand.Invocation) {
        val args = invocation.arguments()
        if (invocation.source() !is Player) {
            invocation.source().sendMessage(Component.text(veloSystem.messageConfig!!.notAPlayer))
            return
        }
        val player = invocation.source() as Player
        if (!player.hasPermission("velosystem.teamchat")) {
            player.sendMessage(
                MiniMessage.miniMessage().deserialize(prefix + veloSystem.messageConfig!!.noPerms)
            )
            return
        }
        if (args.isEmpty()) {
            player.sendMessage(
                MiniMessage.miniMessage()
                    .deserialize(prefix + veloSystem.messageConfig!!.wrongArgsLength)
            )
            return
        }
        val messageBuilder = StringBuilder()
        for (arg in args) {
            messageBuilder.append(arg).append(" ")
        }
        val message = messageBuilder.toString()
        veloSystem.server.allPlayers.forEach { players ->
            if (!players.hasPermission("velosystem.teamchat")) return@forEach
            players.sendMessage(
                MiniMessage.miniMessage().deserialize(
                    prefix + "<color:gray>" + player.username + " <color:dark_gray>» <color:gray:>" + message
                )
            )
        }
    }
}
