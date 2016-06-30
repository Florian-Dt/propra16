package de.hhu.propra16.florian_dt.konfig;

public interface IKonfig {
	public IKonfigDaten einstellungAbfragen(String eigenschaft);
	public void erstenTestAuswaehlen();
	public boolean naechsterTest();
}
