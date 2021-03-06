/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.gotostmt.impl;

import io.proleap.cobol.Cobol85Parser.GoToDependingOnStatementContext;
import io.proleap.cobol.Cobol85Parser.GoToStatementContext;
import io.proleap.cobol.Cobol85Parser.GoToStatementSimpleContext;
import io.proleap.cobol.Cobol85Parser.ProcedureNameContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.Scope;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.procedure.StatementType;
import io.proleap.cobol.asg.metamodel.procedure.StatementTypeEnum;
import io.proleap.cobol.asg.metamodel.procedure.gotostmt.DependingOn;
import io.proleap.cobol.asg.metamodel.procedure.gotostmt.GoToStatement;
import io.proleap.cobol.asg.metamodel.procedure.gotostmt.Simple;
import io.proleap.cobol.asg.metamodel.procedure.impl.StatementImpl;

public class GoToStatementImpl extends StatementImpl implements GoToStatement {

	protected final GoToStatementContext ctx;

	protected DependingOn dependingOn;

	protected Simple simple;

	protected final StatementType statementType = StatementTypeEnum.GO_TO;

	protected Type type;

	public GoToStatementImpl(final ProgramUnit programUnit, final Scope scope, final GoToStatementContext ctx) {
		super(programUnit, scope, ctx);

		this.ctx = ctx;
	}

	@Override
	public DependingOn addDependingOn(final GoToDependingOnStatementContext ctx) {
		DependingOn result = (DependingOn) getASGElement(ctx);

		if (result == null) {
			result = new DependingOnImpl(programUnit, ctx);

			// procedures
			for (final ProcedureNameContext procedureNameContext : ctx.procedureName()) {
				final Call procedureCall = createCall(procedureNameContext);
				result.addProcedureCall(procedureCall);
			}

			// depending on
			if (ctx.identifier() != null) {
				final Call dependingOnCall = createCall(ctx.identifier());
				result.setDependingOnCall(dependingOnCall);
			}

			// more labels
			if (ctx.MORE_LABELS() != null) {
				result.setMoreLabels(true);
			}

			dependingOn = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public Simple addSimple(final GoToStatementSimpleContext ctx) {
		Simple result = (Simple) getASGElement(ctx);

		if (result == null) {
			result = new SimpleImpl(programUnit, ctx);

			final Call procedureCall = createCall(ctx.procedureName());
			result.setProcedureCall(procedureCall);

			simple = result;
			registerASGElement(result);
		}

		return result;
	}

	@Override
	public DependingOn getDependingOn() {
		return dependingOn;
	}

	@Override
	public Simple getSimple() {
		return simple;
	}

	@Override
	public StatementType getStatementType() {
		return statementType;
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
