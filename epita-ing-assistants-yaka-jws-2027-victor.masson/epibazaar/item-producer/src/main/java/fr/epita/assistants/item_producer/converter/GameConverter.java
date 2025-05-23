package fr.epita.assistants.item_producer.converter;
import  fr.epita.assistants.item_producer.data.model.*;
import  fr.epita.assistants.item_producer.domain.entity.*;
import static fr.epita.assistants.common.aggregate.ItemAggregate.ResourceType.*;
import static fr.epita.assistants.common.aggregate.ItemAggregate.*;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ApplicationScoped
public class GameConverter {



    public GameModel gtomodel(GameEntity ent)
    {
        ResourceType[][] matrix = ent.getMap();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < matrix.length; i++ ) {
            for (int j = 0; j < matrix[i].length; j++) {
                int count = 1;
                while (j + count < matrix[i].length && matrix[i][j] == matrix[i][j + count] && count<9) {
                    count++;
                }
                sb.append(count);
                sb.append((matrix[i][j].getItemInfo().getValue()));
                j += count - 1;
            }
            if (i < matrix.length-1) {
                sb.append(";");
            }
        }
        GameModel res = new GameModel();
        res.setMap(sb.toString());
        System.out.println(res.getMap());
        return res;

    }

    public GameEntity gtoentity(GameModel mod)
    {
        String data = mod.getMap();
        String[] rows = data.split(";");
        List<ResourceType[]> matrixList = new ArrayList<>();
        for ( String row : rows) {
            List<ResourceType> rowList = new ArrayList<>();
            String[] crow = row.split("(?<=\\G..)");
             for (String cell : crow) {
                 int i = cell.charAt(0) - '0';
                 for (int j = 0;j<i;j++)
                    {
                        rowList.add(getResource(cell.charAt(1)));
                    }

            }
             matrixList.add(rowList.toArray(new ResourceType[0]));
        }
        return new GameEntity(matrixList.toArray(new ResourceType[0][]));

    }



}
