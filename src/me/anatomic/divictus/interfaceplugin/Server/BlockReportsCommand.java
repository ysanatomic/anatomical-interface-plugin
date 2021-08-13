package me.anatomic.divictus.interfaceplugin.Server;

import me.anatomic.divictus.interfaceplugin.network.ReportAbilityChange;
import me.anatomic.divictus.interfaceplugin.network.SetNoteRequest;
import me.anatomic.divictus.interfaceplugin.network.Websockets;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Arrays;

public class BlockReportsCommand implements CommandExecutor {

    Websockets wsC;
    public BlockReportsCommand(Websockets ws){
        wsC = ws;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // /blockreports AnatomicGod
        if (args.length < 1){
            return false;
        }
        else {
            String username = args[0];
            ReportAbilityChange note = new ReportAbilityChange(username, false);
            System.out.println(note.jsonObj);
            System.out.println(wsC);
            System.out.println(wsC.ws);
            if(wsC == null || wsC.ws == null || !wsC.runningSocket){
                sender.sendMessage("[*} There is no connection to the interface currently.");
                return true;
            }
            wsC.ws.sendText(note.jsonObj.toString());
            sender.sendMessage("[***] " + args[0] + " blocked from reporting!");
            return true;
        }
    }
}
