package io.pivotal.pal.tracker;

import java.util.List;

public interface TimeEntryRepository {

     TimeEntry create(TimeEntry timeEntry) throws Exception;

     TimeEntry find(Long id) throws Exception;

     void delete(Long id) throws Exception;

     TimeEntry update(Long id, TimeEntry timeEntry) throws Exception;

    List<TimeEntry> list() throws Exception;
}
