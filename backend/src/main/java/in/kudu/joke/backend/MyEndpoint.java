/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package in.kudu.joke.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import javax.inject.Named;

/** An endpoint class we are exposing */
@Api(
  name = "joke",
  version = "v1",
  namespace = @ApiNamespace(
    ownerDomain = "backend.joke.kudu.in",
    ownerName = "backend.joke.kudu.in",
    packagePath=""
  )
)
public class MyEndpoint {

    /** A simple endpoint method that takes a name and says Hi back */
    @ApiMethod(name = "tellAJoke")
    public JokeData tellAJoke() {

        kudu.in.joke_provider.JokeGenerator jokeGenerator = new kudu.in.joke_provider.JokeGenerator();
        JokeData response = new JokeData();
        response.setData(jokeGenerator.tellAJoke());

        return response;
    }

}
