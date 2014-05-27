package me.Craftiii4.PartyCraft;

import java.util.logging.Level;
import java.util.logging.Logger;

import me.Craftiii4.PartyCraft.Commands.Command_DropCast;
import me.Craftiii4.PartyCraft.Commands.Command_DropParty;
import me.Craftiii4.PartyCraft.Commands.Command_PartyCraft;
import me.Craftiii4.PartyCraft.Files.Config_DropParty;
import me.Craftiii4.PartyCraft.Files.Config_PartyCraft;
import me.Craftiii4.PartyCraft.Files.File_Voting;
import me.Craftiii4.PartyCraft.Listeners.DropVoteListener;
import me.Craftiii4.PartyCraft.Listeners.ItemAddListener;
import me.Craftiii4.PartyCraft.Listeners.ItemPickUpListener;
import me.Craftiii4.PartyCraft.Listeners.JoinListener;
import me.Craftiii4.PartyCraft.Listeners.SignPlaceListener;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.vexsoftware.votifier.Votifier;

public class PartyCraft extends JavaPlugin {
	
	final static private String Prefix = "§6[§aPartyCraft§6] "; 
	final private Logger log = getLogger();
	
	public void LogMessage(Level level, String message) {
		log.log(level, message);
	}
	
	public static String getPrefix() { 
		return Prefix;
	}
	
	@Override
	public void onDisable() { 
		
		File_Voting.saveConfig();
		
		LogMessage(Level.INFO, "Disabled v" + getDescription().getVersion());
		
	}
	
	@Override
	public void onEnable() {
		
		new Config_PartyCraft(this);
		new Config_DropParty(this);
		new File_Voting(this);
		
		new SignPlaceListener(this);
		new ItemAddListener(this);
		new ItemPickUpListener(this);
		new JoinListener(this);
		
		getCommand("partycraft").setExecutor(new Command_PartyCraft(this));
		getCommand("dropcast").setExecutor(new Command_DropCast());
		getCommand("dropparty").setExecutor(new Command_DropParty());
		
		if (Config_PartyCraft.getCheckForUpdateOnStart())
			UpdateChecker.StartUpdateChecker(this, null);
						
		LogMessage(Level.INFO, "Enabled v" + getDescription().getVersion());
		
		if (Config_DropParty.getAllowVote()) {
			
			Plugin vote = this.getServer().getPluginManager().getPlugin("Votifier");
			
			if (vote != null & vote instanceof Votifier) {
				Config_DropParty.setDefaultVoteStartingItems();
				new DropVoteListener(this);
				LogMessage(Level.INFO, "Votifier found and linked");
			} else {
				LogMessage(Level.INFO, "Votifier was not found, cannot link");
			}

		}
		
	}

	public static String convertColourMessage(String message) {
		
		message = message.replace("&1", "§1").replace("&2", "§2")
				.replace("&3", "§3").replace("&4", "§4")
				.replace("&5", "§5").replace("&6", "§6")
				.replace("&7", "§7").replace("&8", "§8")
				.replace("&9", "§9").replace("&0", "§0")
				.replace("&a", "§a").replace("&b", "§b")
				.replace("&c", "§c").replace("&d", "§d")
				.replace("&e", "§e").replace("&f", "§f")
				.replace("&k", "§k").replace("&l", "§l")
				.replace("&m", "§m").replace("&n", "§n")
				.replace("&o", "§o");
		
		return message;
		
	}
	
	public static String removeColour(String message) {
		
		message = message.replace("§1", "").replace("§2", "")
				.replace("§3", "").replace("§4", "")
				.replace("§5", "").replace("§6", "")
				.replace("§7", "").replace("§8", "")
				.replace("§9", "").replace("§0", "")
				.replace("§a", "").replace("§b", "")
				.replace("§c", "").replace("§d", "")
				.replace("§e", "").replace("§f", "")
				.replace("§k", "").replace("§l", "")
				.replace("§m", "").replace("§n", "")
				.replace("§o", "");
		
		return message;
		
	}	

}
