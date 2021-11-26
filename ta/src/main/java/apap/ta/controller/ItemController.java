package apap.ta.controller;

import apap.ta.restcontroller.ItemRestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
public class ItemController {

    @GetMapping(value="/list-item")
    private String listItem(
            HttpServletResponse response,
            Model model
    ) {
//        model.addAttribute("listItem", listItem);
        return "daftar-item";
    }


}
