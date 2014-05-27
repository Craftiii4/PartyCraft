package me.Craftiii4.PartyCraft.Commands;

import java.util.ArrayList;
import java.util.List;

import me.Craftiii4.PartyCraft.PartyCraft;
import me.Craftiii4.PartyCraft.DropParties.DP;
import me.Craftiii4.PartyCraft.DropParties.DropParty;
import me.Craftiii4.PartyCraft.Files.Config_DropParty;
import me.Craftiii4.PartyCraft.Files.File_Voting;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Command_DropParty implements CommandExecutor {
	

	@Override
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String args[]) {
		
		Integer length = args.length;
		String prefix = PartyCraft.getPrefix();
		
		if (length == 0) {
			sender.sendMessage(prefix + "§eCreated by §bCraftiii4");
			sender.sendMessage(prefix + "§eView help with /dropparty help");
			return true;
		}
		
		if (length == 1 || length > 3) {
			
			String first = args[0].toLowerCase();
			
			switch (first) {
			
			case "help" : 
				
				List<String> Permissions = new ArrayList<String>();
				
				if (sender.hasPermission("partycraft.dropparty.settime"))
					Permissions.add("§eChange time of a drop party§f: §7/dropparty settime §c<§7id§c> §c<§7time§c>");
				
				if (sender.hasPermission("partycraft.dropparty.stop"))
					Permissions.add("§eStop a drop party§f: §7/dropparty stop §c<§7id§c>");
				
				if (sender.hasPermission("partycraft.dropparty.setvotes"))
					Permissions.add("§eSet current vote amount§f: §7/dropparty setvotes §c<§7amount§c>");
				
				if (Permissions.size() > 0) {
					
					sender.sendMessage("");
					sender.sendMessage(prefix + "§eDrop Party Help");
					sender.sendMessage("");
					
					for (String s : Permissions)
						sender.sendMessage(s);
					
					sender.sendMessage("");
					
				} else {
					sender.sendMessage(prefix + "§cYou lack access to any features help offers");
				}
				
				return true;
				
			case "settime" : 
				if (sender.hasPermission("partycraft.dropparty.settime")) {
					sender.sendMessage("§cCorrect Usage§f: §7/dropparty settime §c<§7id§c> §c<§7time§c>");
				} else {
					sender.sendMessage(prefix + "§cYou lack permission to do that");
				}
				return true;
				
			case "setvotes" : 
				if (sender.hasPermission("partycraft.dropparty.setvotes")) {
					sender.sendMessage("§cCorrect Usage§f: §7/dropparty setvotes §c<§7amount§c>");
				} else {
					sender.sendMessage(prefix + "§cYou lack permission to do that");
				}
				return true;
				
			case "stop" : 
				if (sender.hasPermission("partycraft.dropparty.stop")) {
					sender.sendMessage("§cCorrect Usage§f: §7/dropparty stop §c<§7id§c>");
				} else {
					sender.sendMessage(prefix + "§cYou lack permission to do that");
				}
				return true;
				
			default :
				sender.sendMessage(prefix + "§cUnknown command");
				return true;
			
			}
			
			
		}
		
		if (length == 2) {
			
			String first = args[0].toLowerCase();
			
			switch (first) {
			
			case "help" : 
				
				List<String> Permissions = new ArrayList<String>();
				
				if (sender.hasPermission("partycraft.dropparty.settime"))
					Permissions.add("§eChange time of a drop party§f: §7/dropparty settime §c<§7id§c> §c<§7time§c>");
				
				if (sender.hasPermission("partycraft.dropparty.stop"))
					Permissions.add("§eStop a drop party§f: §7/dropparty stop §c<§7id§c>");
				
				if (sender.hasPermission("partycraft.dropparty.setvotes"))
					Permissions.add("§eSet current vote amount§f: §7/dropparty setvotes §c<§7amount§c>");
				
				if (Permissions.size() > 0) {
					
					sender.sendMessage("");
					sender.sendMessage(prefix + "§eDrop Party Help");
					sender.sendMessage("");
					
					for (String s : Permissions)
						sender.sendMessage(s);
					
					sender.sendMessage("");
					
				} else {
					sender.sendMessage(prefix + "§cYou lack access to any features help offers");
				}
				
				return true;
				
			case "settime" : 
				if (sender.hasPermission("partycraft.dropparty.settime")) {
					sender.sendMessage(prefix + "§cCorrect Usage§f: §7/dropparty settime §c<§7id§c> §c<§7time§c>");
				} else {
					sender.sendMessage(prefix + "§cYou lack permission to do that");
				}
				return true;
				
			case "setvotes" : 
				
				if (!sender.hasPermission("partycraft.dropparty.setvotes")) {
					sender.sendMessage(prefix + "§cYou lack permission to do that");
					return true;
				}
				
				if (!Config_DropParty.getAllowVote()) {
					sender.sendMessage(prefix + "§cVote drop party is not enabled");
					return true;
				}
				
				Integer amount = -1;
				
				try {
					amount = Integer.parseInt(args[1]);
				} catch (NumberFormatException e) {
					sender.sendMessage(prefix + "§cAmount must be a number");
					return true;
				}
				
				if (amount > Config_DropParty.getVotesRequired())
					amount = Config_DropParty.getVotesRequired();
				
				File_Voting.setCurrentVotes(amount);
				sender.sendMessage(prefix + "§eCurrent vote amount set to §c" + amount);
								
				return true;
				
			case "stop" : 
				
				if (!sender.hasPermission("partycraft.dropparty.stop")) {
					sender.sendMessage(prefix + "§cYou lack permission to do that");
					return true;
				}
				
				Integer id = -1;
				
				try {
					id = Integer.parseInt(args[1]);
				} catch (NumberFormatException e) {
					sender.sendMessage(prefix + "§cID must be a number");
					return true;
				}
				
				DropParty dropparty = DP.getDropParty(id);
				
				if (dropparty == null) {
					sender.sendMessage(prefix + "§cNo drop party with that id currently active");
					return true;
				}
				
				sender.sendMessage(prefix + "§cDrop party stopped");
				
				dropparty.sendNearMessage(prefix + "§cDrop Party stopped by §e" + sender.getName());
				
				if (Config_DropParty.getLogDropParties())
					dropparty.addtoLog("Drop Party stopped by " + sender.getName());
				
				dropparty.Stop();
				
				return true;
				
			default :
				sender.sendMessage(prefix + "§cUnknown command");
				return true;
			
			}
			
		}
		
		if (length == 3) {
			
			String first = args[0].toLowerCase();
			
			switch (first) {
			
			case "help" : 
				
				List<String> Permissions = new ArrayList<String>();
				
				if (sender.hasPermission("partycraft.dropparty.settime"))
					Permissions.add("§eChange time of a drop party§f: §7/dropparty settime §c<§7id§c> §c<§7time§c>");
				
				if (sender.hasPermission("partycraft.dropparty.stop"))
					Permissions.add("§eStop a drop party§f: §7/dropparty stop §c<§7id§c>");
				
				if (sender.hasPermission("partycraft.dropparty.setvotes"))
					Permissions.add("§eSet current vote amount§f: §7/dropparty setvotes §c<§7amount§c>");
				
				if (Permissions.size() > 0) {
					
					sender.sendMessage("");
					sender.sendMessage(prefix + "§eDrop Party Help");
					sender.sendMessage("");
					
					for (String s : Permissions)
						sender.sendMessage(s);
					
					sender.sendMessage("");
					
				} else {
					sender.sendMessage(prefix + "§cYou lack access to any features help offers");
				}
				
				return true;
				
			case "settime" : 
				
				if (!sender.hasPermission("partycraft.dropparty.settime")) {
					sender.sendMessage(prefix + "§cYou lack permission to do that");
					return true;
				}
				
				Integer id = -1;
				
				try {
					id = Integer.parseInt(args[1]);
				} catch (NumberFormatException e) {
					sender.sendMessage(prefix + "§cID must be a number");
					return true;
				}
				
				Integer newtime = -1;
				
				try {
					newtime = Integer.parseInt(args[2]);
				} catch (NumberFormatException e) {
					sender.sendMessage(prefix + "§cTime must be a number");
					return true;
				}
				
				if (newtime <= 0) {
					sender.sendMessage(prefix + "§cTime must be greater than 0");
				}
				
				DropParty dropparty = DP.getDropParty(id);
				
				if (dropparty == null) {
					sender.sendMessage(prefix + "§cNo drop party with that id currently active");
					return true;
				}
				
				if (newtime > Config_DropParty.getMaxTime())
					newtime = Config_DropParty.getMaxTime();
				
				sender.sendMessage(prefix + "§eTime set to §c" + newtime + " seconds");
				
				dropparty.sendNearMessage(prefix + "§bTime left set to §d" + newtime + " §bseconds by §e" + sender.getName());
				
				if (Config_DropParty.getLogDropParties())
					dropparty.addtoLog("Drop Party time set to " + newtime + " seconds by " + sender.getName());
				
				dropparty.setTime(newtime);
				
				return true;
				
			case "setvotes" : 
				if (sender.hasPermission("partycraft.dropparty.setvotes")) {
					sender.sendMessage("§cCorrect Usage§f: §7/dropparty setvotes §c<§7amount§c>");
				} else {
					sender.sendMessage(prefix + "§cYou lack permission to do that");
				}
				return true;
				
			case "stop" : 
				if (sender.hasPermission("partycraft.dropparty.stop")) {
					sender.sendMessage("§cCorrect Usage§f: §7/dropparty stop §c<§7id§c>");
				} else {
					sender.sendMessage(prefix + "§cYou lack permission to do that");
				}
				return true;
				
			default :
				sender.sendMessage(prefix + "§cUnknown command");
				return true;
				
			}
			
		}
		
		return false;
	}

}
