package pt.iscte.daam.githubinfograbber;

/**
 * Created by cserrao on 15/03/2017.
 */

public class GithubUser {
    protected String githubUser, gitRealName, githubPublicURL, githubPublicRepos, githubLocation, githubEmail, githubAvatar;

    public GithubUser() {
    }

    public String getGithubUser() {
        return githubUser;
    }

    public void setGithubUser(String githubUser) {
        this.githubUser = githubUser;
    }

    public String getGitRealName() {
        return gitRealName;
    }

    public void setGitRealName(String gitRealName) {
        this.gitRealName = gitRealName;
    }

    public String getGithubPublicURL() {
        return githubPublicURL;
    }

    public void setGithubPublicURL(String githubPublicURL) {
        this.githubPublicURL = githubPublicURL;
    }

    public String getGithubPublicRepos() {
        return githubPublicRepos;
    }

    public void setGithubPublicRepos(String githubPublicRepos) {
        this.githubPublicRepos = githubPublicRepos;
    }

    public String getGithubLocation() {
        return githubLocation;
    }

    public void setGithubLocation(String githubLocation) {
        this.githubLocation = githubLocation;
    }

    public String getGithubEmail() {
        return githubEmail;
    }

    public void setGithubEmail(String githubEmail) {
        this.githubEmail = githubEmail;
    }

    public String getGithubAvatar() {
        return githubAvatar;
    }

    public void setGithubAvatar(String githubAvatar) {
        this.githubAvatar = githubAvatar;
    }
}
