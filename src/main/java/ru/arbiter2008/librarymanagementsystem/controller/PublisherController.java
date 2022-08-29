package ru.arbiter2008.librarymanagementsystem.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.arbiter2008.librarymanagementsystem.model.Publisher;
import ru.arbiter2008.librarymanagementsystem.service.PublisherService;

@Controller
@RequiredArgsConstructor
public class PublisherController {

    private final PublisherService publisherService;

    @RequestMapping("/publishers")
    public String findAllPublishers(Model model) {
        model.addAttribute("publishers", publisherService.findAllPublishers());

        return "publisher/list-publishers";
    }

    @RequestMapping("/publisher/{id}")
    public String findPublisherById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("publisher", publisherService.findPublisherById(id));

        return "publisher/list-publisher";
    }

    @GetMapping("/addPublisher")
    public String showCreateForm(Publisher publisher) {
        return "publisher/add-publisher";
    }

    @RequestMapping("/add-publisher")
    public String createPublisher(Model model, Publisher publisher, BindingResult result) {
        if (result.hasErrors()) {
            return "publisher/add-publisher";
        }

        publisherService.createPublisher(publisher);
        model.addAttribute("publisher", publisherService.findAllPublishers());

        return "redirect:/publishers";
    }

    @GetMapping("/updatePublisher/{id}")
    public String showUpdateForm(Model model, @PathVariable("id") Long id) {
        model.addAttribute("publisher", publisherService.findPublisherById(id));

        return "publisher/update-publisher";
    }

    @RequestMapping("/update-publisher/{id}")
    public String updatePublisher(Model model, @PathVariable("id") Long id, Publisher publisher,
                                    BindingResult result) {
        if (result.hasErrors()) {
            publisher.setId(id);
            return "publisher/update-publisher";
        }

        publisherService.updatePublisher(publisher);
        model.addAttribute("publisher", publisherService.findAllPublishers());

        return "redirect:/publishers";
    }

    @RequestMapping("/remove-publisher/{id}")
    public String deletePublisher(Model model, @PathVariable("id") Long id) {
        publisherService.deletePublisher(id);
        model.addAttribute("publisher", publisherService.findAllPublishers());

        return "redirect:/publishers";
    }

}
