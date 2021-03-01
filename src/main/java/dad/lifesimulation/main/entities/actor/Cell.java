package dad.lifesimulation.main.entities.actor;

import dad.lifesimulation.main.entities.Entity;
import dad.lifesimulation.main.entities.EntityFinalType;
import dad.lifesimulation.main.utils.Coordinates;
import dad.lifesimulation.main.utils.Dimension;
import dad.lifesimulation.main.utils.Statistics;

public class Cell extends Actor{

	private String cell_id;
	
	public Cell(Coordinates _coordinates, Dimension _dimension, Statistics _statistics, Boolean hostilToOthers,
			Orientation _orientation) {
		super(_coordinates, _dimension, _statistics, hostilToOthers, _orientation);
		this.cell_id = cell_id;
		this.entityType = EntityFinalType.CELL;
		tangible = true;
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Cell [cell_id=" + cell_id + ", statistics=" + statistics + ", orientation=" + orientation
				+ ", hostilToOthers=" + hostilToOthers + ", detectionRangeNorth=" + detectionRangeNorth
				+ ", detectionRangeSouth=" + detectionRangeSouth + ", detectionRangeWest=" + detectionRangeWest
				+ ", detectionRangeEast=" + detectionRangeEast + ", detectionRangeList=" + detectionRangeList
				+ ", coordinates=" + coordinates + ", dimension=" + dimension + ", tangible=" + tangible + ", drawable="
				+ drawable + ", debugging=" + debugging + ", entityType=" + entityType + ", support=" + support
				+ ", deletable=" + deletable + ", traspasable=" + traspasable + "]";
	}

	@Override
	public void interact(Entity entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void interact(Actor actor) {
		if (hostilToOthers)
		{
			System.out.println("Pegando");
			actor.getStatistics().damageReceive(getStatistics().getDamage());
		}
		
	}
	
	

}
