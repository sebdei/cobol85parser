/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.move.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.Cobol85Parser.MoveCorrespondingToStatementContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.move.MoveCorrespondingTo;

public class MoveCorrespondingToImpl extends CobolDivisionElementImpl implements MoveCorrespondingTo {

	protected final MoveCorrespondingToStatementContext ctx;

	protected List<Call> receivingAreaCalls = new ArrayList<Call>();

	protected Call sendingCall;

	public MoveCorrespondingToImpl(final ProgramUnit programUnit, final MoveCorrespondingToStatementContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public void addReceivingAreaCall(final Call receivingAreaCall) {
		receivingAreaCalls.add(receivingAreaCall);
	}

	@Override
	public List<Call> getReceivingAreaCalls() {
		return receivingAreaCalls;
	}

	@Override
	public Call getSendingCall() {
		return sendingCall;
	}

	@Override
	public void setSendingCall(final Call sendingCall) {
		this.sendingCall = sendingCall;
	}

}
