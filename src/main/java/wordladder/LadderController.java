package wordladder;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.apache.log4j.Logger;
@RestController
public class LadderController {

    private static Logger logger = Logger.getLogger(LadderController.class.getClass());
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();


    @RequestMapping("/hello")
    public Ladder Hello(@RequestParam(value="filename", defaultValue="smalldict1.txt") String name, @RequestParam(value="word1", defaultValue="cat") String word1,@RequestParam(value="word2", defaultValue="dog") String word2) {
        Wordladder wl = new Wordladder();
        try {
            logger.info("searching from " + word1 + " to " + word2);
            return wl.GetLadder(name, word1, word2);
        }catch(Exception e){
            logger.error("IOException occured");
            return new Ladder(1,"IOException occured", new String[]{});
        }

    }
}
