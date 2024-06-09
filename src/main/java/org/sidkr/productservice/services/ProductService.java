package org.sidkr.productservice.services;

import org.sidkr.productservice.clients.FakeStoreClient;
import org.sidkr.productservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private FakeStoreClient fakeStoreClient;

    public Product getProduct(long id) {
       return fakeStoreClient.getProduct(id);
    }
}
