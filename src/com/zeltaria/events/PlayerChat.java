package com.zeltaria.events;

import com.zeltaria.commands.ChatQuestion;
import com.zeltaria.main.Main;
import com.zeltaria.tasks.SchedulerManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerChat implements Listener {

    private Main main;
    private SchedulerManager sm;

    public PlayerChat(Main main, SchedulerManager sm) {
        this.main = main;
        this.sm = sm;
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e){
        if( ChatQuestion.on == 1){
            ChatQuestion.p = e.getPlayer();
            if(e.getMessage().equals(ChatQuestion.word)){
                sm.stopChatQuestionTimer();
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        Bukkit.broadcastMessage(ChatColor.GOLD+"[ZelChat] Bravo à "+ ChatColor.GREEN + e.getPlayer().getDisplayName() + ChatColor.GOLD +" qui a donné la bonne réponse qui était: "+ ChatColor.GREEN +ChatQuestion.word);
                    }
                }.runTaskLater(main, 1L);
                ChatQuestion.on = 0;
                if(main.setupEconomy()){
                    main.economy.depositPlayer(ChatQuestion.p, main.getConfig().getDouble("reward"));
                }
                sm.startChatQuestionTimer();
            }
        }
    }
}
