/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.merge.impl;

import io.proleap.cobol.Cobol85Parser.MergeOutputProcedurePhraseContext;
import io.proleap.cobol.Cobol85Parser.MergeOutputThroughContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.merge.OutputProcedure;
import io.proleap.cobol.asg.metamodel.procedure.merge.OutputThrough;

public class OutputProcedureImpl extends CobolDivisionElementImpl implements OutputProcedure {

	protected final MergeOutputProcedurePhraseContext ctx;

	protected OutputThrough outputThrough;

	protected Call procedureCall;

	public OutputProcedureImpl(final ProgramUnit programUnit, final MergeOutputProcedurePhraseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public OutputThrough addOutputThrough(final MergeOutputThroughContext ctx) {
		OutputThrough result = (OutputThrough) getASGElement(ctx);

		if (result == null) {
			result = new OutputThroughImpl(programUnit, ctx);

			// procedure call
			final Call procedureCall = createCall(ctx.procedureName());
			result.setProcedureCall(procedureCall);

			outputThrough = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public OutputThrough getOutputThrough() {
		return outputThrough;
	}

	@Override
	public Call getProcedureCall() {
		return procedureCall;
	}

	@Override
	public void setProcedureCall(final Call procedureCall) {
		this.procedureCall = procedureCall;
	}

}
