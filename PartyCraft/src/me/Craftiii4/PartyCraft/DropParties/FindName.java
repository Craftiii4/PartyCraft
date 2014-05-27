package me.Craftiii4.PartyCraft.DropParties;

import me.Craftiii4.PartyCraft.Files.Config_DropParty;

import org.apache.commons.lang.WordUtils;
import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class FindName {
	
	@SuppressWarnings("deprecation")
	public static String getItemName(ItemStack item) {

		int id = item.getTypeId();
		int sub = item.getDurability();
		
		String time = "";
		
		if (Config_DropParty.hasTime(item)) {
			time = " §3Which added §d" + (Config_DropParty.getTime(item) * item.getAmount()) + " §3to the time";
		}
		
		String displayname = "";
		
		if (item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {
			displayname = " §aCalled §5" + item.getItemMeta().getDisplayName();
		}
		
		String enchantmentstoadd = "";

		if (item.containsEnchantment(
				Enchantment.ARROW_DAMAGE)) {
			int level = item.getEnchantmentLevel(
					Enchantment.ARROW_DAMAGE);
			enchantmentstoadd = enchantmentstoadd + ChatColor.GOLD + " ("
					+ ChatColor.GREEN + "Power " + ChatColor.GRAY
					+ "[" + ChatColor.RED + level + ChatColor.GRAY + "]"
					+ ChatColor.GOLD + ")";
		}
		if (item.containsEnchantment(
				Enchantment.ARROW_FIRE)) {
			int level = item.getEnchantmentLevel(
					Enchantment.ARROW_FIRE);
			enchantmentstoadd = enchantmentstoadd + ChatColor.GOLD + " ("
					+ ChatColor.GREEN + "Flame " + ChatColor.GRAY
					+ "[" + ChatColor.RED + level + ChatColor.GRAY + "]"
					+ ChatColor.GOLD + ")";
		}
		if (item.containsEnchantment(
				Enchantment.ARROW_INFINITE)) {
			int level = item.getEnchantmentLevel(
					Enchantment.ARROW_INFINITE);
			enchantmentstoadd = enchantmentstoadd + ChatColor.GOLD + " ("
					+ ChatColor.GREEN + "Infinity " + ChatColor.GRAY
					+ "[" + ChatColor.RED + level + ChatColor.GRAY + "]"
					+ ChatColor.GOLD + ")";
		}

		if (item.containsEnchantment(
				Enchantment.ARROW_KNOCKBACK)) {
			int level = item.getEnchantmentLevel(
					Enchantment.ARROW_KNOCKBACK);
			enchantmentstoadd = enchantmentstoadd + ChatColor.GOLD + " ("
					+ ChatColor.GREEN + "Punch " + ChatColor.GRAY
					+ "[" + ChatColor.RED + level + ChatColor.GRAY + "]"
					+ ChatColor.GOLD + ")";
		}
		if (item.containsEnchantment(
				Enchantment.DAMAGE_ALL)) {
			int level = item.getEnchantmentLevel(
					Enchantment.DAMAGE_ALL);
			enchantmentstoadd = enchantmentstoadd + ChatColor.GOLD + " ("
					+ ChatColor.GREEN + "Sharpness " + ChatColor.GRAY
					+ "[" + ChatColor.RED + level + ChatColor.GRAY + "]"
					+ ChatColor.GOLD + ")";
		}
		if (item.containsEnchantment(
				Enchantment.DAMAGE_ARTHROPODS)) {
			int level = item.getEnchantmentLevel(
					Enchantment.DAMAGE_ARTHROPODS);
			enchantmentstoadd = enchantmentstoadd + ChatColor.GOLD + " ("
					+ ChatColor.GREEN + "Arthropods " + ChatColor.GRAY
					+ "[" + ChatColor.RED + level + ChatColor.GRAY + "]"
					+ ChatColor.GOLD + ")";
		}
		if (item.containsEnchantment(
				Enchantment.DAMAGE_UNDEAD)) {
			int level = item.getEnchantmentLevel(
					Enchantment.DAMAGE_UNDEAD);
			enchantmentstoadd = enchantmentstoadd + ChatColor.GOLD + " ("
					+ ChatColor.GREEN + "Smite " + ChatColor.GRAY
					+ "[" + ChatColor.RED + level + ChatColor.GRAY + "]"
					+ ChatColor.GOLD + ")";
		}
		if (item.containsEnchantment(
				Enchantment.DIG_SPEED)) {
			int level = item.getEnchantmentLevel(
					Enchantment.DIG_SPEED);
			enchantmentstoadd = enchantmentstoadd + ChatColor.GOLD + " ("
					+ ChatColor.GREEN + "Efficiency " + ChatColor.GRAY + "["
					+ ChatColor.RED + level + ChatColor.GRAY + "]"
					+ ChatColor.GOLD + ")";
		}
		if (item.containsEnchantment(
				Enchantment.DURABILITY)) {
			int level = item.getEnchantmentLevel(
					Enchantment.DURABILITY);
			enchantmentstoadd = enchantmentstoadd + ChatColor.GOLD + " ("
					+ ChatColor.GREEN + "Durability " + ChatColor.GRAY
					+ "[" + ChatColor.RED + level + ChatColor.GRAY + "]"
					+ ChatColor.GOLD + ")";
		}
		if (item.containsEnchantment(
				Enchantment.FIRE_ASPECT)) {
			int level = item.getEnchantmentLevel(
					Enchantment.FIRE_ASPECT);
			enchantmentstoadd = enchantmentstoadd + ChatColor.GOLD + " ("
					+ ChatColor.GREEN + "Fire Aspect " + ChatColor.GRAY + "["
					+ ChatColor.RED + level + ChatColor.GRAY + "]"
					+ ChatColor.GOLD + ")";
		}
		if (item.containsEnchantment(
				Enchantment.KNOCKBACK)) {
			int level = item.getEnchantmentLevel(
					Enchantment.KNOCKBACK);
			enchantmentstoadd = enchantmentstoadd + ChatColor.GOLD + " ("
					+ ChatColor.GREEN + "Knockback " + ChatColor.GRAY + "["
					+ ChatColor.RED + level + ChatColor.GRAY + "]"
					+ ChatColor.GOLD + ")";
		}
		if (item.containsEnchantment(
				Enchantment.LOOT_BONUS_BLOCKS)) {
			int level = item.getEnchantmentLevel(
					Enchantment.LOOT_BONUS_BLOCKS);
			enchantmentstoadd = enchantmentstoadd + ChatColor.GOLD + " ("
					+ ChatColor.GREEN + "Fortune " + ChatColor.GRAY
					+ "[" + ChatColor.RED + level + ChatColor.GRAY + "]"
					+ ChatColor.GOLD + ")";
		}
		if (item.containsEnchantment(
				Enchantment.LOOT_BONUS_MOBS)) {
			int level = item.getEnchantmentLevel(
					Enchantment.LOOT_BONUS_MOBS);
			enchantmentstoadd = enchantmentstoadd + ChatColor.GOLD + " ("
					+ ChatColor.GREEN + "Looting " + ChatColor.GRAY + "["
					+ ChatColor.RED + level + ChatColor.GRAY + "]"
					+ ChatColor.GOLD + ")";
		}
		if (item.containsEnchantment(Enchantment.LUCK)) {
			int level = item.getEnchantmentLevel(
					Enchantment.LUCK);
			enchantmentstoadd = enchantmentstoadd + ChatColor.GOLD + " ("
					+ ChatColor.GREEN + "Luck " + ChatColor.GRAY + "["
					+ ChatColor.RED + level + ChatColor.GRAY + "]"
					+ ChatColor.GOLD + ")";
		}
		if (item.containsEnchantment(Enchantment.LURE)) {
			int level = item.getEnchantmentLevel(
					Enchantment.LURE);
			enchantmentstoadd = enchantmentstoadd + ChatColor.GOLD + " ("
					+ ChatColor.GREEN + "Lure " + ChatColor.GRAY + "["
					+ ChatColor.RED + level + ChatColor.GRAY + "]"
					+ ChatColor.GOLD + ")";
		}
		if (item.containsEnchantment(Enchantment.OXYGEN)) {
			int level = item.getEnchantmentLevel(
					Enchantment.OXYGEN);
			enchantmentstoadd = enchantmentstoadd + ChatColor.GOLD + " ("
					+ ChatColor.GREEN + "Respiration " + ChatColor.GRAY + "["
					+ ChatColor.RED + level + ChatColor.GRAY + "]"
					+ ChatColor.GOLD + ")";
		}
		if (item.containsEnchantment(
				Enchantment.PROTECTION_ENVIRONMENTAL)) {
			int level = item.getEnchantmentLevel(
					Enchantment.PROTECTION_ENVIRONMENTAL);
			enchantmentstoadd = enchantmentstoadd + ChatColor.GOLD + " ("
					+ ChatColor.GREEN + "Protection "
					+ ChatColor.GRAY + "[" + ChatColor.RED + level
					+ ChatColor.GRAY + "]" + ChatColor.GOLD + ")";
		}
		if (item.containsEnchantment(
				Enchantment.PROTECTION_EXPLOSIONS)) {
			int level = item.getEnchantmentLevel(
					Enchantment.PROTECTION_EXPLOSIONS);
			enchantmentstoadd = enchantmentstoadd + ChatColor.GOLD + " ("
					+ ChatColor.GREEN + "Blast Protection "
					+ ChatColor.GRAY + "[" + ChatColor.RED + level
					+ ChatColor.GRAY + "]" + ChatColor.GOLD + ")";
		}
		if (item.containsEnchantment(
				Enchantment.PROTECTION_FALL)) {
			int level = item.getEnchantmentLevel(
					Enchantment.PROTECTION_FALL);
			enchantmentstoadd = enchantmentstoadd + ChatColor.GOLD + " ("
					+ ChatColor.GREEN + "Feather Falling " + ChatColor.GRAY
					+ "[" + ChatColor.RED + level + ChatColor.GRAY + "]"
					+ ChatColor.GOLD + ")";
		}
		if (item.containsEnchantment(
				Enchantment.PROTECTION_FIRE)) {
			int level = item.getEnchantmentLevel(
					Enchantment.PROTECTION_FIRE);
			enchantmentstoadd = enchantmentstoadd + ChatColor.GOLD + " ("
					+ ChatColor.GREEN + "Fire Protection " + ChatColor.GRAY
					+ "[" + ChatColor.RED + level + ChatColor.GRAY + "]"
					+ ChatColor.GOLD + ")";
		}
		if (item.containsEnchantment(
				Enchantment.PROTECTION_PROJECTILE)) {
			int level = item.getEnchantmentLevel(
					Enchantment.PROTECTION_PROJECTILE);
			enchantmentstoadd = enchantmentstoadd + ChatColor.GOLD + " ("
					+ ChatColor.GREEN + "Projectile Protection "
					+ ChatColor.GRAY + "[" + ChatColor.RED + level
					+ ChatColor.GRAY + "]" + ChatColor.GOLD + ")";
		}
		if (item.containsEnchantment(
				Enchantment.SILK_TOUCH)) {
			int level = item.getEnchantmentLevel(
					Enchantment.SILK_TOUCH);
			enchantmentstoadd = enchantmentstoadd + ChatColor.GOLD + " ("
					+ ChatColor.GREEN + "Silk Touch " + ChatColor.GRAY
					+ "[" + ChatColor.RED + level + ChatColor.GRAY + "]"
					+ ChatColor.GOLD + ")";
		}
		if (item.containsEnchantment(Enchantment.THORNS)) {
			int level = item.getEnchantmentLevel(
					Enchantment.THORNS);
			enchantmentstoadd = enchantmentstoadd + ChatColor.GOLD + " ("
					+ ChatColor.GREEN + "Thorns " + ChatColor.GRAY + "["
					+ ChatColor.RED + level + ChatColor.GRAY + "]"
					+ ChatColor.GOLD + ")";
		}
		if (item.containsEnchantment(
				Enchantment.WATER_WORKER)) {
			int level = item.getEnchantmentLevel(
					Enchantment.WATER_WORKER);
			enchantmentstoadd = enchantmentstoadd + ChatColor.GOLD + " ("
					+ ChatColor.GREEN + "Aqua Affinity " + ChatColor.GRAY
					+ "[" + ChatColor.RED + level + ChatColor.GRAY + "]"
					+ ChatColor.GOLD + ")";
		}
		
		if (id == 1) {
			if (sub == 0)
				return "Stone" + displayname + enchantmentstoadd + time;
			if (sub == 1)
				return "Granite" + displayname + enchantmentstoadd + time;
			if (sub == 2)
				return "Polished Granite" + displayname + enchantmentstoadd + time;
			if (sub == 3)
				return "Diorite" + displayname + enchantmentstoadd + time;
			if (sub == 4)
				return "Polished Diorite" + displayname + enchantmentstoadd + time;
			if (sub == 5)
				return "Andesite" + displayname + enchantmentstoadd + time;
			if (sub == 6)
				return "Polished Andesite" + displayname + enchantmentstoadd + time;
			return "Unknown Stone" + displayname + enchantmentstoadd + time;
		}
		if (id == 3) {
			if (sub == 0)
				return "Dirt" + displayname + enchantmentstoadd + time;
			if (sub == 1)
				return "Dirt (No Grass)" + displayname + enchantmentstoadd + time;
			if (sub == 2)
				return "Podzol" + displayname + enchantmentstoadd + time;
			return "Unknown Wood Plank" + displayname + enchantmentstoadd + time;
		}
		if (id == 5) {
			if (sub == 0)
				return "Oak Wood Plank" + displayname + enchantmentstoadd + time;
			if (sub == 1)
				return "Spruce Wood Plank" + displayname + enchantmentstoadd + time;
			if (sub == 2)
				return "Birch Wood Plank" + displayname + enchantmentstoadd + time;
			if (sub == 3)
				return "Jungle Wood Plank" + displayname + enchantmentstoadd + time;
			if (sub == 4)
				return "Acacia Wood Plank" + displayname + enchantmentstoadd + time;
			if (sub == 5)
				return "Dark Oak Wood Plank" + displayname + enchantmentstoadd + time;
			return "Unknown Wood Plank" + displayname + enchantmentstoadd + time;
		}
		if (id == 6) {
			if (sub == 0)
				return "Oak Sapling" + displayname + enchantmentstoadd + time;
			if (sub == 1)
				return "Spruce Sapling" + displayname + enchantmentstoadd + time;
			if (sub == 2)
				return "Birch Sapling" + displayname + enchantmentstoadd + time;
			if (sub == 3)
				return "Jungle Sapling" + displayname + enchantmentstoadd + time;
			if (sub == 4)
				return "Acacia Sapling" + displayname + enchantmentstoadd + time;
			if (sub == 5)
				return "Dark Oak Sapling" + displayname + enchantmentstoadd + time;
			return "Unknown Sapling" + displayname + enchantmentstoadd + time;
		}
		if (id == 8)
			return "Moving Water Block" + displayname + enchantmentstoadd + time;
		if (id == 9)
			return "Stationary Water Block" + displayname + enchantmentstoadd + time;
		if (id == 10)
			return "Moving Lava Block" + displayname + enchantmentstoadd + time;
		if (id == 11)
			return "Stationary Lava Block" + displayname + enchantmentstoadd + time;
		if (id == 12) {
			if (sub == 0)
				return "Sand" + displayname + enchantmentstoadd + time;
			if (sub == 1)
				return "Red Sand" + displayname + enchantmentstoadd + time;
			return "Unknown Sand" + displayname + enchantmentstoadd + time;
		}
		if (id == 17) {
			if (sub == 0)
				return "Oak Tree" + displayname + enchantmentstoadd + time;
			if (sub == 1)
				return "Spruce Tree" + displayname + enchantmentstoadd + time;
			if (sub == 2)
				return "Birch Tree" + displayname + enchantmentstoadd + time;
			if (sub == 3)
				return "Jungle Tree" + displayname + enchantmentstoadd + time;
			return "Unknown Tree" + displayname + enchantmentstoadd + time;
		}
		if (id == 18) {
			if (sub == 0)
				return "Oak Leaves" + displayname + enchantmentstoadd + time;
			if (sub == 1)
				return "Spruce Leaves" + displayname + enchantmentstoadd + time;
			if (sub == 2)
				return "Birch Leaves" + displayname + enchantmentstoadd + time;
			if (sub == 3)
				return "Jungle Leaves" + displayname + enchantmentstoadd + time;
			return "Unknown Leaves" + displayname + enchantmentstoadd + time;
		}
		if (id == 24) {
			if (sub == 0)
				return "Sandstone" + displayname + enchantmentstoadd + time;
			if (sub == 1)
				return "Chiseled Sandstone" + displayname + enchantmentstoadd + time;
			if (sub == 2)
				return "Smooth Sandstone" + displayname + enchantmentstoadd + time;
			return "Unknown Sandstone" + displayname + enchantmentstoadd + time;
		}
		if (id == 26)
			return "Bed Block" + displayname + enchantmentstoadd + time;
		if (id == 31) {
			if (sub == 0)
				return "Dead Shrub" + displayname + enchantmentstoadd + time;
			if (sub == 1)
				return "Tall Grass" + displayname + enchantmentstoadd + time;
			if (sub == 2)
				return "Fern" + displayname + enchantmentstoadd + time;
			return "Tall Grass" + displayname + enchantmentstoadd + time;
		}
		if (id == 35) {
			if (sub == 0)
				return "White Wool" + displayname + enchantmentstoadd + time;
			if (sub == 1)
				return "Orange Wool" + displayname + enchantmentstoadd + time;
			if (sub == 2)
				return "Magenta Wool" + displayname + enchantmentstoadd + time;
			if (sub == 3)
				return "Light Blue Wool" + displayname + enchantmentstoadd + time;
			if (sub == 4)
				return "Yellow Wool" + displayname + enchantmentstoadd + time;
			if (sub == 5)
				return "Lime Wool" + displayname + enchantmentstoadd + time;
			if (sub == 6)
				return "Pink Wool" + displayname + enchantmentstoadd + time;
			if (sub == 7)
				return "Gray Wool" + displayname + enchantmentstoadd + time;
			if (sub == 8)
				return "Light Gray Wool" + displayname + enchantmentstoadd + time;
			if (sub == 9)
				return "Cyan Wool" + displayname + enchantmentstoadd + time;
			if (sub == 10)
				return "Purple Wool" + displayname + enchantmentstoadd + time;
			if (sub == 11)
				return "Blue Wool" + displayname + enchantmentstoadd + time;
			if (sub == 12)
				return "Brown Wool" + displayname + enchantmentstoadd + time;
			if (sub == 13)
				return "Green Wool" + displayname + enchantmentstoadd + time;
			if (sub == 14)
				return "Red Wool" + displayname + enchantmentstoadd + time;
			if (sub == 15)
				return "Black Wool" + displayname + enchantmentstoadd + time;
			return "Unknown Wool" + displayname + enchantmentstoadd + time;
		}
		if (id == 38) {
			if (sub == 0)
				return "Poppy" + displayname + enchantmentstoadd + time;
			if (sub == 1)
				return "Blue Orchid" + displayname + enchantmentstoadd + time;
			if (sub == 2)
				return "Allium" + displayname + enchantmentstoadd + time;
			if (sub == 3)
				return "Azure Bluet" + displayname + enchantmentstoadd + time;
			if (sub == 4)
				return "Red Tulip" + displayname + enchantmentstoadd + time;
			if (sub == 5)
				return "Orange Tulip" + displayname + enchantmentstoadd + time;
			if (sub == 6)
				return "White Tulip" + displayname + enchantmentstoadd + time;
			if (sub == 7)
				return "Pink Tulip" + displayname + enchantmentstoadd + time;
			if (sub == 8)
				return "Oxeye Daisy" + displayname + enchantmentstoadd + time;
			return "Unknown Flower" + displayname + enchantmentstoadd + time;
		}
		if (id == 43) {
			if (sub == 0)
				return "Double Stone Slab" + displayname + enchantmentstoadd + time;
			if (sub == 1)
				return "Double Sandstone Slab" + displayname + enchantmentstoadd + time;
			if (sub == 2)
				return "Double Wooden Slab" + displayname + enchantmentstoadd + time;
			if (sub == 3)
				return "Double Cobblestone Slab" + displayname + enchantmentstoadd + time;
			if (sub == 4)
				return "Double Brick Slab" + displayname + enchantmentstoadd + time;
			if (sub == 5)
				return "Double Stone Brick Slab" + displayname + enchantmentstoadd + time;
			if (sub == 6)
				return "Double Nether Brick Slab" + displayname + enchantmentstoadd + time;
			if (sub == 7)
				return "Double Quartz Slab" + displayname + enchantmentstoadd + time;
			if (sub == 8)
				return "Double Smooth Stone Slab" + displayname + enchantmentstoadd + time;
			if (sub == 9)
				return "Double Smooth Sandstone Slab" + displayname + enchantmentstoadd + time;
			return "Unknown Double Slab" + displayname + enchantmentstoadd + time;
		}
		if (id == 44) {
			if (sub == 0)
				return "Stone Slab" + displayname + enchantmentstoadd + time;
			if (sub == 1)
				return "Sandstone Slab" + displayname + enchantmentstoadd + time;
			if (sub == 2)
				return "Wooden Slab" + displayname + enchantmentstoadd + time;
			if (sub == 3)
				return "Cobblestone Slab" + displayname + enchantmentstoadd + time;
			if (sub == 4)
				return "Brick Slab" + displayname + enchantmentstoadd + time;
			if (sub == 5)
				return "Stone Brick Slab" + displayname + enchantmentstoadd + time;
			if (sub == 6)
				return "Nether Brick Slab" + displayname + enchantmentstoadd + time;
			if (sub == 7)
				return "Double Quartz Slab" + displayname + enchantmentstoadd + time;
			return "Unknown Slab" + displayname + enchantmentstoadd + time;
		}
		if (id == 59)
			return "Crop Block" + displayname + enchantmentstoadd + time;
		if (id == 75)
			return "Redstone Torch (Off)" + displayname + enchantmentstoadd + time;
		if (id == 76)
			return "Redseone Torch" + displayname + enchantmentstoadd + time;
		if (id == 83)
			return "Sugar Cane Block" + displayname + enchantmentstoadd + time;
		if (id == 90)
			return "Portal Block" + displayname + enchantmentstoadd + time;
		if (id == 93)
			return "Redstone Repeater Block (off)" + displayname + enchantmentstoadd + time;
		if (id == 94)
			return "Redstone Repeater Block" + displayname + enchantmentstoadd + time;
		if (id == 95) {
			if (sub == 0)
				return "White Stained Glass" + displayname + enchantmentstoadd + time;
			if (sub == 1)
				return "Orange Stained Glass" + displayname + enchantmentstoadd + time;
			if (sub == 2)
				return "Magenta Stained Glass" + displayname + enchantmentstoadd + time;
			if (sub == 3)
				return "Light Blue Stained Glass" + displayname + enchantmentstoadd + time;
			if (sub == 4)
				return "Yellow Stained Glass" + displayname + enchantmentstoadd + time;
			if (sub == 5)
				return "Lime Stained Glass" + displayname + enchantmentstoadd + time;
			if (sub == 6)
				return "Pink Stained Glass" + displayname + enchantmentstoadd + time;
			if (sub == 7)
				return "Gray Stained Glass" + displayname + enchantmentstoadd + time;
			if (sub == 8)
				return "Light Gray Stained Glass" + displayname + enchantmentstoadd + time;
			if (sub == 9)
				return "Cyan Stained Glass" + displayname + enchantmentstoadd + time;
			if (sub == 10)
				return "Purple Stained Glass" + displayname + enchantmentstoadd + time;
			if (sub == 11)
				return "Blue Stained Glass" + displayname + enchantmentstoadd + time;
			if (sub == 12)
				return "Brown Stained Glass" + displayname + enchantmentstoadd + time;
			if (sub == 13)
				return "Green Stained Glass" + displayname + enchantmentstoadd + time;
			if (sub == 14)
				return "Red Stained Glass" + displayname + enchantmentstoadd + time;
			if (sub == 15)
				return "Black Stained Glass" + displayname + enchantmentstoadd + time;
			return "Unknown Stained Glass" + displayname + enchantmentstoadd + time;
		}
		if (id == 97) {
			if (sub == 0)
				return "SilverFish Stone" + displayname + enchantmentstoadd + time;
			if (sub == 1)
				return "SilverFish Cobblstone" + displayname + enchantmentstoadd + time;
			if (sub == 2)
				return "SilverFish Stone Brick" + displayname + enchantmentstoadd + time;
			if (sub == 3)
				return "SilverFish Mossy Stone Brick" + displayname + enchantmentstoadd + time;
			if (sub == 4)
				return "SilverFish Cracked Stone Brick" + displayname + enchantmentstoadd + time;
			if (sub == 5)
				return "SilverFish Chiseled Stone Brick" + displayname + enchantmentstoadd + time;
			return "Unknown SilverFish Block" + displayname + enchantmentstoadd + time;
		}
		if (id == 98) {
			if (sub == 0)
				return "Stone Brick" + displayname + enchantmentstoadd + time;
			if (sub == 1)
				return "Mossy Stone Brick" + displayname + enchantmentstoadd + time;
			if (sub == 2)
				return "Cracked Stone Brick" + displayname + enchantmentstoadd + time;
			if (sub == 3)
				return "Chiseled Stone Brick" + displayname + enchantmentstoadd + time;
			return "Unknown Stone Block" + displayname + enchantmentstoadd + time;
		}
		if (id == 125) {
			if (sub == 0)
				return "Double Oak Wood Slab" + displayname + enchantmentstoadd + time;
			if (sub == 1)
				return "Double Spruce Wood Slab" + displayname + enchantmentstoadd + time;
			if (sub == 2)
				return "Double Birch Wood Slab" + displayname + enchantmentstoadd + time;
			if (sub == 3)
				return "Double Jungle Wood Slab" + displayname + enchantmentstoadd + time;
			if (sub == 4)
				return "Double Acacia Wood Slab" + displayname + enchantmentstoadd + time;
			if (sub == 5)
				return "Double Dark Oak Wood Slab" + displayname + enchantmentstoadd + time;
			return "Unknown Double Wood Slab" + displayname + enchantmentstoadd + time;
		}
		if (id == 126) {
			if (sub == 0)
				return "Oak Wood Slab" + displayname + enchantmentstoadd + time;
			if (sub == 1)
				return "Spruce Wood Slab" + displayname + enchantmentstoadd + time;
			if (sub == 2)
				return "Birch Wood Slab" + displayname + enchantmentstoadd + time;
			if (sub == 3)
				return "Jungle Wood Slab" + displayname + enchantmentstoadd + time;
			if (sub == 4)
				return "Acacia Wood Slab" + displayname + enchantmentstoadd + time;
			if (sub == 5)
				return "Dark Oak Wood Slab" + displayname + enchantmentstoadd + time;
			return "Unknown Wood Slab" + displayname + enchantmentstoadd + time;
		}
		if (id == 139) {
			if (sub == 0)
				return "Cobblestone Wall" + displayname + enchantmentstoadd + time;
			if (sub == 1)
				return "Mossy Cobblestone Wall" + displayname + enchantmentstoadd + time;
			return "Unknown Cobblestone Wall" + displayname + enchantmentstoadd + time;
		}
		if (id == 147)
			return "Golden Weighted Pressure Plate" + displayname + enchantmentstoadd + time;
		if (id == 148)
			return "Iron Weighted Pressure Plate" + displayname + enchantmentstoadd + time;
		if (id == 155) {
			if (sub == 0)
				return "Quartz Block" + displayname + enchantmentstoadd + time;
			if (sub == 1)
				return "Chiseld Quartz Block" + displayname + enchantmentstoadd + time;
			if (sub == 2)
				return "Pillar Quartz Block" + displayname + enchantmentstoadd + time;
			return "Unknown Quartz Block" + displayname + enchantmentstoadd + time;
		}
		if (id == 159) {
			if (sub == 0)
				return "White Clay" + displayname + enchantmentstoadd + time;
			if (sub == 1)
				return "Orange Clay" + displayname + enchantmentstoadd + time;
			if (sub == 2)
				return "Magenta Clay" + displayname + enchantmentstoadd + time;
			if (sub == 3)
				return "Light Blue Clay" + displayname + enchantmentstoadd + time;
			if (sub == 4)
				return "Yellow Clay" + displayname + enchantmentstoadd + time;
			if (sub == 5)
				return "Lime Clay" + displayname + enchantmentstoadd + time;
			if (sub == 6)
				return "Pink Clay" + displayname + enchantmentstoadd + time;
			if (sub == 7)
				return "Gray Clay" + displayname + enchantmentstoadd + time;
			if (sub == 8)
				return "Light Gray Clay" + displayname + enchantmentstoadd + time;
			if (sub == 9)
				return "Cyan Clay" + displayname + enchantmentstoadd + time;
			if (sub == 10)
				return "Purple Clay" + displayname + enchantmentstoadd + time;
			if (sub == 11)
				return "Blue Clay" + displayname + enchantmentstoadd + time;
			if (sub == 12)
				return "Brown Clay" + displayname + enchantmentstoadd + time;
			if (sub == 13)
				return "Green Clay" + displayname + enchantmentstoadd + time;
			if (sub == 14)
				return "Red Clay" + displayname + enchantmentstoadd + time;
			if (sub == 15)
				return "Black Clay" + displayname + enchantmentstoadd + time;
			return "Unknown Clay" + displayname + enchantmentstoadd + time;
		}
		if (id == 160) {
			if (sub == 0)
				return "White Glass Pane" + displayname + enchantmentstoadd + time;
			if (sub == 1)
				return "Orange Glass Pane" + displayname + enchantmentstoadd + time;
			if (sub == 2)
				return "Magenta Glass Pane" + displayname + enchantmentstoadd + time;
			if (sub == 3)
				return "Light Blue Glass Pane" + displayname + enchantmentstoadd + time;
			if (sub == 4)
				return "Yellow Glass Pane" + displayname + enchantmentstoadd + time;
			if (sub == 5)
				return "Lime Glass Pane" + displayname + enchantmentstoadd + time;
			if (sub == 6)
				return "Pink Glass Pane" + displayname + enchantmentstoadd + time;
			if (sub == 7)
				return "Gray Glass Pane" + displayname + enchantmentstoadd + time;
			if (sub == 8)
				return "Light Gray Glass Pane" + displayname + enchantmentstoadd + time;
			if (sub == 9)
				return "Cyan Glass Pane" + displayname + enchantmentstoadd + time;
			if (sub == 10)
				return "Purple Glass Pane" + displayname + enchantmentstoadd + time;
			if (sub == 11)
				return "Blue Glass Pane" + displayname + enchantmentstoadd + time;
			if (sub == 12)
				return "Brown Glass Pane" + displayname + enchantmentstoadd + time;
			if (sub == 13)
				return "Green Glass Pane" + displayname + enchantmentstoadd + time;
			if (sub == 14)
				return "Red Glass Pane" + displayname + enchantmentstoadd + time;
			if (sub == 15)
				return "Black Glass Pane" + displayname + enchantmentstoadd + time;
			return "Unknown Glass Pane" + displayname + enchantmentstoadd + time;
		}
		if (id == 162) {
			if (sub == 0)
				return "Acacia Wood" + displayname + enchantmentstoadd + time;
			if (sub == 1)
				return "Dark Oak Wood" + displayname + enchantmentstoadd + time;
			return "Unknown Wood" + displayname + enchantmentstoadd + time;
		}
		if (id == 171) {
			if (sub == 0)
				return "White Carpet" + displayname + enchantmentstoadd + time;
			if (sub == 1)
				return "Orange Carpet" + displayname + enchantmentstoadd + time;
			if (sub == 2)
				return "Magenta Carpet" + displayname + enchantmentstoadd + time;
			if (sub == 3)
				return "Light Blue Carpet" + displayname + enchantmentstoadd + time;
			if (sub == 4)
				return "Yellow Carpet" + displayname + enchantmentstoadd + time;
			if (sub == 5)
				return "Lime Carpet" + displayname + enchantmentstoadd + time;
			if (sub == 6)
				return "Pink Carpet" + displayname + enchantmentstoadd + time;
			if (sub == 7)
				return "Gray Carpet" + displayname + enchantmentstoadd + time;
			if (sub == 8)
				return "Light Gray Carpet" + displayname + enchantmentstoadd + time;
			if (sub == 9)
				return "Cyan Carpet" + displayname + enchantmentstoadd + time;
			if (sub == 10)
				return "Purple Carpet" + displayname + enchantmentstoadd + time;
			if (sub == 11)
				return "Blue Carpet" + displayname + enchantmentstoadd + time;
			if (sub == 12)
				return "Brown Carpet" + displayname + enchantmentstoadd + time;
			if (sub == 13)
				return "Green Carpet" + displayname + enchantmentstoadd + time;
			if (sub == 14)
				return "Red Carpet" + displayname + enchantmentstoadd + time;
			if (sub == 15)
				return "Black Carpet" + displayname + enchantmentstoadd + time;
			return "Unknown Carpet" + displayname + enchantmentstoadd + time;
		}
		if (id == 175) {
			if (sub == 0)
				return "Sunflower" + displayname + enchantmentstoadd + time;
			if (sub == 1)
				return "Lilac" + displayname + enchantmentstoadd + time;
			if (sub == 2)
				return "Double Tall Grass" + displayname + enchantmentstoadd + time;
			if (sub == 3)
				return "Large Fern" + displayname + enchantmentstoadd + time;
			if (sub == 4)
				return "Rose Bush" + displayname + enchantmentstoadd + time;
			if (sub == 5)
				return "Peony" + displayname + enchantmentstoadd + time;
			return "Unknown Flower" + displayname + enchantmentstoadd + time;
		}
		if (id == 263) {
			if (sub == 0)
				return "Coal" + displayname + enchantmentstoadd + time;
			if (sub == 1)
				return "Charcoal" + displayname + enchantmentstoadd + time;
			return "Unknown Coal" + displayname + enchantmentstoadd + time;
		}
		if (id == 322) {
			if (sub == 0)
				return "Golden Apple" + displayname + enchantmentstoadd + time;
			if (sub == 1)
				return "Enchanted Golden Apple" + displayname + enchantmentstoadd + time;
			return "Unknown Golden Apple" + displayname + enchantmentstoadd + time;
		}
		if (id == 349) {
			if (sub == 0)
				return "Raw Fish" + displayname + enchantmentstoadd + time;
			if (sub == 1)
				return "Raw Salmon" + displayname + enchantmentstoadd + time;
			if (sub == 2)
				return "Clownfish" + displayname + enchantmentstoadd + time;
			if (sub == 3)
				return "Pufferfish" + displayname + enchantmentstoadd + time;
			return "Unknown Fish" + displayname + enchantmentstoadd + time;
		}
		if (id == 350) {
			if (sub == 0)
				return "Cooked Fish" + displayname + enchantmentstoadd + time;
			if (sub == 1)
				return "Cooked Salmon" + displayname + enchantmentstoadd + time;
			if (sub == 2)
				return "Clownfish" + displayname + enchantmentstoadd + time;
			if (sub == 3)
				return "Pufferfish" + displayname + enchantmentstoadd + time;
			return "Unknown Fish" + displayname + enchantmentstoadd + time;
		}
		if (id == 351) {
			if (sub == 0)
				return "Ink Sack" + displayname + enchantmentstoadd + time;
			if (sub == 1)
				return "Rose Red Dye" + displayname + enchantmentstoadd + time;
			if (sub == 2)
				return "Cactus Green Dye" + displayname + enchantmentstoadd + time;
			if (sub == 3)
				return "Coco Beans" + displayname + enchantmentstoadd + time;
			if (sub == 4)
				return "Lapis Lazlui" + displayname + enchantmentstoadd + time;
			if (sub == 5)
				return "Purple Dye" + displayname + enchantmentstoadd + time;
			if (sub == 6)
				return "Cyan Dye" + displayname + enchantmentstoadd + time;
			if (sub == 7)
				return "Light Gray Dye" + displayname + enchantmentstoadd + time;
			if (sub == 8)
				return "Gray Dye" + displayname + enchantmentstoadd + time;
			if (sub == 9)
				return "Pink Dye" + displayname + enchantmentstoadd + time;
			if (sub == 10)
				return "Lime Dye" + displayname + enchantmentstoadd + time;
			if (sub == 11)
				return "Dandelion Yellow Dye" + displayname + enchantmentstoadd + time;
			if (sub == 12)
				return "Light Blue Dye" + displayname + enchantmentstoadd + time;
			if (sub == 13)
				return "Magenta Dye" + displayname + enchantmentstoadd + time;
			if (sub == 14)
				return "Orange Dye" + displayname + enchantmentstoadd + time;
			if (sub == 15)
				return "Bone Meal" + displayname + enchantmentstoadd + time;
			return "Unknown Dye" + displayname + enchantmentstoadd + time;
		}
		if (id == 373) {	
			if (sub == 0)
				return "Water Bottle" + displayname + enchantmentstoadd + time;
			if (sub == 7)
				return "Clear Potion" + displayname + enchantmentstoadd + time;
			if (sub == 11)
				return "Diffuse Potion" + displayname + enchantmentstoadd + time;
			if (sub == 15)
				return "Thin Potion" + displayname + enchantmentstoadd + time;
			if (sub == 16)
				return "Awkward Potion" + displayname + enchantmentstoadd + time;
			if (sub == 23)
				return "Bungling Potion" + displayname + enchantmentstoadd + time;
			if (sub == 27)
				return "Smooth Potion" + displayname + enchantmentstoadd + time;
			if (sub == 31)
				return "Debonair Potion" + displayname + enchantmentstoadd + time;
			if (sub == 32)
				return "Thick Potion" + displayname + enchantmentstoadd + time;
			if (sub == 39)
				return "Charming Potion" + displayname + enchantmentstoadd + time;
			if (sub == 43)
				return "Refined Potion" + displayname + enchantmentstoadd + time;
			if (sub == 47)
				return "Sparkling Potion" + displayname + enchantmentstoadd + time;
			if (sub == 48)
				return "Potent Potion" + displayname + enchantmentstoadd + time;
			if (sub == 55)
				return "Rank Potion" + displayname + enchantmentstoadd + time;
			if (sub == 59)
				return "Acrid Potion" + displayname + enchantmentstoadd + time;
			if (sub == 63)
				return "Stinky Potion" + displayname + enchantmentstoadd + time;
			if (sub == 64)
				return "Mundane Potion" + displayname + enchantmentstoadd + time;
			if (sub == 8192)
				return "Mundane Potion" + displayname + enchantmentstoadd + time;
			
			Potion potion = Potion.fromItemStack(item);
						
			String potionname = "";
			
			if (potion.isSplash()) { 
				potionname = "Splash potion of ";
				} 
			else { 
				potionname = "Potion of ";
			}
			
			for (PotionEffect peffect : potion.getEffects()) {
				
				String potname = "";
				PotionEffectType peffecttype = peffect.getType();
				
				if (peffecttype.getName().equalsIgnoreCase("ABSORPTION")) potname = "Absorption";
				if (peffecttype.getName().equalsIgnoreCase("BLINDNESS")) potname = "Blindness";
				if (peffecttype.getName().equalsIgnoreCase("CONFUSION")) potname = "Nausea";
				if (peffecttype.getName().equalsIgnoreCase("DAMAGE_RESISTANCE")) potname = "Resistance";
				if (peffecttype.getName().equalsIgnoreCase("FAST_DIGGING")) potname = "Haste";
				if (peffecttype.getName().equalsIgnoreCase("FIRE_RESISTANCE")) potname = "Fire Resistance";
				if (peffecttype.getName().equalsIgnoreCase("HARM")) potname = "Instant Damage";
				if (peffecttype.getName().equalsIgnoreCase("HEAL")) potname = "Instant Health";
				if (peffecttype.getName().equalsIgnoreCase("HEALTH_BOOST")) potname = "Health Boost";
				if (peffecttype.getName().equalsIgnoreCase("HUNGER")) potname = "Hunger";
				if (peffecttype.getName().equalsIgnoreCase("INCREASE_DAMAGE")) potname = "Strength";
				if (peffecttype.getName().equalsIgnoreCase("INVISIBILITY")) potname = "Invisibility";
				if (peffecttype.getName().equalsIgnoreCase("JUMP")) potname = "Jump Boost";
				if (peffecttype.getName().equalsIgnoreCase("NIGHT_VISION")) potname = "Night Vision";
				if (peffecttype.getName().equalsIgnoreCase("POISON")) potname = "Poison";
				if (peffecttype.getName().equalsIgnoreCase("REGENERATION")) potname = "Regeneration";
				if (peffecttype.getName().equalsIgnoreCase("SATURATION")) potname = "Saturation";
				if (peffecttype.getName().equalsIgnoreCase("SLOW")) potname = "Slowness";
				if (peffecttype.getName().equalsIgnoreCase("SLOW_DIGGING")) potname = "Mining Fatigue";
				if (peffecttype.getName().equalsIgnoreCase("SPEED")) potname = "Swiftness";
				if (peffecttype.getName().equalsIgnoreCase("WATER_BREATHING")) potname = "Water Breathing";
				if (peffecttype.getName().equalsIgnoreCase("WEAKNESS")) potname = "Weakness";
				if (peffecttype.getName().equalsIgnoreCase("WITHER")) potname = "Wither";
				
				int min = 0;
				int sec = 0;
				
				int time1 = peffect.getDuration() / 20;
				
				while (time1 >= 60) {
					min++;
					time1 = time1 - 60;
				}
				sec = time1;
				
				String min1 = "";
				
				if (min < 10) {
					min1 = "0" + min;
				} else {
					min1 = "" + min;
				}
				
				String sec1 = "";
				
				if (sec < 10) {
					sec1 = "0" + sec;
				} else {
					sec1 = "" + sec;
				}
				
				potionname = potionname + "" + potname + " §b(§d" + (peffect.getAmplifier() + 1) + "§b) §7" + min1 + "§f:§7" + sec1 + " ";
				
			}
			
			return potionname;
			
		}
		if (id == 383) {
			if (sub == 1)
				return "Item Spawn Egg" + displayname + enchantmentstoadd + time;
			if (sub == 2)
				return "XP Orb Spawn Egg" + displayname + enchantmentstoadd + time;
			if (sub == 8)
				return "Lead knot Spawn Egg" + displayname + enchantmentstoadd + time;
			if (sub == 9)
				return "Painting Spawn Egg" + displayname + enchantmentstoadd + time;
			if (sub == 10)
				return "Arrow Spawn Egg" + displayname + enchantmentstoadd + time;
			if (sub == 11)
				return "Snowball Spawn Egg" + displayname + enchantmentstoadd + time;
			if (sub == 12)
				return "Ghast Fireball Spawn Egg" + displayname + enchantmentstoadd + time;
			if (sub == 13)
				return "Blaze Fireball Spawn Egg" + displayname + enchantmentstoadd + time;
			if (sub == 14)
				return "Ender Pearl Spawn Egg" + displayname + enchantmentstoadd + time;
			if (sub == 15)
				return "Eye of Ender Spawn Egg" + displayname + enchantmentstoadd + time;
			if (sub == 16)
				return "Splash Potion Spawn Egg" + displayname + enchantmentstoadd + time;
			if (sub == 17)
				return "Bottle o' Enchanting Spawn Egg" + displayname + enchantmentstoadd + time;
			if (sub == 18)
				return "Item Frame Spawn Egg" + displayname + enchantmentstoadd + time;
			if (sub == 19)
				return "Wither Skull Spawn Egg" + displayname + enchantmentstoadd + time;
			if (sub == 20)
				return "Primed TNT Spawn Egg" + displayname + enchantmentstoadd + time;
			if (sub == 21)
				return "Falling Block Spawn Egg" + displayname + enchantmentstoadd + time;
			if (sub == 22)
				return "Firework Spawn Egg" + displayname + enchantmentstoadd + time;
			if (sub == 40)
				return "Minecart (Command Block) Spawn Egg" + displayname + enchantmentstoadd + time;
			if (sub == 41)
				return "Boat Spawn Egg" + displayname + enchantmentstoadd + time;
			if (sub == 42)
				return "Minecart Spawn Egg" + displayname + enchantmentstoadd + time;
			if (sub == 43)
				return "Minecart (Chest) Spawn Egg" + displayname + enchantmentstoadd + time;
			if (sub == 44)
				return "Minecart (Furnace) Spawn Egg" + displayname + enchantmentstoadd + time;
			if (sub == 45)
				return "Minecart (TNT) Spawn Egg" + displayname + enchantmentstoadd + time;
			if (sub == 46)
				return "Minecart (Hopper) Spawn Egg" + displayname + enchantmentstoadd + time;
			if (sub == 47)
				return "Minecart (Spawner) Spawn Egg" + displayname + enchantmentstoadd + time;
			if (sub == 50)
				return "Creeper Spawn Egg" + displayname + enchantmentstoadd + time;
			if (sub == 51)
				return "Skeleton Spawn Egg" + displayname + enchantmentstoadd + time;
			if (sub == 52)
				return "Spider Spawn Egg" + displayname + enchantmentstoadd + time;
			if (sub == 53)
				return "Giant Spawn Egg" + displayname + enchantmentstoadd + time;
			if (sub == 54)
				return "Zombie Spawn Egg" + displayname + enchantmentstoadd + time;
			if (sub == 55)
				return "Slime Spawn Egg" + displayname + enchantmentstoadd + time;
			if (sub == 56)
				return "Ghast Spawn Egg" + displayname + enchantmentstoadd + time;
			if (sub == 57)
				return "Pigman Spawn Egg" + displayname + enchantmentstoadd + time;
			if (sub == 58)
				return "Enderman Spawn Egg" + displayname + enchantmentstoadd + time;
			if (sub == 59)
				return "Cave Spider Spawn Egg" + displayname + enchantmentstoadd + time;
			if (sub == 60)
				return "Silverfish Spawn Egg" + displayname + enchantmentstoadd + time;
			if (sub == 61)
				return "Blaze Spawn Egg";
			if (sub == 62)
				return "Magma Cube Spawn Egg" + displayname + enchantmentstoadd + time;
			if (sub == 63)
				return "Enderdragon Spawn Egg" + displayname + enchantmentstoadd + time;
			if (sub == 63)
				return "Wither Spawn Egg" + displayname + enchantmentstoadd + time;
			if (sub == 65)
				return "Bat Spawn Egg" + displayname + enchantmentstoadd + time;
			if (sub == 66)
				return "Witch Spawn Egg" + displayname + enchantmentstoadd + time;
			if (sub == 67)
				return "Endermite Spawn Egg" + displayname + enchantmentstoadd + time;
			if (sub == 90)
				return "Pig Spawn Egg" + displayname + enchantmentstoadd + time;
			if (sub == 91)
				return "Sheep Spawn Egg" + displayname + enchantmentstoadd + time;
			if (sub == 92)
				return "Cow Spawn Egg" + displayname + enchantmentstoadd + time;
			if (sub == 93)
				return "Chicken Spawn Egg" + displayname + enchantmentstoadd + time;
			if (sub == 94)
				return "Squid Spawn Egg" + displayname + enchantmentstoadd + time;
			if (sub == 95)
				return "Wolf Spawn Egg" + displayname + enchantmentstoadd + time;
			if (sub == 96)
				return "Mooshroom Spawn Egg" + displayname + enchantmentstoadd + time;
			if (sub == 97)
				return "Snow Golem Spawn Egg" + displayname + enchantmentstoadd + time;
			if (sub == 98)
				return "Oclot Spawn Egg" + displayname + enchantmentstoadd + time;
			if (sub == 99)
				return "Iron Golem Spawn Egg" + displayname + enchantmentstoadd + time;
			if (sub == 100)
				return "Horse Spawn Egg" + displayname + enchantmentstoadd + time;
			if (sub == 120)
				return "Villager Spawn Egg" + displayname + enchantmentstoadd + time;
			return "Unknown Spawn Egg" + displayname + enchantmentstoadd + time;
		}
		if (id == 397) {
			if (sub == 0)
				return "Skeleton Head" + displayname + enchantmentstoadd + time;
			if (sub == 1)
				return "Wither Skeleton Head" + displayname + enchantmentstoadd + time;
			if (sub == 2)
				return "Zombie Head" + displayname + enchantmentstoadd + time;
			if (sub == 3) {
				if (item.hasItemMeta()) {
					SkullMeta headmeta = (SkullMeta) item.getItemMeta();
					if (headmeta.hasOwner())
						return "Player §6(§c" + headmeta.getOwner() + "§6) §aHead" + displayname + enchantmentstoadd + time;
				}
				return "Player Head" + displayname + enchantmentstoadd + time;
			}
			if (sub == 4)
				return "Creeper Head" + displayname + enchantmentstoadd + time;
			return "Unknown Head" + displayname + enchantmentstoadd + time;
		}
		
		if (id == 403) {

			EnchantmentStorageMeta metatest = (EnchantmentStorageMeta) item.getItemMeta();

			if (metatest != null) {

				String enchantmentonbook = "Enchanted Book ";
				boolean firstenchant = true;

				int ARROW_DAMAGE = metatest
						.getStoredEnchantLevel(Enchantment.ARROW_DAMAGE);
				int ARROW_FIRE = metatest
						.getStoredEnchantLevel(Enchantment.ARROW_FIRE);
				int ARROW_INFINITE = metatest
						.getStoredEnchantLevel(Enchantment.ARROW_INFINITE);
				int ARROW_KNOCKBACK = metatest
						.getStoredEnchantLevel(Enchantment.ARROW_KNOCKBACK);
				int DAMAGE_ALL = metatest
						.getStoredEnchantLevel(Enchantment.DAMAGE_ALL);
				int DAMAGE_ARTHROPODS = metatest
						.getStoredEnchantLevel(Enchantment.DAMAGE_ARTHROPODS);
				int DAMAGE_UNDEAD = metatest
						.getStoredEnchantLevel(Enchantment.DAMAGE_UNDEAD);
				int DIG_SPEED = metatest
						.getStoredEnchantLevel(Enchantment.DIG_SPEED);
				int DURABILITY = metatest
						.getStoredEnchantLevel(Enchantment.DURABILITY);
				int FIRE_ASPECT = metatest
						.getStoredEnchantLevel(Enchantment.FIRE_ASPECT);
				int KNOCKBACK = metatest
						.getStoredEnchantLevel(Enchantment.KNOCKBACK);
				int LOOT_BONUS_BLOCKS = metatest
						.getStoredEnchantLevel(Enchantment.LOOT_BONUS_BLOCKS);
				int LOOT_BONUS_MOBS = metatest
						.getStoredEnchantLevel(Enchantment.LOOT_BONUS_MOBS);
				int LUCK = metatest.getStoredEnchantLevel(Enchantment.LUCK);
				int LURE = metatest.getStoredEnchantLevel(Enchantment.LURE);
				int OXYGEN = metatest.getStoredEnchantLevel(Enchantment.OXYGEN);
				int PROTECTION_ENVIRONMENTAL = metatest
						.getStoredEnchantLevel(Enchantment.PROTECTION_ENVIRONMENTAL);
				int PROTECTION_EXPLOSIONS = metatest
						.getStoredEnchantLevel(Enchantment.PROTECTION_EXPLOSIONS);
				int PROTECTION_FALL = metatest
						.getStoredEnchantLevel(Enchantment.PROTECTION_FALL);
				int PROTECTION_FIRE = metatest
						.getStoredEnchantLevel(Enchantment.PROTECTION_FIRE);
				int PROTECTION_PROJECTILE = metatest
						.getStoredEnchantLevel(Enchantment.PROTECTION_PROJECTILE);
				int SILK_TOUCH = metatest
						.getStoredEnchantLevel(Enchantment.SILK_TOUCH);
				int THORNS = metatest.getStoredEnchantLevel(Enchantment.THORNS);
				int WATER_WORKER = metatest
						.getStoredEnchantLevel(Enchantment.WATER_WORKER);

				if (ARROW_DAMAGE != 0) {
					if (firstenchant == true) {
						enchantmentonbook = enchantmentonbook + "§ePower: §c"
								+ ARROW_DAMAGE;
						firstenchant = false;
					} else {
						enchantmentonbook = enchantmentonbook
								+ " §b| §ePower: §c" + ARROW_DAMAGE;
					}
				}
				if (ARROW_FIRE != 0) {
					if (firstenchant == true) {
						enchantmentonbook = enchantmentonbook + "§eFlame: §c"
								+ ARROW_FIRE;
						firstenchant = false;
					} else {
						enchantmentonbook = enchantmentonbook
								+ " §b| §eFlame: §c" + ARROW_FIRE;
					}
				}
				if (ARROW_INFINITE != 0) {
					if (firstenchant == true) {
						enchantmentonbook = enchantmentonbook
								+ "§eInfinity: §c" + ARROW_INFINITE;
						firstenchant = false;
					} else {
						enchantmentonbook = enchantmentonbook
								+ " §b| §eInfinity: §c" + ARROW_INFINITE;
					}
				}
				if (ARROW_KNOCKBACK != 0) {
					if (firstenchant == true) {
						enchantmentonbook = enchantmentonbook + "§ePunch: §c"
								+ ARROW_KNOCKBACK;
						firstenchant = false;
					} else {
						enchantmentonbook = enchantmentonbook
								+ " §b| §ePunch: §c" + ARROW_KNOCKBACK;
					}
				}
				if (DAMAGE_ALL != 0) {
					if (firstenchant == true) {
						enchantmentonbook = enchantmentonbook
								+ "§eSharpess: §c" + DAMAGE_ALL;
						firstenchant = false;
					} else {
						enchantmentonbook = enchantmentonbook
								+ " §b| §eSharpess: §c" + DAMAGE_ALL;
					}
				}
				if (DAMAGE_ARTHROPODS != 0) {
					if (firstenchant == true) {
						enchantmentonbook = enchantmentonbook
								+ "§eArthropods: §c"
								+ DAMAGE_ARTHROPODS;
						firstenchant = false;
					} else {
						enchantmentonbook = enchantmentonbook
								+ " §b| §eArthropods: §c"
								+ DAMAGE_ARTHROPODS;
					}
				}
				if (DAMAGE_UNDEAD != 0) {
					if (firstenchant == true) {
						enchantmentonbook = enchantmentonbook + "§eSmite: §c"
								+ DAMAGE_UNDEAD;
						firstenchant = false;
					} else {
						enchantmentonbook = enchantmentonbook
								+ " §b| §eSmite: §c" + DAMAGE_UNDEAD;
					}
				}
				if (DIG_SPEED != 0) {
					if (firstenchant == true) {
						enchantmentonbook = enchantmentonbook
								+ "§eEfficiency: §c" + DIG_SPEED;
						firstenchant = false;
					} else {
						enchantmentonbook = enchantmentonbook
								+ " §b| §eEfficiency: §c" + DIG_SPEED;
					}
				}
				if (DURABILITY != 0) {
					if (firstenchant == true) {
						enchantmentonbook = enchantmentonbook
								+ "§eUnbreaking: §c" + DURABILITY;
						firstenchant = false;
					} else {
						enchantmentonbook = enchantmentonbook
								+ " §b| §eUnbreaking: §c" + DURABILITY;
					}
				}
				if (FIRE_ASPECT != 0) {
					if (firstenchant == true) {
						enchantmentonbook = enchantmentonbook
								+ "§eFire Aspect: §c" + FIRE_ASPECT;
						firstenchant = false;
					} else {
						enchantmentonbook = enchantmentonbook
								+ " §b| §eFire Aspect: §c" + FIRE_ASPECT;
					}
				}
				if (KNOCKBACK != 0) {
					if (firstenchant == true) {
						enchantmentonbook = enchantmentonbook
								+ "§eKnockback: §c" + KNOCKBACK;
						firstenchant = false;
					} else {
						enchantmentonbook = enchantmentonbook
								+ " §b| §eKnockback: §c" + KNOCKBACK;
					}
				}
				if (LOOT_BONUS_BLOCKS != 0) {
					if (firstenchant == true) {
						enchantmentonbook = enchantmentonbook + "§eFortune: §c"
								+ LOOT_BONUS_BLOCKS;
						firstenchant = false;
					} else {
						enchantmentonbook = enchantmentonbook
								+ " §b| §eFortune: §c" + LOOT_BONUS_BLOCKS;
					}
				}
				if (LOOT_BONUS_MOBS != 0) {
					if (firstenchant == true) {
						enchantmentonbook = enchantmentonbook + "§eLooting: §c"
								+ LOOT_BONUS_MOBS;
						firstenchant = false;
					} else {
						enchantmentonbook = enchantmentonbook
								+ " §b| §eLooting: §c" + LOOT_BONUS_MOBS;
					}
				}
				if (LUCK != 0) {
					if (firstenchant == true) {
						enchantmentonbook = enchantmentonbook + "§eLuck: §c"
								+ LUCK;
						firstenchant = false;
					} else {
						enchantmentonbook = enchantmentonbook
								+ " §b| §eLuck: §c" + LUCK;
					}
				}
				if (LURE != 0) {
					if (firstenchant == true) {
						enchantmentonbook = enchantmentonbook + "§eLure: §c"
								+ LURE;
						firstenchant = false;
					} else {
						enchantmentonbook = enchantmentonbook
								+ " §b| §eLure: §c" + LURE;
					}
				}
				if (OXYGEN != 0) {
					if (firstenchant == true) {
						enchantmentonbook = enchantmentonbook
								+ "§eRespiration: §c" + OXYGEN;
						firstenchant = false;
					} else {
						enchantmentonbook = enchantmentonbook
								+ " §b| §eRespiration: §c" + OXYGEN;
					}
				}
				if (PROTECTION_ENVIRONMENTAL != 0) {
					if (firstenchant == true) {
						enchantmentonbook = enchantmentonbook
								+ "§eProtection: §c" + PROTECTION_ENVIRONMENTAL;
						firstenchant = false;
					} else {
						enchantmentonbook = enchantmentonbook
								+ " §b| §eProtection: §c"
								+ PROTECTION_ENVIRONMENTAL;
					}
				}
				if (PROTECTION_EXPLOSIONS != 0) {
					if (firstenchant == true) {
						enchantmentonbook = enchantmentonbook
								+ "§eBlast Protection: §c"
								+ PROTECTION_EXPLOSIONS;
						firstenchant = false;
					} else {
						enchantmentonbook = enchantmentonbook
								+ " §b| §eBlast Protection: §c"
								+ PROTECTION_EXPLOSIONS;
					}
				}
				if (PROTECTION_FALL != 0) {
					if (firstenchant == true) {
						enchantmentonbook = enchantmentonbook
								+ "§eFall Protection: §c" + PROTECTION_FALL;
						firstenchant = false;
					} else {
						enchantmentonbook = enchantmentonbook
								+ " §b| §eFall Protection: §c"
								+ PROTECTION_FALL;
					}
				}
				if (PROTECTION_FIRE != 0) {
					if (firstenchant == true) {
						enchantmentonbook = enchantmentonbook
								+ "§eFire Protection: §c" + PROTECTION_FIRE;
						firstenchant = false;
					} else {
						enchantmentonbook = enchantmentonbook
								+ " §b| §eFire Protection: §c"
								+ PROTECTION_FIRE;
					}
				}
				if (PROTECTION_PROJECTILE != 0) {
					if (firstenchant == true) {
						enchantmentonbook = enchantmentonbook
								+ "§eProjectile Protection: §c"
								+ PROTECTION_PROJECTILE;
						firstenchant = false;
					} else {
						enchantmentonbook = enchantmentonbook
								+ " §b| §eProjectile Protection: §c"
								+ PROTECTION_PROJECTILE;
					}
				}
				if (SILK_TOUCH != 0) {
					if (firstenchant == true) {
						enchantmentonbook = enchantmentonbook
								+ "§eSilk Touch: §c" + SILK_TOUCH;
						firstenchant = false;
					} else {
						enchantmentonbook = enchantmentonbook
								+ " §b| §eSilk Touch: §c" + SILK_TOUCH;
					}
				}
				if (THORNS != 0) {
					if (firstenchant == true) {
						enchantmentonbook = enchantmentonbook + "§eThorns: §c"
								+ THORNS;
						firstenchant = false;
					} else {
						enchantmentonbook = enchantmentonbook
								+ " §b| §eThorns: §c" + THORNS;
					}
				}
				if (WATER_WORKER != 0) {
					if (firstenchant == true) {
						enchantmentonbook = enchantmentonbook
								+ "§eAqua Affinity: §c" + WATER_WORKER;
						firstenchant = false;
					} else {
						enchantmentonbook = enchantmentonbook
								+ " §b| §eAqua Affinity: §c" + WATER_WORKER;
					}
				}

				return enchantmentonbook + displayname + enchantmentstoadd + time;

			}

		}
		
		if (id == 417)
			return "Iron Horse Armour" + displayname + enchantmentstoadd + time;
		if (id == 418)
			return "Gold Horse Armour" + displayname + enchantmentstoadd + time;
		if (id == 419)
			return "Diamond Horse Armour" + displayname + enchantmentstoadd + time;
		if (id == 2256)	return "Disk 13 §b(§6Gold§b)" + displayname + enchantmentstoadd + time;
		if (id == 2257)	return "Disk Cat §b(§aGreen§b)" + displayname + enchantmentstoadd + time;
		if (id == 2258)	return "Disk Blocks §b(§cOrange§b)" + displayname + enchantmentstoadd + time;
		if (id == 2259)	return "Disk Chirp §b(§6G§b)" + displayname + enchantmentstoadd + time;
		if (id == 2260)	return "Disk Far §b(§aG§f/§eY§b)" + displayname + enchantmentstoadd + time;
		if (id == 2261)	return "Disk Mall §b(§6G§b)" + displayname + enchantmentstoadd + time;
		if (id == 2262)	return "Disk Mellohi §b(§5P§f/§9B§b)" + displayname + enchantmentstoadd + time;
		if (id == 2263)	return "Disk Stal §b(§0Black§b)" + displayname + enchantmentstoadd + time;
		if (id == 2264)	return "Disk Stard §b(§fWhite§b)" + displayname + enchantmentstoadd + time;
		if (id == 2265)	return "Disk Ward §b(§2G§7/§aG§b)" + displayname + enchantmentstoadd + time;
		if (id == 2266)	return "Disk 11 §b(§3Broken§b) §b(§0Black§b)" + displayname + enchantmentstoadd + time;
		if (id == 2267)	return "Disk Wait §b(§9Blue§b)" + displayname + enchantmentstoadd + time;
		
		String itemname = item.getType().name();
		
		itemname = itemname.replace("_", " ");

		itemname = itemname.toLowerCase();

		itemname = WordUtils.capitalize(itemname);
		
		return itemname + displayname + enchantmentstoadd + time;
		
	}
}
