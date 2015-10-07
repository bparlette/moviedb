package com.moviedb.web.rest;

import com.moviedb.Application;
import com.moviedb.domain.Movie;
import com.moviedb.repository.MovieRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.Matchers.hasItem;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Test class for the MovieResource REST controller.
 *
 * @see MovieResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class MovieResourceTest {

    private static final String DEFAULT_TITLE = "SAMPLE_TEXT";
    private static final String UPDATED_TITLE = "UPDATED_TEXT";
    private static final String DEFAULT_YEAR = "SAMPLE_TEXT";
    private static final String UPDATED_YEAR = "UPDATED_TEXT";
    private static final String DEFAULT_RATED = "SAMPLE_TEXT";
    private static final String UPDATED_RATED = "UPDATED_TEXT";
    private static final String DEFAULT_RELEASED = "SAMPLE_TEXT";
    private static final String UPDATED_RELEASED = "UPDATED_TEXT";
    private static final String DEFAULT_RUNTIME = "SAMPLE_TEXT";
    private static final String UPDATED_RUNTIME = "UPDATED_TEXT";
    private static final String DEFAULT_GENRE = "SAMPLE_TEXT";
    private static final String UPDATED_GENRE = "UPDATED_TEXT";
    private static final String DEFAULT_DIRECTOR = "SAMPLE_TEXT";
    private static final String UPDATED_DIRECTOR = "UPDATED_TEXT";
    private static final String DEFAULT_ACTORS = "SAMPLE_TEXT";
    private static final String UPDATED_ACTORS = "UPDATED_TEXT";
    private static final String DEFAULT_PLOT = "SAMPLE_TEXT";
    private static final String UPDATED_PLOT = "UPDATED_TEXT";
    private static final String DEFAULT_LANGUAGE = "SAMPLE_TEXT";
    private static final String UPDATED_LANGUAGE = "UPDATED_TEXT";
    private static final String DEFAULT_COUNTRY = "SAMPLE_TEXT";
    private static final String UPDATED_COUNTRY = "UPDATED_TEXT";
    private static final String DEFAULT_AWARDS = "SAMPLE_TEXT";
    private static final String UPDATED_AWARDS = "UPDATED_TEXT";
    private static final String DEFAULT_POSTER = "SAMPLE_TEXT";
    private static final String UPDATED_POSTER = "UPDATED_TEXT";
    private static final String DEFAULT_METASCORE = "SAMPLE_TEXT";
    private static final String UPDATED_METASCORE = "UPDATED_TEXT";
    private static final String DEFAULT_IMDB_RATING = "SAMPLE_TEXT";
    private static final String UPDATED_IMDB_RATING = "UPDATED_TEXT";
    private static final String DEFAULT_IMDB_VOTES = "SAMPLE_TEXT";
    private static final String UPDATED_IMDB_VOTES = "UPDATED_TEXT";
    private static final String DEFAULT_IMDB_ID = "SAMPLE_TEXT";
    private static final String UPDATED_IMDB_ID = "UPDATED_TEXT";
    private static final String DEFAULT_TYPE = "SAMPLE_TEXT";
    private static final String UPDATED_TYPE = "UPDATED_TEXT";
    private static final String DEFAULT_RESPONSE = "SAMPLE_TEXT";
    private static final String UPDATED_RESPONSE = "UPDATED_TEXT";
    private static final String DEFAULT_COMMENT = "SAMPLE_TEXT";
    private static final String UPDATED_COMMENT = "UPDATED_TEXT";

    @Inject
    private MovieRepository movieRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restMovieMockMvc;

    private Movie movie;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        MovieResource movieResource = new MovieResource();
        ReflectionTestUtils.setField(movieResource, "movieRepository", movieRepository);
        this.restMovieMockMvc = MockMvcBuilders.standaloneSetup(movieResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        movie = new Movie();
        movie.setTitle(DEFAULT_TITLE);
        movie.setYear(DEFAULT_YEAR);
        movie.setRated(DEFAULT_RATED);
        movie.setReleased(DEFAULT_RELEASED);
        movie.setRuntime(DEFAULT_RUNTIME);
        movie.setGenre(DEFAULT_GENRE);
        movie.setDirector(DEFAULT_DIRECTOR);
        movie.setActors(DEFAULT_ACTORS);
        movie.setPlot(DEFAULT_PLOT);
        movie.setLanguage(DEFAULT_LANGUAGE);
        movie.setCountry(DEFAULT_COUNTRY);
        movie.setAwards(DEFAULT_AWARDS);
        movie.setPoster(DEFAULT_POSTER);
        movie.setMetascore(DEFAULT_METASCORE);
        movie.setImdbRating(DEFAULT_IMDB_RATING);
        movie.setImdbVotes(DEFAULT_IMDB_VOTES);
        movie.setImdbID(DEFAULT_IMDB_ID);
        movie.setType(DEFAULT_TYPE);
        movie.setResponse(DEFAULT_RESPONSE);
        movie.setComment(DEFAULT_COMMENT);
    }

    @Test
    @Transactional
    public void createMovie() throws Exception {
        int databaseSizeBeforeCreate = movieRepository.findAll().size();

        // Create the Movie

        restMovieMockMvc.perform(post("/api/movies")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(movie)))
                .andExpect(status().isCreated());

        // Validate the Movie in the database
        List<Movie> movies = movieRepository.findAll();
        assertThat(movies).hasSize(databaseSizeBeforeCreate + 1);
        Movie testMovie = movies.get(movies.size() - 1);
        assertThat(testMovie.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testMovie.getYear()).isEqualTo(DEFAULT_YEAR);
        assertThat(testMovie.getRated()).isEqualTo(DEFAULT_RATED);
        assertThat(testMovie.getReleased()).isEqualTo(DEFAULT_RELEASED);
        assertThat(testMovie.getRuntime()).isEqualTo(DEFAULT_RUNTIME);
        assertThat(testMovie.getGenre()).isEqualTo(DEFAULT_GENRE);
        assertThat(testMovie.getDirector()).isEqualTo(DEFAULT_DIRECTOR);
        assertThat(testMovie.getActors()).isEqualTo(DEFAULT_ACTORS);
        assertThat(testMovie.getPlot()).isEqualTo(DEFAULT_PLOT);
        assertThat(testMovie.getLanguage()).isEqualTo(DEFAULT_LANGUAGE);
        assertThat(testMovie.getCountry()).isEqualTo(DEFAULT_COUNTRY);
        assertThat(testMovie.getAwards()).isEqualTo(DEFAULT_AWARDS);
        assertThat(testMovie.getPoster()).isEqualTo(DEFAULT_POSTER);
        assertThat(testMovie.getMetascore()).isEqualTo(DEFAULT_METASCORE);
        assertThat(testMovie.getImdbRating()).isEqualTo(DEFAULT_IMDB_RATING);
        assertThat(testMovie.getImdbVotes()).isEqualTo(DEFAULT_IMDB_VOTES);
        assertThat(testMovie.getImdbID()).isEqualTo(DEFAULT_IMDB_ID);
        assertThat(testMovie.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testMovie.getResponse()).isEqualTo(DEFAULT_RESPONSE);
        assertThat(testMovie.getComment()).isEqualTo(DEFAULT_COMMENT);
    }

    @Test
    @Transactional
    public void getAllMovies() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get all the movies
        restMovieMockMvc.perform(get("/api/movies"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(movie.getId().intValue())))
                .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE.toString())))
                .andExpect(jsonPath("$.[*].year").value(hasItem(DEFAULT_YEAR.toString())))
                .andExpect(jsonPath("$.[*].rated").value(hasItem(DEFAULT_RATED.toString())))
                .andExpect(jsonPath("$.[*].released").value(hasItem(DEFAULT_RELEASED.toString())))
                .andExpect(jsonPath("$.[*].runtime").value(hasItem(DEFAULT_RUNTIME.toString())))
                .andExpect(jsonPath("$.[*].genre").value(hasItem(DEFAULT_GENRE.toString())))
                .andExpect(jsonPath("$.[*].director").value(hasItem(DEFAULT_DIRECTOR.toString())))
                .andExpect(jsonPath("$.[*].actors").value(hasItem(DEFAULT_ACTORS.toString())))
                .andExpect(jsonPath("$.[*].plot").value(hasItem(DEFAULT_PLOT.toString())))
                .andExpect(jsonPath("$.[*].language").value(hasItem(DEFAULT_LANGUAGE.toString())))
                .andExpect(jsonPath("$.[*].country").value(hasItem(DEFAULT_COUNTRY.toString())))
                .andExpect(jsonPath("$.[*].awards").value(hasItem(DEFAULT_AWARDS.toString())))
                .andExpect(jsonPath("$.[*].poster").value(hasItem(DEFAULT_POSTER.toString())))
                .andExpect(jsonPath("$.[*].metascore").value(hasItem(DEFAULT_METASCORE.toString())))
                .andExpect(jsonPath("$.[*].imdbRating").value(hasItem(DEFAULT_IMDB_RATING.toString())))
                .andExpect(jsonPath("$.[*].imdbVotes").value(hasItem(DEFAULT_IMDB_VOTES.toString())))
                .andExpect(jsonPath("$.[*].imdbID").value(hasItem(DEFAULT_IMDB_ID.toString())))
                .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
                .andExpect(jsonPath("$.[*].response").value(hasItem(DEFAULT_RESPONSE.toString())))
                .andExpect(jsonPath("$.[*].comment").value(hasItem(DEFAULT_COMMENT.toString())));
    }

    @Test
    @Transactional
    public void getMovie() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

        // Get the movie
        restMovieMockMvc.perform(get("/api/movies/{id}", movie.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(movie.getId().intValue()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE.toString()))
            .andExpect(jsonPath("$.year").value(DEFAULT_YEAR.toString()))
            .andExpect(jsonPath("$.rated").value(DEFAULT_RATED.toString()))
            .andExpect(jsonPath("$.released").value(DEFAULT_RELEASED.toString()))
            .andExpect(jsonPath("$.runtime").value(DEFAULT_RUNTIME.toString()))
            .andExpect(jsonPath("$.genre").value(DEFAULT_GENRE.toString()))
            .andExpect(jsonPath("$.director").value(DEFAULT_DIRECTOR.toString()))
            .andExpect(jsonPath("$.actors").value(DEFAULT_ACTORS.toString()))
            .andExpect(jsonPath("$.plot").value(DEFAULT_PLOT.toString()))
            .andExpect(jsonPath("$.language").value(DEFAULT_LANGUAGE.toString()))
            .andExpect(jsonPath("$.country").value(DEFAULT_COUNTRY.toString()))
            .andExpect(jsonPath("$.awards").value(DEFAULT_AWARDS.toString()))
            .andExpect(jsonPath("$.poster").value(DEFAULT_POSTER.toString()))
            .andExpect(jsonPath("$.metascore").value(DEFAULT_METASCORE.toString()))
            .andExpect(jsonPath("$.imdbRating").value(DEFAULT_IMDB_RATING.toString()))
            .andExpect(jsonPath("$.imdbVotes").value(DEFAULT_IMDB_VOTES.toString()))
            .andExpect(jsonPath("$.imdbID").value(DEFAULT_IMDB_ID.toString()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.response").value(DEFAULT_RESPONSE.toString()))
            .andExpect(jsonPath("$.comment").value(DEFAULT_COMMENT.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingMovie() throws Exception {
        // Get the movie
        restMovieMockMvc.perform(get("/api/movies/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMovie() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

		int databaseSizeBeforeUpdate = movieRepository.findAll().size();

        // Update the movie
        movie.setTitle(UPDATED_TITLE);
        movie.setYear(UPDATED_YEAR);
        movie.setRated(UPDATED_RATED);
        movie.setReleased(UPDATED_RELEASED);
        movie.setRuntime(UPDATED_RUNTIME);
        movie.setGenre(UPDATED_GENRE);
        movie.setDirector(UPDATED_DIRECTOR);
        movie.setActors(UPDATED_ACTORS);
        movie.setPlot(UPDATED_PLOT);
        movie.setLanguage(UPDATED_LANGUAGE);
        movie.setCountry(UPDATED_COUNTRY);
        movie.setAwards(UPDATED_AWARDS);
        movie.setPoster(UPDATED_POSTER);
        movie.setMetascore(UPDATED_METASCORE);
        movie.setImdbRating(UPDATED_IMDB_RATING);
        movie.setImdbVotes(UPDATED_IMDB_VOTES);
        movie.setImdbID(UPDATED_IMDB_ID);
        movie.setType(UPDATED_TYPE);
        movie.setResponse(UPDATED_RESPONSE);
        movie.setComment(UPDATED_COMMENT);
        

        restMovieMockMvc.perform(put("/api/movies")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(movie)))
                .andExpect(status().isOk());

        // Validate the Movie in the database
        List<Movie> movies = movieRepository.findAll();
        assertThat(movies).hasSize(databaseSizeBeforeUpdate);
        Movie testMovie = movies.get(movies.size() - 1);
        assertThat(testMovie.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testMovie.getYear()).isEqualTo(UPDATED_YEAR);
        assertThat(testMovie.getRated()).isEqualTo(UPDATED_RATED);
        assertThat(testMovie.getReleased()).isEqualTo(UPDATED_RELEASED);
        assertThat(testMovie.getRuntime()).isEqualTo(UPDATED_RUNTIME);
        assertThat(testMovie.getGenre()).isEqualTo(UPDATED_GENRE);
        assertThat(testMovie.getDirector()).isEqualTo(UPDATED_DIRECTOR);
        assertThat(testMovie.getActors()).isEqualTo(UPDATED_ACTORS);
        assertThat(testMovie.getPlot()).isEqualTo(UPDATED_PLOT);
        assertThat(testMovie.getLanguage()).isEqualTo(UPDATED_LANGUAGE);
        assertThat(testMovie.getCountry()).isEqualTo(UPDATED_COUNTRY);
        assertThat(testMovie.getAwards()).isEqualTo(UPDATED_AWARDS);
        assertThat(testMovie.getPoster()).isEqualTo(UPDATED_POSTER);
        assertThat(testMovie.getMetascore()).isEqualTo(UPDATED_METASCORE);
        assertThat(testMovie.getImdbRating()).isEqualTo(UPDATED_IMDB_RATING);
        assertThat(testMovie.getImdbVotes()).isEqualTo(UPDATED_IMDB_VOTES);
        assertThat(testMovie.getImdbID()).isEqualTo(UPDATED_IMDB_ID);
        assertThat(testMovie.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testMovie.getResponse()).isEqualTo(UPDATED_RESPONSE);
        assertThat(testMovie.getComment()).isEqualTo(UPDATED_COMMENT);
    }

    @Test
    @Transactional
    public void deleteMovie() throws Exception {
        // Initialize the database
        movieRepository.saveAndFlush(movie);

		int databaseSizeBeforeDelete = movieRepository.findAll().size();

        // Get the movie
        restMovieMockMvc.perform(delete("/api/movies/{id}", movie.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Movie> movies = movieRepository.findAll();
        assertThat(movies).hasSize(databaseSizeBeforeDelete - 1);
    }
}
