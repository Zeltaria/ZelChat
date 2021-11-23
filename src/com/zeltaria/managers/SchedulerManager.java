package com.zeltaria.managers;

import com.zeltaria.main.Main;
import com.zeltaria.tasks.ChatQuestionTimer;

public class SchedulerManager {

    private final Main main;
    public ChatQuestionTimer task;

    public SchedulerManager(Main main) {
        this.main = main;
    }

    public void startChatQuestionTimer(){
        task = new ChatQuestionTimer(main, this);
        task.runTaskTimer(main, 0, 20);
    }

    public void stopChatQuestionTimer(){
        task.cancel();
    }
}
