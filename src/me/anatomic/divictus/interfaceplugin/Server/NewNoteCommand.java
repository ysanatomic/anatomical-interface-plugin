package me.anatomic.divictus.interfaceplugin.Server;

import me.anatomic.divictus.interfaceplugin.network.SetNoteRequest;
import me.anatomic.divictus.interfaceplugin.network.Websockets;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;

public class NewNoteCommand implements CommandExecutor {

    Websockets wsC;
    public NewNoteCommand(Websockets ws){
        wsC = ws;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // /newnote AnatomicGod <....>
        if (args.length < 2){
            return false;
        }
        else {
            if(wsC.ws != null){
                String username = args[0];
                ArrayList<String> argWords = new ArrayList<String>(Arrays.asList(args));
                argWords.remove(0);
                String noteContent = String.join(" ", argWords);
                SetNoteRequest note = new SetNoteRequest(username, Bukkit.getServer().getPlayer(sender.getName()).getUniqueId().toString(),
                        noteContent);
                if(wsC == null || wsC.ws == null || !wsC.runningSocket){
                    sender.sendMessage("[*} There is no connection to the interface currently.");
                    return true;
                }
                wsC.ws.sendText(note.jsonObj.toString());
                sender.sendMessage("[***] Note to " + args[0] + " added!");

                for(Player p: Bukkit.getOnlinePlayers()){
                    if(p.hasPermission("divictusinterface.playerinfo")){
                        Bukkit.getServer().dispatchCommand(
                                Bukkit.getConsoleSender(),
                                "tellraw " + p.getName() +
                                        " {text:\"==============================" + "\", \"color\": \"red\", \"bold\": \"true\"}");
                        Bukkit.getServer().dispatchCommand(
                                Bukkit.getConsoleSender(),
                                "tellraw " + p.getName() +
                                        " {text:\"NEW NOTE OF " + username + ".\", \"color\": \"red\", \"bold\": \"true\"}");
                        Bukkit.getServer().dispatchCommand(
                                Bukkit.getConsoleSender(),
                                "tellraw " + p.getName() +
                                        " {text:\"MADE BY " + sender.getName() + ".\", \"color\": \"green\", \"bold\": \"true\"}");
                        Bukkit.getServer().dispatchCommand(
                                Bukkit.getConsoleSender(),
                                "tellraw " + p.getName() +
                                        " {text:\"NOTE: " + noteContent + ".\", \"color\": \"blue\", \"bold\": \"true\"}");
                        Bukkit.getServer().dispatchCommand(
                                Bukkit.getConsoleSender(),
                                "tellraw " + p.getName() +
                                        " {text:\"==============================" + "\", \"color\": \"red\", \"bold\": \"true\"}");

                    }

                }
            }
            else{
                Bukkit.getServer().dispatchCommand(
                        Bukkit.getConsoleSender(),
                        "tellraw " + sender.getName() +
                                " {text:\"" + "No connection." + "\", \"color\": \"red\"}");
            }


            return true;
        }
    }
}
