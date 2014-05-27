package me.Craftiii4.PartyCraft.Files;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import me.Craftiii4.PartyCraft.PartyCraft;
import me.Craftiii4.PartyCraft.DropParties.ConvertItem;

public class Config_DropParty {
	
	public 	static 		PartyCraft 					plugin;
	
	private static		Integer[]					Integers = new Integer[12];
	private	static		Boolean[]					Booleans = new Boolean[5];
	
	private static		List<String>				CommandResult = new ArrayList<String>();
	private static		List<String>				LaunchMessage = new ArrayList<String>();
	private static		List<String>				StartMessage = new ArrayList<String>();
	private static		List<String>				VoteReachMessage = new ArrayList<String>();
	private static		List<String>				VoteNotifyMessage = new ArrayList<String>();
	
	private static		List<String>				VoteNotifyAt = new ArrayList<String>();
	
	private static		List<ItemStack>				VoteStartingItems = new ArrayList<ItemStack>();
	
	private static 		HashMap<String, Integer> 	ItemTime = new HashMap<String, Integer>();
	private static 		List<String> 				BlockedItems = new ArrayList<String>();	
	
	{
		// 0 = Item Spread
		// 1 = Released Over
		// 2 = Waves
		// 3 = Wave Delay
		// 4 = Max Items
		// 5 = Height
		// 6 = Starting time
		// 7 = Max Time
		// 8 = VotesRequired
		// 9 = LaunchMessageRadius
		// 10 = StartMessageRadius
		// 11 = ItemAddMessageRadius
	}
	{
		// 0 = AllowUnsafeEnchants
		// 1 = Allow Vote
		// 2 = Must be online
		// 3 = Command override
		// 4 = Log drop party
	}

	public Config_DropParty(PartyCraft instance) {
		plugin = instance;
		
		LoadConfig();
		LoadValues();
		
	}
	
	private static FileConfiguration dropparty = null;
	private static File droppartyfile = null;
	
	@SuppressWarnings("deprecation")
	public static void reloadDropPartyFile() {
		
		if (droppartyfile == null) {
			droppartyfile = new File(plugin.getDataFolder(), "DropParty.yml");
		}
		
		dropparty = YamlConfiguration.loadConfiguration(droppartyfile);

		InputStream defConfigStream2 = plugin.getResource("DropParty.yml");
		if (defConfigStream2 != null) {
			YamlConfiguration defConfig2 = YamlConfiguration
					.loadConfiguration(defConfigStream2);
			dropparty.setDefaults(defConfig2);
		}
		
	}

	private static FileConfiguration getDropPartyFile() {
		if (dropparty == null) {
			reloadDropPartyFile();
		}
		return dropparty;
	}
	
	private void LoadConfig() {
		
		File customConfigFile = null;
		
	    if (customConfigFile == null) {
	        customConfigFile = new File(plugin.getDataFolder(), "DropParty.yml");
	    }
	    
	    if (!customConfigFile.exists()) {
	    	plugin.LogMessage(Level.INFO, "Created Config: DropParty.yml");
	    	plugin.saveResource("DropParty.yml", false);
	    }
		
	}
	
