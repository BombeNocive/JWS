package fr.epita.assistants.item_producer.domain.service;
import java.io.*;
import java.util.*;

import  fr.epita.assistants.common.aggregate.ItemAggregate.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.*;
import fr.epita.assistants.item_producer.data.repository.*;
import fr.epita.assistants.item_producer.domain.entity.*;
import fr.epita.assistants.item_producer.converter.*;
import java.io.BufferedReader;
import java.io.IOException;

import static fr.epita.assistants.common.aggregate.ItemAggregate.ResourceType.*;

@ApplicationScoped
public class GameService {
    @Inject
    public GameRepository repo;
    @Inject
    GameConverter convert;

    public void resetrepo()
    {
        repo.resetDatabase();
    }


    public ResourceType[][] add(String file)
    {
        try {
        GameEntity s = new GameEntity(readFileToMatrix(file));
        repo.add(convert.gtomodel(s));
        return s.getMap();
        } catch (IOException e) {

            return null;
        }

    }

    public void add(GameEntity input)
    {
        repo.add(convert.gtomodel(input));
    }

    public ResourceType[][] getmap()
    {
        return convert.gtoentity(repo.getgame()).getMap();
    }


    public static ResourceType[][] readFileToMatrix(String filePath) throws IOException {
        List<ResourceType[]> matrixList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] row = line.split("(?<=\\G..)");
                 List<ResourceType> rowList = new ArrayList<>();
                for (String cell : row) {
                    if (!isValidCell(cell)) {

                        return null;
                    }

                    int i = cell.charAt(0) - '0';
                    for (int j = 0;j<i;j++)
                    {
                        rowList.add(getResource(cell.charAt(1)));
                    }
                }
                matrixList.add(rowList.toArray(new ResourceType[0]));
            }
        }

        if (matrixList.isEmpty()) {

            return null;
        }

        return matrixList.toArray(new ResourceType[0][]);
    }

    private static boolean isValidCell(String cell) {
        return cell.matches("[123456789][RWGOM]");
    }

    public boolean isgame()
    {
        return repo.started();
    }

    public void change(GameEntity map)
    {
        repo.change(convert.gtomodel(map).getMap());
    }



}


