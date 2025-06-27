package me.solitude.bedrocktridents;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Trident;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

public class ImpaleListener implements Listener {

    @EventHandler
    public void onTridentHit(EntityDamageByEntityEvent e) {
        Main main = Main.getInstance();

        LivingEntity target = (e.getEntity() instanceof LivingEntity) ? (LivingEntity) e.getEntity() : null;
        if (target == null) return;

        ItemStack weapon = null;
        int level = 0;

        if (e.getDamager() instanceof Trident trident) {
            weapon = trident.getItem();
        }
        else if (e.getDamager() instanceof LivingEntity attacker) {
            weapon = attacker.getEquipment().getItemInMainHand();
        }

        if (weapon == null || !weapon.containsEnchantment(Enchantment.IMPALING)) return;
        level = weapon.getEnchantmentLevel(Enchantment.IMPALING);

        boolean inRain = target.getLocation().getWorld().hasStorm() &&
                target.getLocation().getBlockY() >= target.getLocation().getWorld().getHighestBlockYAt(target.getLocation());
        boolean inWater = target.isInWater();

        if (inRain || inWater) {
            double extraDamage = level * main.getDamagePerLevel();
            e.setDamage(e.getDamage() + extraDamage);

            if (main.isDebugMode())
                main.getServer().broadcastMessage("[DEBUG] Extra impaling damage applied: " + extraDamage +
                        " (Level: " + level + ", InWater: " + inWater + ", InRain: " + inRain + ")");
        }
    }
}