	public static void LoadValues() {
		
		Integer itemspread = getDropPartyFile().getInt("ItemSpread");
		Integers[0] = itemspread < 1 ? 3 : itemspread;
		Integer releasedover = getDropPartyFile().getInt("ReleasedOver");
		Integers[1] = releasedover < 1 ? 15 : releasedover;
		
		Integer waves = getDropPartyFile().getInt("Waves");
		Integers[2] = waves < 1 ? 4 : waves;
		Integer wavedelay = getDropPartyFile().getInt("WaveDelay");
		Integers[3] = wavedelay < 1 ? 15 : wavedelay;
		
		Integer maxitems = getDropPartyFile().getInt("MaxItems");
		Integers[4] = maxitems < 1 ? 1024 : maxitems;
		
		Integer height = getDropPartyFile().getInt("Height");
		Integers[5] = height < 1 ? 32 : height;
		
		Integer startingtime = getDropPartyFile().getInt("StartingTime");
		Integers[6] = startingtime < 1 ? 60 : startingtime;
		Integer maxtime = getDropPartyFile().getInt("MaxTime");
		Integers[7] = maxtime < 1 ? 300 : maxtime;
		
		Integer votesrequired = getDropPartyFile().getInt("VotesRequired");
		Integers[8] = votesrequired < 1 ? 100 : votesrequired;
		
		Integer radiuslaunch = getDropPartyFile().getInt("LaunchMessageRadius");
		Integers[9] = radiuslaunch < -2 ? -2 : radiuslaunch;
		Integer radiusstart = getDropPartyFile().getInt("StartMessageRadius");
		Integers[10] = radiusstart < -2 ? -2 : radiusstart;
		
		Integer addmessageradius = getDropPartyFile().getInt("ItemAddMessageRadius");
		Integers[11] = addmessageradius < -2 ? -2 : addmessageradius;
		
		Boolean unsafeenchant = getDropPartyFile().getBoolean("AllowUnsafeEnchants");
		Booleans[0] = unsafeenchant == null ? false : unsafeenchant;
		
		Boolean allowvote = getDropPartyFile().getBoolean("AllowVote");
		Booleans[1] = allowvote == null ? false : allowvote;
		
		Boolean online = getDropPartyFile().getBoolean("MustBeOnline");
		Booleans[2] = online == null ? false : online;
		
		Boolean commandoverride = getDropPartyFile().getBoolean("CommandOverRide");
		Booleans[3] = commandoverride == null ? false : commandoverride;
		
		Boolean logdropparties = getDropPartyFile().getBoolean("LogDropParty");
		Booleans[4] = logdropparties == null ? false : logdropparties;
		
		CommandResult = convertColourMessage(getDropPartyFile().getStringList("CommandResult"));
		LaunchMessage = convertColourMessage(getDropPartyFile().getStringList("LaunchMessage"));
		StartMessage = convertColourMessage(getDropPartyFile().getStringList("StartMessage"));
		VoteReachMessage = convertColourMessage(getDropPartyFile().getStringList("VoteReachMessage"));
		VoteNotifyMessage = convertColourMessage(getDropPartyFile().getStringList("VoteNotifyMessage"));
		VoteNotifyAt = getDropPartyFile().getStringList("NotifyAt");
		
		List<String> timeditems = getDropPartyFile().getStringList("Times");
		
		for (String timeditem : timeditems) {
			
			if (!timeditem.contains(" ")) {
		    	plugin.LogMessage(Level.WARNING, "Incorrect format for time: " + timeditem);
				continue;
			}
			
			String[] split = timeditem.split(" ");
			
			Integer itemid = -1;
			Short itemsubid = -1;
			
			if (split[0].contains(":")) {
				
				String[] split2 = split[0].split(":");

				try {
					itemid = Integer.parseInt(split2[0]);
					itemsubid = Short.parseShort(split2[1]);
				} catch (NumberFormatException e) {
			    	plugin.LogMessage(Level.WARNING, "Incorrect format for time: " + timeditem);
			    	continue;
				}
				
			} else {
				try {
					itemid = Integer.parseInt(split[0]);
				} catch (NumberFormatException e) {
			    	plugin.LogMessage(Level.WARNING, "Incorrect format for time: " + timeditem);
			    	continue;
				}
			}
			
			if (itemid == -1) {
		    	plugin.LogMessage(Level.WARNING, "Incorrect format for time: " + timeditem);
		    	continue;
			}
			
			if (!ConvertItem.checkItem(itemid, itemsubid.intValue() == -1 ? 0 : itemsubid.intValue(), false)) {
		    	plugin.LogMessage(Level.WARNING, "Incorrect item for time: " + timeditem);
		    	continue;
			}
			
			Integer time = 0;
			
			try {
				time = Integer.parseInt(split[1]);
			} catch (NumberFormatException e) {
		    	plugin.LogMessage(Level.WARNING, "Incorrect time for time: " + timeditem);
		    	continue;
			}
			
			if (itemsubid > -1) {
				ItemTime.put("" + itemid + ":" + itemsubid, time);
			} else {
				ItemTime.put("" + itemid, time);
			}
			
		}
		
		List<String> blockeditems = getDropPartyFile().getStringList("BannedItems");
		
		for (String itemstring : blockeditems) {
			
			Integer itemid = -1;
			Short itemsubid = -1;
			
			if (itemstring.contains(":")) {
				
				String[] split2 = itemstring.split(":");

				try {
					itemid = Integer.parseInt(split2[0]);
					itemsubid = Short.parseShort(split2[1]);
				} catch (NumberFormatException e) {
			    	plugin.LogMessage(Level.WARNING, "Incorrect format for banned item: " + itemstring);
			    	continue;
				}
				
			} else {
				
				try {
					itemid = Integer.parseInt(itemstring);
				} catch (NumberFormatException e) {
			    	plugin.LogMessage(Level.WARNING, "Incorrect format for banned item: " + itemstring);
			    	continue;
				}
				
			}
			
			if (itemid == -1) {
		    	plugin.LogMessage(Level.WARNING, "Incorrect format for banned item: " + itemstring);
		    	continue;
			}
			
			if (!ConvertItem.checkItem(itemid, itemsubid.intValue() == -1 ? 0 : itemsubid.intValue(), false)) {
		    	plugin.LogMessage(Level.WARNING, "Incorrect item for banned item: " + itemstring);
		    	continue;
			}
			
			if (itemsubid > -1) {
				BlockedItems.add("" + itemid + ":" + itemsubid);
			} else {
				BlockedItems.add("" + itemid);
			}
			
		}
				
    	plugin.LogMessage(Level.INFO, "Loaded DropParty Config");
		
	}
	
