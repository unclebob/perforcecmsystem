package fitnesse.wiki.cmSystems;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PerforceCmSystemDouble extends PerforceCmSystem {

  static {
    try {
      executeMethod = PerforceCmSystemDouble.class.getDeclaredMethod(
          "executeSimulatedPerforceCommand", String.class, String.class);
    } catch (NoSuchMethodException e) {
      e.printStackTrace();
    }
  }

  static Map<String, String> commandResponseMap;

  static List<String> recordedCommands = new ArrayList<String>();

  public static String executeSimulatedPerforceCommand(String method,
      String command) throws Exception {
    recordedCommands.add(command);
    return commandResponseMap.get(command);
  }

  public static void reset() {
    recordedCommands = new ArrayList<String>();
  }

  public static void setCommandResponseMap(Map<String, String> values) {
    commandResponseMap = values;
  }

  public static List<String> getRecordedCommands() {
    return recordedCommands;
  }

}
