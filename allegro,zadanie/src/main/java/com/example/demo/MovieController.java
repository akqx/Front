package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie")
public class MovieController {

	private MovieRepository movieRepository;

	@Autowired
	public MovieController(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}

	@RequestMapping(method = RequestMethod.POST)
	public void addMovie(@RequestBody Movie movie) {
		movieRepository.save(movie);
	}

	@RequestMapping(value = "/findByName/{name}", method = RequestMethod.GET)
	public List<Movie> getMovieByName(@PathVariable("name") String name) {
		return movieRepository.findByname(name);
	}

	
	@RequestMapping(value = "/allMovies", method = RequestMethod.GET)
	public List<String> getAllMovieNames() {
		List<String> allMovies = new ArrayList<String>();

		for (Movie movie : movieRepository.findAll()) {
			allMovies.add(movie.getName());
		}
		return allMovies;
	}
}
