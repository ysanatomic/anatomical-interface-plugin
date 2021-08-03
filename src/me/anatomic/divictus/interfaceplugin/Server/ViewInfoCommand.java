package me.anatomic.divictus.interfaceplugin.Server;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Arrays;

public class ViewInfoCommand implements CommandExecutor{

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 0){
//            Bukkit.getServer().getPlayer(sender.getName()).sendMessage("Correct usage: /pinfo <player>");
            return false;
        }
        else {
            System.out.println(Arrays.toString(args));
            String url = "http://localhost:8000/player/"+args[0]+"/";
            String message = "Click this to get notes about "+args[0];
            Bukkit.getServer().dispatchCommand(
                    Bukkit.getConsoleSender(),
                    "tellraw " + sender.getName() +
                            " {text:\"" + message + "\",clickEvent:{action:open_url,value:\"" +
                            url + "\"}, \"color\": \"green\"}");
            return true;
        }

    }


}
