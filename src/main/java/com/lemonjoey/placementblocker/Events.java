package com.lemonjoey.placementblocker;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.jetbrains.annotations.NotNull;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.lemonjoey.placementblocker.Main.plugin;

public class Events implements Listener {

    List<Material> bannedItems = Arrays.asList(Material.TRAPPED_CHEST, Material.CHEST, Material.DRAGON_EGG, Material.PLAYER_HEAD, Material.ZOMBIE_HEAD, Material.CREEPER_HEAD, Material.DRAGON_HEAD, Material.SKELETON_SKULL, Material.WITHER_SKELETON_SKULL, Material.ENDER_CHEST, Material.CAMPFIRE, Material.SOUL_CAMPFIRE, Material.BEACON);

    @EventHandler
    public void onBlockPlaceEvent(@NotNull BlockPlaceEvent event) {
        if (!event.getBlock().getWorld().getName().equals("event")) return;
        if (event.getPlayer().hasPermission("placementblocker.bypass")) return;
        if (bannedItems.contains(event.getBlock().getType())) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(Component
                    .text("To optimise the FPS for all players you cannot place a ")
                    .color(TextColor.color(16733525))
                    .append(Component.selector(String.valueOf(event.getBlock().getType()))
                            .color(TextColor.color(16755200))
                            .append(Component.text(" here!")
                                    .color(TextColor.color(16733525)))));
        }
    }

    List<Material> otherbannedItems = Arrays.asList(Material.ITEM_FRAME, Material.GLOW_ITEM_FRAME, Material.ARMOR_STAND, Material.DRAGON_HEAD, Material.PLAYER_HEAD, Material.ZOMBIE_HEAD, Material.CREEPER_HEAD, Material.SKELETON_SKULL, Material.WITHER_SKELETON_SKULL, Material.MINECART, Material.OAK_BOAT, Material.CHEST_MINECART, Material.FURNACE_MINECART, Material.TNT_MINECART, Material.HOPPER_MINECART, Material.SPRUCE_BOAT, Material.BIRCH_BOAT, Material.JUNGLE_BOAT, Material.ACACIA_BOAT, Material.DARK_OAK_BOAT, Material.END_CRYSTAL, Material.FLINT_AND_STEEL);

    @EventHandler
    public void OnInteract(@NotNull PlayerInteractEvent event) {
        Player placer = event.getPlayer();
        if (!event.getAction().isRightClick()) return;
        if (!placer.getWorld().getName().equals("event")) return;
        if (event.getPlayer().hasPermission("placementblocker.bypass")) return;
        if (otherbannedItems.contains(Objects.requireNonNull(event.getItem()).getType())){
            event.setCancelled(true);
            event.getPlayer().sendMessage(Component
                    .text("To optimise the FPS for all players you cannot place a ")
                    .color(TextColor.color(16733525))
                    .append(Component.selector(String.valueOf(event.getMaterial()))
                            .color(TextColor.color(16755200))
                            .append(Component.text(" here!")
                                    .color(TextColor.color(16733525)))));
        }
    }
}



