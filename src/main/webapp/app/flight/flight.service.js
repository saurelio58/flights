'use strict';

(() => {
  angular
    .module('flights.flight')
    .service('flightService', FlightService);

  FlightService.$inject = ['accessService', '$http', '$log'];

  function FlightService(accessService, $http, $log) {
    $log.debug('flightService-init')

    // save delayed trip
    this.tripDelayed



    ///////////////////
    this.getAltFlts = function() {
      $log.debug('flightService.getAltFlts-init')

      this.routeModel = {}
      this.origin = {}
      this.destination = {}

      this.origin.city = this.tripDelayed.orginCity
      this.origin.state = this.tripDelayed.orginState

      this.destination.city = this.tripDelayed.destionationCity
      this.destination.state = this.tripDelayed.destinationState

      this.routeModel.origin = this.origin
      this.routeModel.destination = this.destination

      return $http
        .post('./api/routes', this.routeModel)
        .then(response => response.data)

    };

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

    this.getTrips = () => {
      $log.debug('flightService.getTrips-init')

      return $http
        .get('./api/trips/' + accessService.currentUser.username)
        .then(response => response.data)
        .then(response => {
          $log.debug('flightService.getTrips-exit')
          $log.debug(response)
          return response
        });

    }

    this.getDelayedTrip = (tripId) => {
      $log.debug('flightService.getDelayedTrip-init')
      this.tripId = tripId
      return $http
        .get('./api/trips/segments/' + this.tripId)
        .then(response => response.data)
        .then(response => {
          $log.debug('flightService.getTrips-exit')
          $log.debug(response)
          return response
        });

    }

    this.cancelTrip = (tripId) => {
      $log.debug('flightService.cancelTrip-init')
      this.tripId = tripId
      return $http
        .put('./api/trips/cancel/' + this.tripId)
        .then(response => response.data)
    }

    this.saveDelayTrip = function(trip) {
      $log.debug('FlightDelayController.saveDelayTrip-init')
      this.tripDelayed = trip

    }

    this.getDelayTrip = function() {
      $log.debug('FlightDelayController.getDelayTrip-init')
      return this.tripDelayed

    }



  }
})();
