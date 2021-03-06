/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.string.impl;

import java.util.ArrayList;
import java.util.List;

import io.proleap.cobol.Cobol85Parser.StringDelimitedByPhraseContext;
import io.proleap.cobol.Cobol85Parser.StringForPhraseContext;
import io.proleap.cobol.Cobol85Parser.StringSendingPhraseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;
import io.proleap.cobol.asg.metamodel.procedure.string.DelimitedBy;
import io.proleap.cobol.asg.metamodel.procedure.string.For;
import io.proleap.cobol.asg.metamodel.procedure.string.Sendings;

public class SendingsImpl extends CobolDivisionElementImpl implements Sendings {

	protected final StringSendingPhraseContext ctx;

	protected DelimitedBy delimitedBy;

	protected List<Call> sendingCalls = new ArrayList<Call>();

	protected For sendingFor;

	protected Type type;

	public SendingsImpl(final ProgramUnit programUnit, final StringSendingPhraseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public DelimitedBy addDelimitedBy(final StringDelimitedByPhraseContext ctx) {
		DelimitedBy result = (DelimitedBy) getASGElement(ctx);

		if (result == null) {
			result = new DelimitedByImpl(programUnit, ctx);

			// type
			final DelimitedBy.Type type;

			if (ctx.SIZE() != null) {
				type = DelimitedBy.Type.SIZE;
			} else {
				final Call charactersCall = createCall(ctx.identifier(), ctx.literal());
				result.setCharactersCall(charactersCall);
				type = DelimitedBy.Type.CHARACTERS;
			}

			result.setType(type);

			delimitedBy = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public For addFor(final StringForPhraseContext ctx) {
		For result = (For) getASGElement(ctx);

		if (result == null) {
			result = new ForImpl(programUnit, ctx);

			final Call forCall = createCall(ctx.identifier(), ctx.literal());
			result.setForCall(forCall);

			sendingFor = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public void addSendingCall(final Call sendingCall) {
		sendingCalls.add(sendingCall);
	}

	@Override
	public DelimitedBy getDelimitedBy() {
		return delimitedBy;
	}

	@Override
	public For getFor() {
		return sendingFor;
	}

	@Override
	public List<Call> getSendingCalls() {
		return sendingCalls;
	}

	@Override
	public Type getType() {
		return type;
	}

	@Override
	public void setType(final Type type) {
		this.type = type;
	}

}
