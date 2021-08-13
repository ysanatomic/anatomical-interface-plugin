package me.anatomic.divictus.interfaceplugin.Server;

import com.avaje.ebeaninternal.server.query.BackgroundIdFetch;
import me.anatomic.divictus.interfaceplugin.network.GetNotesRequest;
import me.anatomic.divictus.interfaceplugin.network.Websockets;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Arrays;

public class ViewInfoCommand implements CommandExecutor{

    Websockets wsC;
    public ViewInfoCommand(Websockets ws){
        wsC = ws;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 0){
//            Bukkit.getServer().getPlayer(sender.getName()).sendMessage("Correct usage: /pinfo <player>");
            return false;
        }
        else {
            Integer page = 1;
            if(args.length < 2){
                page = 1;
            } else {
                try{
                    page = Integer.parseInt(args[1]);
                } catch(Exception e){
                    page = 1;
                }
            }
            GetNotesRequest request = new GetNotesRequest(args[0], Bukkit.getServer().getPlayer(sender.getName()).getUniqueId().toString(), page);
            wsC.ws.sendText(request.jsonObj.toString());

            System.out.println(Arrays.toString(args));
            String url = "http://localhost:8000/player/"+args[0]+"/";
            String message = "Click this to get all notes about "+args[0];
            Bukkit.getServer().dispatchCommand(
                    Bukkit.getConsoleSender(),
                    "tellraw " + sender.getName() +
                            " {text:\"" + message + "\",clickEvent:{action:open_url,value:\"" +
                            url + "\"}, \"color\": \"green\"}");
            return true;
        }

    }


}
