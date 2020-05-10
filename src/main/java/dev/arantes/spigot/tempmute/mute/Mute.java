package dev.arantes.spigot.tempmute.mute;

import dev.arantes.spigot.tempmute.user.User;
import org.bukkit.configuration.file.FileConfiguration;

public class Mute {
    private User muted;
    private long duration;
    private long createdAt;
    private String reason;

    public Mute(User muted, String reason, long duration, long createdAt) {
        this.muted = muted;
        this.reason = reason;
        this.duration = duration;
        this.createdAt = createdAt;
    }

    public String getReason() {
        return reason;
    }

    public long getTimeRemainig() {
        return (createdAt + duration) - System.currentTimeMillis();
    }

    public User getMuted() {
        return muted;
    }

    public long getDuration() {
        return duration;
    }

    public boolean isExpired() {
        if (System.currentTimeMillis() >= (createdAt + duration)) {
            cancel();
            return true;
        }

        return false;
    }

    public void save() {
        final FileConfiguration config = muted.getFile().getConfig();
        config.set("mute.duration", duration);
        config.set("mute.createdAt", createdAt);
        config.set("mute.reason", reason);
        muted.getFile().save();

        muted.setMute(this);
    }

    public void cancel() {
        final FileConfiguration config = muted.getFile().getConfig();
        config.set("mute", null);
        muted.getFile().save();

        muted.setMute(null);
    }
}
