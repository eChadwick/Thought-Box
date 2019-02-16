package com.example.thoughtbox;

import android.app.Application;
import android.os.AsyncTask;

public class ThoughtRepository {

    private ThoughtDao mThoughtDao;

    ThoughtRepository(Application app) {
        ThoughtRoomDatabase db = ThoughtRoomDatabase.getDatabase(app);
        mThoughtDao = db.thoughtDao();
    }

    public void insert(Thought thought) {
        new insertAsyncTask(mThoughtDao).execute(thought);
    }

    private static class insertAsyncTask extends AsyncTask<Thought, Void, Void> {

        private ThoughtDao mAsyncTaskDao;

        insertAsyncTask(ThoughtDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Thought... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}