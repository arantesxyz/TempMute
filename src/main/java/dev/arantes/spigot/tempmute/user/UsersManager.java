package dev.arantes.spigot.tempmute.user;

import dev.arantes.spigot.tempmute.TempMutePlugin;
import dev.arantes.spigot.tempmute.utils.ConcurrentCache;
import org.bukkit.Bukkit;

import java.util.UUID;

public class UsersManager extends ConcurrentCache<UUID, User> {
    private TempMutePlugin plugin;

    public UsersManager(TempMutePlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public User get(UUID uuid) {

        if (containsKey(uuid)) {
            return super.get(uuid);
        }

        User user = new User(plugin, Bukkit.getPlayer(uuid));
        put(uuid, user);

        return user;
    }
}