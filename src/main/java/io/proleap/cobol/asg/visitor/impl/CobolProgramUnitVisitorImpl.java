/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.visitor.impl;

import org.antlr.v4.runtime.misc.NotNull;

import io.proleap.cobol.Cobol85Parser;
import io.proleap.cobol.asg.metamodel.CompilationUnit;
import io.proleap.cobol.asg.metamodel.ProgramUnit;

/**
 * Visitor for collecting units in the AST.
 */
public class CobolProgramUnitVisitorImpl extends AbstractCobolParserVisitorImpl {

	protected final CompilationUnit compilationUnit;

	public CobolProgramUnitVisitorImpl(final CompilationUnit compilationUnit) {
		this.compilationUnit = compilationUnit;
	}

	@Override
	public Boolean visitDataDivision(@NotNull final Cobol85Parser.DataDivisionContext ctx) {
		final ProgramUnit programUnit = findProgramUnit(ctx);

		programUnit.addDataDivision(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitEnvironmentDivision(@NotNull final Cobol85Parser.EnvironmentDivisionContext ctx) {
		final ProgramUnit programUnit = findProgramUnit(ctx);

		programUnit.addEnvironmentDivision(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitIdentificationDivision(@NotNull final Cobol85Parser.IdentificationDivisionContext ctx) {
		final ProgramUnit programUnit = findProgramUnit(ctx);

		programUnit.addIdentificationDivision(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitProcedureDivision(@NotNull final Cobol85Parser.ProcedureDivisionContext ctx) {
		final ProgramUnit programUnit = findProgramUnit(ctx);

		programUnit.addProcedureDivision(ctx);

		return visitChildren(ctx);
	}

	@Override
	public Boolean visitProgramUnit(@NotNull final Cobol85Parser.ProgramUnitContext ctx) {
		compilationUnit.addProgramUnit(ctx);

		return visitChildren(ctx);
	}

}
