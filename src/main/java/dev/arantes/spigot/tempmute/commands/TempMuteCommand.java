package dev.arantes.spigot.tempmute.commands;

import dev.arantes.spigot.tempmute.TempMutePlugin;
import dev.arantes.spigot.tempmute.exceptions.PluginException;
import dev.arantes.spigot.tempmute.mute.Mute;
import dev.arantes.spigot.tempmute.user.User;
import dev.arantes.spigot.tempmute.utils.TimeFormat;
import dev.arantes.spigot.tempmute.utils.TimeUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TempMuteCommand extends CommandBase {
    public TempMuteCommand(TempMutePlugin plugin) {
        super(plugin, "tempmute", "senior.tempmute");
    }

    @Override
    public void execute(CommandSender sender, String[] args) throws PluginException {
        if (args.length < 2) {
            throw new PluginException("§cInvalid sintax. Use: /tempmute <player> <time and reason>");
        }

        final Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            throw new PluginException("§cThe player is not online!");
        }

        final User user = getPlugin().getUsers().get(target.getUniqueId());
        if (user.getMute() != null) {
            throw new PluginException("§cThis user is already muted!");
        }

        final Set<String> reason = new HashSet<>();
        long time = -1;
        boolean isSilence = false;

        for (String arg : Arrays.copyOfRange(args, 1, args.length)) {
            // Tries to get a time unit and checks if the argument is part of the time
            final TimeFormat unit = TimeFormat.getFormat(arg);
            if (unit != null) {
                final int number = Integer.parseInt(arg.replaceAll("[\\D]", ""));
                time += number * unit.getTime();

                continue;
            }

            // Checks if the mute should be silent or not
            if (arg.equals("-s")) {
                isSilence = true;
                continue;
            }

            // Adds to the reason if not time or silent selector
            reason.add(arg);
        }

        if (time == -1) {
            throw new PluginException("§cInvalid time!");
        }

        if (time == 0) {
            throw new PluginException("§cThe time cannot be 0.");
        }

        final Mute mute = new Mute(user, String.join(" ", reason), time, System.currentTimeMillis());
        mute.save();

        final String formatedTime = TimeUtils.formatMillisToString(mute.getDuration());

        sender.sendMessage("§cThe player was muted for §e" + formatedTime);

        if (isSilence) {
            return;
        }

        Bukkit.broadcastMessage(String.format(
                "§cThe player §e%s §cwas muted by §e%s §cfor §e%s §cfor %s",
                mute.getMuted().getPlayer().getName(),
                sender.getName(),
                formatedTime,
                mute.getReason()
        ));
    }
}
