package fr.epita.assistants.common.api.response;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.testcontainers.shaded.org.checkerframework.framework.qual.IgnoreInWholeProgramInference;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MoveResponse {
   int posX;
    int posY;
}

