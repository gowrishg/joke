package in.kudu.joke.backend;

/** The object model for the data we are sending through endpoints */
public class JokeData {

    private String myData;

    public String getData() {
        return myData;
    }

    public void setData(String data) {
        myData = data;
    }
}