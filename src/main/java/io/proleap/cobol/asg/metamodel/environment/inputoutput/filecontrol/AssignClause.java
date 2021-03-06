/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.environment.inputoutput.filecontrol;

import io.proleap.cobol.asg.metamodel.CobolDivisionElement;
import io.proleap.cobol.asg.metamodel.call.Call;

public interface AssignClause extends CobolDivisionElement {

	enum Type {
		CALL, DISK, PORT, PRINTER, READER, REMOTE, TAPE, VIRTUAL
	}

	Call getToCall();

	Type getType();

	void setToCall(Call toCall);

	void setType(Type type);

}
