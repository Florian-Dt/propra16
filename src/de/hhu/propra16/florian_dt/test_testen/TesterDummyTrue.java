//Test-Dummy, der das Interface ITester und
//einen Test-Dummy verwaltet, der das Interface
//ITesterRueckgabe implementiert und
//beim Aufruf von testBestanden() immer true zurückgibt

package de.hhu.propra16.florian_dt.test_testen;

import de.hhu.propra16.florian_dt.testen.ITester;
import de.hhu.propra16.florian_dt.testen.ITesterRueckgabe;
import de.hhu.propra16.florian_dt.testen.ITesterUebergabe;

public class TesterDummyTrue implements ITester {
	public ITesterRueckgabe testen(ITesterUebergabe quelltext) {
		return new TesterRueckgabeDummyTrue();
	}
}
