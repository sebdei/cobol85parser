/*
 * Copyright (C) 2016, Ulrich Wolffgang <u.wol@wwu.de>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the BSD 3-clause license. See the LICENSE file for details.
 */

package io.proleap.cobol.asg.metamodel.valuestmt.condition;

import java.util.List;

import io.proleap.cobol.Cobol85Parser.ConditionNameSubscriptReferenceContext;
import io.proleap.cobol.Cobol85Parser.InDataContext;
import io.proleap.cobol.Cobol85Parser.InFileContext;
import io.proleap.cobol.Cobol85Parser.InMnemonicContext;
import io.proleap.cobol.asg.metamodel.call.Call;
import io.proleap.cobol.asg.metamodel.valuestmt.ValueStmt;
import io.proleap.cobol.asg.metamodel.valuestmt.in.InData;
import io.proleap.cobol.asg.metamodel.valuestmt.in.InFile;
import io.proleap.cobol.asg.metamodel.valuestmt.in.InMnemonic;

public interface ConditionNameReference extends ValueStmt {

	InData addInData(InDataContext ctx);

	InFile addInFile(InFileContext ctx);

	InMnemonic addInMnemonic(InMnemonicContext ctx);

	ConditionNameSubscriptReference addSubscriptReference(ConditionNameSubscriptReferenceContext ctx);

	Call getConditionCall();

	List<ConditionNameSubscriptReference> getConditionNameSubscriptReferences();

	List<InData> getInDatas();

	InFile getInFile();

	List<InMnemonic> getInMnemonics();

	@Override
	String getValue();

	void setConditionCall(Call conditionCall);

}
