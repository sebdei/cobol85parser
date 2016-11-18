/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.data.datadescription.impl;

import io.proleap.cobol.Cobol85Parser.DataReceivedByClauseContext;
import io.proleap.cobol.parser.metamodel.ProgramUnit;
import io.proleap.cobol.parser.metamodel.data.datadescription.ReceivedByClause;
import io.proleap.cobol.parser.metamodel.impl.CobolDivisionElementImpl;

public class ReceivedByClauseImpl extends CobolDivisionElementImpl implements ReceivedByClause {

	protected DataReceivedByClauseContext ctx;

	protected ReceivedBy receivedBy;

	public ReceivedByClauseImpl(final ProgramUnit programUnit, final DataReceivedByClauseContext ctx) {
		super(programUnit, ctx);

		this.ctx = ctx;
	}

	@Override
	public ReceivedBy getReceivedBy() {
		return receivedBy;
	}

	@Override
	public void setReceivedBy(final ReceivedBy receivedBy) {
		this.receivedBy = receivedBy;
	}
}