	public static Integer getItemSpread() {
		return Integers[0];
	}
	public static Integer getReleasedOver() {
		return Integers[1];
	}
	public static Integer getWaves() {
		return Integers[2];
	}
	public static Integer getWaveDelay() {
		return Integers[3];
	}
	public static Integer getMaxItems() {
		return Integers[4];
	}
	public static Integer getHeight() {
		return Integers[5];
	}
	public static Integer getStartingTime() {
		return Integers[6];
	}
	public static Integer getMaxTime() {
		return Integers[7];
	}
	public static Integer getVotesRequired() {
		return Integers[8];
	}
	public static Integer getLaunchMessageRadius() {
		return Integers[9];
	}
	public static Integer getStartMessageRadius() {
		return Integers[10];
	}
	public static Integer getItemAddMessageRadius() {
		return Integers[11];
	}
	
	public static Boolean getAllowUnsafeEnchants() {
		return Booleans[0];
	}
	public static Boolean getAllowVote() {
		return Booleans[1];
	}
	public static Boolean getMustBeOnline() {
		return Booleans[2];
	}
	public static Boolean getCommandOverRide() {
		return Booleans[3];
	}
	public static Boolean getLogDropParties() {
		return Booleans[4];
	}
	
	public static List<String> getCommandResult() {
		return CommandResult;
	}
	public static List<String> getLaunchMessage() {
		return LaunchMessage;
	}
	public static List<String> getStartMessage() {
		return StartMessage;
	}
	public static List<String> getVoteReachMessage() {
		return VoteReachMessage;
	}
	public static List<String> getVoteNotifyMessage() {
		return VoteNotifyMessage;
	}
	public static List<String> getVoteNotifyAt() {
		return VoteNotifyAt;
	}
	
	public static List<ItemStack> getVoteItems() {
		return VoteStartingItems;
	}
	
	public static void setDefaultVoteStartingItems() {
		
		List<String> listitems = getDropPartyFile().getStringList("StartingItems");
		
		List<ItemStack> resultitems = new ArrayList<ItemStack>();
		
		for (String itemstring : listitems) {
			
			ItemStack converted = ConvertItem.getItemFromString(plugin, itemstring);
			
			if (converted != null)
				resultitems.add(converted);
			
		}
		
		VoteStartingItems = resultitems;
		
	}
	
	public static List<ItemStack> getDefaultVoteStartingItems() {
		return VoteStartingItems;
	}
	
	@SuppressWarnings("deprecation")
	public static boolean hasTime(ItemStack item) {
		
		if (ItemTime.containsKey("" + item.getTypeId())) return true;
		if (ItemTime.containsKey("" + item.getTypeId() + ":" + item.getDurability())) return true;
		
		return false;
	}
	
	@SuppressWarnings("deprecation")
	public static Integer getTime(ItemStack item) {
		
		if (ItemTime.containsKey("" + item.getTypeId() + ":" + item.getDurability())) {
			return ItemTime.get("" + item.getTypeId() + ":" + item.getDurability());
		}
		
		return ItemTime.get("" + item.getTypeId());
	}
	
	public static Boolean isItemBanned(int id) {
		
		if (BlockedItems.contains("" + id)) {
			return true;
		}
		
		return false;
	}
	
	public static Boolean isItemBanned(int id, int subid) {
		
		if (BlockedItems.contains("" + id)) {
			return true;
		}
		if (BlockedItems.contains("" + id + ":" + subid)) {
			return true;
		}
		
		return false;
	}
	
	private static List<String> convertColourMessage(List<String> messages) {
		
		List<String> newmessages = new ArrayList<String>();
		
		for (String message : messages) {
			
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
			
			newmessages.add(message);
			
		}
		

		
		return newmessages;
		
	}
	
}
