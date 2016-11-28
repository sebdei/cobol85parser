/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.parser.metamodel.procedure.sort;

import io.proleap.cobol.parser.metamodel.CobolDivisionElement;
import io.proleap.cobol.parser.metamodel.call.Call;

public interface Giving extends CobolDivisionElement {

	enum CloseProcedure {
		Crunch, Lock, NoRewind, Release, Save, WithRemoveCrunch
	}

	CloseProcedure getCloseProcedure();

	Call getFileCall();

	void setCloseProcedure(CloseProcedure closeProcedure);

	void setFileCall(Call fileCall);
}
