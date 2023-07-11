package Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
@Getter @Setter @AllArgsConstructor
public class Player {

    private int id;

    private Team team;

    private String name;

    private String position;

    private Timestamp createdAt;


}
