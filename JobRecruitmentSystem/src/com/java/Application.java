package com.java;
import java.util.*;

class Application {
    int userId;
    int jobId;
    double tfIdfScore;  // Store TF-IDF score for ranking
    
    Application(int userId, int jobId) {
        this.userId = userId;
        this.jobId = jobId;
        this.tfIdfScore = 0.0;
    }
}