package me.Craftiii4.PartyCraft.Listeners;

import java.util.ArrayList;
import java.util.List;

import me.Craftiii4.PartyCraft.PartyCraft;

import org.bukkit.Color;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class ItemPickUpListener implements Listener {
	
	public static PartyCraft plugin;

	public ItemPickUpListener(PartyCraft instance) {
		plugin = instance;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerPickupItem(PlayerPickupItemEvent event) {
		
		if (event.getItem().getItemStack().hasItemMeta()) {
			if (event.getItem().getItemStack().getItemMeta().hasLore()) {
				
				List<String> lore = event.getItem().getItemStack().getItemMeta().getLore();
				
				boolean hidden = false;
				
				if (lore.contains("PARTYCRAFT:HIDE")) {
					hidden = true;
				}
				
				ItemStack item = event.getItem().getItemStack();
								
				if (hidden) {
					
					Integer lorelineon = 0;
										
					List<String> lore1 = event.getItem().getItemStack().getItemMeta().getLore();
					
					while (lorelineon < lore1.size()) {
						
						if (lore1.get(lorelineon).contains("PARTYCRAFT:ITEM")) {
							String itemline = lore1.get(lorelineon).replace("PARTYCRAFT:ITEM:", "");
							String splititemline[] = itemline.split(":");
							int itemid = Integer.parseInt(splititemline[0]);
							int subitemid = Integer.parseInt(splititemline[1]);
							item.setTypeId(itemid);
							item.setDurability((short) subitemid);
							if (splititemline.length > 2) {
								ItemMeta meta = item.getItemMeta();
								meta.setDisplayName(splititemline[2]);
								item.setItemMeta(meta);
							}
						}
						lorelineon++;
					}
					
					lorelineon = 0;
					
					while (lorelineon < lore1.size()) {
						
						if (lore1.get(lorelineon).contains("PARTYCRAFT:ENCHANTS")) {
							String enchantline = lore1.get(lorelineon).replace("PARTYCRAFT:ENCHANTS:", "");
							String splitup2[] = enchantline.split("@");
							int enchanton = 0;
							while (splitup2.length > enchanton) {
								String enchantmentandlevel = splitup2[enchanton];
								String splitup3[] = enchantmentandlevel.split(":");
								int enchantid = Integer.parseInt(splitup3[0]);
								int enchantlevel = Integer.parseInt(splitup3[1]);
								Enchantment enchantment = Enchantment.getById(enchantid);
								item.addUnsafeEnchantment(enchantment, enchantlevel);
								enchanton++;
							}
						}
						
						if (lore1.get(lorelineon).contains("PARTYCRAFT:LEATHERCOLOUR")) {
							String leathercolour = lore1.get(lorelineon).replace("PARTYCRAFT:LEATHERCOLOUR:", "");
		        			LeatherArmorMeta lmeta = (LeatherArmorMeta) item.getItemMeta();
		    				if (lmeta != null) {
			        			lmeta.setColor(Color.fromRGB(Integer.parseInt(leathercolour)));
			        			item.setItemMeta(lmeta);
		    				}
						}
						
						if (lore1.get(lorelineon).contains("PARTYCRAFT:SKULLOWNER")) {
							String skullowner = lore1.get(lorelineon).replace("PARTYCRAFT:SKULLOWNER:", "");
		    				SkullMeta smeta = (SkullMeta) item.getItemMeta();
		    				if (smeta != null) {
			    				smeta.setOwner(skullowner);
			    				item.setItemMeta(smeta);
		    				}
							
						}
						
						if (lore1.get(lorelineon).contains("PARTYCRAFT:ENCHANTEDBOOK")) {
		        			EnchantmentStorageMeta ebmeta = (EnchantmentStorageMeta) item.getItemMeta();
		        			
		        			String enchants = lore1.get(lorelineon).replace("PARTYCRAFT:ENCHANTEDBOOK:", "");
		        			String splitenchants[] = enchants.split("@");
		        			
		        			for (String enchanton : splitenchants) {
		        				
		        				String split2[] = enchanton.split(":");
		        				int enchantid = Integer.parseInt(split2[0]);
		        				int enchantlevel = Integer.parseInt(split2[1]);
		        				
		        				ebmeta.addStoredEnchant(Enchantment.getById(enchantid), enchantlevel, true);
		        				
		        			}
		        			
		        			item.setItemMeta(ebmeta);
							
						}
						lorelineon++;
					}
									
				}
				
				List<String> newlore = new ArrayList<String>();
				
				for (String lorelineon : lore) {
					if (lorelineon.toUpperCase().contains("PARTYCRAFT:")) {
						// Don't Add!
					} else {
						newlore.add(lorelineon);
					}
				}
				
				if (newlore.size() == 0)
					newlore = null;
								
				ItemMeta meta = item.getItemMeta();
				meta.setLore(newlore);
				item.setItemMeta(meta);
				
				event.getItem().setItemStack(item);
				
			}
			
		}
		
	}

}
