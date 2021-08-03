package me.anatomic.divictus.interfaceplugin.Server;

import com.neovisionaries.ws.client.WebSocket;
import me.anatomic.divictus.interfaceplugin.network.NoteRequest;
import me.anatomic.divictus.interfaceplugin.network.Websockets;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NewNoteCommand implements CommandExecutor {

    Websockets wsC;
    public NewNoteCommand(Websockets ws){
        wsC = ws;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        // /newnote AnatomicGod <....>
        String username = args[0];
        ArrayList<String> argWords = new ArrayList<String>(Arrays.asList(args));
        System.out.println(argWords);
        argWords.remove(0);
        String noteContent = String.join(" ", argWords);
        NoteRequest note = new NoteRequest(username, Bukkit.getServer().getPlayer(sender.getName()).getUniqueId().toString(),
                noteContent);
        System.out.println(note.jsonObj);
        System.out.println(wsC);
        System.out.println(wsC.ws);
        if(wsC == null || wsC.ws == null || !wsC.runningSocket){
            sender.sendMessage("[*} There is no connection to the interface currently.");
            return true;
        }
        wsC.ws.sendText(note.jsonObj.toString());
        sender.sendMessage("[***] Note to player added!");
        return true;
    }
}
