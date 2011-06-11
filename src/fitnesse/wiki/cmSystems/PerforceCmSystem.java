package fitnesse.wiki.cmSystems;

import java.io.File;

import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fitnesse.components.CommandRunner;
import fitnesse.wiki.FileSystemPage;

import static java.io.File.*;
import static java.lang.String.*;

public class PerforceCmSystem {

  static List<String> ignoredPaths = new ArrayList<String>();

  // hook for test case
  protected static Method executeMethod;

  static {
    ignoredPaths.add(format("%1$sRecentChanges%1$s", separatorChar));
    ignoredPaths.add(format("%1$sErrorLogs%1$s", separatorChar));

    try {
      executeMethod = PerforceCmSystem.class.getDeclaredMethod(
          "executePerforceCommand", String.class, String.class);
    } catch (NoSuchMethodException e) {
      e.printStackTrace();
    }

  }

  public static void cmEdit(String file, String payload) throws Exception {
    if (isIgnored(file)) {
      return;
    }

    Map<String, String> fstats = getFileStats(file);

    if (isUnknown(fstats)) {
      return;
    }

    if (isIntegratedOrBranched(fstats)) {
      execute("cmEdit", "p4 reopen " + file);
    }

    if (isOpened(fstats) && !isOpenForDelete(fstats)) {
      return;
    }

    if (isOpenForDelete(fstats)) {
      execute("cmEdit", "p4 revert " + file);
    }

    execute("cmEdit", "p4 edit " + file);
  }

  public static void cmUpdate(String file, String payload) throws Exception {
    if (isIgnored(file)) {
      return;
    }

    Map<String, String> fstats = getFileStats(file);

    if (isUnknown(fstats)) {
      execute("cmUpdate", "p4 add " + file);
    }
  }

  public static void cmDelete(String file, String payload) throws Exception {

    if (isIgnored(file)) {
      return;
    }

    String directoryPath = file + "/...";
    Map<String, String> fstats = getFileStats(file
        + FileSystemPage.contentFilename);

    if (isUnknown(fstats)) {
      return;
    }

    if (isOpenForDelete(fstats)) {
      return;
    }

    if (isOpened(fstats)) {
      execute("cmDelete", "p4 revert " + directoryPath);
    }

    if (!isOpenForAdd(fstats)) {
      execute("cmDelete", "p4 delete " + directoryPath);
    }
  }

  private static String execute(String method, String command) throws Exception {
    return (String) executeMethod.invoke(null, method, command);
  }

  protected static String executePerforceCommand(String method, String command)
      throws Exception {
    CommandRunner runner = new CommandRunner(command, "");
    runner.run();
    if (runner.getError().length() > 0 || runner.getExitCode() != 0) {
      System.err.println(method + " command: " + command);
      System.err.println(method + " exit code: " + runner.getExitCode());
      System.err.println(method + " out:" + runner.getOutput());
      System.err.println(method + " err:" + runner.getError());
    }
    return runner.getOutput();
  }

  private static Map<String, String> getFileStats(String filePath)
      throws Exception {
    Map<String, String> fstatMap = new HashMap<String, String>();

    String fstatOutput = execute("getFileStats", "p4 fstat " + filePath);

    for (String line : fstatOutput.split("\n")) {
      String[] tokenizedLine = line.split(" ");
      if (tokenizedLine.length > 2)
        fstatMap.put(tokenizedLine[1], tokenizedLine[2]);
    }
    return fstatMap;
  }

  private static boolean isIgnored(String filePath) {
    File currentFile = new File(filePath);
    String absolutePath = currentFile.getAbsolutePath();

    for (String ignoredItem : ignoredPaths) {
      if (absolutePath.contains(ignoredItem))
        return true;
    }

    return false;
  }

  private static boolean isIntegratedOrBranched(Map<String, String> fstats) {
    return "integrate".equals(fstats.get("action"))
        || "branch".equals(fstats.get("action"));
  }

  private static boolean isOpened(Map<String, String> fstats) {
    return (fstats.get("action") != null);
  }

  private static boolean isOpenForAdd(Map<String, String> fstats) {
    return "add".equals(fstats.get("action"));
  }

  private static boolean isOpenForDelete(Map<String, String> fstats) {
    return "delete".equals(fstats.get("action"));
  }

  private static boolean isUnknown(Map<String, String> fstats) {
    return fstats.get("clientFile") == null;
  }
}
