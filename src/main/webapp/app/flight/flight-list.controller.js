'use strict';

(() => {
  angular
    .module('flights.flight')
    .controller('FlightListController', FlightListController);

  FlightListController.$inject = ['tripList', 'flightService', '$state', '$stateParams', '$log'];

  function FlightListController(tripList, flightService, $state, $stateParams, $log) {
    $log.debug('FlightListController-init')

    // on Html
    this.tripList = tripList
    this.message

    this.cancelTrip = function(trip) {
      $log.debug('FlightListController.cancelTrip-init')
      flightService
        .cancelTrip(trip.tripId)
        .then(() => $state.reload())
        // check for errors ???

      // this.message = 'Your Trip has been canceled!'

    }

    this.delayedCheck = function(trip) {
      $log.debug('FlightListController.delayedCheck-init')
      if (trip.tripName == 'DELAYED')
        return true
      else
        return false
    }



  }

})();
