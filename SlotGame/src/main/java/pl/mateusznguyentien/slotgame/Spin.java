package pl.mateusznguyentien.slotgame;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
@JsonPropertyOrder({"gameId","rno"})
@Data
public class Spin {
    private int gameId;
    private int rno;
    @JsonCreator
    public Spin(@JsonProperty("name") int gameID,@JsonProperty("rno") int rno){
        this.gameId = gameId;
        this.rno = rno;
    }

}