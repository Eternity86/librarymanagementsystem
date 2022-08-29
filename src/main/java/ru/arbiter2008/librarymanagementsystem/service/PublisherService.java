package ru.arbiter2008.librarymanagementsystem.service;

import ru.arbiter2008.librarymanagementsystem.model.Publisher;

import java.util.List;

public interface PublisherService {

    List<Publisher> findAllPublishers();

    Publisher findPublisherById(Long id);

    void createPublisher(Publisher publisher);

    void updatePublisher(Publisher publisher);

    void deletePublisher(Long id);

}
