/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.data.datadescription.impl;

import io.proleap.cobol.Cobol85Parser.DataRenamesClauseContext;
import io.proleap.cobol.asg.metamodel.ProgramUnit;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.data.datadescription.RenamesClause;
import io.proleap.cobol.asg.metamodel.impl.CobolDivisionElementImpl;

public class RenamesClauseImpl extends CobolDivisionElementImpl implements RenamesClause {

	protected DataRenamesClauseContext ctx;

	protected Call from;

	protected Call to;

	public RenamesClauseImpl(final ProgramUnit programUnit, final DataRenamesClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public Call getFrom() {
		return from;
	}

	@Override
	public Call getTo() {
		return to;
	}

	@Override
	public void setFrom(final Call from) {
		this.from = from;
	}

	@Override
	public void setTo(final Call to) {
		this.to = to;
	}

}
