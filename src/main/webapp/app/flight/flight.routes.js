'use strict';

(() => {
  angular
    .module('flights.flight')
    .constant('flightRoutes', {

      tripSelect: {
        url: '/flight/select',
        templateUrl: 'app/flight/flight-select.template.html',
        controller: 'FlightSelectController',
        controllerAs: 'flightSelectCtrl',
        resolve: {
          locations: ['flightService', '$stateParams', function(flightService, $stateParams) {
            return flightService.getLocations();
          }]
        },
        data: {
          loggedIn: true
        }
      },
      tripList: {
        url: '/flight/list',
        templateUrl: 'app/flight/flight-list.template.html',
        controller: 'FlightListController',
        controllerAs: 'flightListCtrl',
        resolve: {
          tripList: ['flightService', '$stateParams', function(flightService, $stateParams) {
            return flightService.getTrips();
          }]
        },
        data: {
          loggedIn: true
        }
      }
    })
})();
