package me.Craftiii4.PartyCraft.Files;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import me.Craftiii4.PartyCraft.PartyCraft;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Log_DropParty {
	
	public static String getNextLogFileName(PartyCraft plugin) {
		
		Date date = new Date();
		SimpleDateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy h-mm a");
		
		String filename = dateformat.format(date);
		
		filename = filename + " ";
		
		Boolean foundname = false;
		
		Integer numberon = 1;
		
		while (!foundname) {
			
			File LogFile = new File(plugin.getDataFolder(), "StoredData/DropPartyLogs/" + filename + ".txt");
			LogFile.getParentFile().mkdirs();
			
			if (LogFile.exists()) {
				
				filename = filename + "1";
				
				numberon++;
				
			} else {
				foundname = true;
			}
			
		}
		
		return filename;
		
	}
	
	public static void CreateNewFile(PartyCraft plugin, String startedby, String filename) {
		
		File logFileDirect = new File(plugin.getDataFolder(), "StoredData");
		
		if (!logFileDirect.exists()) {
			logFileDirect.mkdir();
		}
		
		File LogFile = new File(plugin.getDataFolder(), "StoredData/DropPartyLogs/" + filename + ".txt");
		
		LogFile.getParentFile().mkdirs();
		
		if (!LogFile.exists()) {
			
			try {
        	  
				LogFile.createNewFile();
        		FileConfiguration pConfig = YamlConfiguration.loadConfiguration(LogFile);
        	  
        		pConfig.set("StartedBy", startedby);
        		pConfig.set("Logged", new ArrayList<String>());
        		
        		pConfig.save(LogFile);
          	  
        	} catch (Exception e) {
        		        	
        	}  
			
		}
		
	}
	
	public static void SaveLogToFile(PartyCraft plugin, String filename, List<String> newlog) {
		
		File LogFile = new File(plugin.getDataFolder(), "StoredData/DropPartyLogs/" + filename + ".txt");
		
		FileConfiguration pConfig = YamlConfiguration.loadConfiguration(LogFile);
		
		List<String> CurrentList = pConfig.getStringList("Logged");
		
		if (CurrentList == null)
			CurrentList = new ArrayList<String>();
		
		for (String log : newlog)
			CurrentList.add(log);
		
		pConfig.set("Logged", CurrentList);
		
        try {
        	pConfig.save(LogFile);
        } catch (Exception e) {
        	
        }
		
	}

}
