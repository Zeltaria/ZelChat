package com.zeltaria.tasks;

import com.zeltaria.commands.ChatQuestion;
import com.zeltaria.main.Main;
import com.zeltaria.managers.SchedulerManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

public class ChatQuestionTimer extends BukkitRunnable{

    private int timer;
    private SchedulerManager sm;
    private int wait = 120;

    public ChatQuestionTimer(Main main, SchedulerManager sm) {
        this.sm = sm;
        int delay = main.getConfig().getInt("delay");
        timer = delay + wait;

    }

    @Override
    public void run() {
        if(timer == wait) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "chatquestion");
        }
        if(timer == 0){
            Bukkit.broadcastMessage(ChatColor.GOLD+"[ZelChat] Dommage, personne n'a trouvé le mot. Il fallait deviner le mot: "+ ChatColor.GREEN + ChatQuestion.word);
            ChatQuestion.on = 0;
            sm.startChatQuestionTimer();
            cancel();
        }
        timer--;

    }

}
