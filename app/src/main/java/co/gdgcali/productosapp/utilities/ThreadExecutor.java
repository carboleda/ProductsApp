package co.gdgcali.productosapp.utilities;

import android.os.AsyncTask;

/**
 * Created by krlosf on 24/10/16.
 */

public class ThreadExecutor extends AsyncTask<Object, Object, Object> {

    private Executor executor;

    public ThreadExecutor(Executor executor) {
        this.executor = executor;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Object doInBackground(Object... objects) {
        return executor.execute();
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        executor.finish(o);
    }

    public interface Executor {
        //Que se va a ejecutar en segundo plano
        Object execute();
        //Que se va a hacer cuando termine la ejecucion en segundo plano
        void finish(Object result);
    }
}
