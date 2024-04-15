package com.pk2024.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModelService {
    private static ModelRepository modelRepository;

    @Autowired
    public ModelService(ModelRepository modelRepository) {
        ModelService.modelRepository = modelRepository;
    }
}
