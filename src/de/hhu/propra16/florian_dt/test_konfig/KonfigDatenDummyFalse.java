//Test-Dummy, der das Interface IKonfigDaten und
//beim Aufruf von toBoolean() immer false zurückgibt

package de.hhu.propra16.florian_dt.test_konfig;

import de.hhu.propra16.florian_dt.konfig.IKonfigDaten;

public class KonfigDatenDummyFalse implements IKonfigDaten {
	public boolean toBoolean() {
		return false;
	}
}
