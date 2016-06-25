package in.kudu.jokerlib;

/**
 * Created by gowrishg on 21/6/16.
 */
public class JokeGenerator {

    String[] fixedJokes = new String[]{
            "Today a man knocked on my door and asked for a small donation towards the local swimming pool. I gave him a glass of water",
            "Light travels faster than sound. This is why some people appear bright until you hear them speak",
            "Half the people you know are below average",
            "I am a nobody, nobody is perfect, therefore I am perfect",
            "3 kinds of people: those who can count and those who can’t."
    };


    private static int sLastJokeId = 0;

    /**
     * Return different joke everytime when this method is called
     * @return
     */
    public String tellAJoke() {
        if (sLastJokeId == fixedJokes.length) {
            sLastJokeId = 0;
        }
        return fixedJokes[sLastJokeId++];
    }
}
