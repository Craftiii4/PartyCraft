package me.Craftiii4.PartyCraft.DropParties;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.regex.Pattern;

import me.Craftiii4.PartyCraft.PartyCraft;
import me.Craftiii4.PartyCraft.Files.Config_DropParty;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class ConvertItem {
	
	@SuppressWarnings("deprecation")
	public static ItemStack getItemFromString(PartyCraft plugin, String itemstring) {
		
		// ItemID:SUBID <amount> <enchantmentname-level> <name=Name_Here> <head=player> <lore=Line1,Line_2,Line_3>
		
		String[] split = itemstring.split(" ");
		
		Integer itemid = -1;
		Short itemsubid = -1;
		
		Integer amount = -1;
		
		if (split[0].contains(":")) {
			String[] split2 = split[0].split(":");
			try {
				itemid = Integer.parseInt(split2[0]);
				itemsubid = Short.parseShort(split2[1]);
			} catch (NumberFormatException e) {
				plugin.LogMessage(Level.WARNING, "Could not get an item and sub id from " + split[0]);
				return null;
			}
		} else {
			try {
				itemid = Integer.parseInt(split[0]);
			} catch (NumberFormatException e) {
				plugin.LogMessage(Level.WARNING, "Could not get an item id from " + split[0]);
				return null;
			}
		}
		
		if (!checkItem(itemid, itemsubid.intValue() == -1 ? 0 : itemsubid.intValue(), false)) {
			plugin.LogMessage(Level.WARNING, "Could not find item for ID " + split[0]);
			return null;
		}
		
		try {
			amount = Integer.parseInt(split[1]);
		} catch (NumberFormatException e) {
			plugin.LogMessage(Level.WARNING, "Could not get amount from " + split[1]);
			return null;
		}
		
		if (amount <= 0) {
			plugin.LogMessage(Level.WARNING, "Amount must be greater than 0");
			return null;
		}
		
		ItemStack item = new ItemStack(itemid);
		
		if (itemsubid > 0)
			item.setDurability(itemsubid);
		
		item.setAmount(amount);
		
		int ignore = 0;
		
		for (String spliton : split) {
			
			ignore++;
			
			if (ignore <= 2) // Ignore the first two, which should be itemid and amount
				continue;
			
			String lowercase = new String(spliton).toLowerCase();
			
			if (lowercase.startsWith("name=")) {
				
				String displayname = new String(spliton).substring(5).replace("_", " ");
				displayname = PartyCraft.convertColourMessage(displayname);
				
				ItemMeta meta = item.getItemMeta();
				meta.setDisplayName(displayname);
				item.setItemMeta(meta);
				
				continue;
				
			}
			
			if (lowercase.startsWith("lore=")) {
				
				String lore = new String(spliton).substring(5).replace("_", " ");
				lore = PartyCraft.convertColourMessage(lore);
				
				String lines[] = lore.split(",");
				
				List<String> lorelist = new ArrayList<String>();
				
				for (String line : lines)
					lorelist.add(PartyCraft.convertColourMessage(line));
				
				ItemMeta meta = item.getItemMeta();
				meta.setLore(lorelist);
				item.setItemMeta(meta);
				
				continue;
				
			}
			
			if (lowercase.startsWith("head=")) {
				
				String headname = new String(spliton).substring(5).replace("_", " ");
				
				SkullMeta meta = (SkullMeta) item.getItemMeta();
				meta.setOwner(headname);
				
				item.setItemMeta(meta);
				
			}
			
			if (lowercase.contains("-")) {

				String enchantsplit[] = lowercase.split("-");
				
				int level = -1;
				
				try {
					level = Integer.parseInt(enchantsplit[1]);
				} catch (NumberFormatException e) {
			    	plugin.LogMessage(Level.WARNING, "Unknown level for enchant on starting item");
			    	continue;
				}
				
				if (level < 1) {
			    	plugin.LogMessage(Level.WARNING, "Enchantment level can not be below 1");
			    	continue;
				}
				
				String enchant = enchantsplit[0].toUpperCase();
				
				boolean arrowdamagepattern = Pattern.matches("(ARROW(_| )*DAMAGE|POWER)", enchant);
				boolean arrowfirepattern = Pattern.matches("(ARROW(_| )*FIRE|FLAME)", enchant);
				boolean arrowinfinitepattern = Pattern.matches("(ARROW(_| )*INFINIT(E|Y)|INFINIT(E|Y))", enchant);			
				boolean arrowkbpattern = Pattern.matches("(ARROW(_| )*KNOCKBACK|PUNCH)", enchant);
				boolean damageallpattern = Pattern.matches("(DAMAGE(_| )*ALL|SHARP(NESS)?)", enchant);
				boolean damagearthpattern = Pattern.matches("(DAMAGE(_| )*ARTHROPODS|(BANE(_| )*OF(_| )*)?ARTHROPODS)", enchant);
				boolean damageundeadpattern = Pattern.matches("(DAMAGE(_| )*UNDEAD|SMITE)", enchant);
				boolean digspeedpattern = Pattern.matches("(DIG(_| )*SPEED|EFFIC(IENCY)?)", enchant);
				boolean durapattern = Pattern.matches("(DURABILITY|UNBREAK(ING)?)", enchant);
				boolean firepattern = Pattern.matches("FIRE((_| )*ASPECT)?", enchant);			
				boolean kbpattern = Pattern.matches("KNOCKBACK|KB", enchant);
				boolean lootblockspattern = Pattern.matches("(LOOT(_| )*BONUS(_| )*BLOCKS|FORTUNE)", enchant);
				boolean lootmobspattern = Pattern.matches("(LOOT(_| )*BONUS(_| )*MOBS|LOOT(ING)?)", enchant);
				boolean luckpattern = Pattern.matches("(LUCK|LUCK(_| )*OF(_| )*THE(_| )*SEA)", enchant);
				boolean lurepattern = Pattern.matches("LURE", enchant);
				boolean oxygenpattern = Pattern.matches("(OXYGEN|RESPIRATION)", enchant);
				boolean protectionenvpattern = Pattern.matches("(PROT(ECTION)?(_| )*ENVIRONMENTAL|PROT(ECTION)?)", enchant);
				boolean protectionexppattern = Pattern.matches("(PROT(ECTION)?(_| )*EXPLOSIONS|BLAST(_| )*PROT(ECTION)?)", enchant);
				boolean protectionfallpattern = Pattern.matches("(PROT(ECTION)?(_| )*FALL|FEATHER(_| )*FALL(ING)?)", enchant);
				boolean protectionfirepattern = Pattern.matches("(PROT(ECTION)?(_| )*FIRE|FIRE(_| )*PROT(ECTION)?)", enchant);
				boolean protectionprojectilepattern = Pattern.matches("(PROT(ECTION)?(_| )*PROJECTILE|PROJECTILE(_| )*PROT(ECTION)?)", enchant);
				boolean silktouchpattern = Pattern.matches("(SILK(_| )*TOUCH)", enchant);
				boolean thornspattern = Pattern.matches("THORNS?", enchant);
				boolean waterworkerpattern = Pattern.matches("(WATER(_| )*WORK(ER)?|AQUA(_| )*AFFINITY)", enchant);
				
				Enchantment enchanton = null;
				if (arrowdamagepattern)
					enchanton = Enchantment.ARROW_DAMAGE;
				if (arrowfirepattern)
					enchanton = Enchantment.ARROW_FIRE;
				if (arrowinfinitepattern)
					enchanton = Enchantment.ARROW_INFINITE;
				if (arrowkbpattern)
					enchanton = Enchantment.ARROW_KNOCKBACK;
				if (damageallpattern)
					enchanton = Enchantment.DAMAGE_ALL;
				if (damagearthpattern)
					enchanton = Enchantment.DAMAGE_ARTHROPODS;
				if (damageundeadpattern)
					enchanton = Enchantment.DAMAGE_UNDEAD;
				if (digspeedpattern)
					enchanton = Enchantment.DIG_SPEED;
				if (durapattern)
					enchanton = Enchantment.DURABILITY;
				if (firepattern)
					enchanton = Enchantment.FIRE_ASPECT;
				if (kbpattern)
					enchanton = Enchantment.KNOCKBACK;
				if (lootblockspattern)
					enchanton = Enchantment.LOOT_BONUS_BLOCKS;
				if (lootmobspattern)
					enchanton = Enchantment.LOOT_BONUS_MOBS;
				if (luckpattern)
					enchanton = Enchantment.LUCK;
				if (lurepattern)
					enchanton = Enchantment.LURE;
				if (oxygenpattern)
					enchanton = Enchantment.OXYGEN;
				if (protectionenvpattern)
					enchanton = Enchantment.PROTECTION_ENVIRONMENTAL;
				if (protectionexppattern)
					enchanton = Enchantment.PROTECTION_EXPLOSIONS;
				if (protectionfallpattern)
					enchanton = Enchantment.PROTECTION_FALL;
				if (protectionfirepattern)
					enchanton = Enchantment.PROTECTION_FIRE;
				if (protectionprojectilepattern)
					enchanton = Enchantment.PROTECTION_PROJECTILE;
				if (silktouchpattern)
					enchanton = Enchantment.SILK_TOUCH;
				if (thornspattern)
					enchanton = Enchantment.THORNS;
				if (waterworkerpattern)
					enchanton = Enchantment.WATER_WORKER;
				
				if (enchanton != null) {
					if (Config_DropParty.getAllowUnsafeEnchants()) { 
						item.addUnsafeEnchantment(enchanton, level); 
					} else {
						if (CanEnchantItem(item.getType(), enchanton)) {
							if (level > enchanton.getMaxLevel())
								level = enchanton.getMaxLevel();
							item.addEnchantment(enchanton, level);
						} else {
							plugin.LogMessage(Level.WARNING, enchanton.getName() + " can not be applied to " + item.getType().name());
							continue;
						}
					}
				}
												
			}
			
		}
		
		return item;
	}
	
	public static Boolean checkItem(Integer itemid, Integer subid, Boolean hidden) {
		
		if (hidden) {
			
			if (itemid == -1) {
				
				if (subid < 0 || subid > 6)
					return false;
				return true;
				
			}
			
		}
		
		if (itemid <= 0 || itemid > 2267)
			return false;
		
		if (itemid > 422 && itemid < 2256)
			return false;
		
		if (itemid > 175 && itemid < 256)
			return false;
		
		if (itemid == 1)
			if (subid < 0 || subid > 6)
				return false;
		
		if (itemid == 3)
			if (subid < 0 || subid > 2)
				return false;
		
		if (itemid == 5)
			if (subid < 0 || subid > 5)
				return false;
		
		if (itemid == 6)
			if (subid < 0 || subid > 5)
				return false;
		
		if (itemid == 12)
			if (subid < 0 || subid > 1)
				return false;
		
		if (itemid == 17)
			if (subid < 0 || subid > 3)
				return false;
		
		if (itemid == 18)
			if (subid < 0 || subid > 3)
				return false;
		
		if (itemid == 24)
			if (subid < 0 || subid > 2)
				return false;
		
		if (itemid == 31)
			if (subid < 0 || subid > 2)
				return false;
		
		if (itemid == 35)
			if (subid < 0 || subid > 15)
				return false;
		
		if (itemid == 38)
			if (subid < 0 || subid > 8)
				return false;
		
		if (itemid == 43)
			if (subid < 0 || subid > 9)
				return false;
		
		if (itemid == 44)
			if (subid < 0 || subid > 7)
				return false;
		
		if (itemid == 95)
			if (subid < 0 || subid > 15)
				return false;
		
		if (itemid == 97)
			if (subid < 0 || subid > 5)
				return false;
		
		if (itemid == 98)
			if (subid < 0 || subid > 3)
				return false;
		
		if (itemid == 125)
			if (subid < 0 || subid > 5)
				return false;
		
		if (itemid == 126)
			if (subid < 0 || subid > 5)
				return false;
		
		if (itemid == 139)
			if (subid < 0 || subid > 1)
				return false;
		
		if (itemid == 155)
			if (subid < 0 || subid > 2)
				return false;
		
		if (itemid == 159)
			if (subid < 0 || subid > 15)
				return false;
		
		if (itemid == 160)
			if (subid < 0 || subid > 15)
				return false;
		
		if (itemid == 161)
			if (subid < 0 || subid > 1)
				return false;
		
		if (itemid == 162)
			if (subid < 0 || subid > 1)
				return false;
		
		if (itemid == 171)
			if (subid < 0 || subid > 15)
				return false;
		
		if (itemid == 175)
			if (subid < 0 || subid > 5)
				return false;
		
		if (itemid == 163)
			if (subid < 0 || subid > 1)
				return false;
		
		if (itemid == 322)
			if (subid < 0 || subid > 1)
				return false;
		
		if (itemid == 349)
			if (subid < 0 || subid > 3)
				return false;
		
		if (itemid == 350)
			if (subid < 0 || subid > 3)
				return false;
		
		if (itemid == 351)
			if (subid < 0 || subid > 15)
				return false;
		
		if (itemid == 383)
			if (subid < 0 || subid > 120)
				return false;
		
		if (itemid == 397)
			if (subid < 0 || subid > 4)
				return false;
		
		return true;
	}
	
	public static Boolean CanEnchantItem(Material material, Enchantment enchantment) {
		
		String name = material.name();
		
		if (material == Material.BOW) {
			
			if (enchantment == Enchantment.ARROW_DAMAGE)
				return true;
			if (enchantment == Enchantment.ARROW_FIRE)
				return true;
			if (enchantment == Enchantment.ARROW_INFINITE)
				return true;
			if (enchantment == Enchantment.ARROW_KNOCKBACK)
				return true;
			if (enchantment == Enchantment.DURABILITY)
				return true;
			
		}
		
		if (material == Material.FISHING_ROD) {
			
			if (enchantment == Enchantment.LUCK)
				return true;
			if (enchantment == Enchantment.LURE)
				return true;
			if (enchantment == Enchantment.DURABILITY)
				return true;
			
		}
		
		if (name.endsWith("_SWORD")) {
			
			if (enchantment == Enchantment.DAMAGE_ALL)
				return true;
			if (enchantment == Enchantment.DAMAGE_ARTHROPODS)
				return true;
			if (enchantment == Enchantment.DAMAGE_UNDEAD)
				return true;
			if (enchantment == Enchantment.FIRE_ASPECT)
				return true;
			if (enchantment == Enchantment.KNOCKBACK)
				return true;
			if (enchantment == Enchantment.LOOT_BONUS_MOBS)
				return true;
			if (enchantment == Enchantment.DURABILITY)
				return true;
			
		}
		
		if (name.endsWith("_AXE")) {
			
			if (enchantment == Enchantment.DAMAGE_ALL)
				return true;
			if (enchantment == Enchantment.DAMAGE_ARTHROPODS)
				return true;
			if (enchantment == Enchantment.DAMAGE_UNDEAD)
				return true;
			if (enchantment == Enchantment.LOOT_BONUS_BLOCKS)
				return true;
			if (enchantment == Enchantment.SILK_TOUCH)
				return true;
			if (enchantment == Enchantment.DIG_SPEED)
				return true;
			if (enchantment == Enchantment.DURABILITY)
				return true;
			
		}
				
		if (name.endsWith("_SPADE") || material.name().endsWith("_PICKAXE")) {
			
			if (enchantment == Enchantment.LOOT_BONUS_BLOCKS)
				return true;
			if (enchantment == Enchantment.SILK_TOUCH)
				return true;
			if (enchantment == Enchantment.DIG_SPEED)
				return true;
			if (enchantment == Enchantment.DURABILITY)
				return true;
			
		}
		
		if (material == Material.SHEARS) {
			
			if (enchantment == Enchantment.SILK_TOUCH)
				return true;
			if (enchantment == Enchantment.DIG_SPEED)
				return true;
			if (enchantment == Enchantment.DURABILITY)
				return true;
			
		}
		
		if (material == Material.FLINT_AND_STEEL) {
			if (enchantment == Enchantment.DURABILITY)
				return true;
		}
		
		if (name.endsWith("_HELMET") || name.endsWith("_CHESTPLATE") || name.endsWith("_LEGGINGS") || name.endsWith("_BOOTS")) {
			
			if (enchantment == Enchantment.PROTECTION_ENVIRONMENTAL)
				return true;
			if (enchantment == Enchantment.PROTECTION_EXPLOSIONS)
				return true;
			if (enchantment == Enchantment.PROTECTION_FIRE)
				return true;
			if (enchantment == Enchantment.PROTECTION_PROJECTILE)
				return true;
			if (enchantment == Enchantment.THORNS)
				return true;
			if (enchantment == Enchantment.DURABILITY)
				return true;
			
		}
		
		if (name.endsWith("_HELMET")) {
			
			if (enchantment == Enchantment.OXYGEN)
				return true;
			if (enchantment == Enchantment.WATER_WORKER)
				return true;
			
		}
		
		if (name.endsWith("_BOOTS")) {
			if (enchantment == Enchantment.PROTECTION_FALL)
				return true;
		}
		
		if (name.endsWith("_HOE")) {
			if (enchantment == Enchantment.DURABILITY)
				return true;
		}
		return false;
	}

}
