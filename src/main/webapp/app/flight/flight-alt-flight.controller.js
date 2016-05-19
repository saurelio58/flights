'use strict';

(() => {
  angular
    .module('flights.flight')
    .controller('FlightAltFltController', FlightAltFltController);

  FlightAltFltController.$inject = ['altFltList', 'flightService', '$scope', '$state', '$stateParams', '$log'];

  function FlightAltFltController(altFltList, flightService, $scope, $state, $stateParams, $log) {
    $log.debug('FlightAltFltController-init')

    // on Html
    this.altFltList = altFltList
    this.userOrigin
    this.userDestination
    this.routeModel = {}
    this.message

    this.bookRoute = function(route) {
      $log.debug('FlightAltFltController.bookRoute-init')
      $log.debug('route=')
      $log.debug(route)

      flightService.bookFlights(route)
        // check for errors ???


      this.message = 'Your Trip has been reserved!'

    }

    this.select = () => {
      $log.debug('FlightAltFltController.select-init')
      $log.debug('userOrigin=' + this.userOrigin)
      $log.debug('userDestination=' + this.userDestination)

      this.routeModel.origin = this.userOrigin
      this.routeModel.destination = this.userDestination
      flightService.getRouteModel(this.routeModel)

      this.message = null
    }







  }

})();
