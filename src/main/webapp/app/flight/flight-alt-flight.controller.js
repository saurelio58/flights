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
    this.message

    this.bookRoute = function(route) {
      $log.debug('FlightAltFltController.bookRoute-init')
      $log.debug('route=')
      $log.debug(route)

      flightService.bookFlights(route)
        // check for errors ???


      this.message = 'Your Trip has been reserved!'

    }







  }

})();
