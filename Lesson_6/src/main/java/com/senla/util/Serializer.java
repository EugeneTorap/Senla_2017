package com.senla.util;

import com.senla.entity.Entity;
import org.apache.log4j.Logger;
import java.io.*;
import java.util.List;

public class Serializer {
    private final static Logger LOGGER = Logger.getLogger(Serializer.class);

    public void save(List<? extends Entity> entities, String path) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
            oos.writeObject(entities);
        } catch (IOException e) {
            LOGGER.error("IOException");
        }
    }

    public Object load(String path) {
        while (true) {
            try (ObjectInputStream oos = new ObjectInputStream(new FileInputStream(path))) {
                return oos.readObject();
            } catch (ClassNotFoundException e) {
                LOGGER.error("ClassNotFoundException");
            } catch (IOException e) {
                LOGGER.error("File not found");
                path = Input.nextLine("Input path: ");
                continue;
            }
            break;
        }
        return null;
    }
}