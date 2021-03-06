/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.environment.specialnames;

import java.util.List;

import io.proleap.cobol.Cobol85Parser.ClassClauseThroughContext;
import io.proleap.cobol.asg.metamodel.CobolDivisionElement;
import io.proleap.cobol.asg.metamodel.call.Call;

public interface ClassClause extends CobolDivisionElement {

	enum Type {
		ALPHA_NUMERIC, NATIONAL
	}

	ClassThrough addClassThrough(ClassClauseThroughContext ctx);

	Call getClassCall();

	List<ClassThrough> getClassThroughs();

	Type getType();

	void setClassCall(Call classCall);

	void setType(Type type);
}
