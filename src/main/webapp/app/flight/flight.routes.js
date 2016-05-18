'use strict';

(() => {
  angular
    .module('flights.flight')
    .constant('flightRoutes', {

      trip: {
        url: '/flight/select',
        templateUrl: 'app/flight/flight-select.template.html',
        controller: 'FlightSelectController',
        controllerAs: 'flightCtrl',
        resolve: {
          locations: ['flightService', '$stateParams', '$log', function(flightService, $stateParams) {
            return flightService.getLocations();
          }]
        },
        data: {
          loggedIn: true
        }
      }
    })
})();
