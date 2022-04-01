package vttp2022.ssf.assessment.videosearch.service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.list;
import java.util.ArrayList;

import javax.annotation.PostConstruct;

import com.jayway.jsonpath.PathNotFoundException;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import vttp2022.ssf.assessment.videosearch.models.Game;

@Service
public class SearchService {

    private Logger logger = LoggerFactory.getLogger(SearchService.class);

    private static final String OPENGAME_URL = "https://api.rawg.io/api/games";
    
    @value("${rawg.api.key}")
    private String apiKey;

    @PostConstruct
    public void init() {
        this.apiKey = System.getenv("apiKey");
    }

    public List<Game> search(String searchString, Integer count) {
        String searchUrl = UriComponentsBuilder
            .fromUriString(OPENGAME_URL)
            .queryParam("search", searchString)
            .queryParam("page_size", count)
            .queryParam("key", apiKey)
            .toUriString();  
      
      
        RequestEntity<Void> req = RequestEntity
            .get(SearchURL)
            .accept(MediaType.APPLICATION_JSON)
            .build();
    
    RestTemplate resttemplate = new RestTemplate();
        ResponseEntity<String> res = resttemplate.exchange(req, String.class);

    try {
            InputStream is = new ByteArrayInputStream(res.getBody().getBytes());
            JsonReader r = Json.createReader(is);
            JsonObject o = r.readObject();
            JsonArray ResultArray = o.getJsonArray("searchresults");
            System.out.println(">>> searchresults: "+ o);
            List<Game> games = new Arraylist<Game>();

            for(int i=0; i<ResultArray.size(); i++) {
                JsonObject JSobject = ResultArray.getJsonObject(i);
                Game game = new Game();
                game.setName(JSobject.getString("name"));
			    game.setBackgroundImage(JSobject.getString("background_image"));
			    game.setRating(Float.parseFloat(JSobject.get("rating").toString()));
                games.add(game);
            }
            catch (Exception ex) {
                logger.warn(ex.getMessage());

        return games;
    }
}