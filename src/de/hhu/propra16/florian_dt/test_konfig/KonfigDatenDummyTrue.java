//Test-Dummy, der das Interface IKonfigDaten und
//beim Aufruf von toBoolean() immer true zurückgibt

package de.hhu.propra16.florian_dt.test_konfig;

import de.hhu.propra16.florian_dt.konfig.IKonfigDaten;

public class KonfigDatenDummyTrue implements IKonfigDaten {
	public boolean toBoolean() {
		return true;
	}
}
