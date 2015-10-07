'use strict';

angular.module('moviedbApp').controller('MovieDialogController',
    ['$scope', '$stateParams', '$modalInstance', 'entity', 'Movie',
        function($scope, $stateParams, $modalInstance, entity, Movie) {

        $scope.movie = entity;
        $scope.load = function(id) {
            Movie.get({id : id}, function(result) {
                $scope.movie = result;
            });
        };

        var onSaveFinished = function (result) {
            $scope.$emit('moviedbApp:movieUpdate', result);
            $modalInstance.close(result);
        };

        $scope.save = function () {
            if ($scope.movie.id != null) {
                Movie.update($scope.movie, onSaveFinished);
            } else {
                Movie.save($scope.movie, onSaveFinished);
            }
        };

        $scope.clear = function() {
            $modalInstance.dismiss('cancel');
        };
}]);
