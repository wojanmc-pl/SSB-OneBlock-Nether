package com.bgsoftware.ssboneblock.commands;

import com.bgsoftware.ssboneblock.NetherBlockModule;
import org.bukkit.command.CommandSender;

import java.util.List;

public interface ICommand {

    String getLabel();

    String getUsage();

    String getPermission();

    String getDescription();

    int getMinArgs();

    int getMaxArgs();

    void perform(NetherBlockModule plugin, CommandSender sender, String[] args);

    List<String> tabComplete(NetherBlockModule plugin, CommandSender sender, String[] args);

}
