package world.my.moz.restand;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnTest;
    TextView tvServerInput;
    TextView tvPortInput;
    TextView tvTestResult;

    private void retrieveIds() {
        btnTest = (Button) findViewById( R.id.btnTest );
        tvServerInput = (TextView) findViewById( R.id.tvServerInput);
        tvPortInput = (TextView) findViewById( R.id.tvPortInput );
        tvTestResult = (TextView) findViewById( R.id.tvTestResult );
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        retrieveIds();

        btnTest.setOnClickListener( this );

    }

    @Override
    public void onClick(View view) {
        tvTestResult.setText( "Connecting to\n" + tvServerInput.getText() + ":" + tvPortInput.getText() );
    }
}
