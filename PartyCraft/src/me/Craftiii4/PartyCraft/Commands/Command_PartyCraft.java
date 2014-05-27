package me.Craftiii4.PartyCraft.Commands;

import java.util.ArrayList;
import java.util.List;

import me.Craftiii4.PartyCraft.PartyCraft;
import me.Craftiii4.PartyCraft.Files.Config_DropParty;
import me.Craftiii4.PartyCraft.Files.Config_PartyCraft;
import me.Craftiii4.PartyCraft.Listeners.DropVoteListener;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.Plugin;

import com.vexsoftware.votifier.Votifier;

public class Command_PartyCraft implements CommandExecutor {
	
	public static PartyCraft plugin;

	public Command_PartyCraft(PartyCraft instance) {
		plugin = instance;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String args[]) {
		
		Integer length = args.length;
		String prefix = PartyCraft.getPrefix();
		
		if (length == 0) {
			sender.sendMessage(prefix + "§eCreated by §bCraftiii4");
			sender.sendMessage(prefix + "§eView help with /partycraft help");
			return true;
		}
		
		if (length > 0) {
			
			String first = args[0].toLowerCase();
			
			switch (first) {
			
			case "help" : 
				
				List<String> Permissions = new ArrayList<String>();
				
				if (sender.hasPermission("partycraft.reload"))
					Permissions.add("§eReload Config File§f: §7/partycraft reload");
				
				Permissions.add("§eView drop parties help§f: §7/dropparty help");
								
				if (Permissions.size() > 0) {
					
					sender.sendMessage("");
					sender.sendMessage(prefix + "§ePartyCraft Party Help");
					sender.sendMessage("");
					
					for (String s : Permissions)
						sender.sendMessage(s);
					
					sender.sendMessage("");
					
				} else {
					sender.sendMessage(prefix + "§cYou lack access to any features help offers");
				}
				
				return true;
				
			case "reload" :
				
				if (!sender.hasPermission("partycraft.reload")) {
					sender.sendMessage(prefix + "§cYou lack permission to do that");
					return true;
				}
				
				sender.sendMessage(prefix + "§eReloading...");
				
				plugin.reloadConfig();
				Config_PartyCraft.LoadValues();
				
				Config_DropParty.reloadDropPartyFile();
				Config_DropParty.LoadValues();
				
				if (Config_DropParty.getAllowVote()) {
					
					if (DropVoteListener.plugin == null) {
						
						Plugin vote = Bukkit.getServer().getPluginManager().getPlugin("Votifier");
						
						if (vote != null & vote instanceof Votifier) {
							Config_DropParty.setDefaultVoteStartingItems();
							new DropVoteListener(plugin);
							sender.sendMessage(prefix + "§eEnabled Vote Listener");
						} else {
							sender.sendMessage(prefix + "§cVote Listener not enabled, Votifier not found");
						}

					}
					
				} else {
					if (DropVoteListener.plugin != null) {
						
						Plugin vote = Bukkit.getServer().getPluginManager().getPlugin("Votifier");
						
						if (vote != null & vote instanceof Votifier) {
							HandlerList.unregisterAll(new DropVoteListener(DropVoteListener.plugin));
							DropVoteListener.plugin = null;
							sender.sendMessage(prefix + "§eDisabled Vote Listener");
						} else {
							sender.sendMessage(prefix + "§cVote Listener not disabled, Votifier not found");
						}

					}
				}
				
				sender.sendMessage(prefix + "§e...Reloaded!");
				
				return true;
				
			default :
				sender.sendMessage(prefix + "§cUnknown command");
				return true;
				
			}
			
		}
		
		return false;
	}

}
