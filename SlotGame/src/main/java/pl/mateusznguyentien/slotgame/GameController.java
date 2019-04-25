package pl.mateusznguyentien.slotgame;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

@RestController
@RequestMapping(value = "/")
public class GameController {
    LinkedList<Game> games = new LinkedList();

    @RequestMapping(value = "/startGame", method = RequestMethod.GET)
    public  ResponseEntity<String> startGame() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Game game = mapper.readValue(new File("config.json"), Game.class);
        int opened=0;

        for (Game gameiterator:games ){
            if (gameiterator.getStatus()==GameStatus.INPROGRSS){
                opened++;
            }
        }

        if(opened<20) {
            games.add(game);
            game.setGameId(games.size());
            // return game.getGameId() + "";
            System.out.println(game.toString() + "");
            Spin spin =  new Spin(3,30);
            String result = "{\n" +
                    "   status: \"OK\",\n" +
                    "   gameId: "+game.getGameId()+",\n" +
                    "   rno: "+game.getRno()+"\n" +
                    "}";
            return new ResponseEntity<>(result,HttpStatus.OK);
        }

        return new ResponseEntity<>("some response",HttpStatus.OK); //TODO error handler

    }



    @RequestMapping(value = "/spin", method = RequestMethod.POST)
    public ResponseEntity<String> spin(@RequestBody Spin spin) {

        Game game;

        for (Game gameiterator:games ){
            if (gameiterator.getGameId()==spin.getGameId()){

                game = gameiterator;
                game.reelsResult(spin.getRno());
                String result = "{\n" +
                        "   status: \"OK\" ,\n" +
                        "   gameId: "+game.getGameId()+",\n" +
                        "   rno: "+game.getRno()+"\n" +
                        "   symbols: [\n" +
                        "   "+game.getReelsresult()+
                        "   win: "+game.getLastwin()+"\n" +
                        "}";
                return new ResponseEntity<>(result,HttpStatus.OK);
            }
        }



        return new ResponseEntity<>("some response",HttpStatus.INTERNAL_SERVER_ERROR); // TODO error handler
    }

    @RequestMapping(value = "/endGame", method = RequestMethod.POST)
    public ResponseEntity<String> endGame(@RequestParam(value = "gameId") int gameId) {
        String result;
        for (Game gameiterator:games ){
            if (gameiterator.getGameId()==gameId){
                gameiterator.setStatus(GameStatus.COMPLETE);

                result = "{\n" +
                        "   status: \"OK\" ,\n" +
                        "   gameId: "+gameiterator.getGameId()+",\n" +
                        "   rno: "+gameiterator.getRno()+"\n" +
                        "}";
                return new ResponseEntity<>(result,HttpStatus.OK);
            }
        }

        result = "{\n" +
                "   status: \"ERROR\" ,\n" +
                "}";
        return new ResponseEntity<>(result,HttpStatus.OK);  //TODO error handler
    }

    @RequestMapping(value = "/sessions", method = RequestMethod.GET)
    public ResponseEntity<LinkedList<Game>> sessions(){
        return new ResponseEntity<>(games,HttpStatus.OK);
    }
    //TODO sessions settings
}

