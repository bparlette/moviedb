package com.moviedb.repository;

import com.moviedb.domain.Movie;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Movie entity.
 */
public interface MovieRepository extends JpaRepository<Movie,Long> {
	Page<Movie> findByTitleContainingOrPlotContainingOrYearContainingOrGenreContainingOrActorsContainingOrDirectorContainingOrAwardsContainingAllIgnoreCaseOrderByTitleAsc(String search,String search1,String search2,String search3,String search4,String search5,String search6, Pageable pageable);
		
}
