package com.firebase.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;

@Service
public class FireBaseInitializer {

    @PostConstruct
    private void initDB() throws IOException {
        InputStream serviceAccount = this.getClass()
                .getClassLoader()
                .getResourceAsStream("./fir-spring-boot-48604-firebase-adminsdk-lk2af-c7f95c5c5d.json");

        assert serviceAccount != null;
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://fir-spring-boot-48604-default-rtdb.firebaseio.com")
                .build();
        if (FirebaseApp.getApps().isEmpty())
            FirebaseApp.initializeApp(options);
    }


    public Firestore getFireBase(){
        return FirestoreClient.getFirestore();
    }

}
