package fitnesse.wiki.cmSystems;

import static org.junit.Assert.*;

import java.util.Properties;

import org.junit.Test;

public class PerforceContentFilterTest {

  @Test
  public void perforcePasswordGetsRejected() {
    PerforceContentFilter sut = new PerforceContentFilter(new Properties());

    assertFalse(sut.isContentAcceptable("abcd $(P4PASSWD) efgh",
        "some/page/name"));
    assertTrue(sut.isContentAcceptable("abcd P4 PASSWD efgh", "some/page/name"));
    assertFalse(sut.isContentAcceptable("abcd P4PASSWD efgh", "some/page/name"));
  }
}
