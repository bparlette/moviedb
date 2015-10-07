 'use strict';

angular.module('moviedbApp')
    .factory('notificationInterceptor', function ($q, AlertService) {
        return {
            response: function(response) {
                var alertKey = response.headers('X-moviedbApp-alert');
                if (angular.isString(alertKey)) {
                    AlertService.success(alertKey, { param : response.headers('X-moviedbApp-params')});
                }
                return response;
            },
        };
    });