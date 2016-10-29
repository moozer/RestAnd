package world.my.moz.restand;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnTest;
    TextView tvServerInput;
    TextView tvPortInput;
    TextView tvTestResult;
    TextView tvScroll;

    private void retrieveIds() {
        btnTest = (Button) findViewById(R.id.btnTest);
        tvServerInput = (TextView) findViewById(R.id.tvServerInput);
        tvPortInput = (TextView) findViewById(R.id.tvPortInput);
        tvTestResult = (TextView) findViewById(R.id.tvTestResult);
        tvScroll = (TextView) findViewById(R.id.tvScroll);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        retrieveIds();

        btnTest.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        String url = "http://" + tvServerInput.getText() + ":" + tvPortInput.getText() + "/";
        tvTestResult.setText("(working)");

        new connectClass().execute(url);

    }

    class connectClass extends AsyncTask<String, Void, Void> {
        String response = "";

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            if( response != null ) {
                tvScroll.setText("" + response);
                tvTestResult.setText("(ok)");
            }
            else {
                tvScroll.setText("");
                tvTestResult.setText("(error)");
            }
        }

        @Override
        protected Void doInBackground(String ... url ) {

            try {
                response = run( url[0] );
            } catch (IOException e) {
                response = null;
                e.printStackTrace();
            }

            return null;
        }

        String run(String url) throws IOException {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url)
                    .build();

            Response response = client.newCall(request).execute();
            return response.body().string();
        }

    }
}
