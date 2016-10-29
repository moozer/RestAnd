package world.my.moz.restand;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity  {

    Button btnTest;
    Button btnSetA0;
    Button btnGetA0;
    TextView tvServerInput;
    TextView tvPortInput;
    TextView tvTestResult;
    TextView tvScroll;
    TextView tvA0Value;
    TextView tvA0Input;

    private void retrieveIds() {
        btnTest = (Button) findViewById(R.id.btnTest);
        btnGetA0 = (Button) findViewById( R.id.btnGetA0);
        btnSetA0 = (Button) findViewById( R.id.btnSetA0);
        tvServerInput = (TextView) findViewById(R.id.tvServerInput);
        tvPortInput = (TextView) findViewById(R.id.tvPortInput);
        tvTestResult = (TextView) findViewById(R.id.tvTestResult);
        tvScroll = (TextView) findViewById(R.id.tvScroll);
        tvA0Value = (TextView) findViewById(R.id.tvA0Value);
        tvA0Input = (TextView) findViewById(R.id.tvA0Input);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        retrieveIds();

        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "http://" + tvServerInput.getText() + ":" + tvPortInput.getText() + "/";
                tvTestResult.setText("(working)");

                new connectClass().execute(url);
            }
        });

        btnGetA0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "http://" + tvServerInput.getText() + ":" + tvPortInput.getText() + "/gpio/A0";
                tvA0Value.setText("(working)");

                new A0GetConnect().execute(url);
            }
        });

        btnSetA0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "http://" + tvServerInput.getText() + ":" + tvPortInput.getText() + "/gpio/A0";
                tvA0Value.setText("(working)");

                new A0PutConnect().execute(url, tvA0Input.getText().toString());
            }
        });

    }


    class connectClass extends AsyncTask<String, Void, Void> {
        String response = "";

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            if( response != null ) {
                tvScroll.setText( response.toString());
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

    class A0GetConnect extends AsyncTask<String, Void, Void> {
        String response = "";

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            if( response != null ) {
                //tvScroll.setText("" + response);
                GpioConverter c = new GpioConverter();
                String data = c.JsonToGpio(response).getValue();

                //String str = "gpio/A0: "+ data;
                tvScroll.setText( response );
                tvA0Value.setText( data );
                //tvTestResult.setText("(ok)");
            }
            else {
                tvScroll.setText("Get failed");
                tvA0Value.setText( "Error" );
                //tvTestResult.setText("(error)");
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

    class A0PutConnect extends AsyncTask<String, Void, Void> {
        String response = "";

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            tvScroll.setText( response );
        }

        @Override
        protected Void doInBackground(String ... params ) {

            try {
                response = run( params[0], params[1] );
            } catch (IOException e) {
                response = null;
                e.printStackTrace();
            }

            return null;
        }

        String run(String url, String value) throws IOException {
            OkHttpClient client = new OkHttpClient();
            GpioConverter c = new GpioConverter();
            Gpio g = new Gpio();
            g.setValue( value );
            String jsonBody = c.GpioToJson( g );
            MediaType JSON = MediaType.parse("application/json; charset=utf-8");
            RequestBody body = RequestBody.create(JSON, jsonBody);
            Request request = new Request.Builder()
                    .url(url)
                    .put(body)
                    .build();

            Response response = client.newCall(request).execute();
            return response.body().string();
        }

    }
}
