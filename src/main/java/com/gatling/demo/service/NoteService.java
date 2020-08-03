package com.gatling.demo.service;

import com.gatling.demo.model.Note;
import com.gatling.demo.repo.NoteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class NoteService {

    @Autowired
    NoteRepo noteRepo;

    public List<Note> getNotesList() {
        return StreamSupport
                .stream(noteRepo.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Note getNoteById(Long id) throws RuntimeException {
        Note noteFromDb = noteRepo.findById(id).orElse(null);
        if (Objects.nonNull(noteFromDb)) {
            return noteFromDb;
        } else {
            throw new RuntimeException("404");
        }

    }

    public Note createNote(Note note) {
        return noteRepo.save(note);
    }

    public Note editNote(Note note) {
        Note noteFromDb = noteRepo.findById(note.getId()).orElse(null);
        if (Objects.nonNull(noteFromDb)) {
            noteFromDb.setHeader(note.getHeader());
            noteFromDb.setBody(note.getBody());
            noteRepo.save(noteFromDb);
            return noteFromDb;
        } else {
            throw new RuntimeException("404");
        }
    }

    public String deleteNote(Long id) {
        Note noteFromDb = noteRepo.findById(id).orElse(null);
        if (Objects.nonNull(noteFromDb)) {
            noteRepo.delete(noteFromDb);
            return "delete: success";
        } else {
            throw new RuntimeException("404");
        }
    }

}
