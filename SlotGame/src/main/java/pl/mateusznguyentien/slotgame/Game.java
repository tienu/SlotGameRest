package pl.mateusznguyentien.slotgame;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Random;

@JsonPropertyOrder({"reels","spin","winnings"})
@Data
public class Game {
    private int gameId;
    @ToString.Exclude private int[][] reels ;
    @ToString.Exclude private int[] spin ;
    @ToString.Exclude private double[] winnings;
    private int rno;
    private GameStatus status = GameStatus.INPROGRSS ;
    @ToString.Exclude private ArrayList<Integer> spins= new ArrayList<>();
    @ToString.Exclude private int[][] reelsresult;
    private double lastwin;

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

    public String reelsResult(int rno){

        this.spins.add(rno);
        this.rno+=rno;
        if(this.rno>500){
            this.rno-=500;
        }
        reelsresult = new int[3][3];

        if(rno==0){
            for (int i = 0; i < 3; i++) {
                reelsresult[i][0] = reels[i][0];
                reelsresult[i][1] = reels[i][1];
                reelsresult[i][2] = reels[i][2];
            }
        }else {
            int spinindex = spin.length % this.rno;
            for (int i = 0; i < 3; i++) {

                int reelsindex = reels[i].length % spin[spinindex-1];

                reelsresult[i][0] = reels[i][reelsindex - 1];
                reelsresult[i][1] = reels[i][reelsindex ];
                reelsresult[i][2] = reels[i][reelsindex + 1];

            }
        }

        double win = 0;

        for (int i = 0; i < 3; i++){
            if (reelsresult[0][i]==reelsresult[1][i]&&reelsresult[1][i]==reelsresult[2][i]){
                win+=this.winnings[reelsresult[0][i]];
            }

        }
        if(reelsresult[0][0]==reelsresult[1][1]&&reelsresult[1][1]==reelsresult[2][2]){
            win+=this.winnings[reelsresult[0][0]];
        }
        if(reelsresult[0][2]==reelsresult[1][1]&&reelsresult[1][1]==reelsresult[2][0]){
            win+=this.winnings[reelsresult[0][2]];
        }
        this.lastwin=win;

        String result = "[";
        for (int i = 0; i < 3; i++) {
            result+="\t["+this.reelsresult[i][0]+", "+this.reelsresult[i][1]+", "+this.reelsresult[i][2]+"],\n";
        }
        result += "],\n\twin: "+win;
        return  result;
    }



}

