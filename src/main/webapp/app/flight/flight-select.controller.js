'use strict';

(() => {
  angular
    .module('flights.flight')
    .controller('FlightSelectController', FlightSelectController);

  FlightSelectController.$inject = ['locations', 'flightService', '$scope', '$state', '$stateParams', '$log'];

  function FlightSelectController(locations, flightService, $scope, $state, $stateParams, $log) {
    $log.debug('FlightSelectController-init')

    // on Html
    this.locations = locations
    this.userOrigin
    this.userDestination
    this.routeModel = {}
    this.message

    this.bookRoute = function(route) {
      $log.debug('FlightSelectController.bookRoute-init')
      $log.debug('route=')
      $log.debug(route)

      flightService.bookFlights(route)
        // check for errors ???


      this.message = 'Your Trip has been reserved!'

    }

    this.select = () => {
      $log.debug('FlightSelectController.select-init')
      $log.debug('userOrigin=' + this.userOrigin)
      $log.debug('userDestination=' + this.userDestination)

      this.routeModel.origin = this.userOrigin
      this.routeModel.destination = this.userDestination
      flightService.getRouteModel(this.routeModel)

      this.message = null
    }







  }

})();
