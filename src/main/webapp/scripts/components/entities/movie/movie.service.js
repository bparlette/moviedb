'use strict';

angular.module('moviedbApp')
    .factory('Movie', function ($resource, DateUtils) {
        return { 
        	api: $resource('api/movies/:id', {}, {
	            'query': { method: 'GET', isArray: true},
	            'get': {
	                method: 'GET',
	                transformResponse: function (data) {
	                    data = angular.fromJson(data);
	                    return data;
	                }
	            },
	            'update': { method:'PUT' }
	        }),
	        ratingClass: function(ratingPercent) {
		    	var ratingClass = "progress-bar-danger";
		      	if (ratingPercent >= 70) {
		      		ratingClass = "progress-bar-success";
		      	} else if (ratingPercent >= 55) {
		      		ratingClass = "progress-bar-warning";
		      	} 
		          return ratingClass; 
			  }
        };
    });
