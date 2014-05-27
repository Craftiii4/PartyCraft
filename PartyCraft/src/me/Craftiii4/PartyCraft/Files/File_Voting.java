package me.Craftiii4.PartyCraft.Files;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;

import me.Craftiii4.PartyCraft.PartyCraft;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class File_Voting {
	
	public 	static 		PartyCraft 			plugin;
	
	public File_Voting(PartyCraft instance) {
		plugin = instance;
		loadConfig();
	}
	
	public static FileConfiguration dropparty = null;
	public static File droppartyFile = null;
	
	private static Location VoteLocation;
	
	private static Integer CurrentVotes;
	private static String HiddenItem;

	@SuppressWarnings("deprecation")
	public static void reloadDropPartyConfig() {
		if (droppartyFile == null) {
			droppartyFile = new File(plugin.getDataFolder(), "StoredData/VotingInfo.txt");
		}
		dropparty = YamlConfiguration.loadConfiguration(droppartyFile);

		InputStream defConfigStream2 = plugin.getResource("StoredData/VotingInfo.txt");
		if (defConfigStream2 != null) {
			YamlConfiguration defConfig2 = YamlConfiguration
					.loadConfiguration(defConfigStream2);
			dropparty.setDefaults(defConfig2);
		}
	}

	public static FileConfiguration getDropPartyConfig() {
		if (dropparty == null) {
			reloadDropPartyConfig();
		}
		return dropparty;
	}

	public static void saveDropPartyConfig() {
		if (dropparty == null || droppartyFile == null) {
			return;
		}
		try {
			dropparty.save(droppartyFile);
		} catch (IOException ex) {
	    	plugin.LogMessage(Level.INFO, "Created File: VotingInfo.txt");
		}
	}
	
	public static Integer getCurrentVotes() {
		return CurrentVotes;
	}
	
	public static void addtoCurrentVotes() {
		CurrentVotes++;
	}
	
	public static void setCurrentVotes(Integer amount) {
		CurrentVotes = amount;
	}
	
	public static String getHiddenItem() {
		return HiddenItem;
	}
	
	public static void loadConfig() {
		
		getDropPartyConfig().addDefault("CurrentVotes", 0);
		
		getDropPartyConfig().addDefault("Location.World", "!");
		getDropPartyConfig().addDefault("Location.X", 0);
		getDropPartyConfig().addDefault("Location.Y", 0);
		getDropPartyConfig().addDefault("Location.Z", 0);
		
		getDropPartyConfig().addDefault("HiddenID", 0);
		getDropPartyConfig().addDefault("HiddenSUBID", 0);
		
		getDropPartyConfig().options().copyDefaults(true);
		saveDropPartyConfig();
		
		String worldname = getDropPartyConfig().getString("Location.World");
		
		if (worldname.equals("!")) {
			VoteLocation = null;
		} else {
			
			Integer X = getDropPartyConfig().getInt("Location.X");
			Integer Y = getDropPartyConfig().getInt("Location.Y");
			Integer Z = getDropPartyConfig().getInt("Location.Z");
			
			World world = Bukkit.getWorld(worldname);
		
			if (world == null) {
				VoteLocation = null;
			} else {
				VoteLocation = new Location(world, X, Y, Z);
			}
		
		}
		
		CurrentVotes = getDropPartyConfig().getInt("CurrentVotes");
		HiddenItem = getDropPartyConfig().getInt("HiddenID") + ":" + getDropPartyConfig().getInt("HiddenSUBID");
		
	}
	
	public static void saveConfig() {
		
		getDropPartyConfig().set("CurrentVotes", CurrentVotes);
		saveDropPartyConfig();
		
	}
	
	public static Location getVoteLocation() {
		return VoteLocation;
	}
	
	public static void setVoteLocation(Location location) {
		VoteLocation = location;
	}
	
	public static void setHiddenItem(String item) {
		HiddenItem = item;
	}

}
