package pl.mateusznguyentien.slotgame;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.ArrayList;
import java.util.Random;

@JsonPropertyOrder({"reels","spin","winnings"})
@Data
public class Game {
    private int gameId;
    private int[][] reels ;
    private int[] spin ;
    private double[] winnings;
    private int rno;
    private GameStatus status = GameStatus.INPROGRSS ;
    private ArrayList<Integer> spins= new ArrayList<>();
    private int sumspin;

    public Game(){


    }
    @JsonCreator
    public Game(@JsonProperty("reels") int[][] reels,@JsonProperty("spin") int[] spin,@JsonProperty("winnings") double[] winnings) {
        this.reels = reels;
        this.spin = spin;
        this.winnings = winnings;
        Random r =new Random();
        this.rno = r.nextInt(500);
    }
    public void spin(int nextspin){
        this.spins.add(nextspin);
        sumspin+=nextspin;
    }
    public int[][] getReelsResult(int rno){

        this.spins.add(rno);
        sumspin+=rno;
        int[][] reelsresult = new int[3][3];

        if(rno==0){
            for (int i = 0; i < 3; i++) {
                reelsresult[i][0] = reels[i][0];
                reelsresult[i][1] = reels[i][1];
                reelsresult[i][2] = reels[i][2];
            }
        }else {
            int spinindex = spin.length % rno;
            for (int i = 0; i < 3; i++) {
                int reelsindex = reels[i].length % spin[spinindex];
                reelsresult[i][0] = reels[i][reelsindex];
                reelsresult[i][1] = reels[i][reelsindex + 1];
                reelsresult[i][2] = reels[i][reelsindex + 2];
            }
        }

        return reelsresult;
    }

}

