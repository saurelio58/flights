'use strict';

(() => {
  angular
    .module('flights.flight')
    .constant('flightRoutes', {

      altFltList: {
        url: '/flight/altflt',
        templateUrl: 'app/flight/flight-alt-flight.template.html',
        controller: 'FlightAltFltController',
        controllerAs: 'flightAltFltCtrl',
        resolve: {
          locations: ['flightService', '$stateParams', function(flightService, $stateParams) {
            return flightService.getAltFlts();
          }]
        },
        data: {
          loggedIn: true
        }
      },
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
      delayedFlight: {
        url: '/flight/delay/{tripId}',
        templateUrl: 'app/flight/flight-delay.template.html',
        controller: 'FlightDelayController',
        controllerAs: 'flightDelayCtrl',
        resolve: {
          delayedFlightList: ['flightService', '$stateParams', function(flightService, $stateParams) {
            return flightService.getDelayedTrip($stateParams.tripId);
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
