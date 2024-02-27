package dev.eztxm.velosystem.velocity.command;

import com.velocitypowered.api.command.SimpleCommand;
import com.velocitypowered.api.proxy.ConnectionRequestBuilder;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ServerConnection;
import dev.eztxm.velosystem.velocity.VeloSystem;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class GoToCommand implements SimpleCommand {

    @Override
    public void execute(Invocation invocation) {
        String[] args = invocation.arguments();
        if (!(invocation.source() instanceof Player player)) {
            invocation.source().sendMessage(Component.text(VeloSystem.getInstance().getMessageConfig().getNotAPlayer()));
            return;
        }
        if (!player.hasPermission("velosystem.command.goto")) {
            player.sendMessage(MiniMessage.miniMessage().deserialize(VeloSystem.getInstance().getMessageConfig().getNoPerms()));
            return;
        }
        if (args.length != 1) {
            player.sendMessage(MiniMessage.miniMessage().deserialize(VeloSystem.getInstance().getMessageConfig().getWrongArgsLength()));
            return;
        }
        Optional<Player> optionalPlayer = VeloSystem.getInstance().getServer().getPlayer(args[0]);
        if (optionalPlayer.isEmpty()) {
            player.sendMessage(MiniMessage.miniMessage().deserialize(VeloSystem.getInstance().getMessageConfig().getPlayerNotOnline()));
            return;
        }
        Player target = optionalPlayer.get();
        Optional<ServerConnection> optionalServer = target.getCurrentServer();
        if (optionalServer.isEmpty()) {
            player.sendMessage(MiniMessage.miniMessage().deserialize(VeloSystem.getInstance().getMessageConfig().getInvalidServer()));
            return;
        }
        if (player.getCurrentServer().isPresent()) {
            if (player.getCurrentServer().get().getServerInfo().getName().equalsIgnoreCase(optionalServer.get().getServerInfo().getName())) {
                player.sendMessage(MiniMessage.miniMessage().deserialize(VeloSystem.getInstance().getMessageConfig().getAlreadyConnected()));
                return;
            }
        }
        CompletableFuture<ConnectionRequestBuilder.Result> completableFuture = player.createConnectionRequest(optionalServer.get().getServer()).connect();
        if (completableFuture.join().isSuccessful()) {
            player.sendMessage(MiniMessage.miniMessage().deserialize(VeloSystem.getInstance().getMessageConfig().getConnectedSuccessfully(),
                    Placeholder.parsed("server", optionalServer.get().getServer().getServerInfo().getName())));
            return;
        }
        if (completableFuture.isCancelled()) {
            player.sendMessage(MiniMessage.miniMessage().deserialize(VeloSystem.getInstance().getMessageConfig().getConnectionCanceled(),
                    Placeholder.parsed("server", optionalServer.get().getServer().getServerInfo().getName())));
        }
    }

    @Override
    public List<String> suggest(Invocation invocation) {
        String[] args = invocation.arguments();
        if (args.length == 1) {
            List<String> playerNames = new ArrayList<>();
            VeloSystem.getInstance().getServer().getAllPlayers().forEach(player -> playerNames.add(player.getUsername()));
            return playerNames;
        }
        return Collections.emptyList();
    }
}
