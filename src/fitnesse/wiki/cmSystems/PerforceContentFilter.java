package fitnesse.wiki.cmSystems;

import java.util.Properties;

import fitnesse.responders.editing.ContentFilter;

public class PerforceContentFilter extends Object implements ContentFilter {

  public PerforceContentFilter(Properties properties) {
  }

  public boolean isContentAcceptable(String content, String page) {
    return !content.contains("P4PASSWD");
  }

}
