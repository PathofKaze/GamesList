package vttp2022.ssf.assessment.videosearch.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vttp2022.ssf.assessment.videosearch.service.SearchService;
import vttp2022.ssf.assessment.videosearch.models.Game;

@Controller
@RequestMapping(path="/", produces = MediaType.TEXT_HTML_VALUE)
public class SearchController {
    private Logger logger = LoggerFactory.getLogger(SearchController.class);

    @Autowired
    private SearchService GameSvc;

    @GetMapping("/search")
    public String searchResource(
        public String gameSearch(@RequestParam(name="gameName", required=true) String gameName, 
        @RequestParam(name="Results", value="10") Integer Results, Model model) {

        List<Game> theList= GameSvc.search(name, results);
                               
                if(theList.isEmpty()) return "No Games Found";
                
                else {
                    model.addAttribute("games", thelist);

         
        return "searchresults";
        }
    }
}

  