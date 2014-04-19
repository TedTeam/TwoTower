package tedteam.twotowers.main;

import java.awt.Point;

import tedteam.twotowers.logger.Logger;
/**
 * A jatekteret megvalosito osztaly.
 */
public class GameField {
	// A palyat alkoto cellak.
	private Cell cells[] = new Cell[4];

	/**
	 * Lekeri a palya utolso cellajat.
	 * @return az utolso cella.
	 */
	public Cell getFinalCell(){
		Logger.enter("gameField", "getFinalCell", "", "");
		
		Logger.exit("cell4");
		return cells[3];
	}

	/**
	 * Lekeri a palya kezdo cellajat.
	 * @return a kezdo cella.
	 */
	public Cell getStartCell(){
		Logger.enter("gameField", "getStartCell", "", "");
		
		cells[1] = new Cell();
		
		Logger.exit("cell");
		return cells[1];
	}

	/**
	 * Az inicializalo fuggveny. Feladata a cellak letrehozasa,
	 * es azok szomszedossaganak beallitasa.
	 */
	public void init(){ 
		Logger.enter("gameField", "init", "", "");
		
		cells[0] = new Cell();
		cells[1] = new Cell();
		cells[2] = new Cell();
		cells[3] = new Cell();
		//TODO
		/**cells[0].setNeighbors(cells);
		cells[1].setNeighbors(cells);
		cells[2].setNeighbors(cells);
		cells[3].setNeighbors(cells);*/
		
		Logger.exit("void");
		
	}
	
	public void setAllCellUnvisited() {
		for(Cell c : cells) {
			c.setVisited(false);
		}
	}

	/**
	 * Parancsfeldolgozo - tempor�lis fuggveny
	 * Visszaadja a parameterben megadott nevu cellat.
	 * @param name
	 * @return
	 */
	public Cell getCellByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	public void addCell(Cell c, Point point) {
		// TODO Auto-generated method stub
		
	}

}