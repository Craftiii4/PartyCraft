package me.Craftiii4.PartyCraft.Listeners;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.Craftiii4.PartyCraft.PartyCraft;
import me.Craftiii4.PartyCraft.DropParties.DP;
import me.Craftiii4.PartyCraft.DropParties.DropParty;
import me.Craftiii4.PartyCraft.Files.Config_DropParty;

public class ItemAddListener implements Listener {
	
	public static PartyCraft plugin;

	public ItemAddListener(PartyCraft instance) {
		plugin = instance;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		
		String prefix = PartyCraft.getPrefix();
				
		if (event.getAction() == Action.LEFT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			
			Block block = event.getClickedBlock();
			
			if (block.getState() instanceof Sign) {
				Sign sign = (Sign) block.getState();
				
				if (sign.getLine(0).equals("§a[Drop Party]")) {
					
					event.setCancelled(true);
					
					Player player = event.getPlayer();
					
					int id = -1;
					
					try {
						id = Integer.parseInt(sign.getLine(3));
					} catch (NumberFormatException e) {
						
						if (sign.getLine(1).contains("A")) {
							SignPlaceListener.StartNewDropParty(sign, player, false);
							return;
						}
						
						player.sendMessage(prefix + "§cCould not validate drop party ID");
						block.setType(Material.AIR);
						return;
					}
					
					if (DP.DropParties.get(id) == null) {
						
						if (sign.getLine(1).contains("A")) {
							SignPlaceListener.StartNewDropParty(sign, player, false);
							return;
						}
						
						player.sendMessage(prefix + "§cID not active");
						block.setType(Material.AIR);
						return;
					}
					
					DropParty dropparty = DP.DropParties.get(id);
					
					if (!dropparty.getCanPutInItems()) {
						player.sendMessage(prefix + "§cItems can not currently be put in");
						return;
					}
										
					if (player.getItemInHand() == null || player.getItemInHand().getType() == Material.AIR) {
						player.sendMessage(prefix + "§aHold an item in your hand to place into the drop");
						player.sendMessage(prefix + "§eLeft Click §ato put in §d1§a, §eRight click §ato put in §d8");
						return;
					}
					
					if (!player.hasPermission("partycraft.dropparty.add")) {
						player.sendMessage(prefix + "§cYou lack permission to place in items");
						return;
					}
										
					if (Config_DropParty.isItemBanned(player.getItemInHand().getTypeId(), player.getItemInHand().getDurability())) {
						player.sendMessage(prefix + "§cThat item is blocked from the drop party");
						return;
					}
					
					int amountadding = 1;
					
					if (event.getAction() == Action.RIGHT_CLICK_BLOCK) amountadding = 8;
					
					if (amountadding > player.getItemInHand().getAmount()) {
						player.sendMessage("§cYou are not holding 8 or more in your hand!");
						return;
					}
										
					int sizewiththis = dropparty.getCurrentAmount() + amountadding;
					
					if (sizewiththis > dropparty.getMaxItems()) {
						player.sendMessage(prefix + "§cThat would go over the max allowed items");
						return;
					}
					
					ItemStack iteminhand = new ItemStack(player.getItemInHand());
					iteminhand.setAmount(amountadding);
					
					if (player.getItemInHand().getAmount() == amountadding) {
						ItemMeta meta = player.getItemInHand().getItemMeta();
						List<String> lore = new ArrayList<String>();
						lore.add("PartyCraft bla bla boom");
						meta.setLore(lore);
						player.getItemInHand().setItemMeta(meta);
						player.getInventory().remove(player.getItemInHand());
					} else {
						int amounttoset = player.getItemInHand().getAmount() - amountadding;
						player.getItemInHand().setAmount(amounttoset);
					}
					
					dropparty.addItem(iteminhand, player);
					dropparty.sendAddMessage(iteminhand, player);
					
				}
				
			}
			
		}
		
	}

}
