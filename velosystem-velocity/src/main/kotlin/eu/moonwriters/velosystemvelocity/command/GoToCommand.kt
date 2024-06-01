package eu.moonwriters.velosystemvelocity.command

import com.velocitypowered.api.command.SimpleCommand
import com.velocitypowered.api.proxy.ConnectionRequestBuilder
import com.velocitypowered.api.proxy.Player
import eu.moonwriters.velosystemvelocity.VeloSystem
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.minimessage.MiniMessage
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder
import java.util.*
import java.util.concurrent.CompletableFuture

class GoToCommand(
    private val veloSystem: VeloSystem
) : SimpleCommand {

    override fun execute(invocation: SimpleCommand.Invocation) {
        val args = invocation.arguments()
        if (invocation.source() !is Player) {
            invocation.source().sendMessage(Component.text(veloSystem.messageConfig!!.notAPlayer))
            return
        }
        val player = invocation.source() as Player
        if (!player.hasPermission("velosystem.command.goto")) {
            player.sendMessage(
                MiniMessage.miniMessage().deserialize(veloSystem.messageConfig!!.noPerms)
            )
            return
        }
        if (args.size != 1) {
            player.sendMessage(
                MiniMessage.miniMessage().deserialize(veloSystem.messageConfig!!.wrongArgsLength)
            )
            return
        }
        val optionalPlayer: Optional<Player> = veloSystem.server.getPlayer(args[0])
        if (optionalPlayer.isEmpty) {
            player.sendMessage(
                MiniMessage.miniMessage().deserialize(veloSystem.messageConfig!!.playerNotOnline)
            )
            return
        }
        val target = optionalPlayer.get()
        val optionalServer = target.currentServer
        if (optionalServer.isEmpty) {
            player.sendMessage(
                MiniMessage.miniMessage().deserialize(veloSystem.messageConfig!!.invalidServer)
            )
            return
        }
        if (player.currentServer.isPresent) {
            if (player.currentServer.get().serverInfo.name
                    .equals(optionalServer.get().serverInfo.name, ignoreCase = true)
            ) {
                player.sendMessage(
                    MiniMessage.miniMessage()
                        .deserialize(veloSystem.messageConfig!!.alreadyConnected)
                )
                return
            }
        }
        val completableFuture: CompletableFuture<ConnectionRequestBuilder.Result> =
            player.createConnectionRequest(optionalServer.get().server).connect()
        if (completableFuture.join().isSuccessful) {
            player.sendMessage(
                MiniMessage.miniMessage().deserialize(
                    veloSystem.messageConfig!!.connectedSuccessfully,
                    Placeholder.parsed("server", optionalServer.get().server.serverInfo.name)
                )
            )
            return
        }
        if (completableFuture.isCancelled) {
            player.sendMessage(
                MiniMessage.miniMessage().deserialize(
                    veloSystem.messageConfig!!.connectionCanceled,
                    Placeholder.parsed("server", optionalServer.get().server.serverInfo.name)
                )
            )
        }
    }

    override fun suggest(invocation: SimpleCommand.Invocation): List<String> {
        val args = invocation.arguments()
        if (args.size == 1) {
            val playerNames: MutableList<String> = ArrayList()
            veloSystem.server.allPlayers
                .forEach { player -> playerNames.add(player.username) }
            return playerNames
        }
        return emptyList()
    }
}
