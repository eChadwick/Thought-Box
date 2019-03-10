package com.example.thoughtbox;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class ThoughtViewModel extends AndroidViewModel {
    private ThoughtRepository mRepository;

    public ThoughtViewModel(Application app) {
        super(app);
        mRepository = new ThoughtRepository(app);
    }

    public void insert(Thought thought) {
        mRepository.insert(thought);
    }

    public List<Thought> getAllThoughts() {
        return mRepository.getAllThoughts();
    }
}
