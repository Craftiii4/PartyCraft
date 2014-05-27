package me.Craftiii4.PartyCraft.Listeners;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import me.Craftiii4.PartyCraft.PartyCraft;
import me.Craftiii4.PartyCraft.DropParties.ConvertItem;
import me.Craftiii4.PartyCraft.DropParties.CountDown;
import me.Craftiii4.PartyCraft.DropParties.DP;
import me.Craftiii4.PartyCraft.DropParties.DropParty;
import me.Craftiii4.PartyCraft.Files.Config_DropParty;
import me.Craftiii4.PartyCraft.Files.File_Voting;
import me.Craftiii4.PartyCraft.Files.Log_DropParty;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.inventory.ItemStack;

public class SignPlaceListener implements Listener {
	
	public static PartyCraft plugin;

	public SignPlaceListener(PartyCraft instance) {
		plugin = instance;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onBlockPhysics(BlockPhysicsEvent event) {
		
		Block block = event.getBlock();
		
		if (block.getState() instanceof Sign) {
			
			Sign sign = (Sign) block.getState();
			
			if (sign.getLine(0).equals("§a[Drop Party]")) {
				
				int id = -1;
				
				try {
					id = Integer.parseInt(sign.getLine(3));
				} catch (NumberFormatException e) {
					return;
				}
				
				if (DP.DropParties.get(id) != null) {
					event.setCancelled(true);
					sign.getLocation().add(0, -1, 0).getBlock().setType(Material.BEDROCK);
				}
				
			}
			
		}
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onSignChange(SignChangeEvent event) {
		
		String prefix = PartyCraft.getPrefix();
		
		String matchcheck = event.getLine(0).toLowerCase();
		
		boolean pattern = Pattern.matches("((§a)*\\[d( )*p\\]|(§a)*\\[drop( )*((party)*|p?|(pa)?|(par)?|(part)?)\\])", matchcheck);
		
		if (pattern) {
			
			Player player = event.getPlayer();
			Block block = event.getBlock();
			
			if (!player.hasPermission("partycraft.dropparty.create")) {
				event.setCancelled(true);
				block.setType(Material.AIR);
				block.getWorld().dropItem(block.getLocation(), new ItemStack(Material.SIGN));
				player.sendMessage(prefix + "§cYou lack permission to create that");
				return;
			}
			
			String ol = event.getLine(1).toUpperCase();
			String optionstoset = "§f";
			
			if (ol.contains("O") && (ol.contains("A") || ol.contains("V"))) {
				event.setCancelled(true);
				player.sendMessage(prefix + "§cOption §eO §ccannot be joined with §eA§c or §eV");
				block.setType(Material.AIR);
				block.getWorld().dropItem(block.getLocation(), new ItemStack(Material.SIGN));
				return;
			}
			
			if (ol.contains("A") && (ol.contains("V"))) {
				event.setCancelled(true);
				player.sendMessage(prefix + "§cOption §eA §ccannot be joined with §eO§c or §eV");
				block.setType(Material.AIR);
				block.getWorld().dropItem(block.getLocation(), new ItemStack(Material.SIGN));
				return;
			}
			
			if (ol.contains("I") && ol.contains("H")) {
				event.setCancelled(true);
				player.sendMessage(prefix + "§cOption §eI §ccannot be joined with §eH");
				block.setType(Material.AIR);
				block.getWorld().dropItem(block.getLocation(), new ItemStack(Material.SIGN));
				return;
			}
			
			Boolean containsfirst = false;
			if (ol.contains("O") || ol.contains("A") || ol.contains("V"))
				containsfirst = true;
			
			Boolean containssecond = false;
			if (ol.contains("I") || ol.contains("H"))
				containssecond = true;
			
			if (containsfirst == false || containssecond == false) {
				event.setCancelled(true);
				player.sendMessage(prefix + "§cSign must contain one of these options:");
				player.sendMessage("§31§7. §f\"§aO§f\" §6- §eDrop party will run once");
				player.sendMessage("§32§7. §f\"§aA§f\" §6- §eDrop party will repeat");
				player.sendMessage("§34§7. §f\"§aV§f\" §6- §eVote drop partys will start here");
				player.sendMessage(prefix + "§cFollowed by one of these options: ");
				player.sendMessage("§b1§7. §f\"§aI§f\" §6- §eItems appear as they are");
				player.sendMessage("§b2§7. §f\"§aH§f\" §6- §eItems appear as another item");
				block.setType(Material.AIR);
				block.getWorld().dropItem(block.getLocation(), new ItemStack(Material.SIGN));
				return;
			}
			
			boolean hidden = false;
			ItemStack hiddenitem = null;
			
			DropType dptype = null;
			
			if (ol.contains("A")) { optionstoset = optionstoset + "[A]"; dptype = DropType.AUTO; }
			if (ol.contains("O")) { optionstoset = optionstoset + "[O]"; dptype = DropType.ONCE; }
			if (ol.contains("V")) { optionstoset = optionstoset + "[V]"; dptype = DropType.VOTE; }
			
			if (dptype == null) {
				event.setCancelled(true);
				block.setType(Material.AIR);
				block.getWorld().dropItem(block.getLocation(), new ItemStack(Material.SIGN));
				player.sendMessage(prefix + "§cAn error occured, Please try again");
				return;
			}
			
			if (ol.contains("H")) hidden = true;
			
			if (hidden) {
				optionstoset = optionstoset + "[H]";
				
				String thirdline = event.getLine(2);
				
				if (thirdline == null || thirdline.equals("")) {
					
					event.setCancelled(true);
					block.setType(Material.AIR);
					block.getWorld().dropItem(block.getLocation(), new ItemStack(Material.SIGN));
					player.sendMessage(prefix + "§cThird line should contain the item to disguise as");
					player.sendMessage(prefix + "§cFor example§7: §f\"§e354§f\" §cor §f\"§e35:2§f\"");
					return;
					
				}
				
				int itemid = 0;
				Short subitemid = 0;
				
				if (thirdline.contains(":")) {
					
					String split2[] = thirdline.split(":");
										
					try {
						
						itemid = Integer.parseInt(split2[0]);
						subitemid = Short.parseShort(split2[1]);
						
					} catch (NumberFormatException e) {
						event.setCancelled(true);
						block.setType(Material.AIR);
						block.getWorld().dropItem(block.getLocation(), new ItemStack(Material.SIGN));
						player.sendMessage(prefix + "§cThird line should contain the item to disguise as");
						player.sendMessage(prefix + "§cFor example§7: §f\"§e354§f\" §cor §f\"§e35:2§f\"");
						return;
					}
					
				} else {
					
					try {
						
						itemid = Integer.parseInt(thirdline);
						
					} catch (NumberFormatException e) {
						event.setCancelled(true);
						block.setType(Material.AIR);
						block.getWorld().dropItem(block.getLocation(), new ItemStack(Material.SIGN));
						player.sendMessage(prefix + "§cThird line should contain the item to disguise as");
						player.sendMessage(prefix + "§cFor example§7: §f\"§e354§f\" §cor §f\"§e35:2§f\"");
						return;
					}
					
				}
				
				if (!ConvertItem.checkItem(itemid, subitemid.intValue() == -1 ? 0 : subitemid.intValue(), true)) {
					event.setCancelled(true);
					block.setType(Material.AIR);
					block.getWorld().dropItem(block.getLocation(), new ItemStack(Material.SIGN));
					player.sendMessage(prefix + "§cItem to disguise as was not a valid item");
					player.sendMessage(prefix + "§eSpecial Items:");
					player.sendMessage("§a-1:0 §f= §eRandom Wool");
					player.sendMessage("§a-1:1 §f= §eRandom Glass");
					player.sendMessage("§a-1:2 §f= §eRandom Dye");
					player.sendMessage("§a-1:3 §f= §eRandom Fish");
					player.sendMessage("§a-1:4 §f= §eRandom Flower");
					player.sendMessage("§a-1:5 §f= §eRandom Disk");
					player.sendMessage("§a-1:6 §f= §eRandom Bucket");
					return;
				}
				
				if (itemid == -1) {
					hiddenitem = new ItemStack(331);
					hiddenitem.setDurability( (short) (100 + subitemid) );
				} else {
					hiddenitem = new ItemStack(itemid);
					hiddenitem.setDurability(subitemid);
				}

				
				if (dptype == DropType.VOTE) {
				
					File_Voting.getDropPartyConfig().set("Location.World", block.getWorld().getName());
					File_Voting.getDropPartyConfig().set("Location.X", block.getLocation().getBlockX());
					File_Voting.getDropPartyConfig().set("Location.Y", block.getLocation().getBlockY());
					File_Voting.getDropPartyConfig().set("Location.Z", block.getLocation().getBlockZ());
					
					File_Voting.getDropPartyConfig().set("HiddenID", itemid);
					File_Voting.getDropPartyConfig().set("HiddenSUBID", subitemid);
					
					File_Voting.setVoteLocation(block.getLocation());
					File_Voting.setHiddenItem(itemid + ":" + subitemid);
					
					File_Voting.saveDropPartyConfig();
					
					player.sendMessage(prefix + "§bLocation set for vote drop party");
					block.setType(Material.AIR);
					
					return;
				
				}
				
			} else {
				optionstoset = optionstoset + "[I]";
			}
			
			if (dptype == DropType.VOTE) {
				
				File_Voting.getDropPartyConfig().set("Location.World", block.getWorld().getName());
				File_Voting.getDropPartyConfig().set("Location.X", block.getLocation().getBlockX());
				File_Voting.getDropPartyConfig().set("Location.Y", block.getLocation().getBlockY());
				File_Voting.getDropPartyConfig().set("Location.Z", block.getLocation().getBlockZ());
				
				File_Voting.getDropPartyConfig().set("HiddenID", 0);
				File_Voting.getDropPartyConfig().set("HiddenSUBID", 0);
				
				File_Voting.setVoteLocation(block.getLocation());
				File_Voting.setHiddenItem("0:0");
				
				File_Voting.saveDropPartyConfig();
				
				player.sendMessage(prefix + "§bLocation set for vote drop party");
				block.setType(Material.AIR);
				
				return;
			
			}
			
			int id = DP.getFutureID();
						
			Block under = block.getLocation().add(0, -1, 0).getBlock();
						
			event.setLine(0, "§a[Drop Party]");
			event.setLine(1, optionstoset);
			event.setLine(2, "");
			event.setLine(3, "" + id);
			
			int maxitems = Config_DropParty.getMaxItems();
			
			String filename = Log_DropParty.getNextLogFileName(plugin);
			Log_DropParty.CreateNewFile(plugin, player.getName(), filename);
			
			DropParty dparty = new DropParty(plugin, id, block.getLocation(), under.getTypeId(), under.getData(), maxitems, dptype, hidden, hiddenitem, filename);
			
			under.setType(Material.BEDROCK);
									
			DP.DropParties.put(id, dparty);
			CountDown.DropCountDown(plugin, id);
			
			Integer radius = Config_DropParty.getStartMessageRadius();
			
			List<String> messages = Config_DropParty.getStartMessage();
			List<String> newmessages = new ArrayList<String>();;
			
			if (messages == null || messages.size() == 0)
				return;
			
			for (String message : messages) {
				message = message.replace("%playername%", player.getName());
				newmessages.add(message);
			}
			
			if (radius <= -2) {
				DP.BroadCastMessage(newmessages);
			}
			if (radius == -1) {
				DP.BroadCastMessage(newmessages, block.getLocation().getWorld());
			}
			if (radius >= 0) {
				DP.BroadCastMessage(newmessages, block.getLocation(), radius);
			}
			
		}
		
	}
	
	@SuppressWarnings("deprecation")
	public static void StartNewDropParty(Sign sign, Player player, Boolean vote) {
		
		String ol = sign.getLine(1).toUpperCase();
		
		ItemStack hiddenitem = null;
		Boolean hidden = false;
		
		String optionstoset = "";
		
		if (vote) {
			optionstoset = "§f[V]";
		} else {
			optionstoset = "§f[A]";
		}
		
		if (ol.contains("H")) {
			
			optionstoset = optionstoset + "[H]";
			hidden = true;
			
			String thirdline = sign.getLine(2);
			
			if (thirdline == null || thirdline.equals("")) {
				return;
				
			}
			
			int itemid = 0;
			Short subitemid = 0;
			
			if (thirdline.contains(":")) {
				
				String split2[] = thirdline.split(":");
									
				try {
					
					itemid = Integer.parseInt(split2[0]);
					subitemid = Short.parseShort(split2[1]);
					
				} catch (NumberFormatException e) {
					return;
				}
				
			} else {
				
				try {
					
					itemid = Integer.parseInt(thirdline);
					
				} catch (NumberFormatException e) {
					return;
				}
				
			}
			
			if (itemid == -1) {
				hiddenitem = new ItemStack(331);
				hiddenitem.setDurability( (short) (100 + subitemid) );
			} else {
				hiddenitem = new ItemStack(itemid);
				hiddenitem.setDurability(subitemid);
			}
			
		} else {
			optionstoset = optionstoset + "[I]";
		}
		
		int id = DP.getFutureID();
		
		Block under = sign.getLocation().add(0, -1, 0).getBlock();
		
		sign.setLine(0, "§a[Drop Party]");
		sign.setLine(1, optionstoset);
		sign.setLine(2, "");
		sign.setLine(3, "" + id);
		
		int maxitems = Config_DropParty.getMaxItems();
		
		String filename = Log_DropParty.getNextLogFileName(plugin);
		Log_DropParty.CreateNewFile(plugin, player == null ? "Voted Party" : player.getName(), filename);
		
		DropParty dparty = new DropParty(plugin, id, sign.getLocation(), under.getTypeId(), under.getData(), maxitems, DropType.AUTO, hidden, hiddenitem, filename);
		
		under.setType(Material.BEDROCK);
						
		DP.DropParties.put(id, dparty);
		CountDown.DropCountDown(plugin, id);
				
		if (!vote) {
			Integer radius = Config_DropParty.getStartMessageRadius();
			
			List<String> messages = Config_DropParty.getStartMessage();
			List<String> newmessages = new ArrayList<String>();;
			
			if (messages == null || messages.size() == 0)
				return;
			
			for (String message : messages) {
				message = message.replace("%playername%", player.getName());
				newmessages.add(message);
			}
			
			if (radius <= -2) {
				DP.BroadCastMessage(newmessages);
			}
			if (radius == -1) {
				DP.BroadCastMessage(newmessages, sign.getLocation().getWorld());
			}
			if (radius >= 0) {
				DP.BroadCastMessage(newmessages, sign.getLocation(), radius);
			}
		} else {
			List<ItemStack> items = Config_DropParty.getDefaultVoteStartingItems();
			DropParty dp = DP.getDropParty(id);
			if (dp != null)
				for (ItemStack item : items) {
					dp.addItem(item, null);
					dp.sendAddMessage(item, null);
				}
			
		}
		
	}
	
	public enum DropType {
		
		AUTO,
		ONCE,
		VOTE;
		
	}
	
}
