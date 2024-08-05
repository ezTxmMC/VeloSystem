package eu.moonwriters.velosystemvelocity.command.bansystem;

import com.velocitypowered.api.command.SimpleCommand;
import com.velocitypowered.api.proxy.Player;
import eu.moonwriters.velosystemvelocity.VeloSystem;
import net.kyori.adventure.text.Component;

import java.util.Optional;

public class KickCommand implements SimpleCommand {

    @Override
    public void execute(Invocation invocation) {
        if (!(invocation.source() instanceof Player player)) {
            invocation.source().sendMessage(Component.text("You are not a player"));
            return;
        }
        String[] args = invocation.arguments();
        if (args.length < 2) {
            player.sendMessage(Component.text("Wrong argument length"));
            return;
        }
        Optional<Player> optionalTarget = VeloSystem.getInstance().getServer().getPlayer(args[0]);
        if (optionalTarget.isEmpty()) {
            player.sendMessage(Component.text("This player is not online"));
            return;
        }
        Player target = optionalTarget.get();
        StringBuilder reason = new StringBuilder();
        for (int i = 1; i < args.length - 1; i++) {
            reason.append(args[i]);
            if (i < args.length - 2) {
                reason.append(" ");
            }
        }
        target.disconnect(Component.text("""
                Server Network
                
                You has been kicked for %s
                
                Report wrong kicks to the team!"""
                .formatted(reason.toString()))
        );
        player.sendMessage(Component.text("The player %s has been successfully kicked for %s".formatted(target.getUsername(), reason.toString())));
    }
}
