package pt.iscte.daam.githubinfograbber;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class GithubInfo extends AppCompatActivity {
    protected String githubUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_github_info);

        Intent i = getIntent();
        githubUser = i.getStringExtra("GITHUBUSER");

        Toast.makeText(GithubInfo.this, "Getting Github information for " + githubUser, Toast.LENGTH_SHORT).show();
    }
}
