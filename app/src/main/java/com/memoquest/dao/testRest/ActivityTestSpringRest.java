package com.memoquest.dao.testRest;

    import android.os.AsyncTask;
    import android.os.Bundle;
    import android.support.v4.app.Fragment;
    import android.support.v7.app.ActionBarActivity;
    import android.util.Log;
    import android.view.LayoutInflater;
    import android.view.Menu;
    import android.view.MenuItem;
    import android.view.View;
    import android.view.ViewGroup;
    import com.memoquest.app.R;
    import com.memoquest.model.ListOfListe;

    import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
    import org.springframework.web.client.RestTemplate;

    public class ActivityTestSpringRest extends ActionBarActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_test_spring_rest);

            if (savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.container, new PlaceholderFragment())
                        .commit();
            }
        }

        @Override
        protected void onStart() {
            super.onStart();
            new HttpRequestTask().execute();
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {

            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.main, menu);
            return true;
        }

 /*
        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();



            if (id == R.id.action_refresh) {
                new HttpRequestTask().execute();
                return true;
            }
            return super.onOptionsItemSelected(item);
        }
   */
        /**
         * A placeholder fragment containing a simple view.
         */
        public static class PlaceholderFragment extends Fragment {

            public PlaceholderFragment() {
            }

            @Override
            public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                     Bundle savedInstanceState) {
                View rootView = inflater.inflate(R.layout.fragment_main_test_spring, container, false);
                return rootView;
            }
        }

        /* Memo Quest */
        private class HttpRequestTask extends AsyncTask<Void, Void, ListOfListe> {
            @Override
            protected ListOfListe doInBackground(Void... params) {
                try {
                    final String url = "http://memoquest.fr/MemoQuest/app_dev.php/api/users/4/listes";
                    RestTemplate restTemplate = new RestTemplate();
                    restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());



                    // Greeting greeting = restTemplate.getForObject(url, Greeting.class);
                    ListOfListe listOfListe = restTemplate.getForObject(url, ListOfListe.class);



                    Log.e("INFO", "listeTab.length:   " + listOfListe.getEntities().get(1).getNom());
                    return listOfListe;




                } catch (Exception e) {
                    Log.e("ActivityTestSpringRest", e.getMessage(), e);
                }

                return null;
            }

            @Override
            protected void onPostExecute(ListOfListe listOfListe) {
                /*
                TextView greetingIdText = (TextView) findViewById(R.id.id_value);
                TextView greetingContentText = (TextView) findViewById(R.id.content_value);
                greetingIdText.setText(listOfListe.getListOfListe().toString());
                greetingContentText.setText(listOfListe.getListOfListe().toString());
            */
            }
        }
    }