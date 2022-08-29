package ru.arbiter2008.librarymanagementsystem.service.impl;

import org.springframework.stereotype.Service;
import ru.arbiter2008.librarymanagementsystem.model.Publisher;
import ru.arbiter2008.librarymanagementsystem.service.PublisherService;

import java.util.List;

@Service
public class PublisherServiceImpl implements PublisherService {

    @Override
    public List<Publisher> findAllPublishers() {
        return null;
    }

    @Override
    public Publisher findPublisherById(Long id) {
        return null;
    }

    @Override
    public void createPublisher(Publisher publisher) {

    }

    @Override
    public void updatePublisher(Publisher publisher) {

    }

    @Override
    public void deletePublisher(Long id) {

    }

}
