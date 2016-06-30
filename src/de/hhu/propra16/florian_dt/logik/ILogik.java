package de.hhu.propra16.florian_dt.logik;

import de.hhu.propra16.florian_dt.konfig.IKonfig;
import de.hhu.propra16.florian_dt.testen.ITester;
import de.hhu.propra16.florian_dt.testen.ITesterRueckgabe;
import de.hhu.propra16.florian_dt.testen.ITesterUebergabe;

interface ILogik {
    public ITesterRueckgabe weiter(ITesterUebergabe uebergabe);
    public void abbrechen();
    public void zuruecksetzen();
}
