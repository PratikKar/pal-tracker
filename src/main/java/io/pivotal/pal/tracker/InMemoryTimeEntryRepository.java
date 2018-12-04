package io.pivotal.pal.tracker;

import org.springframework.stereotype.Component;

import java.util.List;


public class InMemoryTimeEntryRepository implements TimeEntryRepository  {

    TimeEntryDatabase timeEntryDatbase;
     long indexNumber=1L;

    public InMemoryTimeEntryRepository(){

        timeEntryDatbase =  new TimeEntryDatabase();
    }


    public TimeEntry create(TimeEntry timeEntry) throws Exception {
        TimeEntry t = null;
        List<TimeEntry> timeEntryList = timeEntryDatbase.createRepo();
        if(timeEntry.getId()==0) {
            t = timeEntryDatbase.addRecord(timeEntry,indexNumber++);
        }else{
            t=timeEntryDatbase.addRecord(timeEntry,timeEntry.getId());
        }

        return t;
    }

    @Override
    public TimeEntry find(Long id) throws Exception {

        return timeEntryDatbase.findbyId(id);
    }

    @Override
    public void delete(Long id) throws Exception {
         timeEntryDatbase.deleteById(id);
    }

    @Override
    public TimeEntry update(Long id, TimeEntry timeEntry) throws Exception {

        timeEntryDatbase.updtaeById(id,timeEntry);
        return timeEntryDatbase.findbyId(id);
    }

    @Override
    public List<TimeEntry> list() throws Exception {

        return timeEntryDatbase.getRepo() ;
    }
}
