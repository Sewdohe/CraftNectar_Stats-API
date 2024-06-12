package com.example.examplemod;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.UUID;



//public class PlayerAdapter {
//    public PlayerData read(JsonReader reader, PlayerData playerData) throws IOException {
//        reader.beginObject();
//        while (reader.hasNext()) {
//            String name = reader.nextName();
//            if (name.equals("playerName")) {
//                playerData.playerName = reader.nextString();
//            } else if (name.equals("uuid")) {
//                playerData.uuid = reader.nextString();
//            } else if (name.equals("totalLevels")) {
//                playerData.totalLevels = reader.nextInt();
//            } else {
//                reader.skipValue();
//            }
//        }
//        reader.endObject();
//        return playerData;
//    }
//
//    public void write(JsonWriter writer, PlayerData value) throws IOException {
//        writer.beginObject();
//        writer.name("playerName");
//        writer.value(value.playerName);
//        writer.name("uuid");
//        writer.value(value.uuid);
//        writer.name("totalLevels");
//        writer.value(value.totalLevels);
//        writer.endObject();
//    }
//}
