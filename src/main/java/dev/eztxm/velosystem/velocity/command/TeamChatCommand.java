package dev.eztxm.velosystem.velocity.command;

import com.velocitypowered.api.command.SimpleCommand;
import com.velocitypowered.api.proxy.Player;
import dev.eztxm.velosystem.velocity.VeloSystem;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;

public class TeamChatCommand implements SimpleCommand {

    @Override
    public void execute(Invocation invocation) {
        String[] args = invocation.arguments();
        if (!(invocation.source() instanceof Player player)) {
            invocation.source().sendMessage(Component.text("Du bist kein Spieler"));
            return;
        }
        if (!player.hasPermission("velosystem.teamchat")) {
            player.sendMessage(MiniMessage.miniMessage().deserialize("<color:gray>Du hast ungenügende Berechtigungen"));
            return;
        }
        if (args.length < 1) {
            player.sendMessage(MiniMessage.miniMessage().deserialize("<color:gray>Gebe eine Nachricht ein"));
            return;
        }
        StringBuilder messageBuilder = new StringBuilder();
        for (int i = 0; i < args.length - 1; i++) {
            messageBuilder.append(args[i]).append(" ");
        }
        String message = messageBuilder.toString();
        VeloSystem.getInstance().getServer().getAllPlayers().forEach(players -> {
            if (!players.hasPermission("velocity.teamchat")) return;
            players.sendMessage(MiniMessage.miniMessage().deserialize(
                    "<color:red><bold>Teamchat<reset> <color:dark_gray>| <color:gray>" + player.getUsername() + " <color:dark_gray>» <color:gray:>" + message)
            );
        });
    }
}
