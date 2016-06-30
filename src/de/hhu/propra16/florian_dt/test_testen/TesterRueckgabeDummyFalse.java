//Test-Dummy, der das Interface ITesterRueckgabe und
//beim Aufruf von testBestanden() immer false zurückgibt

package de.hhu.propra16.florian_dt.test_testen;

import de.hhu.propra16.florian_dt.testen.ITesterRueckgabe;

public class TesterRueckgabeDummyFalse implements ITesterRueckgabe {
	public boolean testBestanden() {
		return false;
	}
}
