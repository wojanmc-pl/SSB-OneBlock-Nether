package com.bgsoftware.ssboneblock.actions;

import com.bgsoftware.ssboneblock.error.ParsingException;
import com.bgsoftware.ssboneblock.factory.BlockOffsetFactory;
import com.bgsoftware.superiorskyblock.api.island.Island;
import com.bgsoftware.superiorskyblock.api.wrappers.BlockOffset;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class CommandAction extends Action {

    private final List<String> commands;

    private CommandAction(List<String> commands, BlockOffset offsetPosition) {
        super(offsetPosition);
        this.commands = commands;
    }

    @Override
    public void run(Location location, Island island, Player player) {
        if (offsetPosition != null)
            location = offsetPosition.applyToLocation(location);

        for (String command : commands) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command
                    .replace("{player}", player == null ? "null" : player.getName())
                    .replace("{world}", location.getWorld().getName())
                    .replace("{x}", location.getBlockX() + "")
                    .replace("{y}", location.getBlockY() + "")
                    .replace("{z}", location.getBlockZ() + "")
            );
        }
    }

    public static Optional<Action> fromJson(JsonObject jsonObject) throws ParsingException {
        JsonElement jsonElement = jsonObject.get("execute");
        List<String> commands = new ArrayList<>();

        if (jsonElement instanceof JsonArray) {
            ((JsonArray) jsonElement).forEach(_jsonElement -> commands.add(_jsonElement.getAsString()));
        } else {
            commands.add(jsonElement.getAsString());
        }

        return commands.isEmpty() ? Optional.empty() :
                Optional.of(new CommandAction(commands, BlockOffsetFactory.createOffset(jsonObject.get("offset").getAsString())));
    }

}
