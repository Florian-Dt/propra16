//Test-Dummy, der das Interface ITesterRueckgabe und
//beim Aufruf von testBestanden() immer true zurückgibt

package de.hhu.propra16.florian_dt.test_testen;

import de.hhu.propra16.florian_dt.testen.ITesterRueckgabe;

public class TesterRueckgabeDummyTrue implements ITesterRueckgabe {
	public boolean testBestanden() {
		return true;
	}
}
