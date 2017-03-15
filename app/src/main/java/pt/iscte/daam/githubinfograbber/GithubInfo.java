package pt.iscte.daam.githubinfograbber;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class GithubInfo extends AppCompatActivity {
    protected String githubUser;
    protected GithubUser user;

    protected TextView tvGithubUser, tvRealName, tvPublicURL, tvPublicRepos, tvLocation, tvEmail;
    protected ImageView ivAvatar;

    protected ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_github_info);

        tvGithubUser = (TextView) findViewById(R.id.tvGithubUser);
        tvRealName = (TextView) findViewById(R.id.tvRealName);
        tvPublicURL = (TextView) findViewById(R.id.tvPublicURL);
        tvPublicRepos = (TextView) findViewById(R.id.tvPublicRepos);
        tvLocation = (TextView) findViewById(R.id.tvLocation);
        tvEmail = (TextView) findViewById(R.id.tvEmail);

        ivAvatar = (ImageView) findViewById(R.id.ivAvatar);

        pDialog = new ProgressDialog(GithubInfo.this);

        // initialization of the image loader - a requirement from the library
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
        ImageLoader.getInstance().init(config);


        Intent i = getIntent();
        githubUser = i.getStringExtra("GITHUBUSER");

        Toast.makeText(GithubInfo.this, "Getting Github information for " + githubUser, Toast.LENGTH_SHORT).show();

        user = new GithubUser();
        getGitHubDetails(githubUser);

        pDialog.setMessage("Getting user details from Github...");
        pDialog.show();
    }

    /**
     * Get user details from Github API
     */
    public void getGitHubDetails(String githubUser) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://api.github.com/users/" + githubUser,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("GithubInfoGrabber", response);

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            user.setGithubUser(jsonObject.getString("login"));
                            user.setGitRealName(jsonObject.getString("name"));
                            user.setGithubEmail(jsonObject.getString("email"));
                            user.setGithubLocation(jsonObject.getString("location"));
                            user.setGithubPublicRepos(jsonObject.getString("public_repos"));
                            user.setGithubPublicURL(jsonObject.getString("html_url"));
                            user.setGithubAvatar(jsonObject.getString("avatar_url"));

                            fillDataInActivity();

                        } catch (Exception e) {
                            Log.i("GithubInfoGrabber", "Error processing the JSON answer -> " + e.getMessage());
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pDialog.dismiss();
                        Toast.makeText(GithubInfo.this, "Some error occured while getting Github information!", Toast.LENGTH_SHORT).show();
                        Log.i("GithubInfoGrabber", error.toString());
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    /**
     * Fills all the visual components with the data returned from the API
     */
    public void fillDataInActivity() {
        tvGithubUser.setText(user.getGithubUser());
        tvRealName.setText(user.getGitRealName());
        tvEmail.setText(user.getGithubEmail());
        tvLocation.setText(user.getGithubLocation());
        tvPublicRepos.setText(user.getGithubPublicRepos());
        tvPublicURL.setText(user.getGithubPublicURL());

        // Get the remote image and add it to the imageView
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.loadImage(user.getGithubAvatar(), new SimpleImageLoadingListener() {
            @Override
            public void onLoadingComplete(String imageUri, View v, Bitmap loadedImage) {
                ivAvatar.setImageBitmap(loadedImage);
                pDialog.dismiss();
            }
        });
    }

    /**
     * Closes this activity and returns to the previous one
     */
    public void closeGithubDetails(View v) {
        finish();
    }
}
