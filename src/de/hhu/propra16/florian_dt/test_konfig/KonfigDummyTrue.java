//Test-Dummy, der das Interface IKonfig implementiert,
//beim Aufruf von naechsterTest() immer true zurückgibt
//einen Test-Dummy verwaltet, der das Interface
//IKonfigDaten implementiert und
//beim Aufruf von toBoolean() immer true zurückgibt

package de.hhu.propra16.florian_dt.test_konfig;

import de.hhu.propra16.florian_dt.konfig.IKonfig;
import de.hhu.propra16.florian_dt.konfig.IKonfigDaten;

public class KonfigDummyTrue implements IKonfig {
	public IKonfigDaten einstellungAbfragen(String eigenschaft) {
		return new KonfigDatenDummyTrue();
	}

	public void erstenTestAuswaehlen() { }

	public boolean naechsterTest() {
		return true;
	}
}
