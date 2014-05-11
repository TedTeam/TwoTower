package tedteam.twotowers.main;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

/**
 * A tornyot megvalosito osztaly. Egy torony fobb
 * tulajdonsagait tartalmazza.
 * Ososztaly: Element
 * Interface: IDamage
 */
public class Tower extends Element implements EnemyVisitor {
	// A torp ellenseg sebzesenek merteke.
	private int dwarfDamage = 50;
	// A tunde ellenseg sebzesenek merteke.
	private int elfDamage = 50;
	// A hobbit ellenseg sebzesenek merteke.
	private int hobbitDamage = 50;
	// Az ember ellenseg sebzesenek merteke.
	private int humanDamage = 50;
	// A torony hatotavolsaga.
	private int range = 5;
	// A torony tuzelesi gyakorisaga.
	private int speed = 2;
	// A tornyon levo kovek szama.
	// Maximalis ertek = 2 (alapertek = 0)
	private int stoneNumber = 0;
	// Erteke true, ha a tornyon van zold ko
	private boolean enhancedByGreen = false;
	// Erteke true, ha a tornyon van kek ko
	private boolean enhancedByBlue = false;
	// Erteke true, ha a tornyon van piros ko
	private boolean enhancedByRed = false;
	// Egy igaz/hamis valtozo, mely erteke eldonti,
	// hogy leszallt-e a kod a toronyra, vagy sem.
	private boolean fog = false;
	// Az utemezo alapegysege.
	private int tick = 0;
	// A torony loveseit eltarolo hit objektum.
	private Hit hit = new Hit();
	
	/**
	 * Ez a metodus donti el, hogy a varazskovel lehet-e erositeni
	 * a tornyot. Ha igen, akkor erositi is.
	 * @param magicStone: a varazsko, mellyel erositeni szeretnenk a tornyot.
	 * @return visszajelzes, hogy sikeres volt-e a muvelet.
	 */
	public boolean enhance(MagicStone magicStone) {
		return magicStone.effect(this);
	}

	// Egy ellenseg megtalalasa hatotavon belul 
	// a Breadth First Search algoritmus segitsegevel
	public Enemy findEnemyInRange() {
		// a sor melyben eltaroljuk a vizsgalni kivant cellakat (FIFO)
		LinkedList<Cell> queue = new LinkedList<Cell>();
		ArrayList<Cell> visited = new ArrayList<Cell>();
		// az aktualisan viszgalt hatotav
		int actualRange = 0;
		// a cellak szama az aktualisan vizsgalt hatotavban
		int cellsInActualRange = 1;
		// novelhetjuk-e az aktualis hatotavot
		boolean actualRangeIncrease = false;
		// berakjuk a sorba az elso elemet es meg is tekintettuk
		queue.add(this.cell);
		visited.add(this.cell);
		// ciklus, amig a sor ures nem lesz
		while(!queue.isEmpty()) {
			// toroljuk az elso elemet a sorban
			Cell x = queue.remove();
			// ha a torolt cellan volt ellenseg visszaterunk az ellenseggel
			// (ha tobb van rajta akkor a legelsot adjuk vissza)
			if(x.hasEnemy() == true)	{
				return x.getEnemy().get(0);
			} else {
				// ha a cellak szama az aktialisan vizsgalt hatotavban 0-ra csokkent
				if(--cellsInActualRange == 0) {
					// noveljuk az aktualis hatotavot, de ha az nagyobb mint
					// a toronyban megadott, akkor nem talaltunk ellenseget
					if(++actualRange > this.range) {
						return null;
					}
					// ha nem ertuk el a megadott hatotavot,
					// akkor felvehetjuk a hatotavban levo cellak szamat
					actualRangeIncrease = true;
				}
				// lekerdezzuk a szomszedokat
				ArrayList<Cell> neighbors = new ArrayList<Cell>(x.getNeighbors().values());
				for(Cell y : neighbors) {
					// ha van szomszed es azt meg nem tekintettuk meg
					if(y != null && visited.contains(y) == false) {
						// megtekintettuk es a sorbarakjuk
						visited.add(y);
						queue.add(y);
					}
				}
				// ha felvehetjuk a hatotavban levo cellak szamat
				if(actualRangeIncrease == true) {
					actualRangeIncrease = false;
					// a sor aktualis merete pont megadja ezt a szamot
					cellsInActualRange = queue.size();
				}
			}
		}
		return null;
	}
	
	/**
	 * A torony loveset vegrehajto metodus.
	 */
	public void action() {
		if(10 - tick  == speed){
			Enemy targetEnemy;
			targetEnemy = findEnemyInRange();
			if(targetEnemy != null) {
				targetEnemy.accept(this);
				hit.addHit(this.cell,targetEnemy.getCell());
			}
			tick = 0;
			
			Random r = new Random();
			int chanceFog = r.nextInt(13);
			if(chanceFog == 3) {
				fog = true;
			}
		}
		else tick++;
	}

