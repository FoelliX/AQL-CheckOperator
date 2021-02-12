import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import de.foellix.aql.Log;
import de.foellix.aql.converter.IConverter;
import de.foellix.aql.converter.horndroid.ConverterHD;
import de.foellix.aql.datastructure.Answer;
import de.foellix.aql.datastructure.QuestionPart;
import de.foellix.aql.datastructure.Reference;
import de.foellix.aql.datastructure.handler.AnswerHandler;
import de.foellix.aql.helper.Helper;
import de.foellix.aql.operator.horndroid.CheckOperator;
import de.foellix.aql.system.task.ToolTaskInfo;

public class CheckOperatorTest {
	private static final File TEMP = new File("test/temp.xml");

	@BeforeAll
	private static void setup() {
		Log.setLogLevel(Log.DEBUG_DETAILED);
	}

	@AfterEach
	private void cleanUp() {
		TEMP.delete();
	}

	@Test
	void test01() {
		test(new File("test/AnswerTC_DirectLeak1.xml"), new File("test/AnswerHD_DirectLeak1.xml"), 1);
	}

	@Test
	void test02() {
		testRAW(new File("test/AnswerTC_DirectLeak1.xml"), new File("test/raw/DirectLeak1.apk.json"),
				new File("test/apps/DirectLeak1.apk"), 1);
	}

	@Test
	void test03() {
		testRAW(new File("test/AnswerTC_ArrayAccess2.xml"), new File("test/raw/ArrayAccess2.apk.json"),
				new File("test/apps/ArrayAccess2.apk"), 1);
	}

	@Test
	void test04() {
		testRAW(new File("test/AnswerTC_ArrayAccess2.xml"), new File("test/raw/ArrayAccess2.apk_with_option_w.json"),
				new File("test/apps/ArrayAccess2.apk"), 0);
	}

	private void test(File testTCFile, File testHDFile, int expectedNumberOfFlows) {
		boolean noException = true;
		try {
			// Run CheckOperator
			final CheckOperator co = new CheckOperator(testTCFile, testHDFile);
			final Answer answer = AnswerHandler.parseXML(co.getResultFile());
			assertEquals(expectedNumberOfFlows, answer.getFlows().getFlow().size());
		} catch (final Exception e) {
			noException = false;
			e.printStackTrace();
		}

		assertTrue(noException);
	}

	private void testRAW(File testTCFile, File testHDFileRAW, File apkFile, int expectedNumberOfFlows) {
		boolean noException = true;
		try {
			// Run ConverterHD
			final Reference ref = new Reference();
			ref.setApp(Helper.createApp(apkFile));
			final QuestionPart qp = new QuestionPart();
			qp.addReference(ref);
			final ToolTaskInfo taskInfo = new ToolTaskInfo(null, qp);
			final IConverter hdConverter = new ConverterHD();
			AnswerHandler.createXML(hdConverter.parse(testHDFileRAW, taskInfo), TEMP);

			// Run CheckOperator
			final CheckOperator co = new CheckOperator(testTCFile, TEMP);
			final Answer answer = AnswerHandler.parseXML(co.getResultFile());
			assertEquals(expectedNumberOfFlows, answer.getFlows().getFlow().size());
		} catch (final Exception e) {
			noException = false;
			e.printStackTrace();
		}

		assertTrue(noException);
	}
}
