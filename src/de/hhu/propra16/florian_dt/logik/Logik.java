package de.hhu.propra16.florian_dt.logik;

import de.hhu.propra16.florian_dt.konfig.IKonfig;
import de.hhu.propra16.florian_dt.konfig.IKonfigDaten;
import de.hhu.propra16.florian_dt.testen.ITester;
import de.hhu.propra16.florian_dt.testen.ITesterUebergabe;
import de.hhu.propra16.florian_dt.testen.ITesterRueckgabe;

public class Logik implements ILogik {
	private IKonfig fKonfig;
	private ITester fTester;
	private Schritt schritt;
	private boolean atdd;
	private boolean refactor2;

      //Konstruktor: Erstellt eine Instanz von Logik
	public Logik(ITester tester, IKonfig konfig) {
			fKonfig = konfig;                                           //Uebergabewerte speichern
			fTester = tester;                                           //und Variablen initialisieren
			schritt = Schritt.RED;
			konfigLaden();
	}

	//Lädt die relevanten Einstellungen aus der Konfiguration
	void konfigLaden() {
		IKonfigDaten einstellung = fKonfig.einstellungAbfragen("ATDD"); //Einstellung holen
		atdd = einstellung.toBoolean();                                 //und konvertieren
		einstellung = fKonfig.einstellungAbfragen("REFACTOR2");
		refactor2 = einstellung.toBoolean();
	}

    //weiter: wird von GUI aufgerufen, testet und geht gegebenenfalls einen Zyklenschritt weiter
	public ITesterRueckgabe weiter(ITesterUebergabe uebergabe) {
		switch(schritt) {                                               //Aktuellen Schritt ermitteln
			case RED:       return redWeiter(uebergabe);                //Da mir return zurückgesprungen wird, ist kein break notwendig
			case GREEN:     return greenWeiter(uebergabe);
			case REFACTOR1: return refactor1Weiter(uebergabe);
			case REFACTOR2: return refactor2Weiter(uebergabe);
		}                                                               //Wenn ENDE erreicht wurde, nichts tun
		return null;
	}

    //Versucht in Schritt.RED, weiterzugehen
	ITesterRueckgabe redWeiter(ITesterUebergabe uebergabe) {
		ITesterRueckgabe rueckgabe = fTester.testen(uebergabe);         //Test ausführen
		if (rueckgabe.testBestanden()) schritt = Schritt.GREEN;         //Red -> Green
		return rueckgabe;                                               //Testergebnisse zurückgeben
	}

    //Versucht in Schritt.GREEN, weiterzugehen
	ITesterRueckgabe greenWeiter(ITesterUebergabe uebergabe) {
		ITesterRueckgabe rueckgabe = fTester.testen(uebergabe);         //Test ausführen
		if (rueckgabe.testBestanden()) schritt = Schritt.REFACTOR1;     //Green -> Refactor 1
		return rueckgabe;                                               //Testergebnisse zurückgeben
	}

    //Versucht in Schritt.REFACTOR1, weiterzugehen
	ITesterRueckgabe refactor1Weiter(ITesterUebergabe uebergabe) {
		ITesterRueckgabe rueckgabe = fTester.testen(uebergabe);         //Test ausführen
		Refactor2Aufrufen(rueckgabe, uebergabe);                        //Zusatz Refactor 2 ggf. ausführen
		return rueckgabe;                                               //Testergebnisse zurückgeben
	}

    //Geht im Schritt REFACTOR1 weiter zu REFACTOR 2 oder zum Ende
	void Refactor2Aufrufen(ITesterRueckgabe rueckgabe, ITesterUebergabe uebergabe) {
		if (refactor2) {                                                //Zusatz: Refactor 2
			if (rueckgabe.testBestanden()) schritt = Schritt.REFACTOR2; //Refactor 1 -> Refactor 2
		} else {
			if (rueckgabe.testBestanden()) endeAufrufen(uebergabe);     //Refactor 1 -> Ende
		}
	}

    //Versucht in Schritt.REFACTOR2, weiterzugehen
	ITesterRueckgabe refactor2Weiter(ITesterUebergabe uebergabe) {
		ITesterRueckgabe  rueckgabe = fTester.testen(uebergabe);         //Test ausführen
		if (rueckgabe.testBestanden()) endeAufrufen(uebergabe);          //Refactor 2 -> Ende
		return rueckgabe;                                                //Testergebnisse zurückgeben
	}

    //Geht weiter zum Ende
	ITesterRueckgabe endeAufrufen(ITesterUebergabe uebergabe) {
		if (atdd) {                                                      //Zusatz: Akzeptanztest ausführen
			ITesterRueckgabe rueckgabe = fTester.testen(uebergabe);      //Testen
			if (rueckgabe.testBestanden()) {                             //Falls der Test bestanden wurde
				if (fKonfig.naechsterTest()) schritt = Schritt.RED;      //Ende -> Red
				else schritt = Schritt.ENDE;                             //Entwicklungsende
			} else {
				return rueckgabe;                                        //Testergebnisse zurückgeben
			}
		} else {
			schritt = Schritt.RED;                                       //Ende -> Red
		}
		return null;                                                     //Kein Test ausgeführt, nichts zurückgeben
	}

      //Bricht den aktuellen Test ab und setzt das Programm zum letzten bestandendenen Test zurück
	public void abbrechen() {
		schritt = Schritt.RED;
	}

      //Bricht den aktuellen Test ab und setzt das Programm zum letzten bestandendenen Akzeptanztest zurück
	public void zuruecksetzen() {
		schritt = Schritt.RED;
		fKonfig.erstenTestAuswaehlen();
	}
}
