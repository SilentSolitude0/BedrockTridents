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

        if (!(e.getDamager() instanceof Trident trident)) return;

        ItemStack item = trident.getItem();
        if (!item.containsEnchantment(Enchantment.IMPALING)) return;

        if (!(e.getEntity() instanceof LivingEntity)) return;

        int level = item.getEnchantmentLevel(Enchantment.IMPALING);

        boolean inRain = e.getEntity().getLocation().getWorld().hasStorm() &&
                e.getEntity().getLocation().getBlockY() >= e.getEntity().getLocation().getWorld().getHighestBlockYAt(e.getEntity().getLocation());
        boolean inWater = e.getEntity().isInWater();

        if (inRain || inWater) {
            double extraDamage = level * main.getDamagePerLevel();
            e.setDamage(e.getDamage() + extraDamage);
        }
    }
}
