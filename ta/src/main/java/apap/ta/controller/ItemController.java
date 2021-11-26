package apap.ta.controller;

import apap.ta.restcontroller.ItemRestController;
import apap.ta.service.ItemRestServiceImpl;
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

    // ini controllernya yang akan nge return htmlnya kak
    @GetMapping(value="/list-item")
    private String listItem(
            Model model
    ) {
        //aku bingung gimana cara manggil method di restcontroller
//        model.addAttribute("listItem", listItem);
        return "daftar-item";
    }


}
