package dev.arantes.spigot.tempmute;

import dev.arantes.spigot.tempmute.commands.TempMuteCommand;
import dev.arantes.spigot.tempmute.commands.UnMuteCommand;
import dev.arantes.spigot.tempmute.listeners.PlayerListener;
import dev.arantes.spigot.tempmute.user.UsersManager;
import org.bukkit.plugin.java.JavaPlugin;

public class TempMutePlugin extends JavaPlugin {
    private UsersManager users;

    public void onLoad() {
        this.users = new UsersManager(this);
    }

    public void onEnable() {
        loadModules();
    }

    private void loadModules() {
        new TempMuteCommand(this);
        new UnMuteCommand(this);

        new PlayerListener(this);
    }

    public UsersManager getUsers() {
        return users;
    }
}
