package me.Craftiii4.PartyCraft.Files;

import java.io.File;
import java.util.logging.Level;

import me.Craftiii4.PartyCraft.PartyCraft;

public class Config_PartyCraft {
	
	public 	static 		PartyCraft 					plugin;
	
	private	static		Boolean[]					Booleans = new Boolean[3];
	
	{
		// 0 = CheckForUpdateOnStartUp
		// 1 = CheckForUpdateOnAdminJoin
		// 2 = AlertAdminsOfUpdate
	}
	
	public Config_PartyCraft(PartyCraft instance) {
		plugin = instance;
		
		LoadConfig();
		LoadValues();
		
	}
	
	private void LoadConfig() {
		
		File customConfigFile = null;
		
	    if (customConfigFile == null) {
	        customConfigFile = new File(plugin.getDataFolder(), "config.yml");
	    }
	    
	    if (!customConfigFile.exists()) {
	    	plugin.LogMessage(Level.INFO, "Created Config: config.yml");
	    	plugin.saveResource("config.yml", false);
	    }
		
	}
	
	public static void LoadValues() {
		
		Boolean checkupdateonstart = plugin.getConfig().getBoolean("CheckForUpdateOnStart");
		Booleans[0] = checkupdateonstart == null ? false : checkupdateonstart;
		
		Boolean checkupdateonadminjoin = plugin.getConfig().getBoolean("CheckForUpdateOnAdminJoin");
		Booleans[1] = checkupdateonadminjoin == null ? false : checkupdateonadminjoin;
		
		Boolean alertadmins = plugin.getConfig().getBoolean("AlertAdminsOfUpdate");
		Booleans[2] = alertadmins == null ? false : alertadmins;
		
	}
	
	public static Boolean getCheckForUpdateOnStart() {
		return Booleans[0];
	}
	
	public static Boolean getCheckForUpdateOnAdminJoin() {
		return Booleans[1];
	}
	
	public static Boolean getAlertAdminsOfUpdate() {
		return Booleans[2];
	}

}
