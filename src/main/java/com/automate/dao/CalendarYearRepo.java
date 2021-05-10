package com.automate.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.automate.entity.globalsettings.TGsCalendarYear;

public interface CalendarYearRepo extends JpaRepository<TGsCalendarYear, Integer> {

	TGsCalendarYear findByParentOrgIdAndUserId(int parentOrgId, int userId);

	TGsCalendarYear findByGsCalendarYearId(int calendarYearId);




}
