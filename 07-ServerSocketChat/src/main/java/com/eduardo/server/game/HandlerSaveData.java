package com.eduardo.server.game;

import com.eduardo.entity.User;
import com.eduardo.model.ModelUser;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

public class HandlerSaveData extends Thread {
    
    private Map<UUID, User> users;
    
    public HandlerSaveData(Map<UUID, User> users) {
        this.users = users;
    }
    
    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (input.equalsIgnoreCase("close")) {
            ModelUser modelUser = new ModelUser();
            modelUser.open();
            modelUser.saveBatch(users);
            modelUser.close();
        }
        System.exit(0);
    }
    
}
