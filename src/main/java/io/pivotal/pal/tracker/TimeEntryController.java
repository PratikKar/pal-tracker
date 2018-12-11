package io.pivotal.pal.tracker;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TimeEntryController {


    @Autowired
    TimeEntryRepository inMemoryTimeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {

        this.inMemoryTimeEntryRepository=timeEntryRepository;
    }

    @PostMapping(value="/time-entries", produces= MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity create(@RequestBody TimeEntry timeEntryToCreate) {
        ResponseEntity<TimeEntry> entity=null;
        TimeEntry temp = null;
        try {
            temp = inMemoryTimeEntryRepository.create(timeEntryToCreate);
        } catch (Exception e) {
            entity = new ResponseEntity<TimeEntry>(temp,HttpStatus.EXPECTATION_FAILED);
        }
       entity = new ResponseEntity<TimeEntry>(temp,HttpStatus.CREATED);
        return entity;

    }

    @GetMapping(value="/time-entries/{timeEntryId}", produces= MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody  ResponseEntity<TimeEntry> read(@PathVariable Long timeEntryId) {
        ResponseEntity<TimeEntry> entity=null;
        TimeEntry temp = null;
        try {
            temp = inMemoryTimeEntryRepository.find(timeEntryId);
        } catch (Exception e) {
            entity = new ResponseEntity<TimeEntry>(temp,HttpStatus.EXPECTATION_FAILED);
        }
        if(temp==null){
            entity = new ResponseEntity<TimeEntry>(temp,HttpStatus.NOT_FOUND);
        }else {
            entity = new ResponseEntity<TimeEntry>(temp, HttpStatus.OK);
        }
        return entity;

    }

    @GetMapping(value="/time-entries", produces= MediaType.APPLICATION_JSON_VALUE)

    public @ResponseBody ResponseEntity<List<TimeEntry>> list() {

        ResponseEntity<List<TimeEntry>> entity=null;
        List<TimeEntry> temp = new ArrayList<>();
        try {

            temp = inMemoryTimeEntryRepository.list();

        } catch (Exception e) {
            entity = new ResponseEntity<List<TimeEntry>>(temp,HttpStatus.EXPECTATION_FAILED);
        }

            entity = new ResponseEntity<List<TimeEntry>>(temp, HttpStatus.OK);
        return entity;
    }

    @PutMapping(value="/time-entries/{timeEntryId}", produces= MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody  ResponseEntity update(@PathVariable long timeEntryId,@RequestBody TimeEntry updatedTimeEntry) {

        ResponseEntity<TimeEntry> entity=null;
        TimeEntry temp = null;
        try {
            temp = inMemoryTimeEntryRepository.update(timeEntryId,updatedTimeEntry);
        } catch (Exception e) {
            entity = new ResponseEntity<TimeEntry>(temp,HttpStatus.EXPECTATION_FAILED);
        }
        if(temp==null){
            entity = new ResponseEntity<TimeEntry>(temp,HttpStatus.NOT_FOUND);
        }else {
            entity = new ResponseEntity<TimeEntry>(temp, HttpStatus.OK);
        }
        return entity;
    }

    @DeleteMapping(value="/time-entries/{timeEntryId}", produces= MediaType.APPLICATION_JSON_VALUE)
    // changes for triggerring build
    public @ResponseBody  ResponseEntity<TimeEntry> delete(@PathVariable long timeEntryId) {

        ResponseEntity<TimeEntry> entity=null;
        try {
            inMemoryTimeEntryRepository.delete(timeEntryId);
        } catch (Exception e) {
            entity = new ResponseEntity<TimeEntry>(HttpStatus.EXPECTATION_FAILED);
        }

            entity = new ResponseEntity<TimeEntry>(HttpStatus.NO_CONTENT);
        return entity;
    }
}
