package com.stackroute.keepnote.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.stackroute.keepnote.dao.NoteDAO;
import com.stackroute.keepnote.model.Note;



/*
 * Annotate the class with @Controller annotation.@Controller annotation is used to mark 
 * any POJO class as a controller so that Spring can recognize this class as a Controller
 */
@Controller
public class NoteController {
	/*
	 * From the problem statement, we can understand that the application requires
	 * us to implement the following functionalities.
	 * 
	 * 1. display the list of existing notes from the persistence data. Each note
	 * should contain Note Id, title, content, status and created date. 2. Add a new
	 * note which should contain the note id, title, content and status. 3. Delete
	 * an existing note 4. Update an existing note
	 * 
	 */

	/*
	 * Autowiring should be implemented for the NoteDAO. Create a Note object.
	 * 
	 */

	
	private NoteDAO dao;
	@Autowired
	public NoteController(NoteDAO dao) {
		this.dao = dao;
	}

	/*
	 * Define a handler method to read the existing notes by calling the
	 * getAllNotes() method of the NoteRepository class and add it to the ModelMap
	 * which is an implementation of Map for use when building model data for use
	 * with views. it should map to the default URL i.e. "/"
	 */
	
	@RequestMapping("/")
	public String getAllNotes(Model model) {
		List<Note> noteList= dao.getAllNotes();
		model.addAttribute("noteList", noteList);
		return "index";
	}

	/*
	 * Define a handler method which will read the Note data from request parameters
	 * and save the note by calling the addNote() method of NoteRepository class.
	 * Please note that the createdAt field should always be auto populated with
	 * system time and should not be accepted from the user. Also, after saving the
	 * note, it should show the same along with existing notes. Hence, reading notes
	 * has to be done here again and the retrieved notes object should be sent back
	 * to the view using ModelMap. This handler method should map to the URL
	 * "/saveNote".
	 */
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String addNote(Model model, @RequestParam String noteTitle, @RequestParam String noteContent, @RequestParam String noteStatus) {		
		
		Note note = new Note();
		note.setNoteTitle(noteTitle);
		note.setNoteContent(noteContent);
		note.setNoteStatus(noteStatus);
		note.setCreatedAt(LocalDateTime.now());
		
		if(noteTitle.isEmpty() || noteContent.isEmpty() || noteStatus.isEmpty()){
			model.addAttribute("error", "Fields should not be empty");
			model.addAttribute("noteList", dao.getAllNotes());
			return "index";
		}	
		else {			
			dao.saveNote(note);
			return "redirect:/";
		}
		
	}

	/*
	 * Define a handler method to delete an existing note by calling the
	 * deleteNote() method of the NoteRepository class This handler method should
	 * map to the URL "/deleteNote"
	 */
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String deleteNote(Model model,@RequestParam int noteId) {
		dao.deleteNote(noteId);
		return "redirect:/";
	}
	
	
	@RequestMapping(value="/updateNote", method = RequestMethod.GET)
	public String update(Model model,@RequestParam int noteId) {
		Note note = dao.getNoteById(noteId);
		model.addAttribute("note", note);
		return "update";		
	}
	
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String updateNote(@RequestParam int noteId, @RequestParam String noteTitle, @RequestParam String noteContent, @RequestParam String noteStatus, ModelMap map) {
		Note note = new Note();
		note.setNoteId(noteId);
		note.setNoteContent(noteContent);
		note.setNoteStatus(noteStatus);
		note.setNoteTitle(noteTitle);
		note.setCreatedAt(LocalDateTime.now());
		dao.UpdateNote(note);
		map.addAttribute("note",dao.getAllNotes());
		return "redirect:/";
	}	
}