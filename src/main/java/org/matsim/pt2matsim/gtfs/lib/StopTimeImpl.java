/* *********************************************************************** *
 * project: org.matsim.*
 * StopTime.java
 *                                                                         *
 * *********************************************************************** *
 *                                                                         *
 * copyright       : (C) 2011 by the members listed in the COPYING,        *
 *                   LICENSE and WARRANTY file.                            *
 * email           : info at matsim dot org                                *
 *                                                                         *
 * *********************************************************************** *
 *                                                                         *
 *   This program is free software; you can redistribute it and/or modify  *
 *   it under the terms of the GNU General Public License as published by  *
 *   the Free Software Foundation; either version 2 of the License, or     *
 *   (at your option) any later version.                                   *
 *   See also COPYING, LICENSE and WARRANTY file                           *
 *                                                                         *
 * *********************************************************************** */

package org.matsim.pt2matsim.gtfs.lib;

import org.matsim.core.utils.misc.Time;

/**
 * Container for GTFS StopTime. Contains stopId, arrivalTime and departureTime
 */
public class StopTimeImpl implements StopTime {

	private final Integer sequencePosition;
	private final int arrivalTime;
	private final int departureTime;
	private final Stop stop;
	private final Trip trip;

	public StopTimeImpl(Integer sequencePosition, int arrivalTime, int departureTime, Stop stop, Trip trip) {
		this.sequencePosition = sequencePosition;
		this.arrivalTime = arrivalTime;
		this.departureTime = departureTime;
		this.stop = stop;
		this.trip = trip;
	}


	@Override
	public Stop getStop() {
		return stop;
	}

	@Override
	public Trip getTrip() {
		return trip;
	}

	/**
	 * required attribute
	 */
	@Override
	public int getArrivalTime() {
		return arrivalTime;
	}

	/**
	 * required attribute
	 */
	@Override
	public int getDepartureTime() {
		return departureTime;
	}

	/**
	 * required attribute
	 *
	 * @return the position of the stopTime within the stopSequence
	 */
	@Override
	public Integer getSequencePosition() {
		return sequencePosition;
	}

	public boolean eqals(Object o) {
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;

		StopTimeImpl stopTime = (StopTimeImpl) o;

		return getSequencePosition().equals(stopTime.getSequencePosition()) && getStop().equals(stopTime.getStop()) && getTrip().equals(stopTime.getTrip());
	}

	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o == null || getClass() != o.getClass()) return false;

		StopTimeImpl stopTime = (StopTimeImpl) o;

		if(arrivalTime != stopTime.arrivalTime) return false;
		if(departureTime != stopTime.departureTime) return false;
		if(sequencePosition != null ? !sequencePosition.equals(stopTime.sequencePosition) : stopTime.sequencePosition != null)
			return false;
		if(stop != null ? !stop.equals(stopTime.stop) : stopTime.stop != null) return false;
		return trip != null ? trip.equals(stopTime.trip) : stopTime.trip == null;
	}

	@Override
	public int hashCode() {
		int result = sequencePosition != null ? sequencePosition.hashCode() : 0;
		result = 31 * result + arrivalTime;
		result = 31 * result + departureTime;
		result = 31 * result + (stop != null ? stop.hashCode() : 0);
		result = 31 * result + (trip != null ? trip.hashCode() : 0);
		return result;
	}

	@Override
	public int compareTo(StopTime o) {
		if(this.equals(o)) {
			return 0;
		} else if(!o.getTrip().getId().equals(trip.getId())) {
			return this.getDepartureTime() - o.getDepartureTime();
		}
		return this.getSequencePosition() - o.getSequencePosition();
	}

	@Override
	public String toString() {
		return stop + "[pos:" + sequencePosition + ", arrivalTime:" + Time.writeTime(arrivalTime) + ", departureTime:" + Time.writeTime(departureTime) + "]" + trip;
	}
}
