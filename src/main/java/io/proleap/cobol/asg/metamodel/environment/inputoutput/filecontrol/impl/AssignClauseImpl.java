/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.environment.inputoutput.filecontrol.impl;

import io.proleap.cobol.Cobol85Parser.AssignClauseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.environment.inputoutput.filecontrol.AssignClause;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public class AssignClauseImpl extends CobolDivisionElementImpl implements AssignClause {

	protected final AssignClauseContext ctx;

	protected Call toCall;

	protected Type type;

	public AssignClauseImpl(final ProgramUnit programUnit, final AssignClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Call getToCall() {
		return toCall;
	}

	@Override
	public Type getType() {
		return type;
	}

	@Override
	public void setToCall(final Call toCall) {
		this.toCall = toCall;
	}

	@Override
	public void setType(final Type type) {
		this.type = type;
	}

}
