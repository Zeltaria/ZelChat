package com.zeltaria.commands;

import com.zeltaria.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ChatQuestion implements CommandExecutor {

    private static Main main;
    public static int on = 0;
    public static Player p;
    public static String word;

    public ChatQuestion(Main main) {
        ChatQuestion.main = main ;
    }

    private static String randomWord(){
        List<String> list = main.getConfig().getStringList("mots");
        String rWord;
        int max = list.size();
        Random rand = new Random();
        rWord = list.get(rand.nextInt(max));
        return rWord;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        List<String> wordRandom = new ArrayList<String>();
        if(sender instanceof Player){
            Player p = (Player) sender;
            if(p.hasPermission("Zelchat.chatquestion")){
                if(on == 0){
                    startGame(wordRandom);
                }
                else{
                    p.sendMessage(ChatColor.RED+"Une partie est déjà en cours!");
                }
            }
            else{
                p.sendMessage(ChatColor.RED + "Vous n'avez pas la permission d'utiliser cette commande!");
            }
        }
        else{
            if(on == 0){
                startGame(wordRandom);
            }
            else{
                System.out.println(ChatColor.RED+"Une partie est déjà en cours!");
            }
        }
        return false;
    }

    private void startGame(List<String> wordRandom){
        word = randomWord();
        for(char ch: word.toCharArray()){
            wordRandom.add(String.valueOf(ch));
        }
        Collections.shuffle(wordRandom);
        String wordShuffle = String.join(", ", wordRandom);
        Bukkit.broadcastMessage(ChatColor.GOLD + "[ZelChat] Quel est le mot :" + ChatColor.GREEN + wordShuffle + ChatColor.GOLD+" ?");
        on = 1;
    }
}
