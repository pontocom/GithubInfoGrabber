package pt.iscte.daam.githubinfograbber;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    protected EditText etGithubUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etGithubUser = (EditText) findViewById(R.id.etGithubUser);
    }

    public void getGithubInfo(View v) {
        Intent i = new Intent(MainActivity.this, GithubInfo.class);
        i.putExtra("GITHUBUSER", etGithubUser.getText().toString());
        startActivity(i);
    }
}
