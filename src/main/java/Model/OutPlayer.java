package Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@AllArgsConstructor
@Setter
@Getter
public class OutPlayer {

    private int id;


    private int playerId;


    private String reason;


    private Timestamp createdAt;


    private Player player;
}
