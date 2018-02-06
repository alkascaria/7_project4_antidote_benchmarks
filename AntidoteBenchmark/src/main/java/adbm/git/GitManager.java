package adbm.git;

import adbm.docker.DockerManager;
import adbm.settings.MapDBManager;
import adbm.settings.ui.SettingsDialog;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jgit.api.CreateBranchCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.ListBranchCommand;
import org.eclipse.jgit.lib.Constants;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.TextProgressMonitor;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class GitManager
{

    private static final Logger log = LogManager.getLogger(GitManager.class);
    private static Git git;

    private static final String gitUrl = "https://github.com/SyncFree/antidote.git";

    public static void startGit()
    {
        if (!MapDBManager.isReady()) return;
        try {
            String repoLocation = MapDBManager.getAppSetting(MapDBManager.GitRepoLocationSetting);
            if (repoLocation.equals("")) {
                log.info("No location for the git repository was selected!");
                log.info("Please select a valid location in the settings!");
                new SettingsDialog();
            }
            else {
                File directory = new File(repoLocation);
                File[] contents = directory.listFiles();
                if (contents == null) {
                    log.info("The location for the git repository is not a directory!");
                    log.info("Please select a valid location in the settings!");
                }
                try {
                    git = Git.open(new File(repoLocation));
                } catch (Exception e) {
                    git = null;
                }
                if (git != null) {
                    if (git.status().call().isClean()) {
                        String url = git.getRepository().getConfig().getString("remote", "origin", "url");
                        if (url.equals(gitUrl)) {
                            log.info("Git connection was successfully established!");
                            log.info(
                                    "This application does not yet automatically fetch remote changes and that must be done manually!");//TODO add fetch
                        }
                        else {
                            log.info(
                                    "The location for the git repository contains a another repository that is not equal to " + gitUrl + "!");
                            log.info(
                                    "Please select another location in the settings or remove that git repository first!");
                            git = null;
                        }
                    }
                    else {
                        log.info("The git repository is not clean!");
                        log.info("Please commit all changes before using this application!");
                        git = null;
                    }

                }
                else {
                    log.info("There is currently no git repository at the selected location!");
                    log.info(
                            "The git repository " + gitUrl + " will be cloned to the selected location if there are no files in that directory.");

                    if (contents.length == 0) {
                        log.info(
                                "Cloning the git repository " + gitUrl + " to the location " + repoLocation + "!");
                        git = Git.cloneRepository()
                                 .setURI(gitUrl)
                                 .setDirectory(new File(repoLocation))
                                 .setProgressMonitor(new TextProgressMonitor(new PrintWriter(System.out)))
                                 .call();
                    }
                    else {
                        // TODO Add Setting that allows this!
                        log.info(
                                "The directory at selected location contains files and cannot be used as a git repository.");
                        log.info(
                                "Please select an empty directory in the settings or remove the existing files in that directory!");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void checkoutBranch(String branchName)
    {
        if (!isReady()) return;
        try {

            if (getAllLocalBranches().contains(branchName)) {
                git.checkout().
                        setName(branchName).
                           call();
                log.info("Checkout of local Branch " + branchName + " was successful!");
            }
            else if (getAllNonLocalRemoteBranches().contains(branchName)) {
                log.info(
                        "Local branch for the remote branch" + branchName + " does not exist and is added now!");
                git.checkout().
                        setCreateBranch(true).
                           setName(branchName).
                           setUpstreamMode(CreateBranchCommand.SetupUpstreamMode.TRACK).
                           setStartPoint("origin/" + branchName).
                           call();
                log.info("Local branch " + branchName + " was created and successfully checked out!");
            }
            else {
                log.info("Branch " + branchName + " could not be checked out!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void checkoutCommit(String commit)
    {
        if (!isReady()) return;
        try {
            log.info("Checking out commit " + commit + " and detaching HEAD!");
            git.checkout().setName(commit).call();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getCurrentBranch()
    {
        if (!isReady()) return "";
        try {
            String branchName = git.getRepository().getBranch();
            if (branchName == null) return "";
            return branchName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static RevCommit getCurrentCommit()
    {
        if (!isReady()) return null;
        try {
            ObjectId id = git.getRepository().resolve(Constants.HEAD);
            RevWalk walk = new RevWalk(git.getRepository());
            return walk.parseCommit(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<String> getAllLocalBranches()
    {
        List<String> list = new ArrayList<>();
        if (!isReady()) return list;
        try {
            List<Ref> branches = git.branchList().call();
            branches.forEach(branch -> list.add(branch.getName()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<String> getAllNonLocalRemoteBranches()
    {
        List<String> list = new ArrayList<>();
        if (!isReady()) return list;
        try {
            List<Ref> branches = git.branchList().setListMode(ListBranchCommand.ListMode.REMOTE).call();
            List<String> localBranches = getAllLocalBranches();
            branches.forEach(branch -> {
                String shortName = git.getRepository().shortenRemoteBranchName(branch.getName());
                if (!localBranches.contains(shortName))
                    list.add(shortName);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<RevCommit> getCommitsForCurrentHead(int number)
    {
        List<RevCommit> list = new ArrayList<>();
        if (!isReady()) return list;
        try {
            ObjectId head = git.getRepository().resolve(Constants.HEAD);
            Iterable<RevCommit> commits = git.log().add(head).setMaxCount(number).call();
            commits.forEach(list::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static RevCommit getCommitFromID(String id)
    {
        ObjectId commitId = ObjectId.fromString(id);
        RevWalk revWalk = new RevWalk(git.getRepository());
        try {
            RevCommit commit = revWalk.parseCommit(commitId);
            revWalk.close();
            return commit;
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("The ID " + id + " did not match any commits!");
        return null;
    }

    public static boolean isReady()
    {
        if (git != null) return true;
        log.info("The connection to Git is not ready!");
        log.info("Please start Git connection again!");
        return false;
    }

    public static boolean isReadyNoText()
    {
        if (git != null) return true;
        return false;
    }

    /*if (git == null) { TODO for later
            }
            else {
                RevWalk walk = new RevWalk(git.getRepository());

                List<Ref> branches = git.branchList().setListMode(ListBranchCommand.ListMode.ALL).call();

                for (Ref branch : branches) {
                    String branchName = branch.getName();
                    log.info("Commits of branch: " + branch.getName());
                    log.info("---------------------------------");
                    log.info("---------------------------------");
                    log.info("---------------------------------");
                    log.info("---------------------------------");
                    log.info("---------------------------------");
                    log.info("---------------------------------");
                    log.info("---------------------------------");
                    log.info("---------------------------------");
                    log.info("---------------------------------");
                    log.info("---------------------------------");
                    log.info("---------------------------------");
                    log.info("---------------------------------");


                    Iterable<RevCommit> commits = git.log().all().call();

                    for (RevCommit commit : commits) {
                        boolean foundInThisBranch = false;

                        RevCommit targetCommit = walk.parseCommit(git.getRepository().resolve(
                                commit.getName()));
                        for (Map.Entry<String, Ref> e : git.getRepository().getAllRefs().entrySet()) {
                            if (e.getKey().startsWith(Constants.R_HEADS)) {
                                if (walk.isMergedInto(targetCommit, walk.parseCommit(
                                        e.getValue().getObjectId())))
                                {
                                    String foundInBranch = e.getValue().getName();
                                    if (branchName.equals(foundInBranch)) {
                                        foundInThisBranch = true;
                                        break;
                                    }
                                }
                            }
                        }

                        if (foundInThisBranch) {
                            log.info(commit.getName());
                            log.info(commit.getAuthorIdent().getName());
                            log.info(new Date((long)commit.getCommitTime()*1000));
                            log.info(commit.getFullMessage());
                        }
                    }
                }
            }*/
}