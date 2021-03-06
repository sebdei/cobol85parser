package io.proleap.cobol.asg.procedure.use;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import io.proleap.cobol.CobolTestSupport;
import io.proleap.cobol.asg.applicationcontext.CobolParserContext;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.Program;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.asg.metamodel.procedure.declaratives.Declarative;
import io.proleap.cobol.asg.metamodel.procedure.declaratives.Declaratives;
import io.proleap.cobol.asg.metamodel.procedure.use.Debug;
import io.proleap.cobol.asg.metamodel.procedure.use.DebugOn;
import io.proleap.cobol.asg.metamodel.procedure.use.UseStatement;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class UseStatementTest extends CobolTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File("src/test/resources/io/proleap/cobol/asg/procedure/use/UseStatement.cbl");
		final Program program = CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile,
				CobolSourceFormatEnum.TANDEM);

		final CompilationUnit compilationUnit = program.getCompilationUnit("UseStatement");
		final ProgramUnit programUnit = compilationUnit.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();
		assertEquals(2, procedureDivision.getParagraphs().size());

		final Declaratives declaratives = procedureDivision.getDeclaratives();
		assertEquals(2, declaratives.getDeclaratives().size());

		{
			final Declarative declarative = declaratives.getDeclaratives().get(0);
			final UseStatement useStatement = declarative.getUseStament();
			assertNotNull(useStatement);
			assertEquals(UseStatement.Type.DEBUG, useStatement.getType());

			{
				final Debug debug = useStatement.getDebug();
				assertEquals(3, debug.getDebugOns().size());

				{
					final DebugOn debugOn = debug.getDebugOns().get(0);
					assertEquals(DebugOn.Type.ALL_REFERENCES, debugOn.getType());

					final Call onCall = debugOn.getOnCall();
					assertEquals(Call.CallType.UNDEFINED_CALL, onCall.getCallType());
				}

				{
					final DebugOn debugOn = debug.getDebugOns().get(1);
					assertEquals(DebugOn.Type.ALL_PROCEDURES, debugOn.getType());
					assertNull(debugOn.getOnCall());
				}

				{
					final DebugOn debugOn = debug.getDebugOns().get(2);
					assertEquals(DebugOn.Type.PROCEDURE, debugOn.getType());

					final Call onCall = debugOn.getOnCall();
					assertEquals(Call.CallType.UNDEFINED_CALL, onCall.getCallType());
				}
			}
		}

		{
			final Declarative declarative = declaratives.getDeclaratives().get(1);
			final UseStatement useStatement = declarative.getUseStament();
			assertNotNull(useStatement);
			assertEquals(UseStatement.Type.AFTER, useStatement.getType());
			assertTrue(useStatement.getAfter().isGlobal());
		}
	}
}