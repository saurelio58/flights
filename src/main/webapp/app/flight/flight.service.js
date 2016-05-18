'use strict';

(() => {
  angular
    .module('flights.flight')
    .service('flightService', FlightService);

  FlightService.$inject = ['accessService','$http', '$log'];

  function FlightService(accessService, $http, $log) {
    $log.debug('flightService-init')


    this.getRouteModel = (routeModel) => {
      $log.debug('flightService.getRouteModel-init')
      return $http
        .post('./api/routes', routeModel)
        .then(response => response.data)
        .then(result => {
          $log.debug('flightService.getRouteModel-exit')
          $log.debug(result)
          angular.extend(routeModel, result);
        })
    };

    this.getLocations = function() {
      $log.debug('flightService.getLocations-init')
      return $http
        .get('./api/locations')
        .then(response => response.data)
    };

    this.bookFlights = (route) => {
      $log.debug('flightService.bookRoute-init')

      return $http
        .post('./api/trips/' + accessService.currentUser.username, route)
        .then(response => response.data)




    }


  }
})();
