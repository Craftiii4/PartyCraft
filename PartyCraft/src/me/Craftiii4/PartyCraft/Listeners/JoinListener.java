package me.Craftiii4.PartyCraft.Listeners;

import me.Craftiii4.PartyCraft.PartyCraft;
import me.Craftiii4.PartyCraft.UpdateChecker;
import me.Craftiii4.PartyCraft.Files.Config_PartyCraft;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener  {
	
	public static PartyCraft plugin;

	public JoinListener(PartyCraft instance) {
		plugin = instance;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		
		Player player = event.getPlayer();
		
		if (!player.hasPermission("partycraft.checkupdate"))
			return;
		
		if (Config_PartyCraft.getAlertAdminsOfUpdate() && UpdateChecker.getUpdateOut()) {
			player.sendMessage(PartyCraft.getPrefix() + "§eA new version §7v" + UpdateChecker.getUpdateVersion() + " §eis out");
			return;
		}
		
		if (Config_PartyCraft.getCheckForUpdateOnAdminJoin())
			UpdateChecker.StartUpdateChecker(plugin, player);
		
	}

}
