package com.example.thoughtbox;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class ThoughtRepository {

    private ThoughtDao mThoughtDao;
    private List<Thought> mAllThoughts;

    ThoughtRepository(Application app) {
        ThoughtRoomDatabase db = ThoughtRoomDatabase.getDatabase(app);
        mThoughtDao = db.thoughtDao();
        mAllThoughts = mThoughtDao.getAllThoughts();
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

    public List<Thought> getAllThoughts() {
        return mAllThoughts;
    }

//    private static class retrievalAsyncTask extends AsyncTask<Void, Void, List<Thought> > {
//
//        private ThoughtDao mAsyncTaskDao;
//
//        retrievalAsyncTask(ThoughtDao dao) {
//            mAsyncTaskDao = dao;
//        }
//
//        @Override
//        protected void doInBackground(Void... params) {
//            mAsyncTaskDao.getAllThoughts();
//        }
//
//        @Override
//        protected void onPostExecute(List<Thought> result) {
//            findViewById()
//        }
//    }
}