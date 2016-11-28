package io.proleap.cobol.gpl.parser.procedure.sort;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import io.proleap.cobol.CobolTestSupport;
import io.proleap.cobol.parser.applicationcontext.CobolParserContext;
import io.proleap.cobol.parser.metamodel.CopyBook;
import io.proleap.cobol.parser.metamodel.Program;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.call.Call;
import io.proleap.cobol.parser.metamodel.procedure.ProcedureDivision;
import io.proleap.cobol.parser.metamodel.procedure.sort.Alphanumeric;
import io.proleap.cobol.parser.metamodel.procedure.sort.CollatingSequence;
import io.proleap.cobol.parser.metamodel.procedure.sort.Duplicates;
import io.proleap.cobol.parser.metamodel.procedure.sort.Giving;
import io.proleap.cobol.parser.metamodel.procedure.sort.Givings;
import io.proleap.cobol.parser.metamodel.procedure.sort.National;
import io.proleap.cobol.parser.metamodel.procedure.sort.OnKey;
import io.proleap.cobol.parser.metamodel.procedure.sort.OutputProcedure;
import io.proleap.cobol.parser.metamodel.procedure.sort.OutputThrough;
import io.proleap.cobol.parser.metamodel.procedure.sort.SortStatement;
import io.proleap.cobol.parser.metamodel.procedure.sort.Using;
import io.proleap.cobol.preprocessor.CobolPreprocessor.CobolSourceFormatEnum;

public class SortStatementTest extends CobolTestSupport {

	@Override
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void test() throws Exception {
		final File inputFile = new File(
				"src/test/resources/io/proleap/cobol/gpl/parser/procedure/sort/SortStatement.cbl");
		final Program program = CobolParserContext.getInstance().getParserRunner().analyzeFile(inputFile, null,
				CobolSourceFormatEnum.TANDEM);

		final CopyBook copyBook = program.getCopyBook("SortStatement");
		final ProgramUnit programUnit = copyBook.getProgramUnit();
		final ProcedureDivision procedureDivision = programUnit.getProcedureDivision();

		{
			final SortStatement sortStatement = (SortStatement) procedureDivision.getStatements().get(0);
			assertNotNull(sortStatement);

			{
				final Call fileCall = sortStatement.getFileCall();
				assertNotNull(fileCall);
				assertEquals(Call.CallType.UndefinedCall, fileCall.getCallType());
			}

			{
				assertEquals(2, sortStatement.getOnKeys().size());

				{
					final OnKey onKey = sortStatement.getOnKeys().get(0);
					assertEquals(OnKey.Type.Descending, onKey.getType());
					assertEquals(1, onKey.getKeyCalls().size());

					{
						final Call keyCall = onKey.getKeyCalls().get(0);
						assertEquals(Call.CallType.UndefinedCall, keyCall.getCallType());
					}
				}

				{
					final OnKey onKey = sortStatement.getOnKeys().get(1);
					assertEquals(OnKey.Type.Ascending, onKey.getType());
					assertEquals(2, onKey.getKeyCalls().size());

					{
						final Call keyCall = onKey.getKeyCalls().get(0);
						assertEquals(Call.CallType.UndefinedCall, keyCall.getCallType());
					}

					{
						final Call keyCall = onKey.getKeyCalls().get(1);
						assertEquals(Call.CallType.UndefinedCall, keyCall.getCallType());
					}
				}
			}

			{
				final CollatingSequence collatingSequence = sortStatement.getCollatingSequence();
				assertEquals(2, collatingSequence.getAlphabetCalls().size());

				{
					final Alphanumeric alphaNumeric = collatingSequence.getAlphaNumeric();
					assertNotNull(alphaNumeric);

					final Call alphabetCall = alphaNumeric.getAlphabetCall();
					assertEquals(Call.CallType.UndefinedCall, alphabetCall.getCallType());
				}

				{
					final National national = collatingSequence.getNational();
					assertNotNull(national);

					final Call alphabetCall = national.getAlphabetCall();
					assertEquals(Call.CallType.UndefinedCall, alphabetCall.getCallType());
				}
			}

			{
				final Duplicates duplicates = sortStatement.getDuplicates();
				assertNotNull(duplicates);
				assertTrue(duplicates.isDuplicatesInOrder());
			}

			{
				assertEquals(1, sortStatement.getUsings().size());

				final Using using = sortStatement.getUsings().get(0);
				assertEquals(1, using.getFileCalls().size());

				final Call fileCall = using.getFileCalls().get(0);
				assertEquals(Call.CallType.UndefinedCall, fileCall.getCallType());
			}

			{
				final OutputProcedure outputProcedure = sortStatement.getOutputProcedure();
				assertNotNull(outputProcedure);

				{
					final Call procedureCall = outputProcedure.getProcedureCall();
					assertNotNull(procedureCall);
					assertEquals(Call.CallType.UndefinedCall, procedureCall.getCallType());
				}

				{
					final OutputThrough outputThrough = outputProcedure.getOutputThrough();
					assertNotNull(outputThrough);

					final Call procedureCall = outputThrough.getProcedureCall();
					assertNotNull(procedureCall);
					assertEquals(Call.CallType.UndefinedCall, procedureCall.getCallType());
				}
			}

			{
				assertEquals(1, sortStatement.getGivings().size());

				final Givings givings = sortStatement.getGivings().get(0);
				assertNotNull(givings);
				assertEquals(1, givings.getGivings().size());

				{
					final Giving giving = givings.getGivings().get(0);
					assertNotNull(giving);
					assertEquals(Giving.CloseProcedure.NoRewind, giving.getCloseProcedure());

					final Call fileCall = giving.getFileCall();
					assertEquals(Call.CallType.UndefinedCall, fileCall.getCallType());
				}
			}
		}
	}
}