package tedteam.twotowers.main;

/**
 * Az epitmenyek (torony, akadaly) absztrakt osztalya.
 */
public abstract class Element {
	// Az epitmeny helye a palyan.
	protected Cell cell;
	
	/**
	 * Ez a metodus donti el, hogy a varazskovel lehet-e erositeni
	 * az epitmenyt. Ha igen, akkor erositi is.
	 * @param m: a varazsko, mellyel erositeni szeretnenk az akadalyt.
	 * @return visszajelzes, hogy sikeres volt-e a muvelet.
	 */
	public abstract boolean enhance(MagicStone m);
	
	/**
	 * Az adott epitmeny akciojat vegrehajto metodus.
	 * Toronynal loves, akadalynal blokkolas.
	 */
	public abstract void action();
	
	/**
	 * Az element cellajat beallito metodus, ha esetleg
	 * nem valamelyik leszarmazottja akarna hozzanyulni.
	 * @param c a beallitando cella
	 */
	public void setCell(Cell c) {
		cell = c;
	}
	
	/**
	 * Az element cellajat lekero metodus, ha esetleg
	 * nem valamelyik leszarmazottja akarna hozzanyulni.
	 * @return az element cellaja
	 */
	public Cell getCell() {
		return cell;
	}

	/**
	 * Absztrakt osztaly. A leszarmazottak valositjak majd meg.
	 * @param visitor
	 */
	public abstract void visitElement(ElementVisitor visitor);
}