	/**
	 * A hobbit ellenseg sebzeseert felelos metodus.
	 * @param hobbit: az ellenseg melyet sebezni kell.
	 */
	public void affect(Hobbit hobbit) {
		Random rand = new Random();
		int cutChance = rand.nextInt(20) +1 ;// 1-tol 20-ig generalok egy szamot, hogy 5% esellyel legyen cut() hivas
		if(cutChance == 4)//ez lehet akemilyen szam 1 es 20 kozott
			hobbit.cut(hobbitDamage);
		else hobbit.damage(hobbitDamage);
	}

	/**
	 * A tunde ellenseg sebzeseert felelos metodus.
	 * @param elf: az ellenseg melyet sebezni kell.
	 */
	public void affect(Elf elf) {
		Random rand = new Random();
		int cutChance = rand.nextInt(20) +1 ;// 1-tol 20-ig generalok egy szamot, hogy 5% esellyel legyen cut() hivas
		if(cutChance == 4)//ez lehet akemilyen szam 1 es 20 kozott
			elf.cut(elfDamage);
		else elf.damage(elfDamage);
	}

	/**
	 * A torp ellenseg sebzeseert felelos metodus.
	 * @param dwarf: az ellenseg melyet sebezni kell.
	 */
	public void affect(Dwarf dwarf) {
		Random rand = new Random();
		int cutChance = rand.nextInt(20) +1 ;// 1-t�l 20-ig generalok egy szamot, hogy 5% esellyel legyen cut() hivas
		if(cutChance == 4)//ez lehet akemilyen szam 1 es 20 kozott
			dwarf.cut(dwarfDamage);
		else dwarf.damage(dwarfDamage);
	}


	/**
	 * Az ember ellenseg sebzeseert felelos metodus.
	 * @param human: az ellenseg melyet sebezni kell.
	 */
	public void affect(Human human) {
		Random rand = new Random();
		int cutChance = rand.nextInt(20) +1 ;// 1-t�l 20-ig generalok egy szamot, hogy 5% esellyel legyen cut() hivas
		if(cutChance == 4)//ez lehet akemilyen szam 1 es 20 kozott
			human.cut(humanDamage);
		else human.damage(humanDamage);//
	}
	
	/**
	 * A stoneNumber attributum ertekevel ter vissza.
	 */
	public int getStoneNumber() {
		return stoneNumber;
	}

	/**
	 * Igaz, ha enhancedByBlue igaz, egyebkent false
	 */
	public boolean getEnhancedByBlue() {
		return enhancedByBlue;
	}
	
	/**
	 * enhancedByBlue attributumot true-ra allitja
	 */
	public void setEnhancedByBlue() {
		enhancedByBlue = true;
	}
	
	/**
	 * Igaz, ha enhancedByGreen igaz, egyebkent false
	 */
	public boolean getEnhancedByGreen() {
		return enhancedByGreen;
	}
	
	/**
	 * enhancedByGreen attributumot true-ra allitja
	 */
	public void setEnhancedByGreen() {
		enhancedByGreen = true;
	}
	
	/**
	 * Igaz, ha enhancedByRed igaz, egyebkent false
	 */
	public boolean getEnhancedByRed() {
		return enhancedByRed;
	}
	
	/**
	 * enhancedByRed attributumot true-ra allitja
	 */
	public void setEnhancedByRed() {
		enhancedByRed = true;
	}

	/**
	 * Noveli a stoneNumber attributum erteket
	 */
	public void increaseStoneNumber() {
		stoneNumber++;
	}

	/**
	 * Noveli a range attributum erteket
	 * @param rangeRate: ennyivel noveli a hatotavot
	 */
	public void increaseRange(int rangeRate) {
		range += rangeRate;
	}
	
	/**
	 * Noveli a speed attributum erteket
	 * @param speedRate: ennyivel noveli a sebesseget
	 */
	public void increaseSpeed(int speedRate) {
		speed += speedRate;
	}

	/**
	 * Sebzesek novelese a parameterben kapott ertekekkel.
	 * @param dwarf
	 * @param elf
	 * @param hobbit
	 * @param human
	 */
	public void increaseDamage(int dwarf,int elf,int hobbit,int human){
		dwarfDamage += dwarf;
		elfDamage += elf;
		hobbitDamage += hobbit;
		humanDamage += human;
	}
	
	/**
	 * Ez a metodus beallitja a fog valtozo erteket true-ra
	 * es az uj range erteket, ami pontosan 3 lesz, es ez vegleges is marad.
	 */
	public void fog() {
		if(fog == false) {
			fog = true;
			range = 3;
		}
	}
	/**
	 * Visszaadja a fog attributum erteket
	 */
	public boolean getFog() {
		return fog;
	}

	/**
	 * A Hit referenciat beallito metodus
	 * @param hit: erre allitja be.
	 */
	public void setHit(Hit hit) {
		this.hit = hit;
	}

	/**
	 * Visszadja a torony hatotavjat.
	 * @return
	 */
	public int getRange() {
		return range;
	}
	
	/**
	 * A toronyhoz tartozo visitor minta resze.
	 */
	public void visitElement(ElementVisitor visitor) {
		visitor.affect(this);
	}
}
