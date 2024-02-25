package dev.eztxm.velosystem.velocity.command;

import com.velocitypowered.api.command.SimpleCommand;
import com.velocitypowered.api.proxy.ConnectionRequestBuilder;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ServerConnection;
import dev.eztxm.velosystem.velocity.VeloSystem;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;

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
            invocation.source().sendMessage(Component.text("Du bist kein Spieler"));
            return;
        }
        if (!player.hasPermission("velosystem.command.goto")) {
            player.sendMessage(MiniMessage.miniMessage().deserialize("<color:gray>Du hast ungenügende Berechtigungen"));
            return;
        }
        if (args.length != 1) {
            player.sendMessage(MiniMessage.miniMessage().deserialize("<color:gray>Bitte gebe einen Spielernamen an"));
            return;
        }
        Optional<Player> optionalPlayer = VeloSystem.getInstance().getServer().getPlayer(args[0]);
        if (optionalPlayer.isEmpty()) {
            player.sendMessage(MiniMessage.miniMessage().deserialize("<color:gray>Dieser Spieler ist aktuell nicht online"));
            return;
        }
        Player target = optionalPlayer.get();
        Optional<ServerConnection> optionalServer = target.getCurrentServer();
        if (optionalServer.isEmpty()) {
            player.sendMessage(MiniMessage.miniMessage().deserialize("<color:red>Server ungültig"));
            return;
        }
        CompletableFuture<ConnectionRequestBuilder.Result> completableFuture = player.createConnectionRequest(optionalServer.get().getServer()).connect();
        if (completableFuture.join().isSuccessful()) {
            player.sendMessage(MiniMessage.miniMessage().deserialize("<color:gray>Mit " + optionalServer.get().getServer().getServerInfo().getName() + " erfolgreich verbunden"));
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
