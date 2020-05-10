package dev.arantes.spigot.tempmute.listeners;

import dev.arantes.spigot.tempmute.TempMutePlugin;
import dev.arantes.spigot.tempmute.mute.Mute;
import dev.arantes.spigot.tempmute.user.User;
import dev.arantes.spigot.tempmute.utils.TimeUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {
    private TempMutePlugin plugin;

    public PlayerListener(TempMutePlugin plugin) {
        this.plugin = plugin;

        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onAsyncChat(AsyncPlayerChatEvent event) {
        final User user = plugin.getUsers().get(event.getPlayer().getUniqueId());
        final Mute mute = user.getMute();

        if (mute == null || mute.isExpired()) {
            return;
        }

        event.setCancelled(true);
        event.getPlayer().sendMessage(String.format(
                "§cYou're muted for §e%s§c for %s.",
                TimeUtils.formatMillisToString(mute.getTimeRemainig()),
                mute.getReason()
        ));
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        // Load user cache
        plugin.getUsers().get(event.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        // Clean cache when user logs out
        plugin.getUsers().remove(event.getPlayer().getUniqueId());
    }
}
