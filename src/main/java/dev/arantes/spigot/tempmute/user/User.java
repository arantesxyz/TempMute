package dev.arantes.spigot.tempmute.user;

import dev.arantes.spigot.tempmute.TempMutePlugin;
import dev.arantes.spigot.tempmute.mute.Mute;
import dev.arantes.spigot.tempmute.utils.YamlConfig;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public class User {
    private TempMutePlugin plugin;
    private Player player;
    private Mute mute = null;
    private YamlConfig file;

    public User(TempMutePlugin plugin, Player player) {
        this.plugin = plugin;
        this.player = player;
        this.file = new YamlConfig("players/" + getPlayer().getUniqueId() + ".yml", getPlugin());

        load();
    }

    private void load() {
        final ConfigurationSection muteSection = file.getConfig().getConfigurationSection("mute");
        if (muteSection != null) {
            this.mute = new Mute(
                    this,
                    muteSection.getString("reason"),
                    muteSection.getLong("duration"),
                    muteSection.getLong("createdAt")
            );
        }
    }

    public Player getPlayer() {
        return player;
    }

    public TempMutePlugin getPlugin() {
        return plugin;
    }

    public Mute getMute() {
        return mute;
    }

    public void setMute(Mute mute) {
        this.mute = mute;
    }

    public YamlConfig getFile() {
        return file;
    }
}
