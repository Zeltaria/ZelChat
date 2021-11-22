package com.zeltaria.main;

import com.zeltaria.commands.ChatQuestion;
import com.zeltaria.events.PlayerChat;
import com.zeltaria.tasks.SchedulerManager;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public SchedulerManager sm = new SchedulerManager(this);
    public Economy economy = null;


    @Override
    public void onEnable(){
        saveDefaultConfig();
        System.out.println("ZelChat à bien été chargé!");
        this.getCommand("chatquestion").setExecutor(new ChatQuestion(this));
        Bukkit.getPluginManager().registerEvents(new PlayerChat(this, sm),this);
        sm.startChatQuestionTimer();
    }

    @Override
    public void onDisable(){

    }

    public boolean setupEconomy(){
        RegisteredServiceProvider<Economy> eco = getServer().getServicesManager().getRegistration(Economy.class);
        if(eco != null){
            economy = eco.getProvider();
        }
        return economy != null;
    }


}
