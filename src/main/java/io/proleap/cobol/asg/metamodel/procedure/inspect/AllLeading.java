/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.procedure.inspect;

import java.util.List;

import io.proleap.cobol.Cobol85Parser.InspectBeforeAfterContext;
import io.proleap.cobol.asg.metamodel.CobolDivisionElement;
import io.proleap.cobol.asg.metamodel.call.Call;

public interface AllLeading extends CobolDivisionElement {

	BeforeAfter addBeforeAfter(InspectBeforeAfterContext ctx);

	List<BeforeAfter> getBeforeAfters();

	Call getPatternDataItemCall();

	void setPatternDataItemCall(Call patternDataItemCall);

}
