package vttp2022.ssf.assessment.videosearch.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vttp2022.ssf.assessment.videosearch.service.SearchService;
import vttp2022.ssf.assessment.videosearch.models.Game;

@Controller
@RequestMapping(path="/")
public class SearchController {

    @Autowired
    private SearchService GameSvc;

    @GetMapping(path="/search")

        public String gameSearch(@RequestParam(name="gameName", required=true) String gameName, 
        @RequestParam(name="Results", value="10") Integer Results, Model model) {

        List<Game> theList= GameSvc.search(gameName, Results);
                               
                if (theList.isEmpty()) { 
                
                return "No Games Found";
                
                } else {
                    model.addAttribute("games", theList);

         
        return "searchresults";
        }
    }
}

  