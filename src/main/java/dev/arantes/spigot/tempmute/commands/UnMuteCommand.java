package dev.arantes.spigot.tempmute.commands;

import dev.arantes.spigot.tempmute.TempMutePlugin;
import dev.arantes.spigot.tempmute.exceptions.PluginException;
import dev.arantes.spigot.tempmute.mute.Mute;
import dev.arantes.spigot.tempmute.user.User;
import org.apache.commons.lang.ArrayUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class UnMuteCommand extends CommandBase {
    public UnMuteCommand(TempMutePlugin plugin) {
        super(plugin, "unmute", "senior.unmute");
    }

    @Override
    public void execute(CommandSender sender, String[] args) throws PluginException {
        if (args.length == 0) {
            throw new PluginException("§cInvalid sintax. Use: /unmute <player>");
        }

        final Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            throw new PluginException("§cThe player is not online!");
        }

        final User user = getPlugin().getUsers().get(target.getUniqueId());
        final Mute mute = user.getMute();
        if (mute == null) {
            throw new PluginException("§cThis player is not muted!");
        }

        mute.cancel();

        sender.sendMessage("§cThe player was unmuted!");
        if (ArrayUtils.indexOf(args, "-s") != -1) {
            return;
        }

        Bukkit.broadcastMessage(String.format(
                "§cThe player §e%s §cwas unmuted by §e%s§c.",
                mute.getMuted().getPlayer().getName(),
                sender.getName()
        ));
    }
}
