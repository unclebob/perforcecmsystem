package fitnesse.wiki.cmSystems;

import static org.junit.Assert.assertEquals;
import static java.lang.String.*;
import static java.io.File.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class PerforceCmSystemTest {

	protected String fileName = null;
	protected Map<String, String> returnMap;
	protected List<String> expectations;

	@Before
	public void setUp() throws Exception {
		fileName = format("file%1$sunder%1$stest", separatorChar);
		returnMap = new HashMap<String, String>();
		expectations = new ArrayList<String>();
		PerforceCmSystemDouble.reset();
	}

	@Test
	public void cmEditUnversionedFile() throws Exception {
		appendFstatCommandToResponseAndExpectation(fileName,
				createUnversionedFStatResponse(fileName));
		PerforceCmSystemDouble.setCommandResponseMap(returnMap);

		PerforceCmSystemDouble.cmEdit(fileName, "");

		assertSentRequests(expectations,
				PerforceCmSystemDouble.getRecordedCommands());
	}

	@Test
	public void cmEditUnopenedFile() throws Exception {
		appendFstatCommandToResponseAndExpectation(fileName,
				createUnopenedFStatResponse(fileName));
		appendEditCommandToResponseAndExpectation(fileName);
		PerforceCmSystemDouble.setCommandResponseMap(returnMap);

		PerforceCmSystemDouble.cmEdit(fileName, "");

		assertSentRequests(expectations,
				PerforceCmSystemDouble.getRecordedCommands());
	}

	@Test
	public void cmEditEditingFile() throws Exception {
		appendFstatCommandToResponseAndExpectation(fileName,
				createEditFStatResponse(fileName));
		PerforceCmSystemDouble.setCommandResponseMap(returnMap);

		PerforceCmSystemDouble.cmEdit(fileName, "");

		assertSentRequests(expectations,
				PerforceCmSystemDouble.getRecordedCommands());
	}

	@Test
	public void cmEditAddingFile() throws Exception {
		appendFstatCommandToResponseAndExpectation(fileName,
				createAddFStatResponse(fileName));
		PerforceCmSystemDouble.setCommandResponseMap(returnMap);

		PerforceCmSystemDouble.cmEdit(fileName, "");

		assertSentRequests(expectations,
				PerforceCmSystemDouble.getRecordedCommands());
	}

	@Test
	public void cmEditDeletedFile() throws Exception {
		appendFstatCommandToResponseAndExpectation(fileName,
				createDeleteFStatResponse(fileName));
		appendRevertCommandToResponseAndExpectation(fileName);
		appendEditCommandToResponseAndExpectation(fileName);
		PerforceCmSystemDouble.setCommandResponseMap(returnMap);

		PerforceCmSystemDouble.cmEdit(fileName, "");

		assertSentRequests(expectations,
				PerforceCmSystemDouble.getRecordedCommands());
	}

	@Test
	public void cmEditIntegratedFile() throws Exception {
		appendFstatCommandToResponseAndExpectation(fileName,
				createIntegrateFStatResponse(fileName));
		appendReopenCommandToResponseAndExpectation(fileName);
		PerforceCmSystemDouble.setCommandResponseMap(returnMap);

		PerforceCmSystemDouble.cmEdit(fileName, "");

		assertSentRequests(expectations,
				PerforceCmSystemDouble.getRecordedCommands());
	}

	@Test
	public void cmEditBranchedFile() throws Exception {
		appendFstatCommandToResponseAndExpectation(fileName,
				createBranchFStatResponse(fileName));
		appendReopenCommandToResponseAndExpectation(fileName);
		PerforceCmSystemDouble.setCommandResponseMap(returnMap);

		PerforceCmSystemDouble.cmEdit(fileName, "");

		assertSentRequests(expectations,
				PerforceCmSystemDouble.getRecordedCommands());
	}

	@Test
	public void cmUpdateUnversionedFile() throws Exception {
		appendFstatCommandToResponseAndExpectation(fileName,
				createUnversionedFStatResponse(fileName));
		appendAddCommandToResponseAndExpectation(fileName);
		PerforceCmSystemDouble.setCommandResponseMap(returnMap);

		PerforceCmSystemDouble.cmUpdate(fileName, "");

		assertSentRequests(expectations,
				PerforceCmSystemDouble.getRecordedCommands());
	}

	@Test
	public void cmUpdateUnopenedFile() throws Exception {
		appendFstatCommandToResponseAndExpectation(fileName,
				createUnopenedFStatResponse(fileName));
		PerforceCmSystemDouble.setCommandResponseMap(returnMap);

		PerforceCmSystemDouble.cmUpdate(fileName, "");

		assertSentRequests(expectations,
				PerforceCmSystemDouble.getRecordedCommands());
	}

	@Test
	public void cmUpdateEditingFile() throws Exception {
		appendFstatCommandToResponseAndExpectation(fileName,
				createEditFStatResponse(fileName));
		PerforceCmSystemDouble.setCommandResponseMap(returnMap);

		PerforceCmSystemDouble.cmUpdate(fileName, "");

		assertSentRequests(expectations,
				PerforceCmSystemDouble.getRecordedCommands());
	}

	@Test
	public void cmUpdateAddingFile() throws Exception {
		appendFstatCommandToResponseAndExpectation(fileName,
				createAddFStatResponse(fileName));
		PerforceCmSystemDouble.setCommandResponseMap(returnMap);

		PerforceCmSystemDouble.cmUpdate(fileName, "");

		assertSentRequests(expectations,
				PerforceCmSystemDouble.getRecordedCommands());
	}

	@Test
	public void cmUpdateDeletedFile() throws Exception {
		appendFstatCommandToResponseAndExpectation(fileName,
				createDeleteFStatResponse(fileName));
		PerforceCmSystemDouble.setCommandResponseMap(returnMap);

		PerforceCmSystemDouble.cmUpdate(fileName, "");

		assertSentRequests(expectations,
				PerforceCmSystemDouble.getRecordedCommands());
	}

	@Test
	public void cmUpdateIntegratedFile() throws Exception {
		appendFstatCommandToResponseAndExpectation(fileName,
				createIntegrateFStatResponse(fileName));
		PerforceCmSystemDouble.setCommandResponseMap(returnMap);

		PerforceCmSystemDouble.cmUpdate(fileName, "");

		assertSentRequests(expectations,
				PerforceCmSystemDouble.getRecordedCommands());
	}

	@Test
	public void cmUpdateBranchedFile() throws Exception {
		appendFstatCommandToResponseAndExpectation(fileName,
				createBranchFStatResponse(fileName));
		PerforceCmSystemDouble.setCommandResponseMap(returnMap);

		PerforceCmSystemDouble.cmUpdate(fileName, "");

		assertSentRequests(expectations,
				PerforceCmSystemDouble.getRecordedCommands());
	}

	@Test
	public void cmDeleteUnversionedFile() throws Exception {
		appendFstatCommandToResponseAndExpectation(
				pathTo(fileName, "content.txt"),
				createUnversionedFStatResponse(pathTo(fileName, "content.txt")));
		PerforceCmSystemDouble.setCommandResponseMap(returnMap);

		PerforceCmSystemDouble.cmDelete(fileName, "");

		assertSentRequests(expectations,
				PerforceCmSystemDouble.getRecordedCommands());
	}

	@Test
	public void cmDeleteUnopenedFile() throws Exception {
		appendFstatCommandToResponseAndExpectation(
				pathTo(fileName, "content.txt"),
				createUnopenedFStatResponse(pathTo(fileName, "content.txt")));
		appendDeleteCommandToResponseAndExpectation(pathTo(fileName, "..."));
		PerforceCmSystemDouble.setCommandResponseMap(returnMap);

		PerforceCmSystemDouble.cmDelete(fileName, "");

		assertSentRequests(expectations,
				PerforceCmSystemDouble.getRecordedCommands());
	}

	@Test
	public void cmDeleteEditingFile() throws Exception {
		appendFstatCommandToResponseAndExpectation(pathTo(fileName, "content.txt"),
				createEditFStatResponse(pathTo(fileName, "content.txt")));
		appendRevertCommandToResponseAndExpectation(pathTo(fileName, "..."));
		appendDeleteCommandToResponseAndExpectation(pathTo(fileName, "..."));
		PerforceCmSystemDouble.setCommandResponseMap(returnMap);

		PerforceCmSystemDouble.cmDelete(fileName, "");

		assertSentRequests(expectations,
				PerforceCmSystemDouble.getRecordedCommands());
	}

	@Test
	public void cmDeleteAddingFile() throws Exception {
		appendFstatCommandToResponseAndExpectation(pathTo(fileName, "content.txt"),
				createAddFStatResponse(pathTo(fileName, "content.txt")));
		appendRevertCommandToResponseAndExpectation(pathTo(fileName, "..."));
		PerforceCmSystemDouble.setCommandResponseMap(returnMap);

		PerforceCmSystemDouble.cmDelete(fileName, "");

		assertSentRequests(expectations,
				PerforceCmSystemDouble.getRecordedCommands());
	}

	@Test
	public void cmDeleteDeletedFile() throws Exception {
		appendFstatCommandToResponseAndExpectation(pathTo(fileName, "content.txt"),
				createDeleteFStatResponse(pathTo(fileName, "content.txt")));
		PerforceCmSystemDouble.setCommandResponseMap(returnMap);

		PerforceCmSystemDouble.cmDelete(fileName, "");

		assertSentRequests(expectations,
				PerforceCmSystemDouble.getRecordedCommands());
	}

	@Test
	public void cmDeleteIntegratedFile() throws Exception {
		appendFstatCommandToResponseAndExpectation(pathTo(fileName, "content.txt"),
				createIntegrateFStatResponse(pathTo(fileName, "content.txt")));
		appendRevertCommandToResponseAndExpectation(pathTo(fileName, "..."));
		appendDeleteCommandToResponseAndExpectation(pathTo(fileName, "..."));
		PerforceCmSystemDouble.setCommandResponseMap(returnMap);

		PerforceCmSystemDouble.cmDelete(fileName, "");

		assertSentRequests(expectations,
				PerforceCmSystemDouble.getRecordedCommands());
	}

	@Test
	public void cmDeleteBranchedFile() throws Exception {
		appendFstatCommandToResponseAndExpectation(pathTo(fileName, "content.txt"),
				createBranchFStatResponse(pathTo(fileName, "content.txt")));
		appendRevertCommandToResponseAndExpectation(pathTo(fileName, "..."));
		appendDeleteCommandToResponseAndExpectation(pathTo(fileName, "..."));
		PerforceCmSystemDouble.setCommandResponseMap(returnMap);

		PerforceCmSystemDouble.cmDelete(fileName, "");

		assertSentRequests(expectations,
				PerforceCmSystemDouble.getRecordedCommands());
	}

	private String pathTo(String directory, String filename) {
		return format("%s%s%s",
				directory, separatorChar, filename);
	}

	protected void assertSentRequests(List<String> expectations,
			List<String> recordedCommands) {
		assertEquals(expectations.size(), recordedCommands.size());

		int i = 0;
		for (String expectedCommand : expectations) {
			assertEquals(expectedCommand, recordedCommands.get(i));
			i++;
		}
	}

	protected String createUnversionedFStatResponse(String fileName2) {
		return "\nunversioned\n";
	}

	protected String createUnopenedFStatResponse(String file) {
		return "... clientFile " + file + "\n";
	}

	protected String createEditFStatResponse(String file) {
		StringBuilder builder = new StringBuilder();
		builder.append(createUnopenedFStatResponse(file));
		builder.append("... action edit\n");
		return builder.toString();
	}

	protected String createAddFStatResponse(String file) {
		StringBuilder builder = new StringBuilder();
		builder.append(createUnopenedFStatResponse(file));
		builder.append("... action add\n");
		return builder.toString();
	}

	protected String createDeleteFStatResponse(String file) {
		StringBuilder builder = new StringBuilder();
		builder.append(createUnopenedFStatResponse(file));
		builder.append("... action delete\n");
		return builder.toString();
	}

	protected String createBranchFStatResponse(String file) {
		StringBuilder builder = new StringBuilder();
		builder.append(createUnopenedFStatResponse(file));
		builder.append("... action branch\n");
		return builder.toString();
	}

	protected String createIntegrateFStatResponse(String file) {
		StringBuilder builder = new StringBuilder();
		builder.append(createUnopenedFStatResponse(file));
		builder.append("... action integrate\n");
		return builder.toString();
	}

	protected String fstatCommand(String file) {
		return "p4 fstat " + file;
	}

	protected void appendAddCommandToResponseAndExpectation(String fileName) {
		returnMap.put(("p4 add " + fileName),
				(fileName + "#1 - opened for add"));
		expectations.add(("p4 add " + fileName));
	}

	protected void appendRevertCommandToResponseAndExpectation(String fileName) {
		returnMap.put(("p4 revert " + fileName),
				(fileName + "#1 - was edit, reverted"));
		expectations.add(("p4 revert " + fileName));
	}

	protected void appendEditCommandToResponseAndExpectation(String fileName) {
		returnMap.put(("p4 edit " + fileName),
				(fileName + "#1 - opened for edit"));
		expectations.add(("p4 edit " + fileName));
	}

	protected void appendReopenCommandToResponseAndExpectation(String fileName) {
		returnMap.put(("p4 reopen " + fileName),
				(fileName + "#1 - reopened for edit"));
		expectations.add(("p4 reopen " + fileName));
	}

	protected void appendDeleteCommandToResponseAndExpectation(String fileName) {
		returnMap.put(("p4 delete " + fileName),
				(fileName + "#1 - opened for delete"));
		expectations.add(("p4 delete " + fileName));
	}

	protected void appendFstatCommandToResponseAndExpectation(String filename,
			String fstatResponse) {
		String fstatCommand = fstatCommand(filename);
		returnMap.put(fstatCommand, fstatResponse);
		expectations.add(fstatCommand);
	}
}
